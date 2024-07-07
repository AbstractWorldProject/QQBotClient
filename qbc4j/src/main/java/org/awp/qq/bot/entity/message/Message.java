package org.awp.qq.bot.entity.message;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.awp.qq.bot.entity.Member;
import org.awp.qq.bot.entity.User;

import java.util.List;

/**
 * 消息
 *
 * @author MovCloud
 *
 * @date 2023/12/24
 *
 * @since  1.0
 */
public class Message {
    /**
     * 消息ID
     */
    @Expose
    @SerializedName("id")
    private String messageId;
    /**
     * 用于私信场景下识别真实的来源频道id
     */
    @Expose
    @SerializedName("src_guild_id")
    private String sourceGuildId;
    /**
     * 频道ID
     */
    @Expose
    @SerializedName("guild_id")
    private String guildId;
    /**
     * 子频道ID
     */
    @Expose
    @SerializedName("channel_id")
    private String channelId;
    /**
     * 消息内容
     */
    @Expose
    @SerializedName("content")
    private String content;
    /**
     * 消息创建时间
     */
    @Expose
    @SerializedName("timestamp")
    private String timestamp;
    /**
     * 消息编辑时间
     */
    @Expose
    @SerializedName("edited_timestamp")
    private String editedTimestamp;
    /**
     * 是否为@全体成员信息
     */
    @Expose
    @SerializedName("mention_everyone")
    private boolean mentionEveryone;
    /**
     * 消息创建者
     */
    @Expose
    @SerializedName("author")
    private User author;
    /**
     * 消息创建者
     */
    @Expose
    @SerializedName("attachments")
    private List<Attachment> attachments;
    /**
     * embed
     */
    @Expose
    @SerializedName("embeds")
    private List<Embed> embeds;
    /**
     * 消息中@的人
     */
    @Expose
    @SerializedName("mentions")
    private List<User> mentions;
    /**
     * 消息创建者的member信息
     */
    @Expose
    @SerializedName("member")
    private Member member;
    /**
     * ark消息
     * 暂时不用，过于复杂，先不做对象化解析
     */
    @Expose
    @SerializedName("ark")
    private JsonObject ark;
    /**
     * 子频道消息排序
     * seq已废弃
     */
    @Expose
    @SerializedName("seq_in_channel")
    private String sequence;
    /**
     * 引用消息对象
     */
    @Expose
    @SerializedName("message_reference")
    private MessageReference messageReference;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSourceGuildId() {
        return sourceGuildId;
    }

    public void setSourceGuildId(String sourceGuildId) {
        this.sourceGuildId = sourceGuildId;
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getEditedTimestamp() {
        return editedTimestamp;
    }

    public void setEditedTimestamp(String editedTimestamp) {
        this.editedTimestamp = editedTimestamp;
    }

    public boolean isMentionEveryone() {
        return mentionEveryone;
    }

    public void setMentionEveryone(boolean mentionEveryone) {
        this.mentionEveryone = mentionEveryone;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public List<Embed> getEmbeds() {
        return embeds;
    }

    public void setEmbeds(List<Embed> embeds) {
        this.embeds = embeds;
    }

    public List<User> getMentions() {
        return mentions;
    }

    public void setMentions(List<User> mentions) {
        this.mentions = mentions;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public JsonObject getArk() {
        return ark;
    }

    public void setArk(JsonObject ark) {
        this.ark = ark;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public MessageReference getMessageReference() {
        return messageReference;
    }

    public void setMessageReference(MessageReference messageReference) {
        this.messageReference = messageReference;
    }

}
