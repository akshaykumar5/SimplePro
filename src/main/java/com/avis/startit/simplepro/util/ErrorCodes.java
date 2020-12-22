/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.util;

import java.util.Arrays;
import lombok.extern.log4j.Log4j2;

/**
 * Provides the enumeration for errorCodes.
 *
 * @author akshay
 */
@Log4j2
public enum ErrorCodes {
    phoneno_uniqueId_no_data_found(100),
    alert_camp_name_not_blank(101),
    alert_accept_reject_timeout_not_blank(102),
    max_call_limit_reached(103);

    private final Integer VALUE;

    private ErrorCodes(Integer value) {
        this.VALUE = value;
    }

    public Integer getValue() {
        return VALUE;
    }

    public static ErrorCodes getContext(String key) {
        try {
            return ErrorCodes.valueOf(key);
        } catch (IllegalArgumentException e) {
            log.trace("Key not found : {}", key);
        }
        return null;
    }
}
