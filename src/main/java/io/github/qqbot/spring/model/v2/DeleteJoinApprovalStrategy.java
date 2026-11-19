package io.github.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 删除入群自动审批策略: DELETE /v2/groups/join_approval_strategy/{strategy_id}. */
public final class DeleteJoinApprovalStrategy {
    private DeleteJoinApprovalStrategy() { }

    public record Request(String strategyId) { }
}
