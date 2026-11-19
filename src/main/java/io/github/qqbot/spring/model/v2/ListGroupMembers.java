package io.github.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** 获取群成员列表: GET /v2/groups/{group_openid}/members. */
public final class ListGroupMembers {
    private ListGroupMembers() { }

    public record Request(String groupOpenid, Query query) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Query(
            @JsonProperty("cursor") String cursor) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("members") List<ResponseMember> members,
            @JsonProperty("next_cursor") String nextCursor) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResponseMember(
            @JsonProperty("member_openid") String memberOpenid,
            @JsonProperty("username") String username,
            @JsonProperty("member_role") String memberRole,
            @JsonProperty("bot") Boolean bot,
            @JsonProperty("joined_at") String joinedAt,
            @JsonProperty("union_openid") String unionOpenid) { }
}
