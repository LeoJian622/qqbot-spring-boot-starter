package io.github.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Channel(String id, @JsonProperty("guild_id") String guildId, String name,
                      Integer type, @JsonProperty("sub_type") Integer subType,
                      Integer position, @JsonProperty("parent_id") String parentId,
                      @JsonProperty("owner_id") String ownerId,
                      @JsonProperty("private_type") Integer privateType,
                      @JsonProperty("speak_permission") Integer speakPermission,
                      @JsonProperty("application_id") String applicationId,
                      String permissions) { }
