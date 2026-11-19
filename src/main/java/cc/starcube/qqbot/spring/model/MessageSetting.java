package cc.starcube.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record MessageSetting(@JsonProperty("disable_create_dm") String disableCreateDm,
                             @JsonProperty("disable_push_msg") String disablePushMsg,
                             @JsonProperty("channel_ids") List<String> channelIds,
                             @JsonProperty("channel_push_max_num") Integer channelPushMaxNum) { }
