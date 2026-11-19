package io.github.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 获取频道详情: GET /guilds/{guild_id}. */
public final class GetGuild {
    private GetGuild() { }

    public record Request(String guildId) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("icon") String icon,
            @JsonProperty("owner_id") String ownerId,
            @JsonProperty("owner") Boolean owner,
            @JsonProperty("member_count") Integer memberCount,
            @JsonProperty("max_members") Integer maxMembers,
            @JsonProperty("description") String description,
            @JsonProperty("joined_at") String joinedAt) { }
}
