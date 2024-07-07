package org.awp.qq.bot.api;

import com.google.gson.JsonParser;
import org.awp.qq.bot.client.QQBotConfiguration;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.time.Duration;

/**
 * WebSocket连接接口
 *
 * @author MovCloud
 *
 * @date 2023/12/23
 *
 * @since  1.0
 */
public final class WebSocketAPI {

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

    public static String getGatewayUrl(QQBotConfiguration configuration){
        String response = RequestAPI.botHttpGet(configuration, "/gateway");
        if (response == null){
            return null;
        }
        return JsonParser.parseString(response).getAsJsonObject().get("url").getAsString();
    }
}
