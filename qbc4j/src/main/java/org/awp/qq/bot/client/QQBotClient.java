package org.awp.qq.bot.client;

import org.awp.qq.bot.api.MessageAPI;
import org.awp.qq.bot.api.RequestAPI;
import org.awp.qq.bot.client.distributor.PayloadDistributor;
import org.awp.qq.bot.client.consumer.PayloadConsumer;
import org.awp.qq.bot.entity.Payload;
import org.awp.qq.bot.entity.message.reply.Reply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.concurrent.*;

/**
 * QQ机器人客户端
 *
 * @author MovCloud
 *
 * @date 2023/12/24
 *
 * @since  1.0
 */
public class QQBotClient {
    private static final Logger log = LoggerFactory.getLogger(QQBotClient.class);
    private final QQBotConfiguration configuration;
    private HttpClient httpClient;
    private WebSocket webSocket;

    private PayloadDistributor distributor;
    private PayloadConsumer consumer;

    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private ScheduledFuture<?> heartbeatScheduledFuture;

    public QQBotClient(QQBotConfiguration configuration) {
        this.configuration = configuration;
    }

    public void start(){
        httpClient = RequestAPI.newHttpClient();
        webSocket = httpClient.newWebSocketBuilder()
                .buildAsync(URI.create(getConfiguration().getGatewayUrl()),
                        new QQBotWebSocketListener(this))
                .join();
    }

    public void shutdown(){
        log.info("关闭QQ机器人客户端");
        CompletableFuture<WebSocket> closeFuture = webSocket.sendClose(1000, "客户端主动关闭连接");
        closeFuture.whenComplete((result, throwable) -> {
            if (throwable != null) {
                log.error("关闭WebSocket连接失败", throwable);
            } else {
                log.info("关闭WebSocket连接成功");
                httpClient.close();
            }
        });
    }

    public QQBotConfiguration getConfiguration() {
        return configuration;
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }

    public void updateSerial(Payload payload){
        if (payload.getSerialNumber() != null){
            getConfiguration().setSerial(payload.getSerialNumber());
        }
    }

    public PayloadDistributor getDistributor() {
        if (distributor == null) {
            distributor = new PayloadDistributor(this);
        }
        return distributor;
    }

    public void setDistributor(PayloadDistributor distributor) {
        this.distributor = distributor;
    }

    public PayloadConsumer getConsumer() {
        if (consumer == null){
            consumer = new PayloadConsumer(this);
        }
        return consumer;
    }

    public void setConsumer(PayloadConsumer consumer) {
        this.consumer = consumer;
    }

    public boolean sendPayload(Payload payload){
        WebSocket ws = getWebSocket();
        if (ws == null){
            return false;
        }
        ws.sendText(payload.toJson(), true);
        return true;
    }

    /**
     * 主动发送消息有条数限制，优先使用回复方法
     * TODO - 主动转被动？尝试存储未过期且有余量的消息，优先将主动消息变为回复消息
     * replyMessage
     */
    public void sendMessage(String channelId, String content){
        Reply reply = new Reply();
        reply.setContent(content);
        String response = MessageAPI.sendToChannel(getConfiguration(), channelId, reply);
        log.info("发送消息返回: " + response);
    }

    public void replyMessage(String channelId, String messageId, String content){
        if (messageId == null || messageId.isBlank()){
            throw new RuntimeException("缺少回复的信息ID");
        }
        Reply reply = new Reply();
        reply.setMsgId(messageId);
        reply.setContent(content);
        String response = MessageAPI.sendToChannel(getConfiguration(), channelId, reply);
        log.info("回复消息返回: " + response);
    }

    public void replyMessageWithReference(String channelId, String messageId, String content){
        if (messageId == null || messageId.isBlank()){
            throw new RuntimeException("缺少回复的信息ID");
        }
        Reply reply = new Reply();
        reply.setMsgId(messageId);
        reply.setContent(content);
        reply.setMessageReference(messageId);
        String response = MessageAPI.sendToChannel(getConfiguration(), channelId, reply);
        log.info("回复消息返回: " + response);
    }

    public void startHeartbeat(){
        heartbeatScheduledFuture = scheduledExecutorService.scheduleAtFixedRate(() ->
                        sendPayload(Payload.heartbeat(getConfiguration().getSerial())),
                5, getConfiguration().getHeartbeatInterval(), TimeUnit.MILLISECONDS);
    }

    public void stopHeartbeat(){
        heartbeatScheduledFuture.cancel(false);
    }

}
