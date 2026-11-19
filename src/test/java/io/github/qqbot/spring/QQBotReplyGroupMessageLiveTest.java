package io.github.qqbot.spring;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.qqbot.spring.api.QQBotV2Api;
import io.github.qqbot.spring.model.v2.SendGroupMessage;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class QQBotReplyGroupMessageLiveTest {

    @Test
    void repliesToNextGroupMessageWithMarkdownMention() throws InterruptedException {
        String appId = System.getenv("QQBOT_APP_ID");
        String clientSecret = System.getenv("QQBOT_CLIENT_SECRET");
        String groupOpenid = System.getenv("QQBOT_REPLY_GROUP_OPENID");
        String memberOpenid = System.getenv("QQBOT_REPLY_MEMBER_OPENID");
        String message = System.getenv("QQBOT_REPLY_MESSAGE");
        String replyEnabled = System.getenv("QQBOT_REPLY_ENABLED");
        String timeoutSecondsValue = System.getenv("QQBOT_REPLY_TIMEOUT_SECONDS");
        assumeTrue(appId != null && !appId.isBlank());
        assumeTrue(clientSecret != null && !clientSecret.isBlank());
        assumeTrue(groupOpenid != null && !groupOpenid.isBlank());
        assumeTrue(memberOpenid != null && !memberOpenid.isBlank());
        assumeTrue(message != null && !message.isBlank());
        assumeTrue(Boolean.parseBoolean(replyEnabled));
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
        QQBotV2Api api = new QQBotV2Api(client);
        CountDownLatch sent = new CountDownLatch(1);
        AtomicReference<RuntimeException> sendFailure = new AtomicReference<>();
        AtomicReference<String> sentMessageId = new AtomicReference<>();

        QQBotGateway gateway = new QQBotGateway(
                properties,
                client,
                tokenProvider,
                (eventType, data, envelope) -> reply(
                        eventType, data, groupOpenid, memberOpenid, message, api,
                        sent, sendFailure, sentMessageId
                ),
                mapper
        );

        gateway.start();
        try {
            assertTrue(sent.await(timeoutSeconds, TimeUnit.SECONDS),
                    "No group message received. Mention the bot in the target group while this test is running.");
            if (sendFailure.get() != null) {
                throw sendFailure.get();
            }
            System.out.println("QQBOT_REPLIED_MESSAGE_ID=" + sentMessageId.get());
        } finally {
            gateway.stop();
        }
    }

    private static void reply(
            String eventType,
            JsonNode data,
            String expectedGroupOpenid,
            String memberOpenid,
            String message,
            QQBotV2Api api,
            CountDownLatch sent,
            AtomicReference<RuntimeException> sendFailure,
            AtomicReference<String> sentMessageId
    ) {
        if (!"GROUP_AT_MESSAGE_CREATE".equals(eventType) && !"GROUP_MESSAGE_CREATE".equals(eventType)) {
            return;
        }
        if (!expectedGroupOpenid.equals(data.path("group_openid").asText())) {
            return;
        }
        String incomingMessageId = data.path("id").asText("");
        if (incomingMessageId.isBlank()) {
            return;
        }
        try {
            String markdownContent = "<qqbot-at-user id=\"" + memberOpenid + "\" /> " + message;
            SendGroupMessage.RequestMessageMarkdown markdown = new SendGroupMessage.RequestMessageMarkdown(
                    null, markdownContent, null, null
            );
            SendGroupMessage.Body body = new SendGroupMessage.Body(
                    2, null, markdown, null, incomingMessageId, null, null, null, null, null
            );
            SendGroupMessage.Response response = api.sendGroupMessage(
                    new SendGroupMessage.Request(expectedGroupOpenid, body)
            );
            sentMessageId.set(response.id());
        } catch (RuntimeException e) {
            sendFailure.set(e);
        } finally {
            sent.countDown();
        }
    }
}
