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
public enum Type {
    ADMIN_REQUEST("ADMIN-REQUEST"),
    MANAGER_REQUEST("MANAGER-REQUEST"),
    AGENT_REQUEST("AGENT-REQUEST"),
    REPORT_REQUEST("REPORT-REQUEST"),
    LICENSE_REQUEST("LICENSE-REQUEST"),
    //Stasis Type
    STASIS_START("StasisStart"),
    STASIS_END("StasisEnd"),
    CHANNEL_HANGUP_REQUEST("ChannelHangupRequest"),
    CHANNEL_DESTROYED("ChannelDestroyed"),
    DIAL("Dial"),
    PLAYBACK_STARTED("PlaybackStarted"),
    PLAYBACK_FINISHED("PlaybackFinished"),
    CHANNEL_DTMF_RECEIVED("ChannelDtmfReceived"),
    CHANNEL,
    TEST,
    RECORDING_FINISHED("RecordingFinished");

    private final String VALUE;

    private Type() {
        this.VALUE = this.name();
    }

    private Type(String value) {
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
    public static Type fromString(String s) throws IllegalArgumentException {
        return Arrays.stream(Type.values())
                .filter(v -> v.VALUE.equals(s))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unknown value: " + s));
    }
}
