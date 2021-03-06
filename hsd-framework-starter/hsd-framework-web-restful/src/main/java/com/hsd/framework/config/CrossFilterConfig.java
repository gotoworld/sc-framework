package com.hsd.framework.config;

import com.hsd.framework.filter.CrossFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CrossFilterConfig {
    @Bean
    public CrossFilter headerFilter() {
        return new CrossFilter();
    }
}
