/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.mqtt.client;

import com.avis.startit.simplepro.json.bean.AgentData;
import com.avis.startit.simplepro.json.bean.AgentRequestData;
import com.avis.startit.simplepro.json.bean.JsonRequest;
import com.avis.startit.simplepro.webclient.MyClient;
import com.avis.startit.simplepro.mqtt.util.Mqtt;
import static com.avis.startit.simplepro.mqtt.util.Mqtt.S_QOS;
import com.avis.startit.simplepro.mqtt.util.Topic;
import static com.avis.startit.simplepro.mqtt.util.Topic.TESTING;
import com.avis.startit.simplepro.service.WaitNotify;
import com.google.gson.Gson;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

/**
 *
 * @author akshay
 */
@Log4j2
@Component
public class MqttClient implements MqttCallbackExtended {

    @Autowired
    ApplicationContext applicationContext;

    @Value("${mqtt.serverUrl}")
    private String mqttServerAddress;//tcp://192.168.1.46:1883";

    @Value("${mqtt.automaticReconnect}")
    private Boolean automaticReconnect;

    @Value("${mqtt.cleanSession}")
    private Boolean cleanSession;

    @Value("${mqtt.connectionTimeout}")
    private Integer connectionTimeout;

    @Autowired
    MyClient webClient;

    @Autowired
    @Qualifier(value = "gsonWithAdpater")
    Gson gson;

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private WaitNotify waitNotify;

    private IMqttAsyncClient instance;
    MemoryPersistence persistence = new MemoryPersistence();

    public void init() {
        try {
            if (this.instance == null) {
                log.debug("initializing mqtt instance");
                this.instance = new MqttAsyncClient(mqttServerAddress, Mqtt.MQTT_PUBLISHER_ID + MqttAsyncClient.generateClientId(), new MemoryPersistence());
                //set this as a callback so that we get all mqtt event within same class (e.g. connectComplete, messageArrived e.t.c.)
                this.instance.setCallback(this);
            }

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(automaticReconnect);
            options.setCleanSession(cleanSession);
            options.setConnectionTimeout(connectionTimeout);

            if (!this.instance.isConnected()) {
                log.debug("connecting mqtt instance");
                this.instance.connect(options);
            }
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }

    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        log.debug("MQTT client Connected to Admin Broker at serverURI: [{}], reconnect: {}", serverURI, reconnect);
//        testTopic();
        if (reconnect) {
            if (this.instance != null && this.instance.isConnected()) {
                log.debug("Reconnected MQTT client: id:[{}]", this.instance.getClientId());
                this.subscribe(TESTING.getValue());
//                subscribe(SHARE_GROUP.getValue() + "1");
            } else {
                log.warn("MQTT Client not connected running at serverURI: [{}]", serverURI);
            }
        } else {
            log.debug("Connected MQTT Client:[{}]", this.instance.getClientId());
            subscribe(TESTING.getValue());
            subscribe(Topic.WAIT_NOTIFY.getValue());
//            log.info("group: {}", SHARE_GROUP.getValue());
//            subscribe(SHARE_GROUP.getValue() + "1");
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        log.error("MQTT connection lost", cause);
    }

    @Override
    public void messageArrived(String topic, MqttMessage payload) throws Exception {
        log.info("messageArrived");
        if (payload == null || payload.toString().isEmpty()) {
            log.warn("MQTT received Message is null or empty for topic:[{}]", topic);
            return;
        }
        log.debug("VM topic:({}) ----- Message: {}", topic, payload.toString());
        switch (Topic.fromString(topic)) {
            case TESTING: {
                if (payload.toString().startsWith("GET-")) {
//                    String str = webClient.ariCommand(payload.toString().substring(4, payload.toString().length()), "GET");
//                    log.info("Response of {} ==>> {}", payload.toString().substring(4, payload.toString().length()), str);
                } else {
//                    webClient.ariCommand(payload.toString(), "POST");
//                    log.warn("**************************************************");
//                    log.warn("**************************************************");
//                    log.warn("**************************************************");
//                    log.warn("**************************************************");
//                    log.info("Response of {} ==>> {}", payload, response);

                    JsonRequest jsonRequest = gson.fromJson(payload.toString(), JsonRequest.class);
                    log.info("JsonRequest : {}", jsonRequest);

                    AgentRequestData agentRequestData = (AgentRequestData) jsonRequest.getData();
                    log.info("agentRequestData : {}", agentRequestData);

//                    CallData callData = new CallData();
                    AgentData agentData = new AgentData();
//                    BeanUtils.copyProperties(agentRequestData, callData);
                    BeanUtils.copyProperties(agentRequestData, agentData);

//                    log.info("callData : {}", callData);
                    log.info("AgentData : {}", agentData);

                    String serverData = gson.toJson(agentData);
                    log.info("serverData : {}", serverData);

                    Map map = gson.fromJson(serverData, Map.class);
                    log.info("map : {}", map);

//                    AstriskConfiguration conf = applicationContext.getBean(AstriskConfiguration.class);
//                    log.info(conf);
                }

            }
            case WAIT_NOTIFY: {
                log.info("IN wait notify");
                JsonRequest jsonRequest = gson.fromJson(payload.toString(), JsonRequest.class);
                log.info("JsonRequest : {}", jsonRequest);

                AgentRequestData data = (AgentRequestData) jsonRequest.getData();
                log.info("agentRequestData : {}", data);

                if (data.getAction().equalsIgnoreCase("ENCRYPT")) {
                    waitNotify.wait(data.getActionId());
                } else {
                    waitNotify.notify(data.getActionId());
                }

            }
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken
    ) {
        try {
            if (iMqttDeliveryToken.getMessage() != null) {
                log.trace("MQTT Message deliveryComplete: ", iMqttDeliveryToken.getMessage().toString());
            } else {
                log.trace("MQTT Message deliveryComplete");
            }
        } catch (Exception e) {
            log.catching(Level.ERROR, e);
        }
    }

    private void subscribe(String topic) {
        this.subscribe(topic, S_QOS.getInitial());
    }

    public void subscribe(String topic, int sQoS) {
        log.info("In Subscribe");
        log.debug("Subscribing Topic Name: {}, MqttClient Id: {} ", topic, this.instance.getClientId());
        try {
            if (this.instance != null && this.instance.isConnected()) {
                log.info("MQTT client is connected.");
                log.info("Subscribe : qos {}", sQoS);

                this.instance.subscribe(topic, sQoS);
            } else {
                log.warn("MQTT client is not connected. Reconnect it...");
                init();
            }
        } catch (MqttException e) {
            log.catching(Level.ERROR, e);
        }
    }

    private void testTopic() {
        log.info("***************************************************************");
        log.info("***************************************************************");
        log.info("***************************************************************");
        try {
//            String string1023 = new String(Files.readAllBytes(Paths.get("config/topics/1023.txt")));
//            string1023 = string1023.replaceAll("\\s", "");
//            log.debug("string1023 length: {}", string1023.length());
//            subscribe(string1023);
//            String string2047 = new String(Files.readAllBytes(Paths.get("config/topics/2047.txt")));
//            string2047 = string2047.replaceAll("\\s", "");
//            log.debug("string2047 length: {}", string2047.length());
//            subscribe(string2047);
//            String string8191 = new String(Files.readAllBytes(Paths.get("config/topics/8191.txt")));
//            string8191 = string8191.replaceAll("\\s", "");
//            log.debug("string8191 length: {}", string8191.length());
//            subscribe(string8191);
//            String string16381 = new String(Files.readAllBytes(Paths.get("config/topics/16381.txt")));
//            string16381 = string16381.replaceAll("\\s", "");
//            log.debug("string16381 length: {}", string16381.length());
//            subscribe(string16381);
            String string65536 = new String(Files.readAllBytes(Paths.get("config/topics/65536.txt")));
            string65536 = string65536.replaceAll("\\s", "");
            log.debug("string65536 length: {}", string65536.length());
            subscribe(string65536);
        } catch (Exception e) {
            log.catching(e);
        }

        log.info("***************************************************************");
        log.info("***************************************************************");
        log.info("***************************************************************");
    }
}
