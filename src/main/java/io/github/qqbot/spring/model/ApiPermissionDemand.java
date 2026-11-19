package io.github.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ApiPermissionDemand(@JsonProperty("guild_id") String guildId,
                                  @JsonProperty("channel_id") String channelId,
                                  @JsonProperty("api_identify") ApiIdentify apiIdentify,
                                  String title, String desc) {
    public record ApiIdentify(String path, String method) { }
}
