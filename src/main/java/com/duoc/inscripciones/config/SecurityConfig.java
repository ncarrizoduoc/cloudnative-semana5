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

    // Este metodo define un Bean que se encarga de extraer el JWT desde
    // la cabecera Authorization. Elimina el prefijo Bearer si esta presente
    @Bean
    public BearerTokenResolver customTokenResolver(){
        return request -> {
            // Se obtiene el contenido de la cabecera Authorization
            String authHeader = request.getHeader("Authorization");

            // Si no hay cabecera Authorization, retorna null
            if(authHeader == null || authHeader.isBlank()){
                return null;
            }

            // Si el prefijo Bearer esta presente, lo elimina y retorna el token.
            // Si no esta presente, retorna el token contenido en la cabecera
            if(authHeader.regionMatches(true, 0, "Bearer ", 0, 7)){
                return authHeader.substring(7).trim();
            }
            return authHeader.trim();
        };
    }

}
