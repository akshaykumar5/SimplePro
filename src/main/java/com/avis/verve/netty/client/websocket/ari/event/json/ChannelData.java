/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.verve.netty.client.websocket.ari.event.json;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 *
 * @author avistech
 */
@Data
public class ChannelData {

    @SerializedName(value = "id")
    private String id;

    @SerializedName(value = "name")
    private String name;

    @SerializedName(value = "state")
    private String state;

    @SerializedName(value = "caller")
    private ChannelInfo caller;

    @SerializedName(value = "connected")
    private ChannelInfo connected;

    @SerializedName(value = "accountcode")
    private String accountCode;

    @SerializedName(value = "dialplan")
    private DialPlan dialPlan;

    @SerializedName(value = "creationtime")
    private String creationTime;
}
