package org.awp.qq.bot.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 成员信息
 *
 * @author MovCloud
 *
 * @date 2023/12/23
 *
 * @since  1.0
 */
public class Member {
    /**
     * 频道ID
     */
    @Expose
    @SerializedName("guild_id")
    private String guildId;
    /**
     * 成员用户信息
     */
    @Expose
    @SerializedName("user")
    private User user;
    /**
     * 成员昵称
     */
    @Expose
    @SerializedName("nick")
    private String nickName;
    /**
     * 成员身分组ID
     */
    @Expose
    @SerializedName("roles")
    private List<String> roles;
    /**
     * 成员加入频道时间
     */
    @Expose
    @SerializedName("joined_at")
    private String joinedAt;

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(String joinedAt) {
        this.joinedAt = joinedAt;
    }
}
