package com.elearning.commonlib.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class SwaggerConfig {
    @Configuration
    public interface SwaggerConfigContract {
        @Bean
        Object apiDocket();
    }
}

