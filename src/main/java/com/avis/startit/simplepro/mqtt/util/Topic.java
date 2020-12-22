/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.mqtt.util;

import java.util.Arrays;

/**
 *
 * @author avistech
 */
public enum Topic {
    SHARE_GROUP("$share/vmverve/"), SETTING("SETTING"), TESTING("TESTING"), WAIT_NOTIFY("WAIT-NOTIFY");

    private final String VALUE;

    Topic(String value) {
        this.VALUE = value;
    }

    public String getValue() {
        return VALUE;
    }

    /**
     * @param s
     * @return the Enum representation for the given string.
     * @throws IllegalArgumentException if unknown string.
     */
    public static Topic fromString(String s) throws IllegalArgumentException {
        return Arrays.stream(Topic.values())
                .filter(v -> v.VALUE.equals(s))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unknown value: " + s));
    }
}
