package cc.starcube.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** 修改入群自动审批策略的白名单号码: POST /v2/groups/join_approval_strategy/{strategy_id}/whitelist_users. */
public final class UpdateJoinApprovalWhitelist {
    private UpdateJoinApprovalWhitelist() { }

    public record Request(String strategyId, Body body) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Body(
            @JsonProperty("op") String op,
            @JsonProperty("whitelist_users") List<String> whitelistUsers) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("strategy_id") String strategyId,
            @JsonProperty("whitelist_user_count") Integer whitelistUserCount,
            @JsonProperty("updated_at") String updatedAt) { }
}
