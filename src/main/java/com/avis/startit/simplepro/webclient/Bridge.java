/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.webclient;

import lombok.Data;

/**
 *
 * @author akshay
 */
@Data
public class Bridge {

    private String id;
    private String technology;
    private String bridgeType;
    private String bridgeClass;
    private String creator;
    private Integer[] channels;
    private String creationTime;

}
