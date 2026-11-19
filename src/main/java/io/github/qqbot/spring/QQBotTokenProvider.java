package io.github.qqbot.spring;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.time.Instant;

/** Fetches and safely reuses QQ Bot app access tokens. */
public class QQBotTokenProvider {
    private final QQBotProperties properties;
    private final ObjectMapper objectMapper;
    private final RestClient restClient;
    private volatile CachedToken cachedToken;

    public QQBotTokenProvider(QQBotProperties properties, ObjectMapper objectMapper, RestClient.Builder builder) {
        this.properties = properties;
        this.objectMapper = objectMapper;
        this.restClient = builder.build();
    }

    public String getAccessToken() {
        CachedToken current = cachedToken;
        if (current != null && Instant.now().isBefore(current.refreshAt())) return current.value();
        synchronized (this) {
            current = cachedToken;
            if (current != null && Instant.now().isBefore(current.refreshAt())) return current.value();
            return refreshToken();
        }
    }

    private String refreshToken() {
        ObjectNode request = objectMapper.createObjectNode()
                .put("appId", properties.getAppId())
                .put("clientSecret", properties.getClientSecret());
        try {
            JsonNode response = restClient.post().uri(properties.getTokenUrl())
                    .contentType(MediaType.APPLICATION_JSON).body(request)
                    .retrieve().body(JsonNode.class);
            if (response == null || response.path("access_token").asText().isBlank()) {
                throw new QQBotApiException("QQ Bot token response did not contain access_token", 0,
                        response == null ? "" : response.toString(), null);
            }
            long expiresIn = response.path("expires_in").asLong(7200);
            long safetyWindow = Math.min(60, Math.max(1, expiresIn / 10));
            Instant refreshAt = Instant.now().plusSeconds(Math.max(1, expiresIn - safetyWindow));
            cachedToken = new CachedToken(response.path("access_token").asText(), refreshAt);
            return cachedToken.value();
        } catch (RestClientResponseException e) {
            throw new QQBotApiException("QQ Bot token request failed", e.getStatusCode().value(),
                    e.getResponseBodyAsString(), e);
        }
    }

    private record CachedToken(String value, Instant refreshAt) { }
}
