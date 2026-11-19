package io.github.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 入群申请审批: POST /v2/groups/{group_openid}/approval_join_request/{member_openid}. */
public final class ApproveGroupJoinRequest {
    private ApproveGroupJoinRequest() { }

    public record Request(String groupOpenid, String memberOpenid, Body body) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Body(
            @JsonProperty("op") String op,
            @JsonProperty("join_request_id") String joinRequestId,
            @JsonProperty("reject_reason") String rejectReason,
            @JsonProperty("add_to_member_blacklist") Boolean addToMemberBlacklist) { }
}
