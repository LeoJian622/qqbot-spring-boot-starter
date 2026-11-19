package io.github.qqbot.spring.api;

import io.github.qqbot.spring.QQBotClient;
import io.github.qqbot.spring.model.GatewayInfo;

public class GatewayApi {
    private final QQBotClient client;
    public GatewayApi(QQBotClient client) { this.client = client; }
    public GatewayInfo getGateway() { return client.get("/gateway", GatewayInfo.class); }
    public GatewayInfo getGatewayBot() { return client.get("/gateway/bot", GatewayInfo.class); }
}
