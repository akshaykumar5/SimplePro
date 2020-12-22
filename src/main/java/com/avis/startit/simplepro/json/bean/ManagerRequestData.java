/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.json.bean;

import com.google.gson.annotations.SerializedName;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author avistech
 */
@Data
@NoArgsConstructor
@Component
public class ManagerRequestData implements ChatData {

    @SerializedName(value = "id")
    private Long id;

    @SerializedName(value = "action")
    private String action;

    @SerializedName(value = "account_code", alternate = "accountCode")
    private String accountCode;

    @SerializedName(value = "conf_account_code", alternate = "confAccountCode")
    private String confAccountCode;

    @SerializedName(value = "user_name", alternate = "userName")
    private String userName;

    @SerializedName(value = "record_id", alternate = "recordId")
    private Long recordId;

    @SerializedName(value = "unique_id", alternate = "uniqueId")
    private String uniqueId;

    @SerializedName(value = "interaction_type", alternate = "interactionType")
    private String interactionType;

    @SerializedName(value = "queue_id", alternate = "queueId")
    private Long queueId;

    @SerializedName(value = "list_id", alternate = "listId")
    private Long listId;

    private Integer line;

    @SerializedName(value = "agent_skill", alternate = "agentSkill")
    private String skill;

    @SerializedName(value = "domain_name", alternate = "domain")
    private String domain;

    @SerializedName(value = "media_url", alternate = {"mediaUrl"})
    private String mediaUrl;

    @SerializedName(value = "media_type", alternate = {"mediaType", "mimeType", "mime_type"})
    private String mimeType;

    @SerializedName(value = "channel", alternate = {"channel_name", "channelName"})
    private String channelName;

    @SerializedName(value = "agent_channel_id", alternate = "agentChannelId")
    private String agentChannelId;

    @SerializedName(value = "customer_channel_id", alternate = "customerChannelId")
    private String customerChannelId;

    @SerializedName(value = "conf_channel_id", alternate = "confChannelId")
    private String confChannelId;

    @SerializedName(value = "file_name", alternate = "fileName")
    private String fileName;

    @SerializedName(value = "file_exten", alternate = "fileExten")
    private String fileExten;

    @SerializedName(value = "call_answer_date", alternate = "callAnswerDate")
    private Date callAnswerDate;

    @SerializedName(value = "current_epoch", alternate = "currentEpoch")
    private Long currentEpoch;

    @SerializedName(value = "conf_phone_number", alternate = "confPhoneNumber")
    private String confPhoneNumber;
}
