package io.github.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** 群成员批量移除: POST /v2/groups/{group_openid}/batch_remove_members. */
public final class BatchRemoveGroupMembers {
    private BatchRemoveGroupMembers() { }

    public record Request(String groupOpenid, Body body) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Body(
            @JsonProperty("member_openids") List<String> memberOpenids) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("remove_members_result") String removeMembersResult) { }
}
