package io.github.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Dms(@JsonProperty("guild_id") String guildId,
                  @JsonProperty("channel_id") String channelId,
                  @JsonProperty("create_time") String createTime) { }
