package cc.starcube.qqbot.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class QQBotGatewayLiveTest {

    @Test
    void connectsToOfficialGatewayAndReceivesReady() throws InterruptedException {
        String appId = System.getenv("QQBOT_APP_ID");
        String clientSecret = System.getenv("QQBOT_CLIENT_SECRET");
        assumeTrue(appId != null && !appId.isBlank());
        assumeTrue(clientSecret != null && !clientSecret.isBlank());

        QQBotProperties properties = new QQBotProperties();
        properties.setAppId(appId);
        properties.setClientSecret(clientSecret);
        properties.getGateway().setIntents(1 << 25);

        ObjectMapper mapper = new ObjectMapper();
        RestClient.Builder builder = RestClient.builder();
        QQBotTokenProvider tokenProvider = new QQBotTokenProvider(properties, mapper, builder);
        QQBotClient client = new QQBotClient(properties, tokenProvider, builder);
        CountDownLatch ready = new CountDownLatch(1);
        QQBotGateway gateway = new QQBotGateway(
                properties,
                client,
                tokenProvider,
                (eventType, data, envelope) -> {
                    if ("READY".equals(eventType)) {
                        ready.countDown();
                    }
                },
                mapper
        );

        gateway.start();
        try {
            assertTrue(ready.await(20, TimeUnit.SECONDS), "QQ Bot Gateway did not emit READY");
        } finally {
            gateway.stop();
        }
    }
}
