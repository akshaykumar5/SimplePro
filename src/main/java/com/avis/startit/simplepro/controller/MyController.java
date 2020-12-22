/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.controller;

import com.avis.startit.simplepro.json.bean.ChatRequest;
import com.avis.startit.simplepro.json.bean.JsonRequest;
import com.avis.startit.simplepro.service.TestObjectDestroyer;
import com.avis.startit.simplepro.service.Translator;
import com.avis.startit.simplepro.util.Util;
import com.avis.startit.simplepro.webclient.MyClient;
import com.google.gson.Gson;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author akshay
 */
@RestController
@CrossOrigin
@RequestMapping(path = "/testing")
@Log4j2
public class MyController {

    @Autowired
    Gson gson;

    @Autowired
    Translator translator;

    @Autowired
    Util util;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    MyClient myClient;
//
//    @Lazy
//    @Autowired
//    TestObjectDestroyer testObjectDestroyer;

    @PostMapping(path = "/start", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> startChat(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @RequestBody String chatRequest) throws Exception {
        Map<String, Object> dataMap = gson.fromJson(chatRequest, Map.class);
        log.warn("authorization = " + authorization);
        log.warn("dataMap--->{}", dataMap);

        return ResponseEntity.accepted().body(dataMap);
    }

    @RequestMapping(value = {"/login", "/force-login"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> login(@RequestBody ChatRequest chatRequest, ServerHttpRequest request) throws Exception {
        Map<String, Object> dataMap = gson.fromJson(gson.toJson(chatRequest), Map.class);

        log.warn("request path --->{}", request.getPath());
        log.warn("request uri --->{}", request.getURI());
        log.warn("request--->{}", request);
        log.warn("dataMap--->{}", dataMap);

        return ResponseEntity.accepted().body(dataMap);
    }

    @GetMapping("/get-base-url")
    public ResponseEntity<String> getBaseUrl(@RequestHeader HttpHeaders headers) {
        InetSocketAddress host = headers.getHost();
        String url = "http://" + host.getHostName() + ":" + host.getPort();
        return new ResponseEntity<>(String.format("Base URL = %s", url), HttpStatus.OK);
    }

    @GetMapping("/nonRequiredHeader")
    public ResponseEntity<String> evaluateNonRequiredHeader(
            @RequestHeader(value = "optional-header", required = false, defaultValue = "helloo") String optionalHeader) {
        return new ResponseEntity<>(String.format(
                "Was the optional header present? %s!",
                (optionalHeader == null ? "No" : "Yes")), HttpStatus.OK);
    }

    @GetMapping("/test-lang")
    public ResponseEntity<Object> testLang(@RequestBody String dataMap) {
        log.warn("dataMap--->{}", dataMap);
        ChatRequest chatRequest = gson.fromJson(dataMap, ChatRequest.class);
        String lang = chatRequest.getLang();
        log.warn("lang--->{}", lang);
        String message = chatRequest.getMessage();

        log.warn("message--->{}", message);
        log.warn("timestamp--->{}", chatRequest.getTimestamp());
        log.warn("date--->{}", chatRequest.getDate());

        JsonRequest request = util.buildErrorMsg(message, lang);

        return ResponseEntity.accepted().body(gson.toJson(request));
    }

    @GetMapping("/destroy-object")
    public ResponseEntity<Object> destroyObject(@RequestBody String dataMap) {
        TestObjectDestroyer testObjectDestroyer = applicationContext.getBean(TestObjectDestroyer.class);
//        TestObjectDestroyer testObjectDestroyer = new TestObjectDestroyer();

        testObjectDestroyer.printMsg("I am calling print Message");

        ((ConfigurableApplicationContext) applicationContext).close();
//        testObjectDestroyer = null;
//        System.gc();
//        Runtime.getRuntime().gc();
        return ResponseEntity.accepted().body("SUCCESS");
    }

    @GetMapping("/timeout")
    public ResponseEntity<Object> testTimeout(@RequestParam Integer timeout) {
        log.warn("timeout: {}", timeout);
//        String urlString = "http://localhost:8088/testing/timeout-response?timeout=" + timeout;
//        log.warn("urlString: {}", urlString);
//        String response = myClient.sendGet(urlString);
//        log.warn("response: {}", response);

        return ResponseEntity.accepted().body("SUCCESS");
    }

    @GetMapping("/timeout-response")
    public ResponseEntity<Object> testTimeoutResponse(@RequestParam Integer timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }

        return ResponseEntity.accepted().body("SUCCESS");
    }

    @GetMapping("/test-get-array")
    public ResponseEntity<Object> testArrayOfValues(@RequestParam(name = "accountcodes") List<String> accountCodes) {
        // Handle values here
        log.debug("accountCodes : {}", accountCodes);

        return ResponseEntity.accepted().body(accountCodes);
    }

}
