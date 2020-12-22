/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.json.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author subhi
 */
@Data
@NoArgsConstructor
public class CallData {

    private Long listId = 0l;
    private Long campId = 0l;
    private String accountCode = "";
    private String phoneNumber = "";
    private String uniqueId = "";
    private String voiceFileFormat = "";
    private Long domainId = 0l;

}
