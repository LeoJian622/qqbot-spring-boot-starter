package cc.starcube.qqbot.spring.model.request;
import com.fasterxml.jackson.annotation.JsonProperty;
public record CreateDmsRequest(@JsonProperty("recipient_id") String recipientId,
                               @JsonProperty("source_guild_id") String sourceGuildId) { }
