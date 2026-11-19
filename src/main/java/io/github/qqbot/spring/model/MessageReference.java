package io.github.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MessageReference(@JsonProperty("message_id") String messageId,
                               @JsonProperty("ignore_get_message_error") Boolean ignoreGetMessageError) { }
