package io.github.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** 群黑名单操作: POST /v2/groups/{group_openid}/member_blacklist. */
public final class UpdateGroupBlacklist {
    private UpdateGroupBlacklist() { }

    public record Request(String groupOpenid, Body body) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Body(
            @JsonProperty("op") String op,
            @JsonProperty("member_openids") List<String> memberOpenids) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("fail_openids") List<String> failOpenids) { }
}
