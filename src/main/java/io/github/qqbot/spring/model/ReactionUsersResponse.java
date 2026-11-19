package io.github.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ReactionUsersResponse(List<User> users, String cookie,
                                    @JsonProperty("is_end") Boolean isEnd) { }
