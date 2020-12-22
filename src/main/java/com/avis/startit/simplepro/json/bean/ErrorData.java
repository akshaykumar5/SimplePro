/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.json.bean;

import lombok.Data;

/**
 *
 * @author avistech
 */
@Data
public class ErrorData {

    private Integer code;
    private String message;
    private String description;
}
