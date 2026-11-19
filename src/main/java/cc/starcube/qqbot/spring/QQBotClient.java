package cc.starcube.qqbot.spring;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.util.UriUtils;

import java.net.URI;
import java.nio.charset.StandardCharsets;

/** Authenticated low-level OpenAPI client used by the typed API facades. */
public class QQBotClient {
    private final QQBotTokenProvider tokenProvider;
    private final RestClient restClient;
    private final String apiBaseUrl;

    public QQBotClient(QQBotProperties properties, QQBotTokenProvider tokenProvider,
                       RestClient.Builder builder) {
        this.tokenProvider = tokenProvider;
        this.apiBaseUrl = stripTrailingSlash(properties.getApiBaseUrl());
        this.restClient = builder.build();
    }

    public JsonNode get(String path) { return get(path, JsonNode.class); }
    public JsonNode post(String path, Object body) { return post(path, body, JsonNode.class); }
    public JsonNode put(String path, Object body) { return put(path, body, JsonNode.class); }
    public JsonNode patch(String path, Object body) { return patch(path, body, JsonNode.class); }
    public JsonNode delete(String path) { return delete(path, JsonNode.class); }

    public <T> T get(String path, Class<T> responseType) {
        return exchange(HttpMethod.GET, path, null, responseType);
    }

    public <T> T get(String path, ParameterizedTypeReference<T> responseType) {
        return exchange(HttpMethod.GET, path, null, responseType);
    }

    public <T> T post(String path, Object body, Class<T> responseType) {
        return exchange(HttpMethod.POST, path, body, responseType);
    }

    public <T> T put(String path, Object body, Class<T> responseType) {
        return exchange(HttpMethod.PUT, path, body, responseType);
    }

    public <T> T patch(String path, Object body, Class<T> responseType) {
        return exchange(HttpMethod.PATCH, path, body, responseType);
    }

    public <T> T delete(String path, Class<T> responseType) {
        return exchange(HttpMethod.DELETE, path, null, responseType);
    }

    public void postVoid(String path, Object body) { exchangeVoid(HttpMethod.POST, path, body); }
    public void putVoid(String path, Object body) { exchangeVoid(HttpMethod.PUT, path, body); }
    public void patchVoid(String path, Object body) { exchangeVoid(HttpMethod.PATCH, path, body); }
    public void deleteVoid(String path) { exchangeVoid(HttpMethod.DELETE, path, null); }
    public void deleteVoid(String path, Object body) { exchangeVoid(HttpMethod.DELETE, path, body); }

    /** Backward-compatible raw group message call. */
    public JsonNode sendGroupMessage(String groupOpenId, Object body) {
        return post("/v2/groups/" + segment(groupOpenId) + "/messages", body);
    }

    /** Backward-compatible raw C2C message call. */
    public JsonNode sendC2cMessage(String userOpenId, Object body) {
        return post("/v2/users/" + segment(userOpenId) + "/messages", body);
    }

    public JsonNode getGateway() { return get("/gateway"); }

    public static String segment(String value) {
        return UriUtils.encodePathSegment(value, StandardCharsets.UTF_8);
    }

    private <T> T exchange(HttpMethod method, String path, Object body, Class<T> responseType) {
        try {
            RestClient.RequestBodySpec request = request(method, path, body);
            return request.retrieve().body(responseType);
        } catch (RestClientResponseException e) {
            throw apiException(method, path, e);
        }
    }

    private <T> T exchange(HttpMethod method, String path, Object body,
                           ParameterizedTypeReference<T> responseType) {
        try {
            RestClient.RequestBodySpec request = request(method, path, body);
            return request.retrieve().body(responseType);
        } catch (RestClientResponseException e) {
            throw apiException(method, path, e);
        }
    }

    private void exchangeVoid(HttpMethod method, String path, Object body) {
        try {
            request(method, path, body).retrieve().toBodilessEntity();
        } catch (RestClientResponseException e) {
            throw apiException(method, path, e);
        }
    }

    private RestClient.RequestBodySpec request(HttpMethod method, String path, Object body) {
        RestClient.RequestBodySpec request = restClient.method(method).uri(URI.create(apiBaseUrl + path))
                .header("Authorization", "QQBot " + tokenProvider.getAccessToken());
        if (body != null) request.contentType(MediaType.APPLICATION_JSON).body(body);
        return request;
    }

    private QQBotApiException apiException(HttpMethod method, String path, RestClientResponseException e) {
        return new QQBotApiException("QQ Bot API request failed: " + method + " " + path,
                e.getStatusCode().value(), e.getResponseBodyAsString(), e);
    }

    private static String stripTrailingSlash(String value) {
        return value.endsWith("/") ? value.substring(0, value.length() - 1) : value;
    }
}
