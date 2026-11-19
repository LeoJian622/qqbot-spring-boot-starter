package io.github.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 获取用户基本信息: GET /v2/users/{user_openid}/info. */
public final class GetUserInfo {
    private GetUserInfo() { }

    public record Request(String userOpenid) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("union_openid") String unionOpenid,
            @JsonProperty("username") String username,
            @JsonProperty("avatar") String avatar,
            @JsonProperty("allow_proactive_msg") Boolean allowProactiveMsg) { }
}
