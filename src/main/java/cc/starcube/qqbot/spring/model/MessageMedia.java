package cc.starcube.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MessageMedia(@JsonProperty("file_info") String fileInfo) { }
