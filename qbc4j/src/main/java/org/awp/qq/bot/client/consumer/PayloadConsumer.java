package org.awp.qq.bot.client.consumer;

import org.awp.qq.bot.client.QQBotClient;
import org.awp.qq.bot.client.QQBotClientReference;
import org.awp.qq.bot.entity.Payload;

import java.util.function.Consumer;

/**
 * 负载消费者
 *
 * @author MovCloud
 *
 * @see Payload
 *
 * @date 2024/6/2
 *
 * @since  1.0
 */
public class PayloadConsumer extends QQBotClientReference implements Consumer<Payload> {
    public PayloadConsumer(QQBotClient client) {
        super(client);
    }

    @Override
    public void accept(Payload payload) {
        System.out.println("收到负载内容: " + payload.toJson());
    }
}
