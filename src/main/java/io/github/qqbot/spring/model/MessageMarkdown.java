package io.github.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record MessageMarkdown(String content,
                              @JsonProperty("custom_template_id") String customTemplateId,
                              List<Param> params) {
    public record Param(String key, List<String> values) { }
}
