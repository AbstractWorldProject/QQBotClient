package org.awp.qq.bot.entity.message.reply;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * 公域回复
 * 私聊与群聊回复
 *
 * @author MovCloud
 *
 * @date 2024/4/22
 *
 * @since  1.0
 */
public final class PublicDomainReply extends Reply {

    /**
     * 消息类型
     */
    @Expose
    @SerializedName("msg_type")
    private Integer msgType;

    /**
     * 消息序号
     *
     */
    @Expose
    @SerializedName("msg_seq")
    private Integer msgSeq;

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public void setType(Type type){
        setMsgType(type.getCode());
    }
    public Integer getMsgSeq() {
        return msgSeq;
    }

    public void setMsgSeq(Integer msgSeq) {
        this.msgSeq = msgSeq;
    }


    public enum Type {
        CONTENT(0, "文本"),
        MARKDOWN(2, "Markdown"),
        ARK(3, "Ark"),
        EMBED(4, "Embed"),
        MEDIA(7, "富媒体"),
        ;

        private final Integer code;
        private final String desc;

        Type(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }
}
