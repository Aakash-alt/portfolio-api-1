package com.kushal.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.MultipartConfigElement;

@EnableWebMvc
@Configuration
public class WebSecurityConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins(
                        "http://localhost:5173",
                        "http://192.168.1.5:5173",
                        "https://www.kushalshah.dev",
                        "https://kushalshah.dev"
                )
                .allowedHeaders("*")
                .exposedHeaders(
                        "Access-Control-Allow-Origin",
                        "Access-Control-Allow-Credentials")
                .allowCredentials(false).maxAge(3600);
    }

}
