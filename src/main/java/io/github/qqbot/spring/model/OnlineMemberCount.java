package io.github.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OnlineMemberCount(@JsonProperty("online_nums") Integer onlineNums) { }
