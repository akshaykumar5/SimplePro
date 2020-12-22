/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.service;

import com.avis.startit.simplepro.json.bean.TenantContext;
import com.avis.startit.simplepro.model.Domain;
import com.avis.startit.simplepro.security.UserPrincipal;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author avistech
 */
@Log4j2
@Service
public class DomainService {

    public UserDetails loadByName(String name) {
        log.warn("name: {}", name);
        try {
            TenantContext.setCurrentTenant(name);
            if (name == null) {
                throw new UsernameNotFoundException("Detail not found");
            } else {
                Domain domain = new Domain();
                domain.setName(name);
                domain.setAuthToken("123456789");
                return new UserPrincipal(domain);
            }
        } catch (Exception e) {
            log.catching(Level.FATAL, e);
            throw new UsernameNotFoundException("Detail not found");
        }
    }

}
