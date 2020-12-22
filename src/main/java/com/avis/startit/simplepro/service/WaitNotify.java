/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.service;

import com.avis.startit.simplepro.util.GlobalVariable;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 *
 * @author Akshay
 */
@Log4j2
@Component
public class WaitNotify {

    public void wait(String actionId) {
        log.debug("actionId: {}", actionId);
        Object lock = new Object();
        synchronized (this) {
            GlobalVariable.LOCK_CONTEXT_MAP.put(actionId, lock);
            try {
                log.info("WAITING !!!!!!!!!!!!!!!!!!!!!!!!!");
                lock.wait(10000);
                log.info("RELEASED !!!!!!!!!!!!!!!!!!!!!!!!!");
            } catch (Exception e) {
                log.catching(e);
            }
        }
    }

    public void notify(String actionId) {
        log.debug("actionId: {}", actionId);
        synchronized (this) {
            Object lock = GlobalVariable.LOCK_CONTEXT_MAP.get(actionId);
            try {
                log.info("RELEASING !!!!!!!!!!!!!!!!!!!!!!!!!");
                lock.notify();
                GlobalVariable.LOCK_CONTEXT_MAP.remove(actionId);
            } catch (Exception e) {
                log.catching(e);
            }
        }
    }
}
