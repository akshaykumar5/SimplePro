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
public class AgentData {

    private String name = "";
    private String extension = "";
    private String domainName = "";

}
