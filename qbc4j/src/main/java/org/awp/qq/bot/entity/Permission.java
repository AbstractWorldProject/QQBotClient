package org.awp.qq.bot.entity;

import com.google.gson.annotations.Expose;

/**
 * 权限（子频道权限）
 *
 * @author MovCloud
 *
 * @date 2024/4/21
 *
 * @since  1.0
 */
public class Permission {
    /**
     * 可查看子频道
     */
    @Expose
    private boolean visible;
    /**
     * 可管理子频道
     */
    @Expose
    private boolean admin;
    /**
     * 可在子频道内发言
     */
    @Expose
    private boolean chat;
    /**
     * 可在子频道内直播
     */
    @Expose
    private boolean live;

    public Permission() {
        this.visible = false;
        this.admin = false;
        this.chat = false;
        this.live = false;
    }

    public static Permission parse(String permissions){
        Permission permission = new Permission();
        if (permissions == null || permissions.isBlank()) {
            return permission;
        }
        try {
            byte permissionsByte = Byte.parseByte(permissions);
            permission.parse(permissionsByte);
            return permission;
        }catch (Exception e) {
            return permission;
        }
    }

    public void parse(byte permissions){
        this.visible = hasPermission(permissions, 0);
        this.admin = hasPermission(permissions, 1);
        this.chat = hasPermission(permissions, 2);
        this.live = hasPermission(permissions, 3);
    }

    public boolean hasPermission(byte permissions, int position){
        return (permissions & 1 << position) != 0;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isChat() {
        return chat;
    }

    public void setChat(boolean chat) {
        this.chat = chat;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}
