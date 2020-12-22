/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author akshay
 */
//@Lazy
@Log4j2
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TestObjectDestroyer implements DisposableBean {

    @PostConstruct
    public void init() {
        log.info("***************");
        log.info("In PostConstruct");
        log.info("***************");
    }

    public void printMsg(String msg) {
        log.info(msg);
    }

    @PreDestroy
    public void shutdown() {
        log.info("***************");
        log.info("In PreDestroy");
        log.info("***************");
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            log.info("Garbage collector called");
            log.info("Object garbage collected : {}", this);
//
//            System.out.println("Object destroyed of type"
//                    + this.getClass().toString());
        } finally {
            super.finalize();
        }
    }

    @Override
    public void destroy() throws Exception {
        log.info("***************");
        log.info("In destroy");
        log.info("***************");
    }

}
