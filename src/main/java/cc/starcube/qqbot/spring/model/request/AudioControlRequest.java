package cc.starcube.qqbot.spring.model.request;
import com.fasterxml.jackson.annotation.JsonProperty;
public record AudioControlRequest(@JsonProperty("audio_url") String audioUrl, String text, Integer status) { }
