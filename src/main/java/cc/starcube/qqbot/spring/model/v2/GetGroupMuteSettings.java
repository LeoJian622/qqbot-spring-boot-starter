package cc.starcube.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** 查询群禁言状态: GET /v2/groups/{group_openid}/restrict_chat_setting. */
public final class GetGroupMuteSettings {
    private GetGroupMuteSettings() { }

    public record Request(String groupOpenid) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("global_rule") ResponseGlobalMuteRule globalRule,
            @JsonProperty("members") List<ResponseMemberMuteState> members) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResponseGlobalMuteRule(
            @JsonProperty("mode") String mode,
            @JsonProperty("schedule_rules") List<ResponseMuteScheduleRule> scheduleRules,
            @JsonProperty("recurring_rules") List<ResponseMuteRecurringRule> recurringRules) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResponseMuteScheduleRule(
            @JsonProperty("task_id") String taskId,
            @JsonProperty("start_at") String startAt,
            @JsonProperty("end_at") String endAt,
            @JsonProperty("enabled") Boolean enabled) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResponseMuteRecurringRule(
            @JsonProperty("task_id") String taskId,
            @JsonProperty("weekdays") List<Integer> weekdays,
            @JsonProperty("start_time") String startTime,
            @JsonProperty("end_time") String endTime,
            @JsonProperty("enabled") Boolean enabled) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResponseMemberMuteState(
            @JsonProperty("member_openid") String memberOpenid,
            @JsonProperty("mute_expire_at") String muteExpireAt,
            @JsonProperty("username") String username,
            @JsonProperty("union_openid") String unionOpenid) { }
}
