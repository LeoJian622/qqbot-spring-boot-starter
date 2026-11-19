package cc.starcube.qqbot.spring.model;

import java.util.List;

public record MessageEmbed(String title, String prompt, Thumbnail thumbnail, List<Field> fields) {
    public record Thumbnail(String url) { }
    public record Field(String name) { }
}
