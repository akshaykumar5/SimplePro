/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.json.bean;

import static com.avis.startit.simplepro.util.Status.SUCCESS;
import static com.avis.startit.simplepro.util.Type.MANAGER_REQUEST;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 *
 * @author avistech
 */
@Data
public class JsonRequest<T> {

    @SerializedName(value = "type")
    private String type = MANAGER_REQUEST.getValue();

    private String query;

    @SerializedName(value = "domain_id", alternate = "domain")
    private Long domainId = 0l;

    private String response = SUCCESS.getValue();

    private T data;

    @SerializedName(value = "error")
    private ErrorData error;
}
