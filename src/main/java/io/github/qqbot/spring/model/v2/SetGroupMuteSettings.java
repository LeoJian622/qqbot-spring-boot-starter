package io.github.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** 设置群成员禁言: POST /v2/groups/{group_openid}/restrict_chat_setting. */
public final class SetGroupMuteSettings {
    private SetGroupMuteSettings() { }

    public record Request(String groupOpenid, Body body) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Body(
            @JsonProperty("members") List<RequestSetMemberMuteState> members) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RequestSetMemberMuteState(
            @JsonProperty("op") String op,
            @JsonProperty("member_openid") String memberOpenid,
            @JsonProperty("mute_expire_at") String muteExpireAt) { }
}
