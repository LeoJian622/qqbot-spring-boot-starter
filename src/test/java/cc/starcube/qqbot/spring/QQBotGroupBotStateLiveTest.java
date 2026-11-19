package cc.starcube.qqbot.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import cc.starcube.qqbot.spring.api.QQBotV2Api;
import cc.starcube.qqbot.spring.model.v2.GetGroupBotState;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class QQBotGroupBotStateLiveTest {

    @Test
    void readsBotStateInGroup() {
        String appId = System.getenv("QQBOT_APP_ID");
        String clientSecret = System.getenv("QQBOT_CLIENT_SECRET");
        String groupOpenid = System.getenv("QQBOT_STATE_GROUP_OPENID");
        assumeTrue(appId != null && !appId.isBlank());
        assumeTrue(clientSecret != null && !clientSecret.isBlank());
        assumeTrue(groupOpenid != null && !groupOpenid.isBlank());

        QQBotProperties properties = new QQBotProperties();
        properties.setAppId(appId);
        properties.setClientSecret(clientSecret);
        RestClient.Builder builder = RestClient.builder();
        QQBotTokenProvider tokenProvider = new QQBotTokenProvider(properties, new ObjectMapper(), builder);
        QQBotV2Api api = new QQBotV2Api(new QQBotClient(properties, tokenProvider, builder));

        GetGroupBotState.Response state = api.getGroupBotState(new GetGroupBotState.Request(groupOpenid));
        assertNotNull(state);
        System.out.println("QQBOT_MEMBER_OPENID=" + state.memberOpenid());
        System.out.println("QQBOT_JOINED_AT=" + state.joinedAt());
        System.out.println("QQBOT_ALLOW_PROACTIVE_MSG=" + state.allowProactiveMsg());
        System.out.println("QQBOT_RECV_MSG_SETTING=" + state.recvMsgSetting());
        System.out.println("QQBOT_MEMBER_ROLE=" + state.memberRole());
    }
}
