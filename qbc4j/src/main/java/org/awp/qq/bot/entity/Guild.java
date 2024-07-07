package org.awp.qq.bot.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 频道信息
 *
 * @author MovCloud
 *
 * @date 2023/12/23
 *
 * @since  1.0
 */
public class Guild {
    /**
     * 频道ID
     */
    @Expose
    @SerializedName("id")
    private String guildId;
    /**
     * 频道名称
     */
    @Expose
    @SerializedName("name")
    private String guildName;
    /**
     * 频道头像地址
     */
    @Expose
    @SerializedName("icon")
    private String profilesPicture;
    /**
     * 频道创建人用户ID
     */
    @Expose
    @SerializedName("owner_id")
    private String ownerId;
    /**
     * 当前人是否是频道创建人
     */
    @Expose
    @SerializedName("owner")
    private boolean owner;
    /**
     * 频道成员数量
     */
    @Expose
    @SerializedName("member_count")
    private Integer memberCount;
    /**
     * 频道成员数量上限
     */
    @Expose
    @SerializedName("max_members")
    private Integer maxMembers;
    /**
     * 频道描述
     */
    @Expose
    @SerializedName("description")
    private String description;
    /**
     * 加入频道时间
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

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    public String getProfilesPicture() {
        return profilesPicture;
    }

    public void setProfilesPicture(String profilesPicture) {
        this.profilesPicture = profilesPicture;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public Integer getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(Integer maxMembers) {
        this.maxMembers = maxMembers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(String joinedAt) {
        this.joinedAt = joinedAt;
    }
}
