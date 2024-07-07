package org.awp.qq.bot.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.awp.qq.bot.client.QQBotConfiguration;
import org.awp.qq.bot.entity.Environment;
import org.awp.qq.bot.entity.Guild;
import org.awp.qq.bot.entity.User;
import org.awp.qq.bot.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 机器人接口
 *
 * @author MovCloud
 *
 * @date 2023/12/23
 *
 * @since  1.0
 */
public final class BotAPI {
    public static User getBotUserInfo(QQBotConfiguration configuration){
        String response = RequestAPI.botHttpGet(configuration, "/users/@me");
        if (response == null){
            return null;
        }
        User botUserInfo = GsonUtil.GSON.fromJson(response, User.class);
        //请求机器人自身信息时不会返回是否为机器人字段
        botUserInfo.setBot(true);
        return botUserInfo;
    }

    public static List<Guild> getBotJoinedGuildList(QQBotConfiguration configuration){
        String response = RequestAPI.botHttpGet(configuration, "/users/@me/guilds");
        if (response == null){
            return null;
        }
        List<Guild> guildList = new ArrayList<>();
        JsonElement json = JsonParser.parseString(response);
        if (json != null && json.isJsonArray()){
            JsonArray array = json.getAsJsonArray();
            if (!array.isEmpty()){
                for (int i = 0, size = array.size(); i < size; i++){
                    guildList.add(GsonUtil.GSON.fromJson(array.get(i), Guild.class));
                }
                return guildList;
            }
        }
        return guildList;
    }
}
