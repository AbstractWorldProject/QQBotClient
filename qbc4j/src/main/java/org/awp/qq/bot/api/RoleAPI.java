package org.awp.qq.bot.api;

import org.awp.qq.bot.client.QQBotConfiguration;
import org.awp.qq.bot.entity.Role;
import org.awp.qq.bot.util.GsonUtil;

import java.util.List;

/**
 * 频道身份组接口
 *
 * @author MovCloud
 *
 * @date 2024/4/9
 *
 * @since  1.0
 */
public final class RoleAPI {
    /**
     * 获取身份组列表
     */
    public static List<Role> getRoleList(QQBotConfiguration configuration, String guildId){
        Role.Info roleInfo = getRoleInfo(configuration, guildId);
        if (roleInfo == null) {
            return null;
        }
        return roleInfo.getRoles();
    }

    /**
     * 获取身份组信息
     * 包含频道ID与身份组默认上限
     */
    public static Role.Info getRoleInfo(QQBotConfiguration configuration, String guildId){
        String response = RequestAPI.botHttpGet(configuration, String.format("/guilds/%s/roles", guildId));
        if (response == null){
            return null;
        }
        return GsonUtil.GSON.fromJson(response, Role.Info.class);
    }
}
