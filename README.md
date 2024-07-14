QQ机器人客户端(QQBotClient)
===============

## 项目说明

    这个项目遵循自由和开放源代码的许可证
    但使用、分发和修改本项目有可能涉及某些风险
    在使用、分发和修改本项目之前，请仔细阅读以下条款：

### 开发环境

* JDK21

### 免责声明

* 使用本项目时，不得违反相关法律法规。
* 使用本项目的用户对使用本项目产生的任何后果承担责任。
* 如果您打算对本项目进行商业使用，请务必咨询专业律师。

### 第三方开发者声明

* 本项目由本人，第三方开发者 [MovCloud](https://github.com/MovCloud) 开发和维护。
* 请注意，本人并非 [QQ开放平台](https://q.qq.com/) 官方团队成员
* 我的贡献和开发内容仅代表个人观点和技术实践，不代表 [QQ开放平台](https://q.qq.com/) 官方立场。
* 如您在使用本项目时遇到问题，请优先参考官方文档和社区支持。
* 在使用本项目时，请遵循官方的使用条款和许可协议。

### 注意事项

* 本项目使用 [QQ开放平台](https://q.qq.com/) 接口进行QQ机器人开发
* 请使用 [QQ开放平台](https://q.qq.com/) 机器人账号进行机器人开发，而非QQ账号
* 官方文档参考: [QQ机器人文档](https://bot.q.qq.com/wiki/)


### 如何合作

如果您对本项目有任何建议或意见欢迎提交Issue。

## qbc4j

    Java版QQ机器人客户端SDK
    QQ Bot Client For Java

### 使用说明

    引入Jar包后创建QQBotClient实例
    根据需要重写长连接负载处理器
    启动QQBotClient

#### 1.引入Jar包
```xml
    <dependencies>
        <!--  slf4j门户  -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>${slf4j.version}</version>
        </dependency>
        <!--  slf4j门户  -->
        <!--  日志实现  -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>${slf4j.version}</version>
        </dependency>
        <!--  日志实现  -->
        <!--  gson  -->
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>${gson.version}</version>
        </dependency>
        <!--  gson  -->
        <!--  QQBotClientForJava依赖  -->
        <dependency>
            <groupId>org.awp.qq.bot</groupId>
            <artifactId>qbc4j</artifactId>
            <version>1.0.0</version>
            <scope>system</scope>
            <systemPath>${pom.basedir}/src/main/resources/lib/qbc4j-1.0.0.jar</systemPath>
        </dependency>
        <!--  QQBotClientForJava依赖  -->
    </dependencies>
```
#### 2.配置并启动QQBotClient实例

```java
public class Main {
    public static void main(String[] args) {
        //此处以传入参数的方法作为示例，也可以自己写静态变量调试
        String appId = null, secretKey = null, token = null;
        for (String arg : args) {
            if (arg.startsWith("APP_ID=")) {
                appId = arg.substring(7);
            }
            if (arg.startsWith("SECRET_KEY=")) {
                secretKey = arg.substring(11);
            }
            if (arg.startsWith("TOKEN=")) {
                token = arg.substring(6);
            }
        }
        if (appId == null) {
            System.err.println("请传入APP_ID参数，格式为:  APP_ID=");
            return;
        }
        if (secretKey == null) {
            System.err.println("请传入SECRET_KEY参数，格式为:  SECRET_KEY=");
            return;
        }
        if (token == null) {
            System.err.println("请传入TOKEN参数，格式为:  TOKEN=");
            return;
        }

        QQBotClient client = startQQBotClient(Environment.DEV, appId, secretKey, token);
        System.out.println("启动QQ机器人客户端测试");
        try {
            Thread.sleep(Duration.ofSeconds(10));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //启动十秒后关闭
        client.shutdown();
        try {
            Thread.sleep(Duration.ofSeconds(3));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //停留一段时间确保日志全部打印完毕后关闭
    }

    public static QQBotClient startQQBotClient(Environment environment, String appId, String secretKey, String token) {
        //创建QQ机器人配置参数实例
        //设定Environment: 沙箱环境DEV/生产环境PROD
        //传入APPID、密钥、token以登录QQ开放平台
        QQBotConfiguration configuration = new QQBotConfiguration(environment, appId, secretKey, token);
        //配置需要监听的事件，如此处监听所有频道消息
        configuration.addEvents(Event.MESSAGE_CREATE);
        //通过配置实例创建机器人客户端实例
        QQBotClient client = new QQBotClient(configuration);
        //在使用下述方法处理负载的时候，请配置客户端使用的负载内容处理器，否则只会使用默认的打印方法
//        client.setConsumer(new TestPayloadConsumer(client));
        //启动客户端
        client.start();
        return client;
    }
}
```

#### 3.实现消息负载内容处理

    在本项目中，我将QQ机器人长连接中返回的消息内容封装为负载Payload
    其中数据内容为data变量，可以通过继承负载消费者对象PayloadConsumer
    重写其中的accept方法以自定义需要的对负载的处理方法
    同时，本项目中封装了一部分常用的HTTP接口，这些接口的类名以API结尾
    可以调用这些接口进行信息查询及消息回复的操作
    以下为一个简单的复读示例

* 请注意，在生产环境中，不建议直接如示例一般直接对所有的消息内容进行回复
* 推荐在收到消息时先筛选消息内容的前缀后再进行回复
* 或者仅对at了机器人的消息进行回复(可以通过监听Event.AT_MESSAGE_CREATE实现)

```java
public static class TestPayloadConsumer extends PayloadConsumer {

    public TestPayloadConsumer(QQBotClient client) {
        super(client);
    }

    @Override
    public void accept(Payload payload) {
        log.info("收到Dispatch");
        switch (payload.getType()) {
            //启动时开启心跳维持
            case "READY" -> {
                getClient().getConfiguration().setSessionId(payload.getData().getAsJsonObject().get("session_id").getAsString());
                getClient().startHeartbeat();
                log.info("长连接已启动");
            }
            case "MESSAGE_CREATE" -> {
                Message message = GsonUtil.GSON.fromJson(payload.getData(), Message.class);
                log.info("收到" + message.getAuthor().getUserName() + "的消息: " + message.getContent());
                Reply reply = new Reply();
                reply.setContent(message.getContent());
                MessageAPI.sendToChannel(getClient().getConfiguration(), message.getChannelId(), reply);
            }
            default -> {
                String type = payload.getType();
                log.info("收到未定义事件[" + payload.getSerialNumber() + "]: " + type);
                log.info(GsonUtil.PRETTY_GSON.toJson(payload));
            }
        }
    }
}
```

#### 4.更底层的实现方法

    如果使用封装的负载不能够满足你的需求，比如需要直接操作WebSocket对象
    那么也可以通过实现负载分发器PayloadDistributor来进行更进一步的定制化操作