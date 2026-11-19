package cc.starcube.qqbot.spring.model.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 删除指令面板: DELETE /v2/panels/{panel_id}. */
public final class DeleteCommandPanel {
    private DeleteCommandPanel() { }

    public record Request(String panelId) { }
}
