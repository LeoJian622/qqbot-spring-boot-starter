package cc.starcube.qqbot.spring;

import com.fasterxml.jackson.databind.JsonNode;

/** Receives raw gateway dispatch events. eventType is the gateway envelope's `t` field. */
@FunctionalInterface
public interface QQBotEventHandler {
    void onEvent(String eventType, JsonNode data, JsonNode envelope);
}
