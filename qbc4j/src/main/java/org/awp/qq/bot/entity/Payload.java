package org.awp.qq.bot.entity;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.awp.qq.bot.api.RequestAPI;
import org.awp.qq.bot.client.QQBotConfiguration;
import org.awp.qq.bot.util.GsonUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * WebSocket数据包
 *
 * @author MovCloud
 *
 * @date 2023/12/23
 *
 * @since  1.0
 */
public class Payload {

    public static Payload auth(QQBotConfiguration configuration, Intent... intents){
        Payload payload = new Payload();
        payload.setOperationCode(OperationCode.Identify);
        JsonObject data = new JsonObject();
        data.addProperty("token", RequestAPI.formatBotAuthorization(configuration));
        data.addProperty("intents", Intent.calculate(intents));
        payload.setData(data);
        return payload;
    }

    public static Payload auth(QQBotConfiguration configuration, Event... events){
        Payload payload = new Payload();
        payload.setOperationCode(OperationCode.Identify);
        JsonObject data = new JsonObject();
        data.addProperty("token", RequestAPI.formatBotAuthorization(configuration));
        data.addProperty("intents", Intent.calculate(events));
        payload.setData(data);
        return payload;
    }

    /**
     * 鉴权成功之后，就需要按照周期进行心跳发送
     * d为客户端收到的最新的消息的s
     * 如果是第一次连接，传null。
     */
    public static Payload heartbeat(Long latestSerialNumber){
        Payload payload = new Payload();
        payload.setOperationCode(OperationCode.Heartbeat);
        payload.setSerialNumber(latestSerialNumber);
        return payload;
    }


    /**
     * 操作码
     */
    @Expose
    @SerializedName("op")
    private Integer operationCode;

    /**
     * 数据包类型
     */
    @Expose
    @SerializedName("t")
    private String type;

    /**
     * 数据
     */
    @Expose
    @SerializedName("d")
    private JsonElement data;

    /**
     * 序列号
     */
    @Expose
    @SerializedName("s")
    private Long serialNumber;

    public Integer getCode() {
        return operationCode;
    }

    public void setCode(Integer code) {
        this.operationCode = code;
    }

    public OperationCode getOperationCode() {
        return OperationCode.valueOf(operationCode);
    }

    public void setOperationCode(OperationCode operationCode) {
        this.operationCode = operationCode.getCode();
    }

    public String getType() {
        return type;
    }

    public Event getEvent() {
        return Event.valueOf(type);
    }

    public void setType(String type) {
        this.type = type;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String toJson(){
        return GsonUtil.PRETTY_GSON.toJson(this);
    }

    /**
     * 操作码
     */
    public enum OperationCode{
        Dispatch(0, "服务端进行消息推送", Operation.Receive),
        Heartbeat(1, "客户端或服务端发送心跳", Operation.Send, Operation.Receive),
        Identify(2, "客户端发送鉴权", Operation.Send),
        Resume(6, "客户端恢复连接", Operation.Send),
        Reconnect(7, "服务端通知客户端重新连接", Operation.Receive),
        Invalid_Session(9, "当identify或resume的时候，如果参数有错，服务端会返回该消息", Operation.Receive),
        Hello(10, "当客户端与网关建立ws连接之后，网关下发的第一条消息", Operation.Receive),
        Heartbeat_ACK(11, "当发送心跳成功之后，就会收到该消息", Operation.Receive, Operation.Reply),
        HTTP_Callback_ACK(12, "仅用于 http 回调模式的回包，代表机器人收到了平台推送的数据", Operation.Reply),

        ;

        /**
         * 名称
         */
        private final Integer code;

        /**
         * 描述
         */
        private final String description;

        /**
         * 客户端操作类型
         */
        private final List<Operation> operations;

        OperationCode(Integer code, String description, Operation... operations) {
            this.operations = Arrays.asList(operations);
            this.code = code;
            this.description = description;
        }

        public String getName(){
            return name();
        }

        public Integer getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }

        public List<Operation> getOperations() {
            return operations;
        }

        public static OperationCode valueOf(Integer code){
            return Arrays.stream(values())
                    .filter(operationCode -> Objects.equals(operationCode.getCode(), code))
                    .findAny()
                    .orElse(null);
        }
    }

    /**
     * 客户端操作类型
     */
    public enum Operation{
        Receive,Send,Reply;
    }
}
