package cc.starcube.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MediaUploadResponse(@JsonProperty("file_uuid") String fileUuid,
                                  @JsonProperty("file_info") String fileInfo,
                                  Integer ttl, String id) { }
