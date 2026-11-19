package io.github.qqbot.spring.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.qqbot.spring.model.*;

public record SendC2cMessageRequest(String content, @JsonProperty("msg_type") Integer msgType,
    MessageMarkdown markdown, MessageKeyboard keyboard, MessageArk ark, MessageMedia media,
    @JsonProperty("message_reference") MessageReference messageReference,
    @JsonProperty("event_id") String eventId, @JsonProperty("msg_id") String messageId,
    @JsonProperty("msg_seq") Integer messageSequence) { }
