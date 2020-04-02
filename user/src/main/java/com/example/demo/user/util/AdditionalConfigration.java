package com.example.demo.user.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdditionalConfigration {
    @Bean
    PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }
}
