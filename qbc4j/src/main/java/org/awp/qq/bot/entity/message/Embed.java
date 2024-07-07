package org.awp.qq.bot.entity.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Embed {
    /**
     * 标题
     */
    @Expose
    @SerializedName("title")
    private String title;
    /**
     * 消息弹窗内容
     */
    @Expose
    @SerializedName("prompt")
    private String prompt;
    /**
     * 缩略图
     */
    @Expose
    @SerializedName("thumbnail")
    private List<MessageEmbedThumbnail> thumbnail;
    /**
     * embed 字段数据
     */
    @Expose
    @SerializedName("fields")
    private List<MessageEmbedField> fields;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public List<MessageEmbedThumbnail> getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(List<MessageEmbedThumbnail> thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<MessageEmbedField> getFields() {
        return fields;
    }

    public void setFields(List<MessageEmbedField> fields) {
        this.fields = fields;
    }

    public static class MessageEmbedThumbnail{
        /**
         * 图片地址
         */
        @Expose
        @SerializedName("url")
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class MessageEmbedField{
        /**
         * 字段名
         */
        @Expose
        @SerializedName("name")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
