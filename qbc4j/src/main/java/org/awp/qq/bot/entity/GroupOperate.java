package org.awp.qq.bot.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 群聊操作
 *
 * @author MovCloud
 *
 * @date 2024/10/21
 *
 * @since  1.0
 */
public class GroupOperate {

    /**
     * 群聊开放平台ID
     */
    @Expose
    @SerializedName("group_openid")
    private String groupOpenid;
    /**
     * 操作人开发平台ID
     */
    @Expose
    @SerializedName("op_member_openid")
    private String operatorOpenid;
    /**
     * 操作时间
     */
    @Expose
    @SerializedName("timestamp")
    private Long timestamp;

    public String getGroupOpenid() {
        return groupOpenid;
    }

    public void setGroupOpenid(String groupOpenid) {
        this.groupOpenid = groupOpenid;
    }

    public String getOperatorOpenid() {
        return operatorOpenid;
    }

    public void setOperatorOpenid(String operatorOpenid) {
        this.operatorOpenid = operatorOpenid;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
