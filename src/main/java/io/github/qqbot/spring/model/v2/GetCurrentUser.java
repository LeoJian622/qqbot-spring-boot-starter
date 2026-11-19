package io.github.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 获取机器人详情: GET /users/@me. */
public final class GetCurrentUser {
    private GetCurrentUser() { }

    public record Request() { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("id") String id,
            @JsonProperty("username") String username,
            @JsonProperty("avatar") String avatar,
            @JsonProperty("bot") Boolean bot,
            @JsonProperty("union_openid") String unionOpenid,
            @JsonProperty("union_user_account") String unionUserAccount) { }
}
