package io.github.qqbot.spring.api;

import io.github.qqbot.spring.QQBotClient;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;

final class ApiSupport {
    private ApiSupport() { }
    static String s(String value) { return QQBotClient.segment(value); }
    static UriComponentsBuilder path(String path) { return UriComponentsBuilder.fromPath(path); }
    static void query(UriComponentsBuilder builder, String name, Object value) {
        if (value != null) {
            builder.queryParam(name, UriUtils.encodeQueryParam(String.valueOf(value), StandardCharsets.UTF_8));
        }
    }
    static String build(UriComponentsBuilder builder) {
        return builder.build(true).toUriString();
    }
}
