package cc.starcube.qqbot.spring.model.request;
import com.fasterxml.jackson.annotation.JsonProperty;
public record MuteGuildRequest(@JsonProperty("mute_end_timestamp") String muteEndTimestamp,
                               @JsonProperty("mute_seconds") String muteSeconds) { }
