/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.verve.netty.client.websocket.ari.event.json;

import com.google.gson.annotations.SerializedName;
import java.util.Map;
import lombok.Data;

/**
 *
 * @author avistech
 */
@Data
public class StasisJsonData {

    @SerializedName(value = "type")
    private String type;

    @SerializedName(value = "application")
    private String application;

    @SerializedName(value = "args")
    private Map<String, String> args;

    @SerializedName(value = "channel")
    private ChannelData channel;

    @SerializedName(value = "dialstatus")
    private String dialStatus;
}
