package cc.starcube.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** 群聊富媒体预上传: POST /v2/groups/{group_id}/upload_prepare. */
public final class PrepareGroupUpload {
    private PrepareGroupUpload() { }

    public record Request(String groupId, Body body) { }

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
