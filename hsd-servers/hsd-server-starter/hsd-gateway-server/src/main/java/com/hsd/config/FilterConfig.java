package com.hsd.config;

import com.hsd.filter.CrossFilter;
import com.hsd.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public CrossFilter crossFilter() {
        return new CrossFilter();
    }

    @Bean
    public JwtFilter jwtFilter() { return new JwtFilter(); }
}
