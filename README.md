# QQ Bot Spring Boot SDK

面向 Java 17 / Spring Boot 3 的 QQ 机器人 API v2 SDK。

本版本仅以 [QQ 机器人官方 API v2 在线文档](https://bot.q.qq.com/wiki/develop/api-v2/) 为接口依据，
不再使用已经过期的 `tencent-connect/bot-docs` 仓库生成接口。

当前官网目录包含 **48 个 HTTP API**，SDK 提供：

- 每个 API 独立的操作模型、请求体、查询参数和响应模型
- `QQBotV2Api` 中与 48 个接口一一对应的强类型方法
- Access Token 自动获取、缓存和到期刷新
- Gateway WebSocket 心跳、断线重连和 Session Resume
- 通用 `QQBotClient`，用于平台新增接口的临时接入
- 旧版频道能力兼容封装，避免已有项目直接编译失败

完整清单见 [API_COVERAGE.md](docs/API_COVERAGE.md)。

## 安装

正式版发布在 GitHub Packages。先在 Maven `settings.xml` 中配置可读取 GitHub Packages
的凭据，再添加仓库：

```xml
<repository>
  <id>github</id>
  <url>https://maven.pkg.github.com/LeoJian622/qqbot-spring-boot-starter</url>
</repository>
```

然后在 Spring Boot 项目中添加依赖：

```xml
<dependency>
  <groupId>cc.starcube.qqbot</groupId>
  <artifactId>qqbot-spring-boot-starter</artifactId>
  <version>0.1.1</version>
</dependency>
```

也可以从 GitHub Release 下载主包、源码包和 Javadoc 包。本地开发源码时仍可执行
`mvn install` 安装到本机 Maven 仓库。

## 配置

不要把 AppSecret 写进代码或提交到版本库。使用环境变量注入：

```yaml
qqbot:
  app-id: ${QQBOT_APP_ID}
  client-secret: ${QQBOT_CLIENT_SECRET}
  api-base-url: https://api.bot.qq.com
  token-url: https://api.bot.qq.com/app/getAppAccessToken
  gateway:
    enabled: false
    intents: 0
    shard-id: 0
    shard-count: 1
```

PowerShell 临时设置：

```powershell
$env:QQBOT_APP_ID = "your-app-id"
$env:QQBOT_CLIENT_SECRET = "your-app-secret"
```

## 使用现行 API

推荐注入统一入口 `QQBotApi`，通过 `v2()` 使用官网当前接口：

```java
@Service
public class BotService {
    private final QQBotApi qqBot;

    public BotService(QQBotApi qqBot) {
        this.qqBot = qqBot;
    }

    public SendGroupMessage.Response replyToGroup(
            String groupOpenId, String incomingMessageId) {
        SendGroupMessage.Body body = new SendGroupMessage.Body(
                0,
                "收到",
                null,
                null,
                incomingMessageId,
                null,
                1,
                null,
                null,
                null
        );
        return qqBot.v2().sendGroupMessage(
                new SendGroupMessage.Request(groupOpenId, body)
        );
    }
}
```

查询群成员：

```java
ListGroupMembers.Response page = qqBot.v2().listGroupMembers(
        new ListGroupMembers.Request(
                groupOpenId,
                new ListGroupMembers.Query(cursor)
        )
);
```

查询用户资料：

```java
GetUserInfo.Response user = qqBot.v2().getUserInfo(
        new GetUserInfo.Request(userOpenId)
);
```

每个接口都有独立模型，例如：

- `SendGroupMessage.Request`、`SendGroupMessage.Body`、`SendGroupMessage.Response`
- `CreateCommandPanel.Request`、`CreateCommandPanel.RequestPanel`、`CreateCommandPanel.Response`
- `PrepareGroupUpload.Request`、`PrepareGroupUpload.Body`、`PrepareGroupUpload.Response`

模型位于 `cc.starcube.qqbot.spring.model.v2`，统一门面位于
`cc.starcube.qqbot.spring.api.QQBotV2Api`。

## WebSocket 事件

开启 Gateway：

```yaml
qqbot:
  gateway:
    enabled: true
    intents: 1073741824
```

注册事件处理器：

```java
@Component
public class BotEvents implements QQBotEventHandler {
    @Override
    public void onEvent(String eventType, JsonNode data, JsonNode envelope) {
        if ("GROUP_AT_MESSAGE_CREATE".equals(eventType)) {
            // 处理群聊 @ 消息
        }
    }
}
```

Gateway 组件负责 `HELLO`、`IDENTIFY`、心跳、断线重连和 Session Resume。
事件载荷保留为 `JsonNode`，避免平台增加事件字段时破坏兼容性。

## 兼容说明

- 官网当前 OpenAPI 统一地址为 `https://api.bot.qq.com`。
- `QQBotEndpoints.ALL` 只列出官网当前 48 个 HTTP API。
- 原有 `GatewayApi`、`UsersApi`、`GuildsApi`、`ChannelsApi` 和 `MessagesApi`
  暂时保留，供已经依赖旧封装的项目迁移使用。
- 旧兼容封装中存在现行官网目录已经不再列出的接口；新业务应使用 `QQBotV2Api`。
- `QQBotClient` 保留通用 JSON 方法，平台发布新接口后可先直接调用，再等待类型模型更新。

## 验证

```shell
mvn test
mvn package
```

测试覆盖 Token 缓存、鉴权、48 项接口目录去重、默认域名、URI 编码、查询参数、
独立请求模型序列化和响应模型反序列化。

### 只读真机测试

`QQBotLiveTest` 仅调用 Access Token、`/users/@me` 和 `/gateway`；
`QQBotGatewayLiveTest` 会建立官方 WebSocket 连接并等待 `READY`。两者都不会发送消息或修改配置，
未提供环境变量时会自动跳过：

```powershell
$env:QQBOT_APP_ID = "your-app-id"
$env:QQBOT_CLIENT_SECRET = "your-app-secret"
mvn "-Dtest=QQBotLiveTest,QQBotGatewayLiveTest" test
Remove-Item Env:QQBOT_APP_ID
Remove-Item Env:QQBOT_CLIENT_SECRET
```
