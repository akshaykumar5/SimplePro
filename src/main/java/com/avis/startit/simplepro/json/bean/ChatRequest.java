/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.json.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author akshay
 */
@Data
public class ChatRequest implements Serializable {

    @Expose(serialize = false, deserialize = false)
    private transient Long domainId;

    private String campaign;

    @Expose(serialize = false, deserialize = false)
    private transient Long campaignId;

    @Expose(serialize = false, deserialize = false)
    private transient Long queueId;

    @Expose(serialize = false, deserialize = false)
    private transient String accountCode;

    @Expose(serialize = false, deserialize = false)
    private transient Long listId;

    @Expose(serialize = false, deserialize = false)
    private transient Long queuePriority;

    private String domain;

    @SerializedName(value = "caller_id")
    private String callerId;

    @SerializedName(value = "user_name")
    private String userName;

    @SerializedName(value = "extension")
    private String extension;

    private String queue;
    private String lang;
    private String message;
    private Timestamp timestamp;
    private Date date;

    @SerializedName(value = "log_id")
    private String logId;
}
