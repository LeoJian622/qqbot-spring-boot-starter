package cc.starcube.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record MuteMembersResponse(@JsonProperty("user_ids") List<String> userIds) { }
