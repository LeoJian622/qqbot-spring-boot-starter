package io.github.qqbot.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "qqbot")
public class QQBotProperties {
    private String appId;
    private String clientSecret;
    private String tokenUrl = "https://api.bot.qq.com/app/getAppAccessToken";
    private String apiBaseUrl = "https://api.bot.qq.com";
    private Gateway gateway = new Gateway();

    public String getAppId() { return appId; }
    public void setAppId(String appId) { this.appId = appId; }
    public String getClientSecret() { return clientSecret; }
    public void setClientSecret(String clientSecret) { this.clientSecret = clientSecret; }
    public String getTokenUrl() { return tokenUrl; }
    public void setTokenUrl(String tokenUrl) { this.tokenUrl = tokenUrl; }
    public Gateway getGateway() { return gateway; }
    public void setGateway(Gateway gateway) { this.gateway = gateway; }

    public String getApiBaseUrl() { return apiBaseUrl; }
    public void setApiBaseUrl(String apiBaseUrl) { this.apiBaseUrl = apiBaseUrl; }

    public static class Gateway {
        private boolean enabled;
        private int intents;
        private int shardId;
        private int shardCount = 1;

        public boolean isEnabled() { return enabled; }
        public void setEnabled(boolean enabled) { this.enabled = enabled; }
        public int getIntents() { return intents; }
        public void setIntents(int intents) { this.intents = intents; }
        public int getShardId() { return shardId; }
        public void setShardId(int shardId) { this.shardId = shardId; }
        public int getShardCount() { return shardCount; }
        public void setShardCount(int shardCount) { this.shardCount = shardCount; }
    }
}
