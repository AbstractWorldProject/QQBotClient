package org.awp.qq.bot.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.awp.qq.bot.client.QQBotConfiguration;
import org.awp.qq.bot.entity.Member;
import org.awp.qq.bot.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 成员接口
 *
 * @author MovCloud
 *
 * @date 2023/12/23
 *
 * @since  1.0
 */
public final class MemberAPI {
    public static Member getMemberInfo(QQBotConfiguration configuration, String guildId, String userId){
        String response = RequestAPI.botHttpGet(configuration, String.format("/guilds/%s/members/%s",
                guildId, userId));
        if (response == null){
            return null;
        }
        return GsonUtil.GSON.fromJson(response, Member.class);
    }

    public static List<Member> getAllGuildMember(QQBotConfiguration configuration, String guildId){
        //TODO - 循环查询直到分页成员数量少于每页数量或为空
        throw new UnsupportedOperationException("测试频道人数不足400，暂不实现该功能");
    }

    public static List<Member> getGuildMemberPage(QQBotConfiguration configuration, String guildId){
        return getGuildMemberPage(configuration, guildId, null, null);
    }

    /**
     * 分页请求频道成员列表
     * @param guildId   频道ID
     * @param after     上一页的最后一个成员的user_id
     * @param limit     每页限制大小，默认为1，最大为400
     */
    public static List<Member> getGuildMemberPage(QQBotConfiguration configuration, String guildId, String after, Integer limit){
        after = (after == null || after.isBlank()) ? "0" : after;
        limit = limit == null ? 100 : Math.max(1, Math.min(limit, 400));
        String response = RequestAPI.botHttpGet(configuration, String.format("/guilds/%s/members?after=%s&limit=%d",
                guildId, after, limit));
        if (response == null){
            return null;
        }
//        System.out.println("DEMO: " + response);
        List<Member> memberList = new ArrayList<>();
        JsonElement json = JsonParser.parseString(response);
        if (json != null && json.isJsonArray()){
            JsonArray array = json.getAsJsonArray();
            if (!array.isEmpty()){
                for (int i = 0, size = array.size(); i < size; i++){
                    memberList.add(GsonUtil.GSON.fromJson(array.get(i), Member.class));
                }
                return memberList;
            }
        }
        return memberList;
    }
}
