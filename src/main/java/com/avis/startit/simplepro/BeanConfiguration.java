/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro;

import com.avis.startit.simplepro.json.bean.JsonRequesAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

/**
 *
 * @author avistech
 */
@Log4j2
@Configuration
public class BeanConfiguration {

//    @Primary
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
    @Autowired
    GsonBuilder gsonBuilder;
//

    @Bean
    @Qualifier("gsonWithAdpater")
    public Gson gson() {
        return gsonBuilder
                .setDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                .create();
    }
    @Autowired
    JsonRequesAdapter jsonRequesAdapter;
//
//
//    @Bean
//    @Qualifier("gsonWithStasisAdpater")
//    public Gson gsonWithStasisAdpater() {
//        return gsonBuilder
//                .registerTypeAdapter(Map.class, stasisRequestAdapter)
//                .create();
//    }
//    @Bean
//    WebClient.Builder getWebClientBuilder() {
//        return WebClient.builder();
//    }

    @Bean
    @Primary
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
//        executor.setQueueCapacity(10);
        executor.setMaxPoolSize(10);
        executor.setThreadNamePrefix("admin_request_task_executor_thread");
        executor.initialize();
        return executor;
    }

    @Bean
    public WebClient getWebClient() {
        HttpClient httpClient = HttpClient.create()
                .tcpConfiguration(client
                        -> client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                        .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(10))
                        .addHandlerLast(new WriteTimeoutHandler(10))));

        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);
        return WebClient.builder()
                .filter(logRequest())
                .clientConnector(connector)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    private ExchangeFilterFunction logRequest() {
        return (ClientRequest clientRequest, ExchangeFunction next) -> {
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers()
                    .forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
            return next.exchange(clientRequest);
        };
    }

}
