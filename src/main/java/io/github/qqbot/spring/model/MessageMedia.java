package io.github.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MessageMedia(@JsonProperty("file_info") String fileInfo) { }
