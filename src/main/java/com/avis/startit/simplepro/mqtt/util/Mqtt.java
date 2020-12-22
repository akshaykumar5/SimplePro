/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.mqtt.util;

/**
 *
 * @author avistech
 */
public enum Mqtt {
    MQTT_PUBLISHER_ID("vm-mqtt-client"), S_QOS(2);

    private final boolean STATUS;
    private final int INITIAL;
    private final String VALUE;

    Mqtt(boolean status) {
        this.STATUS = status;
        this.INITIAL = 2;
        this.VALUE = "";
    }

    Mqtt(int initial) {
        this.STATUS = false;
        this.INITIAL = initial;
        this.VALUE = "";
    }

    Mqtt(String value) {
        this.STATUS = false;
        this.INITIAL = 2;
        this.VALUE = value;
    }

    public boolean isStatus() {
        return STATUS;
    }

    public int getInitial() {
        return INITIAL;
    }

    public String getValue() {
        return VALUE;
    }
}
