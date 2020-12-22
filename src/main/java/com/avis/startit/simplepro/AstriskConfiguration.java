/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author avistech
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "asterisk.manager")
public class AstriskConfiguration {

    private Integer port;
    private String username;
    private String secret;
    private Integer webHttp;
    private Integer webHttps;
    private String webUsername;
    private String webPassword;
    private String dsn;
    private Integer maxConnection;
}
