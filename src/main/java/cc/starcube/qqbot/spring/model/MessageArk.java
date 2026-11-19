package cc.starcube.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record MessageArk(@JsonProperty("template_id") Integer templateId, List<KeyValue> kv) {
    public record KeyValue(String key, String value, List<ArkObject> obj) { }
    public record ArkObject(@JsonProperty("obj_kv") List<ObjectKeyValue> objectKeyValues) { }
    public record ObjectKeyValue(String key, String value) { }
}
