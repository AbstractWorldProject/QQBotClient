package org.awp.qq.bot.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.awp.qq.bot.client.QQBotConfiguration;
import org.awp.qq.bot.entity.Channel;
import org.awp.qq.bot.entity.Environment;
import org.awp.qq.bot.entity.Guild;
import org.awp.qq.bot.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 子频道接口
 *
 * @author MovCloud
 *
 * @date 2024/4/6
 *
 * @since  1.0
 */
public final class ChannelAPI {
    public static List<Channel> getChannelList(QQBotConfiguration configuration, String guildId){
        String response = RequestAPI.botHttpGet(configuration,
                String.format("/guilds/%s/channels", guildId));
        if (response == null){
            return null;
        }
//        System.out.println("DEMO: " + response);
        List<Channel> channelList = new ArrayList<>();
        JsonElement json = JsonParser.parseString(response);
        if (json != null && json.isJsonArray()){
            JsonArray array = json.getAsJsonArray();
            if (!array.isEmpty()){
                for (int i = 0, size = array.size(); i < size; i++){
                    channelList.add(GsonUtil.GSON.fromJson(array.get(i), Channel.class));
                }
                return channelList;
            }
        }
        return channelList;
    }
}
