package org.awp.qq.bot.api;

import org.awp.qq.bot.client.QQBotConfiguration;
import org.awp.qq.bot.entity.message.Message;
import org.awp.qq.bot.entity.message.reply.Reply;
import org.awp.qq.bot.util.GsonUtil;

/**
 * 消息接口
 *
 * @author MovCloud
 *
 * @date 2023/12/24
 *
 * @since  1.0
 */
public final class MessageAPI {
    /**
     * 根据消息ID获取指定子频道内的消息
     * @param channelId     子频道ID
     * @param messageId     消息ID
     */
    public static Message getMessage(QQBotConfiguration configuration, String channelId, String messageId){
        String response = RequestAPI.botHttpGet(configuration,
                String.format("/channels/%s/messages/%s", channelId, messageId));
        if (response == null){
            return null;
        }
        return GsonUtil.GSON.fromJson(response, Message.class);
    }

    /**
     * 向指定的用户发送回复
     * @param openId        子频道ID
     * @param reply         回复消息对象
     */
    public static String sendToUser(QQBotConfiguration configuration, String openId, Reply reply){
        return RequestAPI.botHttpPost(configuration, String.format("/v2/users/%s/messages", openId), reply.getAsRequestBody());
    }

    /**
     * 向指定的群聊发送回复
     * @param groupOpenId   子频道ID
     * @param reply         回复消息对象
     */
    public static String sendToGroup(QQBotConfiguration configuration, String groupOpenId, Reply reply){
        return RequestAPI.botHttpPost(configuration, String.format("/v2/groups/%s/messages", groupOpenId), reply.getAsRequestBody());
    }

    /**
     * 向指定的文字子频道发送回复
     * @param channelId     子频道ID
     * @param reply         回复消息对象
     */
    public static String sendToChannel(QQBotConfiguration configuration, String channelId, Reply reply){
        return RequestAPI.botHttpPost(configuration, String.format("/channels/%s/messages", channelId), reply.getAsRequestBody());
    }

    /**
     * 向指定的频道私信发送回复
     * @param guildId       频道ID
     * @param reply         回复消息对象
     */
    public static String sendToDMS(QQBotConfiguration configuration, String guildId, Reply reply){
        return RequestAPI.botHttpPost(configuration, String.format("/dms/%s/messages", guildId), reply.getAsRequestBody());
    }

    /**
     * 拼接@文本
     * @param userId    at的用户ID
     */
    public static String mention(String userId){
        if (userId == null || userId.isBlank()){
            return "";
        }
        return "<@!" + userId + ">";
    }
}
