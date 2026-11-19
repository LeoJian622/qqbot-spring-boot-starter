package cc.starcube.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** 查询指令面板详情: GET /v2/panels/{panel_id}. */
public final class GetCommandPanel {
    private GetCommandPanel() { }

    public record Request(String panelId) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("panel_id") String panelId,
            @JsonProperty("scope") String scope,
            @JsonProperty("target_type") String targetType,
            @JsonProperty("panel") ResponsePanel panel,
            @JsonProperty("created_at") String createdAt,
            @JsonProperty("updated_at") String updatedAt,
            @JsonProperty("version") Integer version,
            @JsonProperty("user_openids") List<String> userOpenids,
            @JsonProperty("group_openids") List<String> groupOpenids) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResponsePanel(
            @JsonProperty("items") List<ResponsePanelItem> items,
            @JsonProperty("remark") String remark,
            @JsonProperty("version") Integer version) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResponsePanelItem(
            @JsonProperty("name") String name,
            @JsonProperty("desc") String desc,
            @JsonProperty("type") String type,
            @JsonProperty("only_admin") Boolean onlyAdmin,
            @JsonProperty("link") String link) { }
}
