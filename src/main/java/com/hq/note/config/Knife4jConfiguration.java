package com.hq.note.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger ui配置
 *
 * @author HQ
 */
@Configuration
public class Knife4jConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("HQ笔记")
                        .description("一个简单的笔记项目")
                        .build())
                .groupName("笔记")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hq.note.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}