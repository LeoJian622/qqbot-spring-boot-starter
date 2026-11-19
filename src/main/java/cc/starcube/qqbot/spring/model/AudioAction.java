package cc.starcube.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AudioAction(@JsonProperty("guild_id") String guildId,
                          @JsonProperty("channel_id") String channelId,
                          @JsonProperty("audio_url") String audioUrl, String text) { }
