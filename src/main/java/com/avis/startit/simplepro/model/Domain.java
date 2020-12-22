/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.model;

import lombok.Data;

/**
 *
 * @author avistech
 */
@Data
public class Domain {

    private Long id;
    private String name;
    private String serverTrunkType;
    private String forceLogin;
    private Long interval;
    private String authToken;
}
