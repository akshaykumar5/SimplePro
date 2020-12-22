///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package com.avis.startit.simplepro;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.reactive.server.ReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.HttpHandler;

/**
 *
 * @author akshay
 */
@Configuration
@RequiredArgsConstructor
public class Config {

    @Value("${http.port}")
    @Getter
    private int httpPort;

    @Value("${server.port}")
    @Getter
    private Long serverPort;

    private WebServer http;
    private final HttpHandler httpHandler;

    @PostConstruct
    public void init() {
        ReactiveWebServerFactory factory = new NettyReactiveWebServerFactory(httpPort);
        this.http = factory.getWebServer(this.httpHandler);
        this.http.start();
    }

    @PreDestroy
    public void shutdown() {
        this.http.stop();
    }

}
