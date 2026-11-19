package io.github.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** 获取子频道列表: GET /guilds/{guild_id}/channels. */
public final class ListGuildChannels {
    private ListGuildChannels() { }

    public record Request(String guildId) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("channels") List<ResponseChannel> channels) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResponseChannel(
            @JsonProperty("id") String id,
            @JsonProperty("guild_id") String guildId,
            @JsonProperty("name") String name,
            @JsonProperty("type") Integer type,
            @JsonProperty("sub_type") Integer subType,
            @JsonProperty("position") Integer position,
            @JsonProperty("parent_id") String parentId,
            @JsonProperty("owner_id") String ownerId,
            @JsonProperty("private_type") Integer privateType,
            @JsonProperty("speak_permission") Integer speakPermission,
            @JsonProperty("application_id") String applicationId,
            @JsonProperty("permissions") String permissions) { }
}
