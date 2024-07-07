package org.awp.qq.bot.entity.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageReference {
    /**
     * 需要引用回复的消息 id
     */
    @Expose
    @SerializedName("message_id")
    private String messageId;
    /**
     * 是否忽略获取引用消息详情错误，默认否
     */
    @Expose
    @SerializedName("ignore_get_message_error")
    private boolean ignore;

    public MessageReference(String messageId) {
        this(messageId, false);
    }

    public MessageReference(String messageId, boolean ignore) {
        this.messageId = messageId;
        this.ignore = ignore;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public boolean isIgnore() {
        return ignore;
    }

    public void setIgnore(boolean ignore) {
        this.ignore = ignore;
    }
}
