# API 覆盖清单

> 来源：QQ 机器人官方 API v2 在线文档。目录快照核对日期：2026-11-19。
> 旧版 `tencent-connect/bot-docs` 仓库不作为本清单的接口依据。

当前公开目录共 **48 个 HTTP API**。每个 API 在 `model.v2` 下都有独立操作模型，
并由 `QQBotV2Api` 提供一一对应的强类型方法。

| 分类 | 方法 | 路径 | SDK 操作模型 |
|---|---|---|---|
| 频道与子频道 | `DELETE` | `/channels/{channel_id}` | `DeleteChannel` |
| 频道与子频道 | `GET` | `/channels/{channel_id}` | `GetChannel` |
| 频道与子频道 | `PATCH` | `/channels/{channel_id}` | `UpdateChannel` |
| Gateway | `GET` | `/gateway` | `GetGateway` |
| 频道与子频道 | `GET` | `/guilds/{guild_id}` | `GetGuild` |
| 频道与子频道 | `GET` | `/guilds/{guild_id}/channels` | `ListGuildChannels` |
| 频道与子频道 | `POST` | `/guilds/{guild_id}/channels` | `CreateGuildChannel` |
| 互动事件 | `PUT` | `/interactions/{interaction_id}` | `RespondInteraction` |
| 机器人与频道 | `GET` | `/users/@me` | `GetCurrentUser` |
| 机器人与频道 | `GET` | `/users/@me/guilds` | `ListCurrentUserGuilds` |
| 其他 | `POST` | `/v2/generate_url_link` | `GenerateUrlLink` |
| 富媒体 | `POST` | `/v2/groups/{group_id}/upload_part_finish` | `FinishGroupUploadPart` |
| 富媒体 | `POST` | `/v2/groups/{group_id}/upload_prepare` | `PrepareGroupUpload` |
| 群与群成员 | `POST` | `/v2/groups/{group_openid}/approval_join_request/{member_openid}` | `ApproveGroupJoinRequest` |
| 群与群成员 | `POST` | `/v2/groups/{group_openid}/batch_remove_members` | `BatchRemoveGroupMembers` |
| 群与群成员 | `GET` | `/v2/groups/{group_openid}/bot_state` | `GetGroupBotState` |
| 富媒体 | `POST` | `/v2/groups/{group_openid}/files` | `UploadGroupMedia` |
| 群与群成员 | `GET` | `/v2/groups/{group_openid}/info` | `GetGroupInfo` |
| 群与群成员 | `GET` | `/v2/groups/{group_openid}/join_request_list` | `ListGroupJoinRequests` |
| 群与群成员 | `GET` | `/v2/groups/{group_openid}/member_blacklist` | `ListGroupBlacklist` |
| 群与群成员 | `POST` | `/v2/groups/{group_openid}/member_blacklist` | `UpdateGroupBlacklist` |
| 群与群成员 | `GET` | `/v2/groups/{group_openid}/members` | `ListGroupMembers` |
| 群与群成员 | `GET` | `/v2/groups/{group_openid}/members/{member_openid}` | `GetGroupMember` |
| 群与群成员 | `POST` | `/v2/groups/{group_openid}/messages` | `SendGroupMessage` |
| 群与群成员 | `DELETE` | `/v2/groups/{group_openid}/messages/{message_id}` | `RecallGroupMessage` |
| 群与群成员 | `GET` | `/v2/groups/{group_openid}/restrict_chat_setting` | `GetGroupMuteSettings` |
| 群与群成员 | `POST` | `/v2/groups/{group_openid}/restrict_chat_setting` | `SetGroupMuteSettings` |
| 入群自动审批 | `GET` | `/v2/groups/join_approval_strategy` | `ListJoinApprovalStrategies` |
| 入群自动审批 | `POST` | `/v2/groups/join_approval_strategy` | `CreateJoinApprovalStrategy` |
| 入群自动审批 | `DELETE` | `/v2/groups/join_approval_strategy/{strategy_id}` | `DeleteJoinApprovalStrategy` |
| 入群自动审批 | `PATCH` | `/v2/groups/join_approval_strategy/{strategy_id}` | `UpdateJoinApprovalStrategy` |
| 入群自动审批 | `POST` | `/v2/groups/join_approval_strategy/{strategy_id}/execute` | `ExecuteJoinApprovalStrategy` |
| 入群自动审批 | `POST` | `/v2/groups/join_approval_strategy/{strategy_id}/whitelist_users` | `UpdateJoinApprovalWhitelist` |
| 自定义菜单 | `GET` | `/v2/menu` | `GetGlobalMenu` |
| 自定义菜单 | `PUT` | `/v2/menu` | `UpdateGlobalMenu` |
| 指令面板 | `GET` | `/v2/panels` | `ListCommandPanels` |
| 指令面板 | `POST` | `/v2/panels` | `CreateCommandPanel` |
| 指令面板 | `DELETE` | `/v2/panels/{panel_id}` | `DeleteCommandPanel` |
| 指令面板 | `GET` | `/v2/panels/{panel_id}` | `GetCommandPanel` |
| 指令面板 | `PUT` | `/v2/panels/{panel_id}` | `UpdateCommandPanel` |
| 指令面板 | `PUT` | `/v2/panels/{panel_id}/target` | `UpdateCommandPanelTargets` |
| 富媒体 | `POST` | `/v2/users/{user_id}/upload_part_finish` | `FinishUserUploadPart` |
| 富媒体 | `POST` | `/v2/users/{user_id}/upload_prepare` | `PrepareUserUpload` |
| 富媒体 | `POST` | `/v2/users/{user_openid}/files` | `UploadUserMedia` |
| 单聊与用户 | `GET` | `/v2/users/{user_openid}/info` | `GetUserInfo` |
| 单聊与用户 | `POST` | `/v2/users/{user_openid}/messages` | `SendUserMessage` |
| 单聊与用户 | `DELETE` | `/v2/users/{user_openid}/messages/{message_id}` | `RecallUserMessage` |
| 单聊与用户 | `POST` | `/v2/users/{user_openid}/stream_messages` | `SendUserStreamMessage` |

现行目录之外的旧频道成员、身份组、权限、公告、精华消息、日程、音频、论坛、
频道消息和频道私信封装仍暂时保留在原有 API 类中用于源码兼容，但不计入上述 48 项。
新增业务应优先使用 `QQBotV2Api`。
