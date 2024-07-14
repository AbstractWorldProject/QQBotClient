package org.awp.qq.bot.api;

import org.awp.qq.bot.client.QQBotConfiguration;
import org.awp.qq.bot.entity.User;
import org.awp.qq.bot.util.GsonUtil;

/**
 * 机器人信息接口
 *
 * @author MovCloud
 *
 * @date 2023/12/23
 *
 * @since  1.0
 */
public final class BotAPI {
    /**
     * 获取机器人信息
     */
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
}
