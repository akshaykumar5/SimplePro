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
public class ChannelInfo {

    @SerializedName(value = "name")
    private String name;

    @SerializedName(value = "number")
    private String number;
}
