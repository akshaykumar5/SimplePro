/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 *
 * @author akshay
 */
@Service
public class Translator {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver customLocaleResolver;

    public String getMessage(String code, String language) {
        return messageSource.getMessage(code, null, customLocaleResolver.resolveLocaleContext(language));
    }
}
