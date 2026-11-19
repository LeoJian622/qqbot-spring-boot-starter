package io.github.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ChannelPermissions(@JsonProperty("channel_id") String channelId,
                                 @JsonProperty("user_id") String userId,
                                 @JsonProperty("role_id") String roleId,
                                 String permissions) { }
