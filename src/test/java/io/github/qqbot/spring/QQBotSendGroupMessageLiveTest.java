package io.github.qqbot.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.qqbot.spring.api.QQBotV2Api;
import io.github.qqbot.spring.model.v2.GetGroupBotState;
import io.github.qqbot.spring.model.v2.SendGroupMessage;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class QQBotSendGroupMessageLiveTest {

    @Test
    void sendsMarkdownMentionToGroup() {
        String appId = System.getenv("QQBOT_APP_ID");
        String clientSecret = System.getenv("QQBOT_CLIENT_SECRET");
        String groupOpenid = System.getenv("QQBOT_SEND_GROUP_OPENID");
        String memberOpenid = System.getenv("QQBOT_SEND_MEMBER_OPENID");
        String message = System.getenv("QQBOT_SEND_MESSAGE");
        String sendEnabled = System.getenv("QQBOT_SEND_ENABLED");
        assumeTrue(appId != null && !appId.isBlank());
        assumeTrue(clientSecret != null && !clientSecret.isBlank());
        assumeTrue(groupOpenid != null && !groupOpenid.isBlank());
        assumeTrue(memberOpenid != null && !memberOpenid.isBlank());
        assumeTrue(message != null && !message.isBlank());
        assumeTrue(Boolean.parseBoolean(sendEnabled));

        QQBotProperties properties = new QQBotProperties();
        properties.setAppId(appId);
        properties.setClientSecret(clientSecret);
        RestClient.Builder builder = RestClient.builder();
        QQBotTokenProvider tokenProvider = new QQBotTokenProvider(properties, new ObjectMapper(), builder);
        QQBotV2Api api = new QQBotV2Api(new QQBotClient(properties, tokenProvider, builder));

        GetGroupBotState.Response state = api.getGroupBotState(new GetGroupBotState.Request(groupOpenid));
        System.out.println("QQBOT_ALLOW_PROACTIVE_MSG=" + state.allowProactiveMsg());
        System.out.println("QQBOT_RECV_MSG_SETTING=" + state.recvMsgSetting());

        String markdownContent = "<qqbot-at-user id=\"" + memberOpenid + "\" /> " + message;
        SendGroupMessage.RequestMessageMarkdown markdown = new SendGroupMessage.RequestMessageMarkdown(
                null, markdownContent, null, null
        );
        SendGroupMessage.Body body = new SendGroupMessage.Body(
                2, null, markdown, null, null, null, null, null, null, null
        );
        SendGroupMessage.Response response = api.sendGroupMessage(
                new SendGroupMessage.Request(groupOpenid, body)
        );

        assertNotNull(response);
        assertNotNull(response.id());
        assertFalse(response.id().isBlank());
        System.out.println("QQBOT_SENT_MESSAGE_ID=" + response.id());
    }
}
