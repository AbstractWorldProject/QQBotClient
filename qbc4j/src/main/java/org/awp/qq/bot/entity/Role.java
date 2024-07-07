package org.awp.qq.bot.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

/**
 * 频道身份组
 *
 * @author MovCloud
 *
 * @date 2024/4/8
 *
 * @since  1.0
 */
public class Role {
    /**
     * 身份组ID
     */
    @Expose
    @SerializedName("id")
    private String roleId;

    /**
     * 身份组名称
     */
    @Expose
    @SerializedName("name")
    private String roleName;

    /**
     * 身份组颜色
     * ARGB的HEX十六进制颜色值转换后的十进制数值
     */
    @Expose
    @SerializedName("color")
    private Long color;

    /**
     * 是否在成员列表中单独展示
     * 0-否, 1-是
     */
    @Expose
    @SerializedName("hoist")
    private Integer hoist;

    /**
     * 人数
     */
    @Expose
    @SerializedName("number")
    private Integer number;

    /**
     * 成员上限
     */
    @Expose
    @SerializedName("member_limit")
    private Integer memberLimit;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getColor() {
        return color;
    }

    public void setColor(Long color) {
        this.color = color;
    }

    public Integer getHoist() {
        return hoist;
    }

    public void setHoist(Integer hoist) {
        this.hoist = hoist;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getMemberLimit() {
        return memberLimit;
    }

    public void setMemberLimit(Integer memberLimit) {
        this.memberLimit = memberLimit;
    }

    /**
     * 频道身份信息
     * 取自{@link org.awp.qq.bot.api.RoleAPI#getRoleInfo(Environment, String) 获取频道身份信息}接口
     * 包含频道ID与当前频道身份组数量限制
     */
    public static class Info {
        /**
         * 频道ID
         */
        @Expose
        @SerializedName("guild_id")
        private String guildId;

        /**
         * 当前频道身份组数量限制
         */
        @Expose
        @SerializedName("role_num_limit")
        private String roleNumLimit;

        /**
         * 当前频道身份组数量限制
         */
        @Expose
        @SerializedName("roles")
        private List<Role> roles;

        public String getGuildId() {
            return guildId;
        }

        public void setGuildId(String guildId) {
            this.guildId = guildId;
        }

        public String getRoleNumLimit() {
            return roleNumLimit;
        }

        public void setRoleNumLimit(String roleNumLimit) {
            this.roleNumLimit = roleNumLimit;
        }

        public List<Role> getRoles() {
            return roles;
        }
    }

    /**
     * 系统默认生成下列身份组ID
     */
    public enum DefaultRoleIDs{
        UNKNOWN(0, "未知身份"),
        ALL(1, "全体成员"),
        ADMIN(2, "管理员"),
        LEADER(4, "群主/创建者"),
        CHANNEL_ADMIN(5, "子频道管理员"),
        ;

        private final Integer code;
        private final String name;

        DefaultRoleIDs(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        public static DefaultRoleIDs valueOf(Integer code){
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
