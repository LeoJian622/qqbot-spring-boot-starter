package io.github.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 单聊富媒体上传: POST /v2/users/{user_openid}/files. */
public final class UploadUserMedia {
    private UploadUserMedia() { }

    public record Request(String userOpenid, Body body) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Body(
            @JsonProperty("file_type") Integer fileType,
            @JsonProperty("url") String url,
            @JsonProperty("srv_send_msg") Boolean srvSendMsg,
            @JsonProperty("upload_id") String uploadId,
            @JsonProperty("file_name") String fileName,
            @JsonProperty("file_data") String fileData) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("file_uuid") String fileUuid,
            @JsonProperty("file_info") String fileInfo,
            @JsonProperty("ttl") Integer ttl,
            @JsonProperty("id") String id,
            @JsonProperty("raw_url") String rawUrl) { }
}
