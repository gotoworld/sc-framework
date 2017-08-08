
package com.hsd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@Order(22)
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket testApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("hsd").genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")// api测试请求地址
                .select()
                .paths(PathSelectors.regex("/boss/.*"))// 过滤的接口
                .build();
    }

}
