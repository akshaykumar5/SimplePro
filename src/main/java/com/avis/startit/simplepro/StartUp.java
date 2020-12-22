/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro;

import com.avis.startit.simplepro.mqtt.client.MqttClient;
import com.avis.startit.simplepro.service.TestObjectDestroyer;
import javafx.application.Application;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

/**
 *
 * @author akshay
 */
@Component
@Log4j2
public class StartUp implements CommandLineRunner {

    public static final String VERSION = "V1.0.0.0 14-FEB-2019";

    @Autowired
    MqttClient mqttClient;

    @Autowired
//    @Qualifier(value = "taskExecutor")
    private TaskExecutor taskExecutor;
//    @Autowired
//    WebClientService webClient;
//    @Autowired
//    MyClient webClient;

    private volatile int num = 0;

    @Override
    public void run(String... args) throws Exception {

        log.info("********************************************************************");
        log.info("*************DEMO MQTT Version: {}*************", VERSION);
        log.info("********************************************************************\n\n\n\n");
//
//        initialize mqtt client
//        mqttClient.init();
//        Mono<String> mjb = webClient.getBridges();
//        log.info("MoJo String : ", mjb);
//        webClient.send("bridges");
//        Runnable runnable = () -> {
//            try {
//                log.info("Start: {}", Thread.currentThread().getName());
//                TimeUnit.MILLISECONDS.sleep(2000);
//                log.info("Stop: {}", Thread.currentThread().getName());
//            } catch (Exception e) {
//            }
//        };
//        for (int i = 0; i < 31; i++) {
//            taskExecutor.execute(runnable);
//        }
//        log.info("Size: {} ", taskExecutor);
    }

}
