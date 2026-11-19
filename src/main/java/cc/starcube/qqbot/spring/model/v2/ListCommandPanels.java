package cc.starcube.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** 查询指令面板列表: GET /v2/panels. */
public final class ListCommandPanels {
    private ListCommandPanels() { }

    public record Request(Query query) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Query(
            @JsonProperty("scope") String scope,
            @JsonProperty("cursor") String cursor,
            @JsonProperty("limit") Integer limit) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("records") List<ResponsePanelRecord> records,
            @JsonProperty("next_cursor") String nextCursor,
            @JsonProperty("is_end") Boolean isEnd) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResponsePanelRecord(
            @JsonProperty("panel_id") String panelId,
            @JsonProperty("scope") String scope,
            @JsonProperty("target_type") String targetType,
            @JsonProperty("panel") ResponsePanel panel,
            @JsonProperty("created_at") String createdAt,
            @JsonProperty("updated_at") String updatedAt,
            @JsonProperty("version") Integer version) { }

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
