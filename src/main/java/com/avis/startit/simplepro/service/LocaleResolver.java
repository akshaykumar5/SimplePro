/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.service;

import java.util.Locale;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.i18n.SimpleLocaleContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author akshay
 */
@Component
@Log4j2
public class LocaleResolver {

    public Locale resolveLocaleContext(String lang) {
        if (lang == null) {
            return LocaleContextHolder.getLocale(new SimpleLocaleContext(Locale.ENGLISH));
        } else {
            Locale locale = Locale.forLanguageTag(lang);
            log.debug("locale: {}", locale);
            return LocaleContextHolder.getLocale(new SimpleLocaleContext(locale));
        }
    }

}
