package com.hsd.file.config;

import com.hsd.file.filter.CrossFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CrossFilterConfig {
    @Bean
    public CrossFilter headerFilter() {
        return new CrossFilter();
    }
}
