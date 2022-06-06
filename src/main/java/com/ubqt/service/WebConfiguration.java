package com.ubqt.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**").allowedMethods("*");
        registry.addMapping("/**").allowedOrigins("http://localhost:4200", "http://localhost:4000",
                "http://localhost:8080","http://35.85.148.89:8080/talent-map","http://35.85.148.89:8080"
                ,"http://103.10.234.99:9092","*","http://103.10.234.99:8891/");
    }
}
