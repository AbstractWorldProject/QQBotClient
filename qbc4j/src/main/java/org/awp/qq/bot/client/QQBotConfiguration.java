package org.awp.qq.bot.client;

import com.google.gson.JsonParser;
import org.awp.qq.bot.api.RequestAPI;
import org.awp.qq.bot.entity.Environment;
import org.awp.qq.bot.entity.Event;
import org.awp.qq.bot.entity.Intent;
import org.awp.qq.bot.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 会话信息
 *
 * @author MovCloud
 *
 * @date 2023/12/24
 *
 * @since  1.0
 */
public class QQBotConfiguration {
    private final Environment environment;
    private final String appId;
    private final String secret;
    private final String token;
    private Shard shard;
    private String sessionId;
    private Long serial;
    private String gatewayUrl;
    private Long heartbeatInterval;
    private User botUserInfo;
    private List<Intent> intentList;

    public QQBotConfiguration(Environment environment, String appId, String secret, String token) {
        this.environment = environment;
        this.appId = appId;
        this.secret = secret;
        this.token = token;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public String getAppId() {
        return appId;
    }

    public String getSecret() {
        return secret;
    }

    public String getToken() {
        return token;
    }

    public Shard getShard() {
        return shard;
    }

    public void setShard(Shard shard) {
        this.shard = shard;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }

    public User getBotUserInfo() {
        return botUserInfo;
    }

    public String getGatewayUrl() {
        if (gatewayUrl == null){
            gatewayUrl = getGatewayUrl(this);
        }
        return gatewayUrl;
    }

    public Long getHeartbeatInterval() {
        if (heartbeatInterval == null) {
            heartbeatInterval = TimeUnit.SECONDS.toMillis(30);
        }
        return heartbeatInterval;
    }

    public void setHeartbeatInterval(Long heartbeatInterval) {
        this.heartbeatInterval = heartbeatInterval;
    }

    public void setBotUserInfo(User botUserInfo) {
        this.botUserInfo = botUserInfo;
    }

    public String getAuthorization(){
        return "Bot " + getAppId() + "." + getToken();
    }

    public void setEvents(Event... events) {
        setIntentList(Arrays.stream(events).map(Event::getIntent).distinct().toList());
    }

    public void addEvents(Event... events) {
        for (Event event : events) {
            addIntents(event.getIntent());
        }
    }

    public List<Intent> getIntentList() {
        if (intentList == null){
            intentList = new ArrayList<>();
        }
        return intentList;
    }

    public void setIntentList(List<Intent> intentList) {
        this.intentList = intentList;
    }

    public Intent[] getIntents() {
        setIntentList(getIntentList().stream().distinct().collect(Collectors.toList()));
        return getIntentList().toArray(new Intent[0]);
    }

    public void setIntents(Intent... intents) {
        setIntentList(Arrays.asList(intents));
    }

    public void addIntents(Intent... intents){
        getIntentList().addAll(Arrays.asList(intents));
    }

    public static class Shard {
        public Shard SINGLE = new Shard(0, 0);

        private Integer index;
        private Integer count;

        public Shard(Integer index, Integer count) {
            this.index = index;
            this.count = count;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public List<Integer> toList(){
            return new ArrayList<>(){{
                add(getIndex());
                add(getCount());
            }};
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
