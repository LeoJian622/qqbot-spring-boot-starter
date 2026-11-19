package cc.starcube.qqbot.spring;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class QQBotClientTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void sendsGroupMessageWithBotAuthenticationAndPayload() {
        RestClient.Builder builder = RestClient.builder();
        MockRestServiceServer server = MockRestServiceServer.bindTo(builder).build();
        QQBotProperties properties = new QQBotProperties();
        properties.setAppId("app-123");
        QQBotTokenProvider tokens = mock(QQBotTokenProvider.class);
        when(tokens.getAccessToken()).thenReturn("test-token");
        QQBotClient client = new QQBotClient(properties, tokens, builder);

        server.expect(requestTo("https://api.bot.qq.com/v2/groups/group-1/messages"))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header("Authorization", "QQBot test-token"))
                .andExpect(org.springframework.test.web.client.match.MockRestRequestMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(org.springframework.test.web.client.match.MockRestRequestMatchers.content()
                        .json("{\"msg_type\":0,\"content\":\"hello\",\"msg_id\":\"msg-1\"}"))
                .andRespond(withSuccess("{\"id\":\"sent-1\"}", MediaType.APPLICATION_JSON));

        JsonNode response = client.sendGroupMessage("group-1", mapper.createObjectNode()
                .put("msg_type", 0).put("content", "hello").put("msg_id", "msg-1"));

        assertEquals("sent-1", response.path("id").asText());
        server.verify();
    }
}
