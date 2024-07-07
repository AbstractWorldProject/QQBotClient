package org.awp.qq.bot.entity;

import java.util.Arrays;

/**
 * 事件标记位
 *
 * @author MovCloud
 *
 * @date 2023/12/24
 *
 * @since  1.0
 */
public enum Intent {
    GUILDS(0),
    GUILD_MEMBERS(1),
    GUILD_MESSAGES(9, true),
    GUILD_MESSAGE_REACTIONS(10),
    DIRECT_MESSAGE(12),
    OPEN_FORUMS_EVENT(18),
    AUDIO_OR_LIVE_CHANNEL_MEMBER(19),
    PUBLIC_MESSAGES(25),
    INTERACTION(26),
    MESSAGE_AUDIT(27),
    FORUMS_EVENT(28, true),
    AUDIO_ACTION(29, true),
    PUBLIC_GUILD_MESSAGES(30, true),
    ;

    /**
     * 标记位编码
     * 1 << code
     */
    private final Integer code;

    /**
     * 仅限私域
     */
    private final boolean privateOnly;

    Intent(Integer code) {
        this(code, false);
    }

    Intent(Integer code, boolean privateOnly) {
        this.code = code;
        this.privateOnly = privateOnly;
    }

    public Integer getCode() {
        return code;
    }

    public boolean isPrivateOnly() {
        return privateOnly;
    }

    public Integer toIntentsValue(){
        return 1 << code;
    }

    public static Integer calculate(Intent... intents){
        int value = 0;
        for (Intent item : intents){
            value += item.toIntentsValue();
        }
        return value;
    }

    public static Integer calculate(Event... events){
        return Arrays.stream(events)
                .map(Event::getIntent)
                .distinct()
                .map(Intent::toIntentsValue)
                .reduce(0, Integer::sum);
    }
}
