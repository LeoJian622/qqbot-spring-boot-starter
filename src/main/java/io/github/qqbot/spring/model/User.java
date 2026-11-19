package io.github.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record User(String id, String username, String avatar, Boolean bot, Boolean system,
                   @JsonProperty("public_flags") Integer publicFlags,
                   @JsonProperty("union_openid") String unionOpenid,
                   @JsonProperty("union_user_account") String unionUserAccount) { }
