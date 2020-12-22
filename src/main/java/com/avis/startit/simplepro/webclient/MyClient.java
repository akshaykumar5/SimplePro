/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.webclient;

import java.time.Duration;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 *
 * @author akshay
 */
@Log4j2
@Component
public class MyClient {

//    @Value("${ari.url}")
    String ariUrl;

//    @Value("${asterisk.username}")
//    String asteriskMgrUserName;
//    @Value("${asterisk.password}")
//    String asteriskMgrUserPassword;
    private WebClient webClient;

//    @Autowired
//    public MyClient(@Value("${ari.url}") String ariUrl,
//            @Value("${asterisk.username}") String asteriskMgrUserName,
//            @Value("${asterisk.password}") String asteriskMgrUserPassword,
//            WebClient.Builder webClientBuilder) {
//        this.ariUrl = ariUrl;
//        this.asteriskMgrUserName = asteriskMgrUserName;
//        this.asteriskMgrUserPassword = asteriskMgrUserPassword;
//        log.debug("ariUrl: {}", ariUrl);
//        log.debug("asteriskMgrUserName: {}", asteriskMgrUserName);
//        log.debug("asteriskMgrUserPassword: {}", asteriskMgrUserPassword);
//        webClient = webClientBuilder // you can also just use WebClient.builder()
//                .baseUrl(ariUrl)
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .filter(logRequest()) // here is the magic
//                .build();
//    }
    // Create WebClient instance using builder.
    // If you use spring-boot 2.0, the builder will be autoconfigured for you
    // with the "prototype" scope, meaning each injection point will receive
    // a newly cloned instance of the builder.
//    public MyClient(WebClient.Builder webClientBuilder) {
//        log.debug("ariUrl: {}", ariUrl);
//        log.debug("asteriskMgrUserName: {}", asteriskMgrUserName);
//        log.debug("asteriskMgrUserPassword: {}", asteriskMgrUserPassword);
//        webClient = webClientBuilder // you can also just use WebClient.builder()
//                .baseUrl("")
//                .filter(logRequest()) // here is the magic
//                .build();
//    }
//    // Just example of sending request
//    public String send(String path) {
//        ClientResponse clientResponse = webClient
//                .get().uri(uriBuilder -> uriBuilder.path(path)
//                .queryParam("param", "value")
//                .build())
//                .exchange()
//                .block();
//        ResponseEntity<String> response = clientResponse.toEntity(String.class).block();
//        log.info("Response: {}", response.toString());
//
//        return response.toString();
//    }
    // Just example of sending request
//    public String sendGetReq(String path) {
//        ClientResponse clientResponse = webClient
//                .get().uri(path)
//                .headers(headers -> headers.setBasicAuth(asteriskMgrUserName, asteriskMgrUserPassword))
//                .exchange()
//                .block();
//        ResponseEntity<String> response = clientResponse.toEntity(String.class).block();
//        log.info("Response: {}", response.toString());
//
//        return response.toString();
//    }
//    public String ariCommand(String url, String method) {
//        Mono<String> reponse = webClient
//                .method(HttpMethod.resolve(method))
//                .uri(url)
//                .headers(headers -> headers.setBasicAuth(asteriskMgrUserName, asteriskMgrUserPassword))
//                .exchange()
//                .block()
//                .bodyToMono(String.class);
//
//        return reponse.block();
//    }
//    public void sendReq(String path) {
//        webClient.post()
//                .uri(path)
//                .headers(headers -> headers.setBasicAuth(asteriskMgrUserName, asteriskMgrUserPassword))
//                .exchange().block();
////                .flatMap(clientResponse -> clientResponse.bodyToMono(String.class));
////        ResponseEntity<String> response = clientResponse.toEntity(String.class).block();
////        log.info("Response: {}", response.toString());
////
////        return response.toString();
//    }
////
//    public String sendDeleteReq(String path) {
//        Mono<String> response = webClient.delete()
//                .uri(path)
//                .headers(headers -> headers.setBasicAuth(asteriskMgrUserName, asteriskMgrUserPassword))
//                .exchange()
//                .flatMap(clientResponse -> clientResponse.bodyToMono(String.class));
//
////        ResponseEntity<String> response = clientResponse.toEntity(String.class).block();
////        log.info("Response: {}", response.toString());
//        return response.block();
//    }
//    // This method returns filter function which will log request data
//    private static ExchangeFilterFunction logRequest() {
//        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
//            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
//            clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.debug("{}={}", name, value)));
//            return Mono.just(clientRequest);
//        });
//    }
    public String sendGet(String path) {
        log.info("path: {}", path);
        Mono<String> reponse = webClient
                .method(HttpMethod.GET)
                .uri(path)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(String.class))
                .timeout(Duration.ofSeconds(30));
        log.warn("WAITING !!!!!!!!!!!!!!!!");
        String response = reponse.block();
        log.warn("WAITING DONE !!!!!!!!!!!!!!!!");
        log.warn("response :{}", response);

        return response;
    }

}
