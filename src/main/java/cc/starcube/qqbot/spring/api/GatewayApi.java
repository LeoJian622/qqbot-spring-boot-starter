package cc.starcube.qqbot.spring.api;

import cc.starcube.qqbot.spring.QQBotClient;
import cc.starcube.qqbot.spring.model.GatewayInfo;

public class GatewayApi {
    private final QQBotClient client;
    public GatewayApi(QQBotClient client) { this.client = client; }
    public GatewayInfo getGateway() { return client.get("/gateway", GatewayInfo.class); }
    public GatewayInfo getGatewayBot() { return client.get("/gateway/bot", GatewayInfo.class); }
}
