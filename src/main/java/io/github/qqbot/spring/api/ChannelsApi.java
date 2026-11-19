package io.github.qqbot.spring.api;

import io.github.qqbot.spring.QQBotClient;
import io.github.qqbot.spring.model.*;
import io.github.qqbot.spring.model.request.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

public class ChannelsApi {
    private static final ParameterizedTypeReference<List<Channel>> CHANNEL_LIST = new ParameterizedTypeReference<>() { };
    private static final ParameterizedTypeReference<List<Schedule>> SCHEDULE_LIST = new ParameterizedTypeReference<>() { };
    private final QQBotClient client;
    public ChannelsApi(QQBotClient client) { this.client = client; }

    public List<Channel> getGuildChannels(String guildId) {
        return client.get("/guilds/" + ApiSupport.s(guildId) + "/channels", CHANNEL_LIST);
    }
    public Channel getChannel(String channelId) { return client.get("/channels/" + ApiSupport.s(channelId), Channel.class); }
    public Channel createChannel(String guildId, CreateChannelRequest request) {
        return client.post("/guilds/" + ApiSupport.s(guildId) + "/channels", request, Channel.class);
    }
    public Channel updateChannel(String channelId, UpdateChannelRequest request) {
        return client.patch("/channels/" + ApiSupport.s(channelId), request, Channel.class);
    }
    public void deleteChannel(String channelId) { client.deleteVoid("/channels/" + ApiSupport.s(channelId)); }
    public OnlineMemberCount getChannelOnlineMemberCount(String channelId) {
        return client.get("/channels/" + ApiSupport.s(channelId) + "/online_nums", OnlineMemberCount.class);
    }

    public ChannelPermissions getChannelMemberPermissions(String channelId, String userId) {
        return client.get("/channels/" + ApiSupport.s(channelId) + "/members/" + ApiSupport.s(userId) + "/permissions", ChannelPermissions.class);
    }
    public ChannelPermissions getChannelRolePermissions(String channelId, String roleId) {
        return client.get("/channels/" + ApiSupport.s(channelId) + "/roles/" + ApiSupport.s(roleId) + "/permissions", ChannelPermissions.class);
    }
    public void updateChannelMemberPermissions(String channelId, String userId, UpdateChannelPermissionsRequest request) {
        client.putVoid("/channels/" + ApiSupport.s(channelId) + "/members/" + ApiSupport.s(userId) + "/permissions", request);
    }
    public void updateChannelRolePermissions(String channelId, String roleId, UpdateChannelPermissionsRequest request) {
        client.putVoid("/channels/" + ApiSupport.s(channelId) + "/roles/" + ApiSupport.s(roleId) + "/permissions", request);
    }

    /** Deprecated by the platform, retained for complete API coverage. */
    @Deprecated
    public Announcement createChannelAnnouncement(String channelId, CreateChannelAnnouncementRequest request) {
        return client.post("/channels/" + ApiSupport.s(channelId) + "/announces", request, Announcement.class);
    }
    public void deleteChannelAnnouncement(String channelId, String messageId) {
        client.deleteVoid("/channels/" + ApiSupport.s(channelId) + "/announces/" + ApiSupport.s(messageId));
    }

    public PinsMessage getPinnedMessages(String channelId) {
        return client.get("/channels/" + ApiSupport.s(channelId) + "/pins", PinsMessage.class);
    }
    public PinsMessage addPinnedMessage(String channelId, String messageId) {
        return client.put("/channels/" + ApiSupport.s(channelId) + "/pins/" + ApiSupport.s(messageId), null, PinsMessage.class);
    }
    public void deletePinnedMessage(String channelId, String messageId) {
        client.deleteVoid("/channels/" + ApiSupport.s(channelId) + "/pins/" + ApiSupport.s(messageId));
    }

    public List<Schedule> getSchedules(String channelId, Long since) {
        UriComponentsBuilder uri = ApiSupport.path("/channels/" + ApiSupport.s(channelId) + "/schedules");
        ApiSupport.query(uri, "since", since);
        return client.get(ApiSupport.build(uri), SCHEDULE_LIST);
    }
    public Schedule getSchedule(String channelId, String scheduleId) {
        return client.get("/channels/" + ApiSupport.s(channelId) + "/schedules/" + ApiSupport.s(scheduleId), Schedule.class);
    }
    public Schedule createSchedule(String channelId, CreateScheduleRequest request) {
        return client.post("/channels/" + ApiSupport.s(channelId) + "/schedules", request, Schedule.class);
    }
    public Schedule updateSchedule(String channelId, String scheduleId, UpdateScheduleRequest request) {
        return client.patch("/channels/" + ApiSupport.s(channelId) + "/schedules/" + ApiSupport.s(scheduleId), request, Schedule.class);
    }
    public void deleteSchedule(String channelId, String scheduleId) {
        client.deleteVoid("/channels/" + ApiSupport.s(channelId) + "/schedules/" + ApiSupport.s(scheduleId));
    }

    public void controlAudio(String channelId, AudioControlRequest request) {
        client.postVoid("/channels/" + ApiSupport.s(channelId) + "/audio", request);
    }
    public void connectMicrophone(String channelId) { client.putVoid("/channels/" + ApiSupport.s(channelId) + "/mic", null); }
    public void disconnectMicrophone(String channelId) { client.deleteVoid("/channels/" + ApiSupport.s(channelId) + "/mic"); }

    public ForumThread.Page getThreads(String channelId) {
        return client.get("/channels/" + ApiSupport.s(channelId) + "/threads", ForumThread.Page.class);
    }
    public ForumThread.Detail getThread(String channelId, String threadId) {
        return client.get("/channels/" + ApiSupport.s(channelId) + "/threads/" + ApiSupport.s(threadId), ForumThread.Detail.class);
    }
    public ForumThread.PublishResult publishThread(String channelId, PublishThreadRequest request) {
        return client.put("/channels/" + ApiSupport.s(channelId) + "/threads", request, ForumThread.PublishResult.class);
    }
    public void deleteThread(String channelId, String threadId) {
        client.deleteVoid("/channels/" + ApiSupport.s(channelId) + "/threads/" + ApiSupport.s(threadId));
    }
}
