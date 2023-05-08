package com.example.weatherapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import reactor.core.publisher.Mono;

import java.util.List;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

    public SecurityConfig(AuthenticationManager authenticationManager, SecurityContextRepository securityContextRepository) {
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

@Bean
public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
  return   http.cors().configurationSource(request -> {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOriginPatterns(List.of("*"));
                configuration.setAllowedMethods(List.of("GET", "POST", "OPTIONS", "PUT", "DELETE", "PATCH"));
                configuration.setAllowedHeaders(List.of("*"));
                configuration.setAllowCredentials(true);
                return configuration;
            }).and()
            .authorizeExchange()
            .pathMatchers("/*/auth/**").permitAll()
            .anyExchange().authenticated()
            .and()
            .securityContextRepository(securityContextRepository)
            .authenticationManager(authenticationManager)
            .exceptionHandling()
            .authenticationEntryPoint((swe, e) -> Mono.fromRunnable(() -> {
                swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            }))
            .accessDeniedHandler((swe, e) -> Mono.fromRunnable(() -> {
                swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            }))
            .and()
            .csrf().disable()
            .build();
}
//@Bean
//public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//    return http
//            .exceptionHandling()
//            .authenticationEntryPoint((swe, e) ->
//                    Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED))
//            ).accessDeniedHandler((swe, e) ->
//                    Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN))
//            ).and()
//            .csrf().disable()
//            .formLogin().disable()
//            .httpBasic().disable()
//            .authenticationManager(authenticationManager)
//            .securityContextRepository(securityContextRepository)
//            .authorizeExchange()
//            .pathMatchers(HttpMethod.OPTIONS).permitAll()
//            .pathMatchers("/*/auth/**").permitAll()
//            .anyExchange().authenticated()
//            .and().build();
//}
}
