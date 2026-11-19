package io.github.qqbot.spring.api;

/** Convenient aggregate for applications that prefer one injected SDK entry point. */
public record QQBotApi(GatewayApi gateway, UsersApi users, GuildsApi guilds,
                       ChannelsApi channels, MessagesApi messages, QQBotV2Api v2) { }
