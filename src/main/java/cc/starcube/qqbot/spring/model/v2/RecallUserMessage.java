package cc.starcube.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 撤回单聊消息: DELETE /v2/users/{user_openid}/messages/{message_id}. */
public final class RecallUserMessage {
    private RecallUserMessage() { }

    public record Request(String userOpenid, String messageId) { }
}
