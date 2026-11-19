package cc.starcube.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** 创建指令面板: POST /v2/panels. */
public final class CreateCommandPanel {
    private CreateCommandPanel() { }

    public record Request(Body body) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Body(
            @JsonProperty("scope") String scope,
            @JsonProperty("target_type") String targetType,
            @JsonProperty("user_openids") List<String> userOpenids,
            @JsonProperty("group_openids") List<String> groupOpenids,
            @JsonProperty("panel") RequestPanel panel) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RequestPanel(
            @JsonProperty("items") List<RequestPanelItem> items,
            @JsonProperty("remark") String remark,
            @JsonProperty("version") Integer version) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RequestPanelItem(
            @JsonProperty("name") String name,
            @JsonProperty("desc") String desc,
            @JsonProperty("type") String type,
            @JsonProperty("only_admin") Boolean onlyAdmin,
            @JsonProperty("link") String link) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("panel_id") String panelId) { }
}
