package io.github.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** 修改入群自动审批策略: PATCH /v2/groups/join_approval_strategy/{strategy_id}. */
public final class UpdateJoinApprovalStrategy {
    private UpdateJoinApprovalStrategy() { }

    public record Request(String strategyId, Body body) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Body(
            @JsonProperty("is_enable") String isEnable,
            @JsonProperty("expire_at") String expireAt,
            @JsonProperty("group_action") RequestGroupAction groupAction,
            @JsonProperty("remark") String remark) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RequestGroupAction(
            @JsonProperty("op") String op,
            @JsonProperty("group_openids") List<String> groupOpenids,
            @JsonProperty("group_ids") List<String> groupIds) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("is_enable") String isEnable,
            @JsonProperty("expire_at") String expireAt) { }
}
