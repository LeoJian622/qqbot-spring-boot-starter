package cc.starcube.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** 入群申请列表拉取: GET /v2/groups/{group_openid}/join_request_list. */
public final class ListGroupJoinRequests {
    private ListGroupJoinRequests() { }

    public record Request(String groupOpenid, Query query) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Query(
            @JsonProperty("cursor") String cursor,
            @JsonProperty("limit") Integer limit) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("list") List<ResponseJoinRequest> list,
            @JsonProperty("next_cursor") String nextCursor) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResponseJoinRequest(
            @JsonProperty("join_request_id") String joinRequestId,
            @JsonProperty("risk_tips") String riskTips,
            @JsonProperty("union_openid") String unionOpenid,
            @JsonProperty("member_openid") String memberOpenid,
            @JsonProperty("username") String username,
            @JsonProperty("apply_at") String applyAt,
            @JsonProperty("apply_source") String applySource,
            @JsonProperty("invited_by") String invitedBy,
            @JsonProperty("bot") Boolean bot,
            @JsonProperty("verify_info") ResponseVerifyInfo verifyInfo) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResponseVerifyInfo(
            @JsonProperty("method") String method,
            @JsonProperty("verify_message") String verifyMessage,
            @JsonProperty("review_qa_list") List<ResponseReviewQA> reviewQaList) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResponseReviewQA(
            @JsonProperty("question") String question,
            @JsonProperty("answer") String answer) { }
}
