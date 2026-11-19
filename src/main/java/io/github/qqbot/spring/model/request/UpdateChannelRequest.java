package io.github.qqbot.spring.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record UpdateChannelRequest(String name, Integer position,
    @JsonProperty("parent_id") String parentId,
    @JsonProperty("private_type") Integer privateType,
    @JsonProperty("private_user_ids") List<String> privateUserIds,
    @JsonProperty("speak_permission") Integer speakPermission) { }
