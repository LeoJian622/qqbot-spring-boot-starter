package io.github.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** 修改指令面板关联对象: PUT /v2/panels/{panel_id}/target. */
public final class UpdateCommandPanelTargets {
    private UpdateCommandPanelTargets() { }

    public record Request(String panelId, Body body) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Body(
            @JsonProperty("op") String op,
            @JsonProperty("user_openids") List<String> userOpenids,
            @JsonProperty("group_openids") List<String> groupOpenids) { }
}
