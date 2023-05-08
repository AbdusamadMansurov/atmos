package com.example.atmos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

    public SecurityConfig(AuthenticationManager authenticationManager, SecurityContextRepository securityContextRepository) {
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
//        return httpSecurity
//                .exceptionHandling()
//                .authenticationEntryPoint(
//                        (swe, e) ->
//                                Mono.fromRunnable(
//                                        () -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED)
//                                )
//                )
//                .accessDeniedHandler(
//                        (swe, e) ->
//                                Mono.fromRunnable(
//                                        () -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN)
//                                )
//                )
//                .and()
//                .csrf().disable()
//                .authenticationManager(authenticationManager)
//                .securityContextRepository(securityContextRepository)
//                .authorizeExchange()
//                .pathMatchers("/*/auth/**").permitAll()
//                .anyExchange().authenticated()
//                .and()
//                .build();
//    }
@Bean
public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    return http
            .authorizeExchange()
            .pathMatchers("/auth/**").permitAll()
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


}
