/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.json.bean;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author avistech
 */
@Data
@NoArgsConstructor
public class AgentRequestData implements ChatData {

    @SerializedName(value = "agent_id")
    private Long agentId;

    @SerializedName(value = "user_name")
    private String userName;

    @SerializedName(value = "full_name")
    private String fullName;

    private String extension;

    @SerializedName(value = "extension_type")
    private String extensionType;

    @SerializedName(value = "extension_trunk_server_id")
    private Integer extensionTrunkServerId;

    @SerializedName(value = "extension_trunk_type")
    private String extensionTrunkType;

    @SerializedName(value = "extension_trunk_name")
    private String extensionTrunkName;

    @SerializedName(value = "extension_dial_prefix")
    private String extensionDialPrefix;

    @SerializedName(value = "extension_dahdi_prefix")
    private String extensionDahdiPrefix;

    @SerializedName(value = "extension_sip_prefix")
    private String extensionSipPrefix;

    @SerializedName(value = "phone_number")
    private String phoneNumber;

    @SerializedName(value = "new_extension")
    private String newExtension;

    @SerializedName(value = "domain_name")
    private String domainName;

    @SerializedName(value = "domain_id")
    private Long domainId;

    @SerializedName(value = "accept_reject_timeout")
    private Integer acceptRejectTimeout;

    @SerializedName(value = "account_code")
    private String accountCode;

    @SerializedName(value = "unique_id")
    private String uniqueId;

    @SerializedName(value = "queue_id")
    private Long queueId;

    @SerializedName(value = "list_id")
    private Long listId;

    @SerializedName(value = "camp_id")
    private Long campId;

    @SerializedName(value = "skill_name")
    private String skillName;

    private Integer line;

    @SerializedName(value = "disposition_type")
    private String dispositionType;

    @SerializedName(value = "disposition_name")
    private String dispositionName;

    @SerializedName(value = "disposition_description")
    private String dispositionDescription;

    @SerializedName(value = "is_ready_for_call")
    private Boolean isReadyForCall = true;

    @SerializedName(value = "interaction_type")
    private String interactionType;

    @SerializedName(value = "phone_code")
    private String phoneCode;

    @SerializedName(value = "dnc_check")
    private String dncCheck;

    @SerializedName(value = "dnc_type")
    private String dncType;

    @SerializedName(value = "phone_count")
    private Integer phoneCount;

    @SerializedName(value = "unique_column_name")
    private String uniqueColumnName;

    @SerializedName(value = "table_name")
    private String tableName;

    @SerializedName(value = "country_id")
    private Integer countryId;

    @SerializedName(value = "trunk_dial")
    private String trunkDial;

    @SerializedName(value = "call_recording")
    private String callRecording;

    @SerializedName(value = "preview_unique_id")
    private String previewUniqueId;

    @SerializedName(value = "dial_code")
    private String dialCode;

    @SerializedName(value = "trunk_group_details")
    private JsonArray trunkGroupDetails;

    @SerializedName(value = "action")
    private String action;

    @SerializedName(value = "action_id")
    private String actionId;

}
