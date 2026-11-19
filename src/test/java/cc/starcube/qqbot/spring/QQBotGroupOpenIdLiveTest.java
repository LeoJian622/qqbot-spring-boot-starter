package cc.starcube.qqbot.spring;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class QQBotGroupOpenIdLiveTest {

    @Test
    void capturesGroupAndMemberOpenidsFromOneMessage() throws InterruptedException {
        String appId = System.getenv("QQBOT_APP_ID");
        String clientSecret = System.getenv("QQBOT_CLIENT_SECRET");
        String captureEnabled = System.getenv("QQBOT_CAPTURE_GROUP_OPENIDS");
        String timeoutSecondsValue = System.getenv("QQBOT_CAPTURE_TIMEOUT_SECONDS");
        assumeTrue(appId != null && !appId.isBlank());
        assumeTrue(clientSecret != null && !clientSecret.isBlank());
        assumeTrue(Boolean.parseBoolean(captureEnabled));
        long timeoutSeconds = timeoutSecondsValue == null || timeoutSecondsValue.isBlank()
                ? 180L
                : Long.parseLong(timeoutSecondsValue);

        QQBotProperties properties = new QQBotProperties();
        properties.setAppId(appId);
        properties.setClientSecret(clientSecret);
        properties.getGateway().setIntents(1 << 25);

        ObjectMapper mapper = new ObjectMapper();
        RestClient.Builder builder = RestClient.builder();
        QQBotTokenProvider tokenProvider = new QQBotTokenProvider(properties, mapper, builder);
        QQBotClient client = new QQBotClient(properties, tokenProvider, builder);
        CountDownLatch captured = new CountDownLatch(1);
        AtomicReference<String> groupOpenid = new AtomicReference<>();
        AtomicReference<String> memberOpenid = new AtomicReference<>();

        QQBotGateway gateway = new QQBotGateway(
                properties,
                client,
                tokenProvider,
                (eventType, data, envelope) -> capture(eventType, data, groupOpenid, memberOpenid, captured),
                mapper
        );

        gateway.start();
        try {
            assertTrue(captured.await(timeoutSeconds, TimeUnit.SECONDS),
                    "No group message received. Mention the bot in a group while this test is running.");
            System.out.println("QQBOT_GROUP_OPENID=" + groupOpenid.get());
            System.out.println("QQBOT_MEMBER_OPENID=" + memberOpenid.get());
        } finally {
            gateway.stop();
        }
    }

    private static void capture(
            String eventType,
            JsonNode data,
            AtomicReference<String> groupOpenid,
            AtomicReference<String> memberOpenid,
            CountDownLatch captured
    ) {
        if (!"GROUP_AT_MESSAGE_CREATE".equals(eventType) && !"GROUP_MESSAGE_CREATE".equals(eventType)) {
            return;
        }
        String group = data.path("group_openid").asText("");
        String member = data.path("author").path("member_openid").asText("");
        if (!group.isBlank() && !member.isBlank()) {
            groupOpenid.set(group);
            memberOpenid.set(member);
            captured.countDown();
        }
    }
}
