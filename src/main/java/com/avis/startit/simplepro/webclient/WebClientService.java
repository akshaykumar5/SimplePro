///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.avis.startit.simplepro.webclient;
//
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.MediaType;
//import static org.springframework.http.RequestEntity.options;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
///**
// *
// * @author akshay
// */
//@Log4j2
////@Component
//public class WebClientService {
//
//    @Value("${ari.base.url}")
//    private String url;
//
////    @Value("${ari.test.url.1}")
////    private String ariTestUrl1;
////
////    @Value("${ari.test.url.1}")
////    private String ariTestUrl2;
//    private final WebClient webClient;
//
//    public WebClientService(WebClient.Builder webClientBuilder) {
//        log.info("Ari Base url : {}", url);
//        this.webClient = webClientBuilder.baseUrl("http://192.168.1.46:8088/").build();
//    }
//
//    public Mono<Bridge> ariCommands(String type) {
//        return this.webClient.get().uri("{}", type)
//                .retrieve().bodyToMono(Bridge.class);
//    }
//
////    Mono<Person> result = client.get()
////            .uri("/persons/{id}", id).accept(MediaType.APPLICATION_JSON)
////            .exchange()
////            .flatMap(response -> response.bodyToMono(Person.class));
//
//    public Mono<Bridge> allBridges() {
//        return this.webClient.get().uri("bridges/")
//                .retrieve().bodyToMono(Bridge.class);
//    }
//
//    public Mono<String> getBridges() {
//        Mono<String> result = webClient.post()
//                .uri("/bridges/")
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .flatMap(clientResponse -> clientResponse.bodyToMono(String.class));
//        return result;
//    }
//}
