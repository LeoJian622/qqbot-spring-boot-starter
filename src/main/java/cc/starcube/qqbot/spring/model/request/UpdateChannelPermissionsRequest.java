package cc.starcube.qqbot.spring.model.request;
import com.fasterxml.jackson.annotation.JsonProperty;
public record UpdateChannelPermissionsRequest(@JsonProperty("add") String addPermissions,
                                              @JsonProperty("remove") String removePermissions) { }
