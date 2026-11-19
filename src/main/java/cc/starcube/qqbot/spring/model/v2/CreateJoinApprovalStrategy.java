package cc.starcube.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** 创建入群自动审批策略: POST /v2/groups/join_approval_strategy. */
public final class CreateJoinApprovalStrategy {
    private CreateJoinApprovalStrategy() { }

    public record Request(Body body) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Body(
            @JsonProperty("group_openids") List<String> groupOpenids,
            @JsonProperty("group_ids") List<String> groupIds,
            @JsonProperty("is_enable") String isEnable,
            @JsonProperty("expire_at") String expireAt,
            @JsonProperty("remark") String remark) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("strategy_id") String strategyId,
            @JsonProperty("is_enable") String isEnable,
            @JsonProperty("expire_at") String expireAt) { }
}
