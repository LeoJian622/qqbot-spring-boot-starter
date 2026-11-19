package cc.starcube.qqbot.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import cc.starcube.qqbot.spring.api.*;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@AutoConfiguration
@EnableConfigurationProperties(QQBotProperties.class)
@ConditionalOnProperty(prefix = "qqbot", name = {"app-id", "client-secret"})
public class QQBotAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    QQBotTokenProvider qqBotTokenProvider(QQBotProperties properties, ObjectMapper mapper, RestClient.Builder builder) {
        return new QQBotTokenProvider(properties, mapper, builder);
    }

    @Bean
    @ConditionalOnMissingBean
    QQBotClient qqBotClient(QQBotProperties properties, QQBotTokenProvider tokenProvider,
                            RestClient.Builder builder) {
        return new QQBotClient(properties, tokenProvider, builder);
    }


    @Bean
    @ConditionalOnMissingBean
    GatewayApi qqBotGatewayApi(QQBotClient client) { return new GatewayApi(client); }

    @Bean
    @ConditionalOnMissingBean
    UsersApi qqBotUsersApi(QQBotClient client) { return new UsersApi(client); }

    @Bean
    @ConditionalOnMissingBean
    GuildsApi qqBotGuildsApi(QQBotClient client) { return new GuildsApi(client); }

    @Bean
    @ConditionalOnMissingBean
    ChannelsApi qqBotChannelsApi(QQBotClient client) { return new ChannelsApi(client); }

    @Bean
    @ConditionalOnMissingBean
    MessagesApi qqBotMessagesApi(QQBotClient client) { return new MessagesApi(client); }

    @Bean
    @ConditionalOnMissingBean
    QQBotV2Api qqBotV2Api(QQBotClient client) { return new QQBotV2Api(client); }

    @Bean
    @ConditionalOnMissingBean
    QQBotApi qqBotApi(GatewayApi gateway, UsersApi users, GuildsApi guilds,
                      ChannelsApi channels, MessagesApi messages, QQBotV2Api v2) {
        return new QQBotApi(gateway, users, guilds, channels, messages, v2);
    }

    @Bean
    @ConditionalOnMissingBean
    QQBotEventHandler qqBotEventHandler() {
        return (eventType, data, envelope) -> { };
    }

    @Bean
    @ConditionalOnBean(QQBotClient.class)
    @ConditionalOnProperty(prefix = "qqbot.gateway", name = "enabled", havingValue = "true")
    @ConditionalOnMissingBean
    QQBotGateway qqBotGateway(QQBotProperties properties, QQBotClient client,
                              QQBotTokenProvider tokenProvider, QQBotEventHandler handler,
                              ObjectMapper mapper) {
        return new QQBotGateway(properties, client, tokenProvider, handler, mapper);
    }
}
