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
public class DialPlan {

    @SerializedName(value = "context")
    private String context;

    @SerializedName(value = "exten")
    private String exten;

    @SerializedName(value = "priority")
    private Integer priority;

    @SerializedName(value = "app_name")
    private String appName;

    @SerializedName(value = "app_data")
    private String appData;
}
