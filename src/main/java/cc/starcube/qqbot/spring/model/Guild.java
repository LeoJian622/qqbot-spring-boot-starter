package cc.starcube.qqbot.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Guild(String id, String name, String icon,
                    @JsonProperty("owner_id") String ownerId, Boolean owner,
                    @JsonProperty("member_count") Integer memberCount,
                    @JsonProperty("max_members") Integer maxMembers,
                    String description, @JsonProperty("joined_at") String joinedAt) { }
