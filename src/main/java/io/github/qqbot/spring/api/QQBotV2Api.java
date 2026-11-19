package io.github.qqbot.spring.api;

import io.github.qqbot.spring.QQBotClient;
import io.github.qqbot.spring.model.v2.*;
import org.springframework.web.util.UriComponentsBuilder;

/** Typed facade for the current API v2 catalog published by bot.q.qq.com. */
public class QQBotV2Api {
    private final QQBotClient client;

    public QQBotV2Api(QQBotClient client) {
        this.client = client;
    }

    /** 删除子频道. */
    public void deleteChannel(DeleteChannel.Request request) {
        String path = "/channels/{channel_id}";
        path = path.replace("{channel_id}", ApiSupport.s(request.channelId()));
        client.deleteVoid(path);
    }

    /** 获取子频道详情. */
    public GetChannel.Response getChannel(GetChannel.Request request) {
        String path = "/channels/{channel_id}";
        path = path.replace("{channel_id}", ApiSupport.s(request.channelId()));
        return client.get(path, GetChannel.Response.class);
    }

    /** 修改子频道. */
    public UpdateChannel.Response updateChannel(UpdateChannel.Request request) {
        String path = "/channels/{channel_id}";
        path = path.replace("{channel_id}", ApiSupport.s(request.channelId()));
        return client.patch(path, request.body(), UpdateChannel.Response.class);
    }

    /** 获取通用 WSS 接入点. */
    public GetGateway.Response getGateway(GetGateway.Request request) {
        String path = "/gateway";
        return client.get(path, GetGateway.Response.class);
    }

    public GetGateway.Response getGateway() {
        return getGateway(new GetGateway.Request());
    }

    /** 获取频道详情. */
    public GetGuild.Response getGuild(GetGuild.Request request) {
        String path = "/guilds/{guild_id}";
        path = path.replace("{guild_id}", ApiSupport.s(request.guildId()));
        return client.get(path, GetGuild.Response.class);
    }

    /** 获取子频道列表. */
    public ListGuildChannels.Response listGuildChannels(ListGuildChannels.Request request) {
        String path = "/guilds/{guild_id}/channels";
        path = path.replace("{guild_id}", ApiSupport.s(request.guildId()));
        return client.get(path, ListGuildChannels.Response.class);
    }

    /** 创建子频道. */
    public CreateGuildChannel.Response createGuildChannel(CreateGuildChannel.Request request) {
        String path = "/guilds/{guild_id}/channels";
        path = path.replace("{guild_id}", ApiSupport.s(request.guildId()));
        return client.post(path, request.body(), CreateGuildChannel.Response.class);
    }

    /** 互动事件响应. */
    public void respondInteraction(RespondInteraction.Request request) {
        String path = "/interactions/{interaction_id}";
        path = path.replace("{interaction_id}", ApiSupport.s(request.interactionId()));
        client.putVoid(path, request.body());
    }

    /** 获取机器人详情. */
    public GetCurrentUser.Response getCurrentUser(GetCurrentUser.Request request) {
        String path = "/users/@me";
        return client.get(path, GetCurrentUser.Response.class);
    }

    public GetCurrentUser.Response getCurrentUser() {
        return getCurrentUser(new GetCurrentUser.Request());
    }

    /** 获取机器人频道列表. */
    public ListCurrentUserGuilds.Response listCurrentUserGuilds(ListCurrentUserGuilds.Request request) {
        String path = "/users/@me/guilds";
        UriComponentsBuilder uri = ApiSupport.path(path);
        if (request.query() != null) {
            ApiSupport.query(uri, "before", request.query().before());
            ApiSupport.query(uri, "after", request.query().after());
            ApiSupport.query(uri, "limit", request.query().limit());
        }
        path = ApiSupport.build(uri);
        return client.get(path, ListCurrentUserGuilds.Response.class);
    }

    /** 生成分享链接. */
    public GenerateUrlLink.Response generateUrlLink(GenerateUrlLink.Request request) {
        String path = "/v2/generate_url_link";
        return client.post(path, request.body(), GenerateUrlLink.Response.class);
    }

    /** 群聊分片上传完成. */
    public void finishGroupUploadPart(FinishGroupUploadPart.Request request) {
        String path = "/v2/groups/{group_id}/upload_part_finish";
        path = path.replace("{group_id}", ApiSupport.s(request.groupId()));
        client.postVoid(path, request.body());
    }

    /** 群聊富媒体预上传. */
    public PrepareGroupUpload.Response prepareGroupUpload(PrepareGroupUpload.Request request) {
        String path = "/v2/groups/{group_id}/upload_prepare";
        path = path.replace("{group_id}", ApiSupport.s(request.groupId()));
        return client.post(path, request.body(), PrepareGroupUpload.Response.class);
    }

    /** 入群申请审批. */
    public void approveGroupJoinRequest(ApproveGroupJoinRequest.Request request) {
        String path = "/v2/groups/{group_openid}/approval_join_request/{member_openid}";
        path = path.replace("{group_openid}", ApiSupport.s(request.groupOpenid()));
        path = path.replace("{member_openid}", ApiSupport.s(request.memberOpenid()));
        client.postVoid(path, request.body());
    }

    /** 群成员批量移除. */
    public BatchRemoveGroupMembers.Response batchRemoveGroupMembers(BatchRemoveGroupMembers.Request request) {
        String path = "/v2/groups/{group_openid}/batch_remove_members";
        path = path.replace("{group_openid}", ApiSupport.s(request.groupOpenid()));
        return client.post(path, request.body(), BatchRemoveGroupMembers.Response.class);
    }

    /** 获取机器人群内状态. */
    public GetGroupBotState.Response getGroupBotState(GetGroupBotState.Request request) {
        String path = "/v2/groups/{group_openid}/bot_state";
        path = path.replace("{group_openid}", ApiSupport.s(request.groupOpenid()));
        return client.get(path, GetGroupBotState.Response.class);
    }

    /** 群聊富媒体上传. */
    public UploadGroupMedia.Response uploadGroupMedia(UploadGroupMedia.Request request) {
        String path = "/v2/groups/{group_openid}/files";
        path = path.replace("{group_openid}", ApiSupport.s(request.groupOpenid()));
        return client.post(path, request.body(), UploadGroupMedia.Response.class);
    }

    /** 获取群基本信息. */
    public GetGroupInfo.Response getGroupInfo(GetGroupInfo.Request request) {
        String path = "/v2/groups/{group_openid}/info";
        path = path.replace("{group_openid}", ApiSupport.s(request.groupOpenid()));
        return client.get(path, GetGroupInfo.Response.class);
    }

    /** 入群申请列表拉取. */
    public ListGroupJoinRequests.Response listGroupJoinRequests(ListGroupJoinRequests.Request request) {
        String path = "/v2/groups/{group_openid}/join_request_list";
        path = path.replace("{group_openid}", ApiSupport.s(request.groupOpenid()));
        UriComponentsBuilder uri = ApiSupport.path(path);
        if (request.query() != null) {
            ApiSupport.query(uri, "cursor", request.query().cursor());
            ApiSupport.query(uri, "limit", request.query().limit());
        }
        path = ApiSupport.build(uri);
        return client.get(path, ListGroupJoinRequests.Response.class);
    }

    /** 群黑名单查询. */
    public ListGroupBlacklist.Response listGroupBlacklist(ListGroupBlacklist.Request request) {
        String path = "/v2/groups/{group_openid}/member_blacklist";
        path = path.replace("{group_openid}", ApiSupport.s(request.groupOpenid()));
        UriComponentsBuilder uri = ApiSupport.path(path);
        if (request.query() != null) {
            ApiSupport.query(uri, "cursor", request.query().cursor());
            ApiSupport.query(uri, "limit", request.query().limit());
        }
        path = ApiSupport.build(uri);
        return client.get(path, ListGroupBlacklist.Response.class);
    }

    /** 群黑名单操作. */
    public UpdateGroupBlacklist.Response updateGroupBlacklist(UpdateGroupBlacklist.Request request) {
        String path = "/v2/groups/{group_openid}/member_blacklist";
        path = path.replace("{group_openid}", ApiSupport.s(request.groupOpenid()));
        return client.post(path, request.body(), UpdateGroupBlacklist.Response.class);
    }

    /** 获取群成员列表. */
    public ListGroupMembers.Response listGroupMembers(ListGroupMembers.Request request) {
        String path = "/v2/groups/{group_openid}/members";
        path = path.replace("{group_openid}", ApiSupport.s(request.groupOpenid()));
        UriComponentsBuilder uri = ApiSupport.path(path);
        if (request.query() != null) {
            ApiSupport.query(uri, "cursor", request.query().cursor());
        }
        path = ApiSupport.build(uri);
        return client.get(path, ListGroupMembers.Response.class);
    }

    /** 获取群成员信息. */
    public GetGroupMember.Response getGroupMember(GetGroupMember.Request request) {
        String path = "/v2/groups/{group_openid}/members/{member_openid}";
        path = path.replace("{group_openid}", ApiSupport.s(request.groupOpenid()));
        path = path.replace("{member_openid}", ApiSupport.s(request.memberOpenid()));
        return client.get(path, GetGroupMember.Response.class);
    }

    /** 发送群聊消息. */
    public SendGroupMessage.Response sendGroupMessage(SendGroupMessage.Request request) {
        String path = "/v2/groups/{group_openid}/messages";
        path = path.replace("{group_openid}", ApiSupport.s(request.groupOpenid()));
        return client.post(path, request.body(), SendGroupMessage.Response.class);
    }

    /** 撤回群聊消息. */
    public void recallGroupMessage(RecallGroupMessage.Request request) {
        String path = "/v2/groups/{group_openid}/messages/{message_id}";
        path = path.replace("{group_openid}", ApiSupport.s(request.groupOpenid()));
        path = path.replace("{message_id}", ApiSupport.s(request.messageId()));
        client.deleteVoid(path);
    }

    /** 查询群禁言状态. */
    public GetGroupMuteSettings.Response getGroupMuteSettings(GetGroupMuteSettings.Request request) {
        String path = "/v2/groups/{group_openid}/restrict_chat_setting";
        path = path.replace("{group_openid}", ApiSupport.s(request.groupOpenid()));
        return client.get(path, GetGroupMuteSettings.Response.class);
    }

    /** 设置群成员禁言. */
    public void setGroupMuteSettings(SetGroupMuteSettings.Request request) {
        String path = "/v2/groups/{group_openid}/restrict_chat_setting";
        path = path.replace("{group_openid}", ApiSupport.s(request.groupOpenid()));
        client.postVoid(path, request.body());
    }

    /** 查询入群自动审批策略列表. */
    public ListJoinApprovalStrategies.Response listJoinApprovalStrategies(ListJoinApprovalStrategies.Request request) {
        String path = "/v2/groups/join_approval_strategy";
        UriComponentsBuilder uri = ApiSupport.path(path);
        if (request.query() != null) {
            ApiSupport.query(uri, "cursor", request.query().cursor());
            ApiSupport.query(uri, "limit", request.query().limit());
        }
        path = ApiSupport.build(uri);
        return client.get(path, ListJoinApprovalStrategies.Response.class);
    }

    /** 创建入群自动审批策略. */
    public CreateJoinApprovalStrategy.Response createJoinApprovalStrategy(CreateJoinApprovalStrategy.Request request) {
        String path = "/v2/groups/join_approval_strategy";
        return client.post(path, request.body(), CreateJoinApprovalStrategy.Response.class);
    }

    /** 删除入群自动审批策略. */
    public void deleteJoinApprovalStrategy(DeleteJoinApprovalStrategy.Request request) {
        String path = "/v2/groups/join_approval_strategy/{strategy_id}";
        path = path.replace("{strategy_id}", ApiSupport.s(request.strategyId()));
        client.deleteVoid(path);
    }

    /** 修改入群自动审批策略. */
    public UpdateJoinApprovalStrategy.Response updateJoinApprovalStrategy(UpdateJoinApprovalStrategy.Request request) {
        String path = "/v2/groups/join_approval_strategy/{strategy_id}";
        path = path.replace("{strategy_id}", ApiSupport.s(request.strategyId()));
        return client.patch(path, request.body(), UpdateJoinApprovalStrategy.Response.class);
    }

    /** 执行入群自动审批策略. */
    public void executeJoinApprovalStrategy(ExecuteJoinApprovalStrategy.Request request) {
        String path = "/v2/groups/join_approval_strategy/{strategy_id}/execute";
        path = path.replace("{strategy_id}", ApiSupport.s(request.strategyId()));
        client.postVoid(path, null);
    }

    /** 修改入群自动审批策略的白名单号码. */
    public UpdateJoinApprovalWhitelist.Response updateJoinApprovalWhitelist(UpdateJoinApprovalWhitelist.Request request) {
        String path = "/v2/groups/join_approval_strategy/{strategy_id}/whitelist_users";
        path = path.replace("{strategy_id}", ApiSupport.s(request.strategyId()));
        return client.post(path, request.body(), UpdateJoinApprovalWhitelist.Response.class);
    }

    /** 查询全局自定义菜单. */
    public GetGlobalMenu.Response getGlobalMenu(GetGlobalMenu.Request request) {
        String path = "/v2/menu";
        return client.get(path, GetGlobalMenu.Response.class);
    }

    public GetGlobalMenu.Response getGlobalMenu() {
        return getGlobalMenu(new GetGlobalMenu.Request());
    }

    /** 修改全局自定义菜单. */
    public UpdateGlobalMenu.Response updateGlobalMenu(UpdateGlobalMenu.Request request) {
        String path = "/v2/menu";
        return client.put(path, request.body(), UpdateGlobalMenu.Response.class);
    }

    /** 查询指令面板列表. */
    public ListCommandPanels.Response listCommandPanels(ListCommandPanels.Request request) {
        String path = "/v2/panels";
        UriComponentsBuilder uri = ApiSupport.path(path);
        if (request.query() != null) {
            ApiSupport.query(uri, "scope", request.query().scope());
            ApiSupport.query(uri, "cursor", request.query().cursor());
            ApiSupport.query(uri, "limit", request.query().limit());
        }
        path = ApiSupport.build(uri);
        return client.get(path, ListCommandPanels.Response.class);
    }

    /** 创建指令面板. */
    public CreateCommandPanel.Response createCommandPanel(CreateCommandPanel.Request request) {
        String path = "/v2/panels";
        return client.post(path, request.body(), CreateCommandPanel.Response.class);
    }

    /** 删除指令面板. */
    public void deleteCommandPanel(DeleteCommandPanel.Request request) {
        String path = "/v2/panels/{panel_id}";
        path = path.replace("{panel_id}", ApiSupport.s(request.panelId()));
        client.deleteVoid(path);
    }

    /** 查询指令面板详情. */
    public GetCommandPanel.Response getCommandPanel(GetCommandPanel.Request request) {
        String path = "/v2/panels/{panel_id}";
        path = path.replace("{panel_id}", ApiSupport.s(request.panelId()));
        return client.get(path, GetCommandPanel.Response.class);
    }

    /** 修改指令面板. */
    public UpdateCommandPanel.Response updateCommandPanel(UpdateCommandPanel.Request request) {
        String path = "/v2/panels/{panel_id}";
        path = path.replace("{panel_id}", ApiSupport.s(request.panelId()));
        return client.put(path, request.body(), UpdateCommandPanel.Response.class);
    }

    /** 修改指令面板关联对象. */
    public void updateCommandPanelTargets(UpdateCommandPanelTargets.Request request) {
        String path = "/v2/panels/{panel_id}/target";
        path = path.replace("{panel_id}", ApiSupport.s(request.panelId()));
        client.putVoid(path, request.body());
    }

    /** 单聊分片上传完成. */
    public void finishUserUploadPart(FinishUserUploadPart.Request request) {
        String path = "/v2/users/{user_id}/upload_part_finish";
        path = path.replace("{user_id}", ApiSupport.s(request.userId()));
        client.postVoid(path, request.body());
    }

    /** 单聊富媒体预上传. */
    public PrepareUserUpload.Response prepareUserUpload(PrepareUserUpload.Request request) {
        String path = "/v2/users/{user_id}/upload_prepare";
        path = path.replace("{user_id}", ApiSupport.s(request.userId()));
        return client.post(path, request.body(), PrepareUserUpload.Response.class);
    }

    /** 单聊富媒体上传. */
    public UploadUserMedia.Response uploadUserMedia(UploadUserMedia.Request request) {
        String path = "/v2/users/{user_openid}/files";
        path = path.replace("{user_openid}", ApiSupport.s(request.userOpenid()));
        return client.post(path, request.body(), UploadUserMedia.Response.class);
    }

    /** 获取用户基本信息. */
    public GetUserInfo.Response getUserInfo(GetUserInfo.Request request) {
        String path = "/v2/users/{user_openid}/info";
        path = path.replace("{user_openid}", ApiSupport.s(request.userOpenid()));
        return client.get(path, GetUserInfo.Response.class);
    }

    /** 发送单聊消息. */
    public SendUserMessage.Response sendUserMessage(SendUserMessage.Request request) {
        String path = "/v2/users/{user_openid}/messages";
        path = path.replace("{user_openid}", ApiSupport.s(request.userOpenid()));
        return client.post(path, request.body(), SendUserMessage.Response.class);
    }

    /** 撤回单聊消息. */
    public void recallUserMessage(RecallUserMessage.Request request) {
        String path = "/v2/users/{user_openid}/messages/{message_id}";
        path = path.replace("{user_openid}", ApiSupport.s(request.userOpenid()));
        path = path.replace("{message_id}", ApiSupport.s(request.messageId()));
        client.deleteVoid(path);
    }

    /** 流式发送单聊消息. */
    public SendUserStreamMessage.Response sendUserStreamMessage(SendUserStreamMessage.Request request) {
        String path = "/v2/users/{user_openid}/stream_messages";
        path = path.replace("{user_openid}", ApiSupport.s(request.userOpenid()));
        return client.post(path, request.body(), SendUserStreamMessage.Response.class);
    }
}
