package cc.starcube.qqbot.spring.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import cc.starcube.qqbot.spring.model.*;

public record SendChannelMessageRequest(String content, String image,
    @JsonProperty("msg_id") String messageId, @JsonProperty("event_id") String eventId,
    MessageEmbed embed, MessageArk ark, MessageMarkdown markdown, MessageKeyboard keyboard,
    @JsonProperty("message_reference") MessageReference messageReference) { }
