package io.github.qqbot.spring.model.request;
import com.fasterxml.jackson.annotation.JsonProperty;
public record MuteGuildMemberRequest(@JsonProperty("mute_end_timestamp") String muteEndTimestamp,
                                     @JsonProperty("mute_seconds") String muteSeconds) { }
