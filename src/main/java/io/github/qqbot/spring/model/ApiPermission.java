package io.github.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ApiPermission(String path, String method, String desc,
                            @JsonProperty("auth_status") Integer authStatus) { }
