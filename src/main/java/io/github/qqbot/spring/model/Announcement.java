package io.github.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record Announcement(@JsonProperty("guild_id") String guildId,
                           @JsonProperty("channel_id") String channelId,
                           @JsonProperty("message_id") String messageId,
                           @JsonProperty("announces_type") Integer announcesType,
                           @JsonProperty("recommend_channels") List<RecommendChannel> recommendChannels) {
    public record RecommendChannel(@JsonProperty("channel_id") String channelId, String introduce) { }
}
