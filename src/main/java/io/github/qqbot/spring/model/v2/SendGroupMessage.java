package io.github.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;

/** 发送群聊消息: POST /v2/groups/{group_openid}/messages. */
public final class SendGroupMessage {
    private SendGroupMessage() { }

    public record Request(String groupOpenid, Body body) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Body(
            @JsonProperty("msg_type") Integer msgType,
            @JsonProperty("content") String content,
            @JsonProperty("markdown") RequestMessageMarkdown markdown,
            @JsonProperty("keyboard") RequestKeyboard keyboard,
            @JsonProperty("msg_id") String msgId,
            @JsonProperty("event_id") String eventId,
            @JsonProperty("msg_seq") Integer msgSeq,
            @JsonProperty("media") RequestMediaInfo media,
            @JsonProperty("message_reference") RequestMessageReference messageReference,
            @JsonProperty("card") RequestMessageCard card) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RequestMessageMarkdown(
            @JsonProperty("template_id") Integer templateId,
            @JsonProperty("content") String content,
            @JsonProperty("custom_template_id") String customTemplateId,
            @JsonProperty("force_verify_image_resource") Boolean forceVerifyImageResource) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RequestKeyboard(
            @JsonProperty("id") String id,
            @JsonProperty("content") RequestKeyboardContent content) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RequestKeyboardContent(
            @JsonProperty("rows") List<RequestRow> rows) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RequestRow(
            @JsonProperty("buttons") List<RequestButton> buttons) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RequestButton(
            @JsonProperty("id") String id,
            @JsonProperty("render_data") RequestRenderData renderData,
            @JsonProperty("action") RequestAction action,
            @JsonProperty("group_id") String groupId) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RequestRenderData(
            @JsonProperty("label") String label,
            @JsonProperty("visited_label") String visitedLabel,
            @JsonProperty("style") Integer style) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RequestAction(
            @JsonProperty("type") Integer type,
            @JsonProperty("permission") RequestPermission permission,
            @JsonProperty("data") String data,
            @JsonProperty("click_limit") Integer clickLimit,
            @JsonProperty("unsupport_tips") String unsupportTips,
            @JsonProperty("enter") Boolean enter,
            @JsonProperty("reply") Boolean reply,
            @JsonProperty("anchor") Integer anchor,
            @JsonProperty("modal") RequestModal modal) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RequestPermission(
            @JsonProperty("type") Integer type,
            @JsonProperty("specify_user_ids") List<String> specifyUserIds,
            @JsonProperty("specify_role_ids") List<String> specifyRoleIds) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RequestModal(
            @JsonProperty("content") String content,
            @JsonProperty("confirm_text") String confirmText,
            @JsonProperty("cancel_text") String cancelText) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RequestMediaInfo(
            @JsonProperty("file_info") String fileInfo) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RequestMessageReference(
            @JsonProperty("message_id") String messageId) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RequestMessageCard(
            @JsonProperty("type") String type,
            @JsonProperty("content") JsonNode content) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("id") String id,
            @JsonProperty("timestamp") String timestamp,
            @JsonProperty("ext_info") ResponseMessageExtInfo extInfo) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResponseMessageExtInfo(
            @JsonProperty("ref_idx") String refIdx) { }
}
