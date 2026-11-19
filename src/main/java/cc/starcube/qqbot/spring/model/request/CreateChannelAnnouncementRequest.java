package cc.starcube.qqbot.spring.model.request;
import com.fasterxml.jackson.annotation.JsonProperty;
public record CreateChannelAnnouncementRequest(@JsonProperty("message_id") String messageId) { }
