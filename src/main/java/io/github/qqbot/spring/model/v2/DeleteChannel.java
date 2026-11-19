package io.github.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 删除子频道: DELETE /channels/{channel_id}. */
public final class DeleteChannel {
    private DeleteChannel() { }

    public record Request(String channelId) { }
}
