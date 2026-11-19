package io.github.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 获取机器人群内状态: GET /v2/groups/{group_openid}/bot_state. */
public final class GetGroupBotState {
    private GetGroupBotState() { }

    public record Request(String groupOpenid) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("member_openid") String memberOpenid,
            @JsonProperty("joined_at") String joinedAt,
            @JsonProperty("allow_proactive_msg") Boolean allowProactiveMsg,
            @JsonProperty("recv_msg_setting") String recvMsgSetting,
            @JsonProperty("member_role") String memberRole) { }
}
