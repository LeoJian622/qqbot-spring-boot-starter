package io.github.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record Member(User user, String nick, List<String> roles,
                     @JsonProperty("joined_at") String joinedAt,
                     Boolean deaf, Boolean mute, Boolean pending,
                     @JsonProperty("guild_id") String guildId) { }
