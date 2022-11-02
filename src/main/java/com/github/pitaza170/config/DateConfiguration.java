package com.github.pitaza170.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class DateConfiguration {

    @Bean
    public Date getDateTime() {
        return new Date();
    }
}
