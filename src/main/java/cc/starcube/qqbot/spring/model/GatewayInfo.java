package cc.starcube.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GatewayInfo(String url, Integer shards,
                          @JsonProperty("session_start_limit") SessionStartLimit sessionStartLimit) {
    public record SessionStartLimit(Integer total, Integer remaining,
                                    @JsonProperty("reset_after") Long resetAfter,
                                    @JsonProperty("max_concurrency") Integer maxConcurrency) { }
}
