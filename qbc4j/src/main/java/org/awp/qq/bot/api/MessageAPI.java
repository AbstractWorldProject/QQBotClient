package org.awp.qq.bot.api;

import org.awp.qq.bot.client.QQBotConfiguration;
import org.awp.qq.bot.entity.Environment;
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
    public static Message getMessage(QQBotConfiguration configuration, String channelId, String messageId){
        String response = RequestAPI.botHttpGet(configuration,
                String.format("/channels/%s/messages/%s", channelId, messageId));
        if (response == null){
            return null;
        }
//        System.out.println("DEMO: " + response);
        return GsonUtil.GSON.fromJson(response, Message.class);
    }

    public static String sendToChannel(QQBotConfiguration configuration, String channelId, Reply reply){
        return RequestAPI.botHttpPost(configuration, String.format("/channels/%s/messages", channelId), reply.getAsRequestBody());
    }

    public static String sendToDMS(QQBotConfiguration configuration, String guildId, Reply reply){
        return RequestAPI.botHttpPost(configuration, String.format("/dms/%s/messages", guildId), reply.getAsRequestBody());
    }

    public static String mention(String userId){
        if (userId == null || userId.isBlank()){
            return "";
        }
        return "<@!" + userId + ">";
    }
}
