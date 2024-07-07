package org.awp.qq.bot.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 子频道权限
 *
 * @author MovCloud
 *
 * @date 2024/4/14
 *
 * @since  1.0
 */
public class ChannelPermissions {
    /**
     * 子频道ID
     */
    @Expose
    @SerializedName("channel_id")
    private String channelId;
    /**
     * 用户ID
     * 用户ID 或 身份组ID，只会返回其中之一
     */
    @Expose
    @SerializedName("user_id")
    private String userId;
    /**
     * 身份组ID
     * 用户ID 或 身份组ID，只会返回其中之一
     */
    @Expose
    @SerializedName("role_id")
    private String roleId;
    /**
     * 用户拥有的子频道权限
     */
    @Expose
    @SerializedName("permissions")
    private String permissions;

    /**
     * 子频道权限封装对象
     */
    private Permission permission;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public Permission getPermission(){
        if (permission == null){
            permission = Permission.parse(getPermissions());
        }
        return permission;
    }
}
