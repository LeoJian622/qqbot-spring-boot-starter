package cc.starcube.qqbot.spring.api;

import org.springframework.http.HttpMethod;
import java.util.List;

/** Canonical catalog from the current bot.q.qq.com API v2 documentation. */
public final class QQBotEndpoints {
    private QQBotEndpoints() { }

    public record Endpoint(HttpMethod method, String path, String title) { }

    public static final List<Endpoint> ALL = List.of(
            new Endpoint(HttpMethod.DELETE, "/channels/{channel_id}", "删除子频道"),
            new Endpoint(HttpMethod.GET, "/channels/{channel_id}", "获取子频道详情"),
            new Endpoint(HttpMethod.PATCH, "/channels/{channel_id}", "修改子频道"),
            new Endpoint(HttpMethod.GET, "/gateway", "获取通用 WSS 接入点"),
            new Endpoint(HttpMethod.GET, "/guilds/{guild_id}", "获取频道详情"),
            new Endpoint(HttpMethod.GET, "/guilds/{guild_id}/channels", "获取子频道列表"),
            new Endpoint(HttpMethod.POST, "/guilds/{guild_id}/channels", "创建子频道"),
            new Endpoint(HttpMethod.PUT, "/interactions/{interaction_id}", "互动事件响应"),
            new Endpoint(HttpMethod.GET, "/users/@me", "获取机器人详情"),
            new Endpoint(HttpMethod.GET, "/users/@me/guilds", "获取机器人频道列表"),
            new Endpoint(HttpMethod.POST, "/v2/generate_url_link", "生成分享链接"),
            new Endpoint(HttpMethod.POST, "/v2/groups/{group_id}/upload_part_finish", "群聊分片上传完成"),
            new Endpoint(HttpMethod.POST, "/v2/groups/{group_id}/upload_prepare", "群聊富媒体预上传"),
            new Endpoint(HttpMethod.POST, "/v2/groups/{group_openid}/approval_join_request/{member_openid}", "入群申请审批"),
            new Endpoint(HttpMethod.POST, "/v2/groups/{group_openid}/batch_remove_members", "群成员批量移除"),
            new Endpoint(HttpMethod.GET, "/v2/groups/{group_openid}/bot_state", "获取机器人群内状态"),
            new Endpoint(HttpMethod.POST, "/v2/groups/{group_openid}/files", "群聊富媒体上传"),
            new Endpoint(HttpMethod.GET, "/v2/groups/{group_openid}/info", "获取群基本信息"),
            new Endpoint(HttpMethod.GET, "/v2/groups/{group_openid}/join_request_list", "入群申请列表拉取"),
            new Endpoint(HttpMethod.GET, "/v2/groups/{group_openid}/member_blacklist", "群黑名单查询"),
            new Endpoint(HttpMethod.POST, "/v2/groups/{group_openid}/member_blacklist", "群黑名单操作"),
            new Endpoint(HttpMethod.GET, "/v2/groups/{group_openid}/members", "获取群成员列表"),
            new Endpoint(HttpMethod.GET, "/v2/groups/{group_openid}/members/{member_openid}", "获取群成员信息"),
            new Endpoint(HttpMethod.POST, "/v2/groups/{group_openid}/messages", "发送群聊消息"),
            new Endpoint(HttpMethod.DELETE, "/v2/groups/{group_openid}/messages/{message_id}", "撤回群聊消息"),
            new Endpoint(HttpMethod.GET, "/v2/groups/{group_openid}/restrict_chat_setting", "查询群禁言状态"),
            new Endpoint(HttpMethod.POST, "/v2/groups/{group_openid}/restrict_chat_setting", "设置群成员禁言"),
            new Endpoint(HttpMethod.GET, "/v2/groups/join_approval_strategy", "查询入群自动审批策略列表"),
            new Endpoint(HttpMethod.POST, "/v2/groups/join_approval_strategy", "创建入群自动审批策略"),
            new Endpoint(HttpMethod.DELETE, "/v2/groups/join_approval_strategy/{strategy_id}", "删除入群自动审批策略"),
            new Endpoint(HttpMethod.PATCH, "/v2/groups/join_approval_strategy/{strategy_id}", "修改入群自动审批策略"),
            new Endpoint(HttpMethod.POST, "/v2/groups/join_approval_strategy/{strategy_id}/execute", "执行入群自动审批策略"),
            new Endpoint(HttpMethod.POST, "/v2/groups/join_approval_strategy/{strategy_id}/whitelist_users", "修改入群自动审批策略的白名单号码"),
            new Endpoint(HttpMethod.GET, "/v2/menu", "查询全局自定义菜单"),
            new Endpoint(HttpMethod.PUT, "/v2/menu", "修改全局自定义菜单"),
            new Endpoint(HttpMethod.GET, "/v2/panels", "查询指令面板列表"),
            new Endpoint(HttpMethod.POST, "/v2/panels", "创建指令面板"),
            new Endpoint(HttpMethod.DELETE, "/v2/panels/{panel_id}", "删除指令面板"),
            new Endpoint(HttpMethod.GET, "/v2/panels/{panel_id}", "查询指令面板详情"),
            new Endpoint(HttpMethod.PUT, "/v2/panels/{panel_id}", "修改指令面板"),
            new Endpoint(HttpMethod.PUT, "/v2/panels/{panel_id}/target", "修改指令面板关联对象"),
            new Endpoint(HttpMethod.POST, "/v2/users/{user_id}/upload_part_finish", "单聊分片上传完成"),
            new Endpoint(HttpMethod.POST, "/v2/users/{user_id}/upload_prepare", "单聊富媒体预上传"),
            new Endpoint(HttpMethod.POST, "/v2/users/{user_openid}/files", "单聊富媒体上传"),
            new Endpoint(HttpMethod.GET, "/v2/users/{user_openid}/info", "获取用户基本信息"),
            new Endpoint(HttpMethod.POST, "/v2/users/{user_openid}/messages", "发送单聊消息"),
            new Endpoint(HttpMethod.DELETE, "/v2/users/{user_openid}/messages/{message_id}", "撤回单聊消息"),
            new Endpoint(HttpMethod.POST, "/v2/users/{user_openid}/stream_messages", "流式发送单聊消息")
    );
}
