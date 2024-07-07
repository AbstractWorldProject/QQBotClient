package org.awp.qq.bot.client;
/**
 * QQ机器人客户端引用
 *
 * @author MovCloud
 *
 * @date 2024/6/3
 *
 * @since  1.0
 */
public abstract class QQBotClientReference {
    private QQBotClient client;

    protected QQBotClientReference() {
    }

    protected QQBotClientReference(QQBotClient client) {
        this.client = client;
    }

    public QQBotClient getClient() {
        if (client == null) {
            throw new NullPointerException("未设置机器人客户端实例");
        }
        return client;
    }

    public void setClient(QQBotClient client) {
        this.client = client;
    }
}
