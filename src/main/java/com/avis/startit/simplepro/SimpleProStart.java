/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author akshay
 */
@SpringBootApplication
@Log4j2
//@EnableAutoConfiguration(exclude = {JacksonAutoConfiguration.class})
public class SimpleProStart {

    public static void main(String[] args) {
//        SpringApplication.run(SimpleProStart.class, args);
        ConfigurableApplicationContext context = SpringApplication.run(SimpleProStart.class, args);
//        context.addApplicationListener(listener);
        context.registerShutdownHook();
    }
}
