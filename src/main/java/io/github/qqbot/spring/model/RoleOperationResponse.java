package io.github.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RoleOperationResponse(@JsonProperty("guild_id") String guildId,
                                    @JsonProperty("role_id") String roleId,
                                    Role role) { }
