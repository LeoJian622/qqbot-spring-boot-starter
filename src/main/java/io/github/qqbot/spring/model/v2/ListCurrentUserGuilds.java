package io.github.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** 获取机器人频道列表: GET /users/@me/guilds. */
public final class ListCurrentUserGuilds {
    private ListCurrentUserGuilds() { }

    public record Request(Query query) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Query(
            @JsonProperty("before") String before,
            @JsonProperty("after") String after,
            @JsonProperty("limit") Integer limit) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("guilds") List<ResponseGuildInfo> guilds) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResponseGuildInfo(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("icon") String icon,
            @JsonProperty("owner_id") String ownerId,
            @JsonProperty("owner") Boolean owner,
            @JsonProperty("joined_at") String joinedAt,
            @JsonProperty("member_count") Integer memberCount,
            @JsonProperty("max_members") Integer maxMembers,
            @JsonProperty("description") String description) { }
}
