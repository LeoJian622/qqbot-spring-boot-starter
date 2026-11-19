package io.github.qqbot.spring.model.request;
import com.fasterxml.jackson.annotation.JsonProperty;
public record UploadC2cMediaRequest(@JsonProperty("file_type") Integer fileType, String url,
    @JsonProperty("srv_send_msg") Boolean serverSendMessage) { }
