/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.webclient.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 *
 * @author akshay
 */
@RestController
@Log4j2
public class WebClientController {

    @GetMapping("/log")
    public void log() {
        log.info("log request");
        ariCommand("http://localhost:2222/test", "GET");
    }
    @GetMapping("/test")
    public void test() {
                log.info("test request");

    }

    public String ariCommand(String url, String method) {
        WebClient webclient = WebClient.create();
        Mono<String> reponse = webclient
                .method(HttpMethod.resolve(method))
                .uri(url)
                .exchange()
                .block()
                .bodyToMono(String.class);

        return reponse.block();
    }
}
