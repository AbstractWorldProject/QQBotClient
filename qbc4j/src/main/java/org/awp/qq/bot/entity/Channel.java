package org.awp.qq.bot.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * 子频道信息
 *
 * @author MovCloud
 *
 * @date 2024/4/5
 *
 * @since  1.0
 */
public class Channel {
    /**
     * 子频道ID
     */
    @Expose
    @SerializedName("id")
    private String channelId;
    /**
     * 频道ID
     */
    @Expose
    @SerializedName("guild_id")
    private String guildId;
    /**
     * 子频道名称
     */
    @Expose
    @SerializedName("name")
    private String channelName;
    /**
     * 子频道类型
     * @see ChannelType
     */
    @Expose
    @SerializedName("type")
    private Integer channelType;
    /**
     * 子频道子类型
     * @see ChannelSubType
     */
    @Expose
    @SerializedName("sub_type")
    private Integer channelSubType;
    /**
     * 排序值
     * position 从 1 开始
     * 当子频道类型为 子频道分组（ChannelType=4）时，由于 position 1 被未分组占用，所以 position 只能从 2 开始
     * 如果不传默认追加到分组下最后一个
     * 如果填写一个已经存在的值，那么会插入在原来的元素之前
     * 如果填写一个较大值，与不填是相同的表现，同时存储的值会根据真实的 position 进行重新计算，并不会直接使用传入的值
     */
    @Expose
    @SerializedName("position")
    private Integer position;
    /**
     * 子频道所属分组ID
     * 仅对子频道有效，对 子频道分组（ChannelType=4） 无效
     */
    @Expose
    @SerializedName("parent_id")
    private String parentId;
    /**
     * 创建人ID
     */
    @Expose
    @SerializedName("owner_id")
    private String ownerId;
    /**
     * 子频道私密类型
     * @see PrivateType
     */
    @Expose
    @SerializedName("private_type")
    private Integer privateType;
    /**
     * 子频道发言权限
     * @see SpeakPermission
     */
    @Expose
    @SerializedName("speak_permission")
    private Integer speakPermission;
    /**
     * 用于标识应用子频道应用类型
     * 仅应用子频道时会使用该字段，具体定义请参考
     * {@link ChannelType 应用子频道的应用类型}
     */
    @Expose
    @SerializedName("application_id")
    private String applicationId;
    /**
     * 用户拥有的子频道权限
     */
    @Expose
    @SerializedName("permissions")
    private String permissions;

    /**
     * 子频道权限封装对象
     */
    private Permission permission;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getChannelType() {
        return channelType;
    }

    public ChannelType getChannelTypeEnum() {
        return ChannelType.valueOf(channelType);
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public void setChannelType(ChannelType channelType) {
        this.channelType = channelType.getCode();
    }

    public Integer getChannelSubType() {
        return channelSubType;
    }

    public ChannelSubType getChannelSubTypeEnum() {
        return ChannelSubType.valueOf(channelSubType);
    }

    public void setChannelSubType(Integer channelSubType) {
        this.channelSubType = channelSubType;
    }

    public void setChannelSubType(ChannelSubType channelSubType) {
        this.channelSubType = channelSubType.getCode();
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getPrivateType() {
        return privateType;
    }

    public PrivateType getPrivateTypeEnum() {
        return PrivateType.valueOf(privateType);
    }

    public void setPrivateType(Integer privateType) {
        this.privateType = privateType;
    }

    public void setPrivateType(PrivateType privateType) {
        this.privateType = privateType.getCode();
    }

    public Integer getSpeakPermission() {
        return speakPermission;
    }

    public SpeakPermission getSpeakPermissionEnum() {
        return SpeakPermission.valueOf(speakPermission);
    }

    public void setSpeakPermission(Integer speakPermission) {
        this.speakPermission = speakPermission;
    }

    public void setSpeakPermission(SpeakPermission speakPermission) {
        this.speakPermission = speakPermission.getCode();
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public Permission getPermission(){
        if (permission == null){
            permission = Permission.parse(getPermissions());
        }
        return permission;
    }

    /**
     * 子频道类型
     */
    public enum ChannelType {
        /**
         * 由于QQ频道还在持续的迭代中，经常会有新的子频道类型增加，文档更新不一定及时，开发者识别 ChannelType 时，请注意相关的未知 ID 的处理。
         * 文档指明不可用的部分类型也归类到该类型中
         */
        UNKNOWN(-1, "未知"),
        TEXT(0, "文字子频道"),
        SPEECH(2, "语音子频道"),
        GROUP(4, "子频道分组"),
        LIVE(10005, "直播子频道"),
        APPLICATION(10006, "应用子频道"),
        FORUM(10007, "论坛子频道"),

        ;

        private final Integer code;
        private final String name;

        ChannelType(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        public static ChannelType valueOf(Integer code){
            return Arrays.stream(values())
                    .filter(channelType -> channelType.getCode().equals(code))
                    .findFirst()
                    .orElse(UNKNOWN);
        }

        public Integer getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 子频道二级分类
     * 目前只有文字子频道具有 ChannelSubType 二级分类，同时二级分类也可能会不断增加，开发者也需要注意对未知 ID 的处理。
     */
    public enum ChannelSubType{
        UNKNOWN(-1, "未知"),
        CHAT(0, "闲聊"),
        ANNOUNCEMENT(1, "公告"),
        WALKTHROUGH(2, "攻略"),
        PLAY_TOGETHER(3, "开黑"),
        ;

        private final Integer code;
        private final String name;

        ChannelSubType(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        public static ChannelSubType valueOf(Integer code){
            return Arrays.stream(values())
                    .filter(channelType -> channelType.getCode().equals(code))
                    .findFirst()
                    .orElse(UNKNOWN);
        }

        public Integer getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 子频道私密类型
     */
    public enum  PrivateType{
        UNKNOWN(-1, "未知"),
        PUBLIC(0, "公开频道"),
        ADMINISTRATOR(1, "群主管理员可见"),
        PERMISSION(2, "群主管理员+指定成员，可使用 修改子频道权限接口 指定成员"),
        ;

        private final Integer code;
        private final String name;

        PrivateType(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        public static PrivateType valueOf(Integer code){
            return Arrays.stream(values())
                    .filter(channelType -> channelType.getCode().equals(code))
                    .findFirst()
                    .orElse(UNKNOWN);
        }

        public Integer getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 发言权限
     */
    public enum SpeakPermission{
        UNKNOWN(0, "无效类型"),
        ALL(1, "所有人"),
        PERMISSION(2, "群主管理员+指定成员，可使用 修改子频道权限接口 指定成员"),
        ;

        private final Integer code;
        private final String name;

        SpeakPermission(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        public static SpeakPermission valueOf(Integer code){
            return Arrays.stream(values())
                    .filter(channelType -> channelType.getCode().equals(code))
                    .findFirst()
                    .orElse(UNKNOWN);
        }

        public Integer getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }
}
