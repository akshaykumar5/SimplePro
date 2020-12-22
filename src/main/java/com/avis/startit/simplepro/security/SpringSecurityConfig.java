/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.security;

import com.avis.startit.simplepro.service.DomainService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 *
 * @author avistech
 */
@Log4j2
@Configuration
@EnableWebFluxSecurity
public class SpringSecurityConfig {

    private static final String[] AUTH_WHITELIST = {
        "/api/**",
        "/testing/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PlainPasswordEncoder();
    }

    @Bean
    public ReactiveUserDetailsService mapReactiveUserDetailsService(DomainService domainService) {
        return (domainname) -> Mono.just(domainService.loadByName(domainname));
    }

//    @Bean
//    public MapReactiveUserDetailsService userDetailsService() {
//        UserDetails user = User
//                .withUsername("user")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//        return new MapReactiveUserDetailsService(user);
//    }
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        log.debug("http: {}", http.headers());
//        return http
//                .cors().and()
//                .csrf().disable()
//                .authorizeExchange().pathMatchers(AUTH_WHITELIST).permitAll()
//                .anyExchange().authenticated()
//                .and()
//                .httpBasic()
//                .and()
//                .formLogin().disable()
//                .build();
//    }
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) { // todo
        log.debug("http: {}", http.headers());
        return http
                .authorizeExchange()
                .pathMatchers(AUTH_WHITELIST)
                .permitAll()
                .anyExchange()
                //                .pathMatchers("/**")
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable()
                .build();
    }

}
