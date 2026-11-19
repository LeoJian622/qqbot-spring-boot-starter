package io.github.qqbot.spring.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.qqbot.spring.QQBotClient;
import io.github.qqbot.spring.QQBotProperties;
import io.github.qqbot.spring.QQBotTokenProvider;
import io.github.qqbot.spring.model.MessageResponse;
import io.github.qqbot.spring.model.ReactionUsersResponse;
import io.github.qqbot.spring.model.request.GetReactionUsersRequest;
import io.github.qqbot.spring.model.request.SendGroupMessageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

class TypedApiTest {
    private MockRestServiceServer server;
    private MessagesApi messages;

    @BeforeEach
    void setUp() {
        QQBotProperties properties = new QQBotProperties();
        properties.setAppId("app-id");
        RestClient.Builder builder = RestClient.builder();
        server = MockRestServiceServer.bindTo(builder).build();
        QQBotTokenProvider tokens = mock(QQBotTokenProvider.class);
        when(tokens.getAccessToken()).thenReturn("access-token");
        messages = new MessagesApi(new QQBotClient(properties, tokens, builder));
    }

    @Test
    void sendsTypedGroupMessage() {
        server.expect(requestTo("https://api.bot.qq.com/v2/groups/group-1/messages"))
                .andExpect(method(org.springframework.http.HttpMethod.POST))
                .andExpect(header("Authorization", "QQBot access-token"))
                .andRespond(withSuccess("{\"id\":\"message-1\",\"timestamp\":123}", MediaType.APPLICATION_JSON));

        MessageResponse response = messages.sendGroupMessage("group-1",
                new SendGroupMessageRequest("hello", 0, null, null, null, null,
                        null, null, "incoming-message", 1));

        assertEquals("message-1", response.id());
        assertEquals(123L, response.timestamp());
        server.verify();
    }

    @Test
    void encodesReactionPaginationQuery() {
        server.expect(requestTo("https://api.bot.qq.com/channels/channel-1/messages/message-1/reactions/1/203?cookie=next&limit=20"))
                .andExpect(method(org.springframework.http.HttpMethod.GET))
                .andRespond(withSuccess("{\"users\":[],\"cookie\":\"done\",\"is_end\":true}", MediaType.APPLICATION_JSON));

        ReactionUsersResponse response = messages.getReactionUsers("channel-1", "message-1", 1, "203",
                new GetReactionUsersRequest("next", 20));

        assertEquals(true, response.isEnd());
        server.verify();
    }
}
