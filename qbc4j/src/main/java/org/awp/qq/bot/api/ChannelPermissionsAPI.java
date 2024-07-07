package org.awp.qq.bot.api;

import org.awp.qq.bot.client.QQBotConfiguration;
import org.awp.qq.bot.entity.ChannelPermissions;
import org.awp.qq.bot.util.GsonUtil;

/**
 * 子频道权限接口
 *
 * @author MovCloud
 *
 * @date 2024/4/15
 *
 * @since  1.0
 */
public final class ChannelPermissionsAPI {
    public static ChannelPermissions getUserPermission(QQBotConfiguration configuration, String channelId, String userId){
        String response = RequestAPI.botHttpGet(configuration,
                String.format("/channels/%s/members/%s/permissions", channelId, userId));
        if (response == null){
            return null;
        }
//        System.out.println("DEMO: " + response);
        return GsonUtil.GSON.fromJson(response, ChannelPermissions.class);
    }

    public static ChannelPermissions getRolePermission(QQBotConfiguration configuration, String channelId, String roleId){
        String response = RequestAPI.botHttpGet(configuration,
                String.format("/channels/%s/roles/%s/permissions", channelId, roleId));
        if (response == null){
            return null;
        }
//        System.out.println("DEMO: " + response);
        return GsonUtil.GSON.fromJson(response, ChannelPermissions.class);
    }
}
