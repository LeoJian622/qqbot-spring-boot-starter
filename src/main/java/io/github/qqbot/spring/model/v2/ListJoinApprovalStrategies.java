package io.github.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** 查询入群自动审批策略列表: GET /v2/groups/join_approval_strategy. */
public final class ListJoinApprovalStrategies {
    private ListJoinApprovalStrategies() { }

    public record Request(Query query) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Query(
            @JsonProperty("cursor") String cursor,
            @JsonProperty("limit") Integer limit) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("strategies") List<ResponseJoinApprovalStrategy> strategies,
            @JsonProperty("next_cursor") String nextCursor) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResponseJoinApprovalStrategy(
            @JsonProperty("strategy_id") String strategyId,
            @JsonProperty("group_openids") List<String> groupOpenids,
            @JsonProperty("group_ids") List<String> groupIds,
            @JsonProperty("whitelist_user_count") Integer whitelistUserCount,
            @JsonProperty("is_enable") String isEnable,
            @JsonProperty("expire_at") String expireAt,
            @JsonProperty("created_at") String createdAt,
            @JsonProperty("updated_at") String updatedAt,
            @JsonProperty("remark") String remark) { }
}
