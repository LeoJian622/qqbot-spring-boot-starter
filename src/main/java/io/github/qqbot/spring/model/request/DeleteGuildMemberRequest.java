package io.github.qqbot.spring.model.request;
import com.fasterxml.jackson.annotation.JsonProperty;
public record DeleteGuildMemberRequest(@JsonProperty("add_blacklist") Boolean addBlacklist,
                                       @JsonProperty("delete_history_msg_days") Integer deleteHistoryMessageDays) { }
