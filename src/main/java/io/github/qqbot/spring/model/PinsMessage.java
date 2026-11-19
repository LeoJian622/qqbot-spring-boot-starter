package io.github.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record PinsMessage(@JsonProperty("guild_id") String guildId,
                          @JsonProperty("channel_id") String channelId,
                          @JsonProperty("message_ids") List<String> messageIds) { }
