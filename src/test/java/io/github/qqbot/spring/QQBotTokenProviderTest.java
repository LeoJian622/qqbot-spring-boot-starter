package io.github.qqbot.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class QQBotTokenProviderTest {
    @Test
    void usesCurrentOfficialEndpointsByDefault() {
        QQBotProperties properties = new QQBotProperties();

        assertEquals("https://api.bot.qq.com", properties.getApiBaseUrl());
        assertEquals("https://api.bot.qq.com/app/getAppAccessToken", properties.getTokenUrl());
    }

    @Test
    void fetchesTokenAndCachesIt() {
        RestClient.Builder builder = RestClient.builder();
        MockRestServiceServer server = MockRestServiceServer.bindTo(builder).build();
        QQBotProperties properties = new QQBotProperties();
        properties.setAppId("app-123");
        properties.setClientSecret("secret-value");
        properties.setTokenUrl("https://auth.example/token");
        server.expect(requestTo("https://auth.example/token"))
                .andExpect(method(org.springframework.http.HttpMethod.POST))
                .andExpect(content().json("{\"appId\":\"app-123\",\"clientSecret\":\"secret-value\"}"))
                .andRespond(withSuccess("{\"access_token\":\"cached-token\",\"expires_in\":3600}",
                        MediaType.APPLICATION_JSON));

        QQBotTokenProvider provider = new QQBotTokenProvider(properties, new ObjectMapper(), builder);

        assertEquals("cached-token", provider.getAccessToken());
        assertEquals("cached-token", provider.getAccessToken());
        server.verify();
    }
}
