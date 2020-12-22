/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.util;

import java.util.Arrays;

/**
 *
 * @author avistech
 */
public enum Status {
    ANSWER("ANSWER"),
    TRANSFER("TRANSFER"),
    INCALL("INCALL"),
    READY("READY"),
    ACW("ACW"),
    CONFER("CONFER"),
    DISPOSED("DISPOSED"),
    HANGUP("HANGUP"),
    SUCCESS("SUCCESS"),
    FAILURE("FAILURE");

    private final String VALUE;

    private Status(String value) {
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
    public static Status fromString(String s) throws IllegalArgumentException {
        return Arrays.stream(Status.values())
                .filter(v -> v.VALUE.equals(s))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unknown value: " + s));
    }
}
