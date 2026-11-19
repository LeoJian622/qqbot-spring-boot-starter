package cc.starcube.qqbot.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import cc.starcube.qqbot.spring.api.QQBotV2Api;
import cc.starcube.qqbot.spring.model.v2.GetCurrentUser;
import cc.starcube.qqbot.spring.model.v2.GetGateway;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class QQBotLiveTest {
    @Test
    void readsCurrentBotAndGatewayFromOfficialApi() {
        String appId = System.getenv("QQBOT_APP_ID");
        String clientSecret = System.getenv("QQBOT_CLIENT_SECRET");
        assumeTrue(appId != null && !appId.isBlank());
        assumeTrue(clientSecret != null && !clientSecret.isBlank());

        QQBotProperties properties = new QQBotProperties();
        properties.setAppId(appId);
        properties.setClientSecret(clientSecret);

        RestClient.Builder builder = RestClient.builder();
        QQBotTokenProvider tokenProvider = new QQBotTokenProvider(
                properties, new ObjectMapper(), builder);
        QQBotV2Api api = new QQBotV2Api(new QQBotClient(properties, tokenProvider, builder));

        GetCurrentUser.Response currentUser = api.getCurrentUser();
        GetGateway.Response gateway = api.getGateway();

        assertNotNull(currentUser);
        assertFalse(currentUser.id().isBlank());
        assertFalse(currentUser.username().isBlank());
        assertNotNull(gateway);
        assertFalse(gateway.url().isBlank());
    }
}
