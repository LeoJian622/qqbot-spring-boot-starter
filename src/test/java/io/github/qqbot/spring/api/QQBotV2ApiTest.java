package io.github.qqbot.spring.api;

import io.github.qqbot.spring.QQBotClient;
import io.github.qqbot.spring.QQBotProperties;
import io.github.qqbot.spring.QQBotTokenProvider;
import io.github.qqbot.spring.model.v2.CreateCommandPanel;
import io.github.qqbot.spring.model.v2.ListGroupMembers;
import io.github.qqbot.spring.model.v2.SendUserStreamMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class QQBotV2ApiTest {
    private MockRestServiceServer server;
    private QQBotV2Api api;

    @BeforeEach
    void setUp() {
        QQBotProperties properties = new QQBotProperties();
        RestClient.Builder builder = RestClient.builder();
        server = MockRestServiceServer.bindTo(builder).build();
        QQBotTokenProvider tokens = mock(QQBotTokenProvider.class);
        when(tokens.getAccessToken()).thenReturn("access-token");
        api = new QQBotV2Api(new QQBotClient(properties, tokens, builder));
    }

    @Test
    void listsGroupMembersWithEncodedCursor() {
        server.expect(requestTo("https://api.bot.qq.com/v2/groups/group%20one/members?cursor=next%20page"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("{\"members\":[],\"next_cursor\":\"done\"}", MediaType.APPLICATION_JSON));

        ListGroupMembers.Response response = api.listGroupMembers(new ListGroupMembers.Request(
                "group one", new ListGroupMembers.Query("next page")));

        assertEquals("done", response.nextCursor());
        server.verify();
    }

    @Test
    void serializesIndependentCommandPanelModel() {
        server.expect(requestTo("https://api.bot.qq.com/v2/panels"))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().json("""
                        {
                          "scope": "group",
                          "target_type": "specific",
                          "group_openids": ["group-1"],
                          "panel": {
                            "items": [{"name":"weather","type":"command"}],
                            "remark": "tools"
                          }
                        }
                        """))
                .andRespond(withSuccess("{\"panel_id\":\"panel-1\"}", MediaType.APPLICATION_JSON));

        CreateCommandPanel.RequestPanelItem item = new CreateCommandPanel.RequestPanelItem(
                "weather", null, "command", null, null);
        CreateCommandPanel.RequestPanel panel = new CreateCommandPanel.RequestPanel(
                List.of(item), "tools", null);
        CreateCommandPanel.Body body = new CreateCommandPanel.Body(
                "group", "specific", null, List.of("group-1"), panel);

        CreateCommandPanel.Response response = api.createCommandPanel(new CreateCommandPanel.Request(body));

        assertEquals("panel-1", response.panelId());
        server.verify();
    }

    @Test
    void deserializesStreamMessageResponse() {
        server.expect(requestTo("https://api.bot.qq.com/v2/users/user-1/stream_messages"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess("""
                        {"id":"stream-1","timestamp":"2026-11-19T00:00:00Z",
                         "ext_info":{"ref_idx":"3"},"remain_msg_len":12}
                        """, MediaType.APPLICATION_JSON));

        SendUserStreamMessage.Body body = new SendUserStreamMessage.Body(
                "append", 1, 0, "text", "hello", null, "message-1", null, 1, null);
        SendUserStreamMessage.Response response = api.sendUserStreamMessage(
                new SendUserStreamMessage.Request("user-1", body));

        assertEquals(12, response.remainMsgLen());
        assertEquals("3", response.extInfo().refIdx());
        server.verify();
    }
}
