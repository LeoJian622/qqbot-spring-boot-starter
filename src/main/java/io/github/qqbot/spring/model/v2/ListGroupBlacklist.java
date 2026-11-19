package io.github.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** 群黑名单查询: GET /v2/groups/{group_openid}/member_blacklist. */
public final class ListGroupBlacklist {
    private ListGroupBlacklist() { }

    public record Request(String groupOpenid, Query query) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Query(
            @JsonProperty("cursor") String cursor,
            @JsonProperty("limit") Integer limit) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("users") List<ResponseBlacklistUser> users,
            @JsonProperty("next_cursor") String nextCursor) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResponseBlacklistUser(
            @JsonProperty("union_openid") String unionOpenid,
            @JsonProperty("member_openid") String memberOpenid,
            @JsonProperty("username") String username,
            @JsonProperty("banned_at") String bannedAt,
            @JsonProperty("bot") Boolean bot) { }
}
