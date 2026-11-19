package cc.starcube.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** 修改全局自定义菜单: PUT /v2/menu. */
public final class UpdateGlobalMenu {
    private UpdateGlobalMenu() { }

    public record Request(Body body) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Body(
            @JsonProperty("menu") RequestMenu menu) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RequestMenu(
            @JsonProperty("items") List<RequestMenuItem> items) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RequestMenuItem(
            @JsonProperty("name") String name,
            @JsonProperty("type") String type,
            @JsonProperty("sub_menu_items") List<RequestSubMenuItem> subMenuItems,
            @JsonProperty("send_message") String sendMessage,
            @JsonProperty("link") String link,
            @JsonProperty("switch") RequestSwitch switchValue) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RequestSubMenuItem(
            @JsonProperty("name") String name,
            @JsonProperty("type") String type,
            @JsonProperty("send_message") String sendMessage,
            @JsonProperty("link") String link) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record RequestSwitch(
            @JsonProperty("switch_id") String switchId,
            @JsonProperty("default") Boolean defaultValue) { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Response(
            @JsonProperty("version") Integer version) { }
}
