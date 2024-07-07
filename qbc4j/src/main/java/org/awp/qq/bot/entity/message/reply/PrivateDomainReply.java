package org.awp.qq.bot.entity.message.reply;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 私域回复
 * 频道私信回复与频道回复
 *
 * @author MovCloud
 *
 * @date 2024/4/22
 *
 * @since  1.0
 */
public final class PrivateDomainReply extends Reply {

    /**
     * 图片
     */
    @Expose
    @SerializedName("image")
    private String image;

    /**
     * 回复事件ID
     */
    @Expose
    @SerializedName("event_id")
    private String eventId;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
