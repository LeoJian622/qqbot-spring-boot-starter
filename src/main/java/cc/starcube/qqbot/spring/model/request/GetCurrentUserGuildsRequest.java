package cc.starcube.qqbot.spring.model.request;
public record GetCurrentUserGuildsRequest(String before, String after, Integer limit) { }
