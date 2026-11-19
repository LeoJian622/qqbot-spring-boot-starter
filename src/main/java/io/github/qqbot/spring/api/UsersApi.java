package io.github.qqbot.spring.api;

import io.github.qqbot.spring.QQBotClient;
import io.github.qqbot.spring.model.Guild;
import io.github.qqbot.spring.model.User;
import io.github.qqbot.spring.model.request.GetCurrentUserGuildsRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

public class UsersApi {
    private static final ParameterizedTypeReference<List<Guild>> GUILD_LIST = new ParameterizedTypeReference<>() { };
    private final QQBotClient client;
    public UsersApi(QQBotClient client) { this.client = client; }
    public User getCurrentUser() { return client.get("/users/@me", User.class); }
    public List<Guild> getCurrentUserGuilds(GetCurrentUserGuildsRequest request) {
        UriComponentsBuilder uri = ApiSupport.path("/users/@me/guilds");
        if (request != null) {
            ApiSupport.query(uri, "before", request.before());
            ApiSupport.query(uri, "after", request.after());
            ApiSupport.query(uri, "limit", request.limit());
        }
        return client.get(ApiSupport.build(uri), GUILD_LIST);
    }
}
