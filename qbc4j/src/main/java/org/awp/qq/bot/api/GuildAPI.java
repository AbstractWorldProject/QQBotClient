package org.awp.qq.bot.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.awp.qq.bot.client.QQBotConfiguration;
import org.awp.qq.bot.entity.Guild;
import org.awp.qq.bot.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 频道信息接口
 *
 * @author MovCloud
 *
 * @date 2023/12/23
 *
 * @since  1.0
 */
public final class GuildAPI {
    /**
     * 获取机器人加入的频道列表
     */
    public static List<Guild> getGuildList(QQBotConfiguration configuration){
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

    /**
     * 按频道ID获取频道信息
     */
    public static Guild getGuildInfo(QQBotConfiguration configuration, String guildId){
        String response = RequestAPI.botHttpGet(configuration, String.format("/guilds/%s", guildId));
        if (response == null){
            return null;
        }
        return GsonUtil.GSON.fromJson(response, Guild.class);
    }
}
