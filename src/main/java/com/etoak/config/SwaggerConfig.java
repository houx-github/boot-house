package com.etoak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        //ApiInfo
        ApiInfo apiInfo=new  ApiInfoBuilder().title("使用swagger创建" +
                "rest风格的接口文档").description("使用swagger创建rest风格的接口文档")
                .termsOfServiceUrl("http://www.etoak.com")
                .version("1.0").build();
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo)//文档描述信息
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.etoak.controller"))//为那个包创建文档
                .build();
    }
}
