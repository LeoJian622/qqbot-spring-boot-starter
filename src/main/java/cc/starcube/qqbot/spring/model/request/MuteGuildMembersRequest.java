package cc.starcube.qqbot.spring.model.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
public record MuteGuildMembersRequest(@JsonProperty("mute_end_timestamp") String muteEndTimestamp,
                                      @JsonProperty("mute_seconds") String muteSeconds,
                                      @JsonProperty("user_ids") List<String> userIds) { }
