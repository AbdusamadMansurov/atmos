package com.example.atmos.security;

import com.example.atmos.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;

//@Import({ SecurityAutoConfiguration.class, ReactiveSecurityAutoConfiguration.class })
@Configuration
@ComponentScan
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;


//public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/login", "/register").permitAll()
                .anyExchange().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .and()
                .build();
    }

//    @Bean
//    public ReactiveUserDetailsService userDetailsService() {
//        return userDetailsService;
//    }

    @Bean
    public WebSessionServerSecurityContextRepository securityContextRepository() {
        return new WebSessionServerSecurityContextRepository();
    }
}