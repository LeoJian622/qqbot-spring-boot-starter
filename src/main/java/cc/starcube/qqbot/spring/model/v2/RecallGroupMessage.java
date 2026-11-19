package cc.starcube.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 撤回群聊消息: DELETE /v2/groups/{group_openid}/messages/{message_id}. */
public final class RecallGroupMessage {
    private RecallGroupMessage() { }

    public record Request(String groupOpenid, String messageId) { }
}
