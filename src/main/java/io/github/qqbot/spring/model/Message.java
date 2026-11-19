package io.github.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;

public record Message(String id, @JsonProperty("channel_id") String channelId,
                      @JsonProperty("guild_id") String guildId, String content,
                      String timestamp, @JsonProperty("edited_timestamp") String editedTimestamp, User author, Member member,
                      List<Attachment> attachments, JsonNode embeds, JsonNode ark,
                      JsonNode markdown, JsonNode keyboard, Integer seq,
                      @JsonProperty("seq_in_channel") String seqInChannel) {
    public record Attachment(String url, String filename, Integer height, Integer width,
                             @JsonProperty("content_type") String contentType, Long size) { }
}
