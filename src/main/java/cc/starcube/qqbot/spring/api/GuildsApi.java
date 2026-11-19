package cc.starcube.qqbot.spring.api;

import cc.starcube.qqbot.spring.QQBotClient;
import cc.starcube.qqbot.spring.model.*;
import cc.starcube.qqbot.spring.model.request.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

public class GuildsApi {
    private static final ParameterizedTypeReference<List<Member>> MEMBER_LIST = new ParameterizedTypeReference<>() { };
    private final QQBotClient client;
    public GuildsApi(QQBotClient client) { this.client = client; }

    public Guild getGuild(String guildId) { return client.get("/guilds/" + ApiSupport.s(guildId), Guild.class); }

    public List<Member> getGuildMembers(String guildId, GetGuildMembersRequest request) {
        UriComponentsBuilder uri = ApiSupport.path("/guilds/" + ApiSupport.s(guildId) + "/members");
        if (request != null) {
            ApiSupport.query(uri, "after", request.after());
            ApiSupport.query(uri, "limit", request.limit());
        }
        return client.get(ApiSupport.build(uri), MEMBER_LIST);
    }

    public Member getGuildMember(String guildId, String userId) {
        return client.get("/guilds/" + ApiSupport.s(guildId) + "/members/" + ApiSupport.s(userId), Member.class);
    }

    public void deleteGuildMember(String guildId, String userId, DeleteGuildMemberRequest request) {
        client.deleteVoid("/guilds/" + ApiSupport.s(guildId) + "/members/" + ApiSupport.s(userId), request);
    }

    public RoleListResponse getGuildRoles(String guildId) {
        return client.get("/guilds/" + ApiSupport.s(guildId) + "/roles", RoleListResponse.class);
    }

    public RoleOperationResponse createGuildRole(String guildId, CreateRoleRequest request) {
        return client.post("/guilds/" + ApiSupport.s(guildId) + "/roles", request, RoleOperationResponse.class);
    }

    public RoleOperationResponse updateGuildRole(String guildId, String roleId, UpdateRoleRequest request) {
        return client.patch("/guilds/" + ApiSupport.s(guildId) + "/roles/" + ApiSupport.s(roleId), request, RoleOperationResponse.class);
    }

    public void deleteGuildRole(String guildId, String roleId) {
        client.deleteVoid("/guilds/" + ApiSupport.s(guildId) + "/roles/" + ApiSupport.s(roleId));
    }

    public void addGuildMemberRole(String guildId, String userId, String roleId) {
        client.putVoid("/guilds/" + ApiSupport.s(guildId) + "/members/" + ApiSupport.s(userId)
                + "/roles/" + ApiSupport.s(roleId), null);
    }

    public void removeGuildMemberRole(String guildId, String userId, String roleId) {
        client.deleteVoid("/guilds/" + ApiSupport.s(guildId) + "/members/" + ApiSupport.s(userId)
                + "/roles/" + ApiSupport.s(roleId));
    }

    public RoleMembersResponse getGuildRoleMembers(String guildId, String roleId, GetRoleMembersRequest request) {
        UriComponentsBuilder uri = ApiSupport.path("/guilds/" + ApiSupport.s(guildId) + "/roles/" + ApiSupport.s(roleId) + "/members");
        if (request != null) {
            ApiSupport.query(uri, "start_index", request.startIndex());
            ApiSupport.query(uri, "limit", request.limit());
        }
        return client.get(ApiSupport.build(uri), RoleMembersResponse.class);
    }

    public void muteGuild(String guildId, MuteGuildRequest request) {
        client.patchVoid("/guilds/" + ApiSupport.s(guildId) + "/mute", request);
    }

    public MuteMembersResponse muteGuildMembers(String guildId, MuteGuildMembersRequest request) {
        return client.patch("/guilds/" + ApiSupport.s(guildId) + "/mute", request, MuteMembersResponse.class);
    }

    public void muteGuildMember(String guildId, String userId, MuteGuildMemberRequest request) {
        client.patchVoid("/guilds/" + ApiSupport.s(guildId) + "/members/" + ApiSupport.s(userId) + "/mute", request);
    }

    public MessageSetting getGuildMessageSetting(String guildId) {
        return client.get("/guilds/" + ApiSupport.s(guildId) + "/message/setting", MessageSetting.class);
    }

    public ApiPermissionsResponse getGuildApiPermissions(String guildId) {
        return client.get("/guilds/" + ApiSupport.s(guildId) + "/api_permission", ApiPermissionsResponse.class);
    }

    public ApiPermissionDemand createGuildApiPermissionDemand(String guildId, CreateApiPermissionDemandRequest request) {
        return client.post("/guilds/" + ApiSupport.s(guildId) + "/api_permission/demand", request, ApiPermissionDemand.class);
    }

    public Announcement createGuildAnnouncement(String guildId, CreateGuildAnnouncementRequest request) {
        return client.post("/guilds/" + ApiSupport.s(guildId) + "/announces", request, Announcement.class);
    }

    public void deleteGuildAnnouncement(String guildId, String messageId) {
        client.deleteVoid("/guilds/" + ApiSupport.s(guildId) + "/announces/" + ApiSupport.s(messageId));
    }
}
