package org.awp.qq.bot.client;

import org.awp.qq.bot.entity.Payload;
import org.awp.qq.bot.util.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.WebSocket;
import java.util.concurrent.CompletionStage;

/**
 * QQ机器人长连接监听器
 *
 * @author MovCloud
 *
 * @date 2023/12/24
 *
 * @since  1.0
 */
public class QQBotWebSocketListener implements WebSocket.Listener {
    private static final Logger log = LoggerFactory.getLogger(QQBotWebSocketListener.class);
    private final QQBotClient client;

    public QQBotWebSocketListener(QQBotClient client) {
        this.client = client;
    }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        Payload payload = GsonUtil.GSON.fromJson(data.toString(), Payload.class);
        getClient().updateSerial(payload);
        getClient().getDistributor().accept(webSocket, payload);
        webSocket.request(1);
        return null;
    }

    @Override
    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        log.info("长连接已关闭[" + statusCode + "]" + reason);
        getClient().stopHeartbeat();
        return null;
    }

    protected QQBotClient getClient() {
        return client;
    }
}
