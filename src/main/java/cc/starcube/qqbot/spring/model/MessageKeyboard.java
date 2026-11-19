package cc.starcube.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record MessageKeyboard(String id, Content content) {
    public record Content(List<Row> rows) { }
    public record Row(List<Button> buttons) { }
    public record Button(String id, @JsonProperty("render_data") RenderData renderData, Action action) { }
    public record RenderData(String label, @JsonProperty("visited_label") String visitedLabel, Integer style) { }
    public record Action(Integer type, Permission permission, String data, Boolean reply, Boolean enter,
                         Integer anchor, @JsonProperty("click_limit") Integer clickLimit,
                         @JsonProperty("unsupport_tips") String unsupportedTips) { }
    public record Permission(Integer type,
                             @JsonProperty("specify_user_ids") List<String> specifyUserIds,
                             @JsonProperty("specify_role_ids") List<String> specifyRoleIds) { }
}
