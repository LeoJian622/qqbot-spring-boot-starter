package cc.starcube.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 执行入群自动审批策略: POST /v2/groups/join_approval_strategy/{strategy_id}/execute. */
public final class ExecuteJoinApprovalStrategy {
    private ExecuteJoinApprovalStrategy() { }

    public record Request(String strategyId) { }
}
