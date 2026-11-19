package cc.starcube.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** 获取群基本信息: GET /v2/groups/{group_openid}/info. */
public final class GetGroupInfo {
    private GetGroupInfo() { }

    public record Request(String groupOpenid) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("group_openid") String groupOpenid,
            @JsonProperty("group_name") String groupName,
            @JsonProperty("group_finger_memo") String groupFingerMemo,
            @JsonProperty("group_class_text") String groupClassText,
            @JsonProperty("group_tags") List<String> groupTags,
            @JsonProperty("group_member_num") Integer groupMemberNum,
            @JsonProperty("union_group_openid") String unionGroupOpenid) { }
}
