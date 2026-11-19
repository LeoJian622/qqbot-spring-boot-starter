package io.github.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record RoleListResponse(@JsonProperty("guild_id") String guildId, List<Role> roles,
                               @JsonProperty("role_num_limit") String roleNumLimit) { }
