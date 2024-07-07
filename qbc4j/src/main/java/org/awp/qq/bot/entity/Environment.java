package org.awp.qq.bot.entity;

/**
 * 环境
 *
 * @author MovCloud
 *
 * @date 2023/12/24
 *
 * @since  1.0
 */
public enum Environment {
    /**
     * 沙箱环境
     */
    DEV("https://sandbox.api.sgroup.qq.com"),
    /**
     * 正式环境
     */
    PROD("https://api.sgroup.qq.com"),
    ;

    private final String url;

    Environment(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    /**
     * 拼接接口路径
     */
    public String formatUrl(String api){
        return getUrl() + (api.startsWith("/") ? "" : "/") + api;
    }
}
