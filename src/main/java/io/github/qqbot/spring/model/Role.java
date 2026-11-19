package io.github.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Role(String id, String name, Long color, Integer hoist, Integer number,
                   @JsonProperty("member_limit") Integer memberLimit) { }
