package org.awp.qq.bot.api;

import org.awp.qq.bot.client.QQBotConfiguration;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * HTTP请求接口
 *
 * @author MovCloud
 *
 * @date 2023/12/23
 *
 * @since  1.0
 */
public final class RequestAPI {

    /**
     * 超时时长（秒）
     */
    private static final long TIMEOUT = 10;

    public static HttpClient newHttpClient(){
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(TIMEOUT))
                .build();
    }

    /**
     * 发送机器人接口GET请求
     */
    public static String botHttpGet(QQBotConfiguration configuration, String api){
        try(HttpClient httpClient = newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(configuration.getEnvironment().formatUrl(api)))
                    .header("Authorization", formatBotAuthorization(configuration))
                    .header("content-type", "application/json")
                    .header("User-Agent", "BotQQForJava/v1")
                    .timeout(Duration.ofSeconds(TIMEOUT))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception ignore) {}
        return null;
    }

    /**
     * 发送机器人接口POST请求
     */
    public static String botHttpPost(QQBotConfiguration configuration, String api, String body){
        try(HttpClient httpClient = newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(configuration.getEnvironment().formatUrl(api)))
                    .header("Authorization", formatBotAuthorization(configuration))
                    .header("content-type", "application/json")
                    .header("User-Agent", "BotQQForJava/v1")
                    .timeout(Duration.ofSeconds(TIMEOUT))
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception ignore) {}
        return null;
    }

    /**
     * 拼接机器人接口权限验证信息
     */
    public static String formatBotAuthorization(QQBotConfiguration configuration){
        return formatBotAuthorization(configuration.getAppId(), configuration.getToken());
    }

    /**
     * 拼接机器人接口权限验证信息
     */
    public static String formatBotAuthorization(String appId, String token){
        return "Bot " + appId + "." + token;
    }

    /**
     * 拼接OAuth权限验证信息
     */
    public static String formatOAuthAuthorization(String token){
        return "Bearer " + token;
    }

}
