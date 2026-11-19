package io.github.qqbot.spring.api;

import io.github.qqbot.spring.QQBotClient;
import io.github.qqbot.spring.model.*;
import io.github.qqbot.spring.model.request.*;
import org.springframework.web.util.UriComponentsBuilder;

public class MessagesApi {
    private final QQBotClient client;
    public MessagesApi(QQBotClient client) { this.client = client; }

    public Dms createDirectMessageSession(CreateDmsRequest request) {
        return client.post("/users/@me/dms", request, Dms.class);
    }
    public Message sendDirectMessage(String guildId, SendDirectMessageRequest request) {
        return client.post("/dms/" + ApiSupport.s(guildId) + "/messages", request, Message.class);
    }
    public MessageResponse sendC2cMessage(String openId, SendC2cMessageRequest request) {
        return client.post("/v2/users/" + ApiSupport.s(openId) + "/messages", request, MessageResponse.class);
    }
    public MessageResponse sendGroupMessage(String groupOpenId, SendGroupMessageRequest request) {
        return client.post("/v2/groups/" + ApiSupport.s(groupOpenId) + "/messages", request, MessageResponse.class);
    }
    public Message sendChannelMessage(String channelId, SendChannelMessageRequest request) {
        return client.post("/channels/" + ApiSupport.s(channelId) + "/messages", request, Message.class);
    }

    public MediaUploadResponse uploadC2cMedia(String openId, UploadC2cMediaRequest request) {
        return client.post("/v2/users/" + ApiSupport.s(openId) + "/files", request, MediaUploadResponse.class);
    }
    public MediaUploadResponse uploadGroupMedia(String groupOpenId, UploadGroupMediaRequest request) {
        return client.post("/v2/groups/" + ApiSupport.s(groupOpenId) + "/files", request, MediaUploadResponse.class);
    }

    public void recallC2cMessage(String openId, String messageId) {
        client.deleteVoid("/v2/users/" + ApiSupport.s(openId) + "/messages/" + ApiSupport.s(messageId));
    }
    public void recallGroupMessage(String groupOpenId, String messageId) {
        client.deleteVoid("/v2/groups/" + ApiSupport.s(groupOpenId) + "/messages/" + ApiSupport.s(messageId));
    }
    public void recallChannelMessage(String channelId, String messageId, Boolean hideTip) {
        UriComponentsBuilder uri = ApiSupport.path("/channels/" + ApiSupport.s(channelId) + "/messages/" + ApiSupport.s(messageId));
        ApiSupport.query(uri, "hidetip", hideTip);
        client.deleteVoid(ApiSupport.build(uri));
    }
    public void recallDirectMessage(String guildId, String messageId, Boolean hideTip) {
        UriComponentsBuilder uri = ApiSupport.path("/dms/" + ApiSupport.s(guildId) + "/messages/" + ApiSupport.s(messageId));
        ApiSupport.query(uri, "hidetip", hideTip);
        client.deleteVoid(ApiSupport.build(uri));
    }

    public void addReaction(String channelId, String messageId, Integer emojiType, String emojiId) {
        client.putVoid(reactionPath(channelId, messageId, emojiType, emojiId), null);
    }
    public void deleteReaction(String channelId, String messageId, Integer emojiType, String emojiId) {
        client.deleteVoid(reactionPath(channelId, messageId, emojiType, emojiId));
    }
    public ReactionUsersResponse getReactionUsers(String channelId, String messageId, Integer emojiType,
                                                   String emojiId, GetReactionUsersRequest request) {
        UriComponentsBuilder uri = ApiSupport.path(reactionPath(channelId, messageId, emojiType, emojiId));
        if (request != null) {
            ApiSupport.query(uri, "cookie", request.cookie());
            ApiSupport.query(uri, "limit", request.limit());
        }
        return client.get(ApiSupport.build(uri), ReactionUsersResponse.class);
    }
    public void respondToInteraction(String interactionId, InteractionResponseRequest request) {
        client.putVoid("/interactions/" + ApiSupport.s(interactionId), request);
    }

    private String reactionPath(String channelId, String messageId, Integer emojiType, String emojiId) {
        return "/channels/" + ApiSupport.s(channelId) + "/messages/" + ApiSupport.s(messageId)
                + "/reactions/" + emojiType + "/" + ApiSupport.s(emojiId);
    }
}
