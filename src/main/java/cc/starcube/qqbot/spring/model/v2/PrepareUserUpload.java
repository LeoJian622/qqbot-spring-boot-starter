package cc.starcube.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** 单聊富媒体预上传: POST /v2/users/{user_id}/upload_prepare. */
public final class PrepareUserUpload {
    private PrepareUserUpload() { }

    public record Request(String userId, Body body) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Body(
            @JsonProperty("file_type") Integer fileType,
            @JsonProperty("file_size") String fileSize,
            @JsonProperty("file_name") String fileName,
            @JsonProperty("md5") String md5,
            @JsonProperty("sha1") String sha1,
            @JsonProperty("md5_10m") String md510m) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("upload_id") String uploadId,
            @JsonProperty("block_size") String blockSize,
            @JsonProperty("parts") List<ResponseUploadPart> parts,
            @JsonProperty("upload_config") ResponseUploadConfig uploadConfig) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResponseUploadPart(
            @JsonProperty("index") Integer index,
            @JsonProperty("presigned_url") String presignedUrl,
            @JsonProperty("block_size") String blockSize) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResponseUploadConfig(
            @JsonProperty("concurrency") Integer concurrency,
            @JsonProperty("retry_timeout") Integer retryTimeout,
            @JsonProperty("retry_delay") Integer retryDelay) { }
}
