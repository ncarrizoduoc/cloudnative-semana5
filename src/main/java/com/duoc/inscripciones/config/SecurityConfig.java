package com.duoc.inscripciones.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
            .anyRequest().authenticated()).oauth2ResourceServer((oauth2) -> oauth2
                .jwt(Customizer.withDefaults())
                .bearerTokenResolver(customTokenResolver()));
        return http.build();
    }

    @Bean
    public BearerTokenResolver customTokenResolver(){
        return request -> {
            String authHeader = request.getHeader("Authorization");

            if(authHeader == null || authHeader.isBlank()){
                return null;
            }

            if(authHeader.regionMatches(true, 0, "Bearer ", 0, 7)){
                return authHeader.substring(7).trim();
            }
            return authHeader.trim();
        };
    }

}
