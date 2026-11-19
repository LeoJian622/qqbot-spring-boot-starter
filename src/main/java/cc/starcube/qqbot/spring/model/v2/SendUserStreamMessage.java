package cc.starcube.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 流式发送单聊消息: POST /v2/users/{user_openid}/stream_messages. */
public final class SendUserStreamMessage {
    private SendUserStreamMessage() { }

    public record Request(String userOpenid, Body body) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Body(
            @JsonProperty("input_mode") String inputMode,
            @JsonProperty("input_state") Integer inputState,
            @JsonProperty("index") Integer index,
            @JsonProperty("content_type") String contentType,
            @JsonProperty("content_raw") String contentRaw,
            @JsonProperty("event_id") String eventId,
            @JsonProperty("msg_id") String msgId,
            @JsonProperty("stream_msg_id") String streamMsgId,
            @JsonProperty("msg_seq") Integer msgSeq,
            @JsonProperty("is_wakeup") Boolean isWakeup) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("id") String id,
            @JsonProperty("timestamp") String timestamp,
            @JsonProperty("ext_info") ResponseMessageExtInfo extInfo,
            @JsonProperty("remain_msg_len") Integer remainMsgLen) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResponseMessageExtInfo(
            @JsonProperty("ref_idx") String refIdx) { }
}
