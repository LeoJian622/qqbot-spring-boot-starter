package cc.starcube.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 单聊分片上传完成: POST /v2/users/{user_id}/upload_part_finish. */
public final class FinishUserUploadPart {
    private FinishUserUploadPart() { }

    public record Request(String userId, Body body) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Body(
            @JsonProperty("upload_id") String uploadId,
            @JsonProperty("part_index") Integer partIndex,
            @JsonProperty("block_size") String blockSize,
            @JsonProperty("md5") String md5) { }
}
