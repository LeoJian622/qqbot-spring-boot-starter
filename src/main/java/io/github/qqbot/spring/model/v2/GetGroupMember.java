package io.github.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 获取群成员信息: GET /v2/groups/{group_openid}/members/{member_openid}. */
public final class GetGroupMember {
    private GetGroupMember() { }

    public record Request(String groupOpenid, String memberOpenid) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("member_openid") String memberOpenid,
            @JsonProperty("username") String username,
            @JsonProperty("member_role") String memberRole,
            @JsonProperty("bot") Boolean bot,
            @JsonProperty("joined_at") String joinedAt,
            @JsonProperty("union_openid") String unionOpenid) { }
}
