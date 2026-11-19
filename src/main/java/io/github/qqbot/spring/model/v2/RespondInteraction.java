package io.github.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 互动事件响应: PUT /interactions/{interaction_id}. */
public final class RespondInteraction {
    private RespondInteraction() { }

    public record Request(String interactionId, Body body) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Body(
            @JsonProperty("code") Integer code) { }
}
