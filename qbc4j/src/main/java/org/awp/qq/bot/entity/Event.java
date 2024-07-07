package org.awp.qq.bot.entity;
/**
 * 事件
 *
 * @author MovCloud
 *
 * @date 2023/12/24
 *
 * @since  1.0
 */
public enum Event {
    GUILD_CREATE(Intent.GUILDS, "当机器人加入新guild时"),
    GUILD_UPDATE(Intent.GUILDS, "当guild资料发生变更时"),
    GUILD_DELETE(Intent.GUILDS, "当机器人退出guild时"),
    CHANNEL_CREATE(Intent.GUILDS, "当channel被创建时"),
    CHANNEL_UPDATE(Intent.GUILDS, "当channel被更新时"),
    CHANNEL_DELETE(Intent.GUILDS, "当channel被删除时"),


    MESSAGE_CREATE(Intent.GUILD_MESSAGES, "发送消息事件"),
    MESSAGE_DELETE(Intent.GUILD_MESSAGES, "删除（撤回）消息事件"),


    DIRECT_MESSAGE_CREATE(Intent.DIRECT_MESSAGE, "当收到用户发给机器人的私信消息时"),
    DIRECT_MESSAGE_DELETE(Intent.DIRECT_MESSAGE, "删除（撤回）消息事件"),


    C2C_MESSAGE_CREATE(Intent.PUBLIC_MESSAGES, "单聊消息"),
    GROUP_AT_MESSAGE_CREATE(Intent.PUBLIC_MESSAGES, "群聊@机器人"),
    GROUP_ADD_ROBOT(Intent.PUBLIC_MESSAGES, "机器人被添加到群聊"),
    GROUP_DEL_ROBOT(Intent.PUBLIC_MESSAGES, "机器人被移出群聊"),
    GROUP_MSG_REJECT(Intent.PUBLIC_MESSAGES, "群聊拒绝机器人主动消息（管理员修改设置通知）"),
    GROUP_MSG_RECEIVE(Intent.PUBLIC_MESSAGES, "群聊接受机器人主动消息（管理员修改设置通知）"),
    FRIEND_ADD(Intent.PUBLIC_MESSAGES, "用户添加机器人'好友'到消息列表"),
    FRIEND_DEL(Intent.PUBLIC_MESSAGES, "用户删除机器人'好友'"),
    C2C_MSG_REJECT(Intent.PUBLIC_MESSAGES, "用户在机器人资料卡手动关闭'主动消息'推送"),
    C2C_MSG_RECEIVE(Intent.PUBLIC_MESSAGES, "用户在机器人资料卡手动开启'主动消息'推送开关"),


    MESSAGE_AUDIT_PASS(Intent.MESSAGE_AUDIT, "消息审核通过"),
    MESSAGE_AUDIT_REJECT(Intent.MESSAGE_AUDIT, "消息审核不通过"),


    AT_MESSAGE_CREATE(Intent.PUBLIC_GUILD_MESSAGES, "当收到@机器人的消息时"),
    PUBLIC_MESSAGE_DELETE(Intent.PUBLIC_GUILD_MESSAGES, "当频道的消息被删除时"),

    ;

    private final Intent intent;

    private final String description;

    Event(Intent intent) {
        this(intent, "");
    }

    Event(Intent intent, String description) {
        this.intent = intent;
        this.description = description;
    }

    public Intent getIntent() {
        return intent;
    }

    public String getDescription() {
        return description;
    }
}
