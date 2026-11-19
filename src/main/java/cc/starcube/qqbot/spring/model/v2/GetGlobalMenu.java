package cc.starcube.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** 查询全局自定义菜单: GET /v2/menu. */
public final class GetGlobalMenu {
    private GetGlobalMenu() { }

    public record Request() { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("version") Integer version,
            @JsonProperty("menu") ResponseMenu menu) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResponseMenu(
            @JsonProperty("items") List<ResponseMenuItem> items) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResponseMenuItem(
            @JsonProperty("name") String name,
            @JsonProperty("type") String type,
            @JsonProperty("sub_menu_items") List<ResponseSubMenuItem> subMenuItems,
            @JsonProperty("send_message") String sendMessage,
            @JsonProperty("link") String link,
            @JsonProperty("switch") ResponseSwitch switchValue) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResponseSubMenuItem(
            @JsonProperty("name") String name,
            @JsonProperty("type") String type,
            @JsonProperty("send_message") String sendMessage,
            @JsonProperty("link") String link) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResponseSwitch(
            @JsonProperty("switch_id") String switchId,
            @JsonProperty("default") Boolean defaultValue) { }
}
