package cc.starcube.qqbot.spring.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import cc.starcube.qqbot.spring.model.*;

public record SendGroupMessageRequest(String content, @JsonProperty("msg_type") Integer msgType,
    MessageMarkdown markdown, MessageKeyboard keyboard, MessageArk ark, MessageMedia media,
    @JsonProperty("message_reference") MessageReference messageReference,
    @JsonProperty("event_id") String eventId, @JsonProperty("msg_id") String messageId,
    @JsonProperty("msg_seq") Integer messageSequence) { }
