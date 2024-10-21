package org.awp.qq.bot.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 用户信息
 *
 * @author MovCloud
 *
 * @date 2023/12/23
 *
 * @since  1.0
 */
public class User {
    /**
     * 用户ID
     */
    @Expose
    @SerializedName("id")
    private String userId;
    /**
     * 用户名
     */
    @Expose
    @SerializedName("username")
    private String userName;
    /**
     * 用户头像地址
     */
    @Expose
    @SerializedName("avatar")
    private String profilesPicture;
    /**
     * 是否机器人
     */
    @Expose
    @SerializedName("bot")
    private boolean bot;
    /**
     * 成员OpenId
     */
    @Expose
    @SerializedName("member_openid")
    private String memberOpenid;
    /**
     * 关联OpenId
     */
    @Expose
    @SerializedName("union_openid")
    private String unionOpenid;
    /**
     * 关联账户
     */
    @Expose
    @SerializedName("union_user_account")
    private String unionUserAccount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilesPicture() {
        return profilesPicture;
    }

    public void setProfilesPicture(String profilesPicture) {
        this.profilesPicture = profilesPicture;
    }

    public boolean isBot() {
        return bot;
    }

    public void setBot(boolean bot) {
        this.bot = bot;
    }

    public String getMemberOpenid() {
        return memberOpenid;
    }

    public void setMemberOpenid(String memberOpenid) {
        this.memberOpenid = memberOpenid;
    }

    public String getUnionOpenid() {
        return unionOpenid;
    }

    public void setUnionOpenid(String unionOpenid) {
        this.unionOpenid = unionOpenid;
    }

    public String getUnionUserAccount() {
        return unionUserAccount;
    }

    public void setUnionUserAccount(String unionUserAccount) {
        this.unionUserAccount = unionUserAccount;
    }
}
