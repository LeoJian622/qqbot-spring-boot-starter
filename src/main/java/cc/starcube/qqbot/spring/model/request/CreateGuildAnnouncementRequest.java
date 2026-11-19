package cc.starcube.qqbot.spring.model.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import cc.starcube.qqbot.spring.model.Announcement.RecommendChannel;
import java.util.List;
public record CreateGuildAnnouncementRequest(@JsonProperty("message_id") String messageId,
                                             @JsonProperty("channel_id") String channelId,
                                             @JsonProperty("announces_type") Integer announcesType,
                                             @JsonProperty("recommend_channels") List<RecommendChannel> recommendChannels) { }
