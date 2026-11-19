package cc.starcube.qqbot.spring;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.context.SmartLifecycle;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Gateway consumer using the JDK WebSocket client, with heartbeats and session resume. */
public class QQBotGateway implements SmartLifecycle {
    private static final Logger log = Logger.getLogger(QQBotGateway.class.getName());
    private final QQBotProperties properties;
    private final QQBotClient client;
    private final QQBotTokenProvider tokenProvider;
    private final QQBotEventHandler eventHandler;
    private final ObjectMapper mapper;
    private final HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(15)).build();
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread t = new Thread(r, "qqbot-gateway"); t.setDaemon(true); return t;
    });
    private final AtomicBoolean running = new AtomicBoolean();
    private final AtomicBoolean reconnectQueued = new AtomicBoolean();
    private final AtomicLong sequence = new AtomicLong(-1);
    private volatile WebSocket socket;
    private volatile String sessionId;
    private volatile String resumeUrl;
    private volatile long heartbeatInterval;
    private volatile ScheduledFuture<?> heartbeatTask;

    public QQBotGateway(QQBotProperties properties, QQBotClient client, QQBotTokenProvider tokenProvider,
                        QQBotEventHandler eventHandler, ObjectMapper mapper) {
        this.properties = properties;
        this.client = client;
        this.tokenProvider = tokenProvider;
        this.eventHandler = eventHandler;
        this.mapper = mapper;
    }

    @Override public void start() {
        if (running.compareAndSet(false, true)) scheduler.execute(this::connect);
    }

    private void connect() {
        if (!running.get()) return;
        try {
            boolean resume = sessionId != null && !sessionId.isBlank() && resumeUrl != null && !resumeUrl.isBlank();
            String url = resume ? resumeUrl : client.getGateway().path("url").asText();
            if (url.isBlank()) throw new IllegalStateException("QQ Bot gateway response did not contain url");
            httpClient.newWebSocketBuilder().connectTimeout(Duration.ofSeconds(15))
                    .buildAsync(URI.create(url), new Listener(resume))
                    .whenComplete((ws, error) -> {
                        if (error != null) {
                            log.log(Level.WARNING, "QQ Bot gateway connection failed", error);
                            scheduleReconnect(3);
                        }
                    });
        } catch (Exception e) {
            log.log(Level.WARNING, "Could not initialize QQ Bot gateway connection", e);
            scheduleReconnect(5);
        }
    }

    private void onPacket(String text, boolean resume) {
        try {
            JsonNode packet = mapper.readTree(text);
            if (packet.has("s") && !packet.get("s").isNull()) sequence.set(packet.path("s").asLong());
            int op = packet.path("op").asInt(-1);
            JsonNode data = packet.path("d");
            switch (op) {
                case 10 -> onHello(data, resume);
                case 0 -> onDispatch(packet, data);
                case 1 -> sendHeartbeat();
                case 7 -> reconnect();
                case 9 -> onInvalidSession(data.asBoolean(false));
                case 11 -> log.fine("QQ Bot gateway heartbeat acknowledged");
                default -> log.fine(() -> "Ignoring QQ Bot gateway opcode " + op);
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to process QQ Bot gateway packet", e);
        }
    }

    private void onHello(JsonNode data, boolean resume) {
        heartbeatInterval = data.path("heartbeat_interval").asLong();
        if (heartbeatInterval <= 0) {
            log.warning("QQ Bot gateway returned an invalid heartbeat interval");
            reconnect();
            return;
        }
        if (heartbeatTask != null) heartbeatTask.cancel(false);
        heartbeatTask = scheduler.scheduleAtFixedRate(this::sendHeartbeat, heartbeatInterval,
                heartbeatInterval, TimeUnit.MILLISECONDS);
        ObjectNode payload = mapper.createObjectNode();
        if (resume && sessionId != null) {
            payload.put("op", 6);
            ObjectNode dataNode = payload.putObject("d");
            dataNode.put("token", "QQBot " + tokenProvider.getAccessToken());
            dataNode.put("session_id", sessionId);
            dataNode.put("seq", sequence.get() < 0 ? null : sequence.get());
        } else {
            payload.put("op", 2);
            ObjectNode dataNode = payload.putObject("d");
            dataNode.put("token", "QQBot " + tokenProvider.getAccessToken());
            dataNode.put("intents", properties.getGateway().getIntents());
            dataNode.putArray("shard").add(properties.getGateway().getShardId())
                    .add(properties.getGateway().getShardCount());
        }
        send(payload);
    }

    private void onDispatch(JsonNode packet, JsonNode data) {
        String type = packet.path("t").asText("");
        if ("READY".equals(type)) {
            sessionId = data.path("session_id").asText(null);
            resumeUrl = data.path("resume_gateway_url").asText(resumeUrl);
            log.info("QQ Bot gateway session is ready");
        }
        try {
            eventHandler.onEvent(type, data, packet);
        } catch (Exception e) {
            log.log(Level.WARNING, "QQ Bot event handler failed for " + type, e);
        }
    }

    private void onInvalidSession(boolean canResume) {
        WebSocket current = socket;
        if (current != null && !current.isOutputClosed()) {
            current.sendClose(WebSocket.NORMAL_CLOSURE, "invalid session");
        }
        if (!canResume) {
            sessionId = null;
            sequence.set(-1);
        }
        scheduleReconnect(3);
    }

    private void sendHeartbeat() {
        if (!running.get() || socket == null) return;
        ObjectNode packet = mapper.createObjectNode().put("op", 1);
        if (sequence.get() < 0) packet.putNull("d"); else packet.put("d", sequence.get());
        send(packet);
    }

    private void send(JsonNode packet) {
        WebSocket current = socket;
        if (current != null && !current.isOutputClosed()) {
            current.sendText(packet.toString(), true).exceptionally(error -> {
                log.log(Level.FINE, "QQ Bot gateway send failed", error); return null;
            });
        }
    }

    private void reconnect() {
        WebSocket current = socket;
        if (current != null) current.sendClose(WebSocket.NORMAL_CLOSURE, "reconnect");
        scheduleReconnect(1);
    }

    private void scheduleReconnect(long delaySeconds) {
        if (!running.get() || !reconnectQueued.compareAndSet(false, true)) return;
        if (heartbeatTask != null) heartbeatTask.cancel(false);
        scheduler.schedule(() -> {
            reconnectQueued.set(false);
            socket = null;
            connect();
        }, delaySeconds, TimeUnit.SECONDS);
    }

    @Override public void stop() {
        running.set(false);
        if (heartbeatTask != null) heartbeatTask.cancel(false);
        WebSocket current = socket;
        if (current != null && !current.isOutputClosed()) current.sendClose(WebSocket.NORMAL_CLOSURE, "shutdown");
        scheduler.shutdownNow();
    }
    @Override public void stop(Runnable callback) { stop(); callback.run(); }
    @Override public boolean isRunning() { return running.get(); }
    @Override public boolean isAutoStartup() { return true; }
    @Override public int getPhase() { return Integer.MAX_VALUE; }

    private final class Listener implements WebSocket.Listener {
        private final boolean resume;
        private final StringBuilder fragments = new StringBuilder();
        private Listener(boolean resume) { this.resume = resume; }
        @Override public void onOpen(WebSocket webSocket) {
            socket = webSocket;
            webSocket.request(1);
            log.info("Connected to QQ Bot gateway");
        }
        @Override public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
            fragments.append(data);
            if (last) {
                String packet = fragments.toString(); fragments.setLength(0);
                onPacket(packet, resume);
            }
            webSocket.request(1);
            return CompletableFuture.completedFuture(null);
        }
        @Override public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
            if (heartbeatTask != null) heartbeatTask.cancel(false);
            if (running.get()) scheduleReconnect(2);
            return CompletableFuture.completedFuture(null);
        }
        @Override public void onError(WebSocket webSocket, Throwable error) {
            log.log(Level.WARNING, "QQ Bot gateway WebSocket error", error);
            if (running.get()) scheduleReconnect(2);
        }
    }
}


