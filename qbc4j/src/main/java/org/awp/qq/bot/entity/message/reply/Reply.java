package org.awp.qq.bot.entity.message.reply;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.awp.qq.bot.entity.message.MessageReference;
import org.awp.qq.bot.util.GsonUtil;

/**
 * 回复信息
 * 调用消息发送接口时的Message对象
 * 为了与接受消息的同名对象区分
 * 命名为回复Reply
 *
 * @author MovCloud
 *
 * @date 2024/4/21
 *
 * @since  1.0
 */
public sealed class Reply permits PrivateDomainReply, PublicDomainReply {
    /**
     * 文本内容
     */
    @Expose
    @SerializedName("content")
    private String content;

    /**
     * 回复消息ID
     */
    @Expose
    @SerializedName("msg_id")
    private String msgId;

    /**
     * 引用消息对象
     */
    @Expose
    @SerializedName("message_reference")
    private MessageReference messageReference;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public MessageReference getMessageReference() {
        return messageReference;
    }

    public void setMessageReference(MessageReference messageReference) {
        this.messageReference = messageReference;
    }

    public void setMessageReference(String messageId){
        setMessageReference(new MessageReference(messageId));
    }

    public String getAsRequestBody() {
        return GsonUtil.GSON.toJson(this);
    }
}
