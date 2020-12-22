/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author avistech
 */
@Log4j2
public class GlobalVariable {

    public final static Map<String, Object> LOCK_CONTEXT_MAP = new ConcurrentHashMap<>();

}
