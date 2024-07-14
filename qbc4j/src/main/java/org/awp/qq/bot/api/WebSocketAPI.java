package org.awp.qq.bot.api;

import com.google.gson.JsonParser;
import org.awp.qq.bot.client.QQBotConfiguration;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.time.Duration;

/**
 * WebSocket连接接口<p/>
 * 已弃用，请使用
 * {@link org.awp.qq.bot.client.QQBotClient}对象
 * 创建长连接
 *
 * @author MovCloud
 *
 * @date 2023/12/23
 *
 * @since  1.0
 */
@Deprecated(since = "1.0.0")
public final class WebSocketAPI {

    /**
     * 启动QQ机器人长连接
     * 一天后关闭，此接口为旧有调试接口
     */
    public static void start(QQBotConfiguration configuration, WebSocket.Listener listener){
        String url = getGatewayUrl(configuration);
        if (url == null || url.isBlank()){
            System.err.println("获取长连接地址失败");
            return;
        }
        try(HttpClient httpClient = RequestAPI.newHttpClient()) {
            httpClient.newWebSocketBuilder()
                    .buildAsync(URI.create(url), listener).join();
            try {
                Thread.sleep(Duration.ofDays(1));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 获取指定环境的开放平台门户URL
     */
    public static String getGatewayUrl(QQBotConfiguration configuration){
        String response = RequestAPI.botHttpGet(configuration, "/gateway");
        if (response == null){
            return null;
        }
        return JsonParser.parseString(response).getAsJsonObject().get("url").getAsString();
    }
}
