package org.awp.qq.bot.client.distributor;

import org.awp.qq.bot.client.QQBotClient;
import org.awp.qq.bot.client.QQBotClientReference;
import org.awp.qq.bot.entity.Intent;
import org.awp.qq.bot.entity.Payload;
import org.awp.qq.bot.util.GsonUtil;

import java.net.http.WebSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * 负载分发器
 *
 * @author MovCloud
 *
 * @date 2024/6/20
 *
 * @since  1.0
 */
public class PayloadDistributor extends QQBotClientReference implements BiConsumer<WebSocket, Payload> {

    private ExecutorService executorService;

    public PayloadDistributor(QQBotClient client) {
        super(client);
    }

    @Override
    public void accept(WebSocket webSocket, Payload payload) {
        switch (payload.getOperationCode()){
            case Hello -> {
                Long heartbeatInterval = payload.getData().getAsJsonObject().get("heartbeat_interval").getAsLong();
                getClient().getConfiguration().setHeartbeatInterval(heartbeatInterval);
                Intent[] intents = getClient().getConfiguration().getIntents();
                if (intents == null || intents.length == 0){
                    System.err.println("参数错误，请检查启动配置");
                    getClient().shutdown();
                }else {
                    Payload authPayload = Payload.auth(getClient().getConfiguration(), intents);
                    webSocket.sendText(authPayload.toJson(), true);
                }
            }
            case Dispatch -> {
                getExecutorService().submit(() -> getClient().getConsumer().accept(payload));
            }
            case Reconnect -> {
                System.out.println("收到重新连接指令");
//                String sessionId = getSessionId();
//                if (sessionId == null){
//                    webSocket.sendClose(7, "收到指令关闭连接");
//                }
//                Payload packet = new Payload();
//                packet.setOperationCode(Payload.OperationCode.Resume);
//                JsonObject data = new JsonObject();
//                data.addProperty("token", RequestAPI.formatBotAuthorization());
//                data.addProperty("session_id", getSessionId());
//                data.addProperty("seq", getLatestSerialNumber());
//                packet.setData(data);
//                sendPayload(webSocket, packet);
            }
            case Heartbeat_ACK -> {
                System.out.println("收到心跳回包");
            }
            case Invalid_Session -> {
                System.err.println("参数错误，请检查启动配置");
                getClient().shutdown();
            }
            default -> {
                String type = payload.getType();
                System.out.println("收到未捕获操作[" + payload.getSerialNumber() + "]: " + type);
                System.out.println(GsonUtil.PRETTY_GSON.toJson(payload));
            }
        }
    }

    public ExecutorService getExecutorService() {
        if (executorService == null) {
            executorService = Executors.newVirtualThreadPerTaskExecutor();
        }
        return executorService;
    }
}
