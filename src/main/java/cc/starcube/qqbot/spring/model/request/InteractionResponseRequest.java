package cc.starcube.qqbot.spring.model.request;
import com.fasterxml.jackson.annotation.JsonProperty;
public record InteractionResponseRequest(Integer code, @JsonProperty("event_id") String eventId) { }
