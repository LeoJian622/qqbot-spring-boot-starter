package cc.starcube.qqbot.spring.model.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import cc.starcube.qqbot.spring.model.ApiPermissionDemand.ApiIdentify;
public record CreateApiPermissionDemandRequest(@JsonProperty("channel_id") String channelId,
                                               @JsonProperty("api_identify") ApiIdentify apiIdentify,
                                               String desc) { }
