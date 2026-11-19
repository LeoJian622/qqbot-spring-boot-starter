package io.github.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 群聊分片上传完成: POST /v2/groups/{group_id}/upload_part_finish. */
public final class FinishGroupUploadPart {
    private FinishGroupUploadPart() { }

    public record Request(String groupId, Body body) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Body(
            @JsonProperty("upload_id") String uploadId,
            @JsonProperty("part_index") Integer partIndex,
            @JsonProperty("block_size") String blockSize,
            @JsonProperty("md5") String md5) { }
}
