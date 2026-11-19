package cc.starcube.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ForumThread(@JsonProperty("guild_id") String guildId,
                          @JsonProperty("channel_id") String channelId,
                          @JsonProperty("author_id") String authorId,
                          @JsonProperty("thread_info") ThreadInfo threadInfo) {
    public record ThreadInfo(@JsonProperty("thread_id") String threadId, String title,
                             String content, @JsonProperty("date_time") String dateTime) { }
    public record Page(List<ForumThread> threads, @JsonProperty("is_finish") Integer isFinish) { }
    public record Detail(ForumThread thread) { }
    public record PublishResult(@JsonProperty("task_id") String taskId,
                                @JsonProperty("create_time") String createTime) { }
}
