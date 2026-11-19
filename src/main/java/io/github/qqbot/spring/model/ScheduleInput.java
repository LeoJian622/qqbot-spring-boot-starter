package io.github.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ScheduleInput(String name, String description,
                            @JsonProperty("start_timestamp") String startTimestamp,
                            @JsonProperty("end_timestamp") String endTimestamp,
                            @JsonProperty("jump_channel_id") String jumpChannelId,
                            @JsonProperty("remind_type") String remindType) { }
