package cc.starcube.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** 修改指令面板: PUT /v2/panels/{panel_id}. */
public final class UpdateCommandPanel {
    private UpdateCommandPanel() { }

    public record Request(String panelId, Body body) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Body(
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
            @JsonProperty("version") Integer version) { }
}
