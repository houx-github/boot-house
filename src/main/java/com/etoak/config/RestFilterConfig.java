package com.etoak.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

/**
 * 支持同步请求发送rest请求的过滤器 将xx请求转换成xx请求 如post转put 或者delete请求
 * 要求表单提交必须使post
 * 表单必须加隐藏域 name属性为_method value属性值是 put,delete等等
 */
@Configuration
public class RestFilterConfig {
    @Bean
    public FilterRegistrationBean<HiddenHttpMethodFilter> restFilter(){
        FilterRegistrationBean<HiddenHttpMethodFilter> restFilter=new FilterRegistrationBean<HiddenHttpMethodFilter>(new HiddenHttpMethodFilter());
        restFilter.addUrlPatterns("/*");
        return restFilter;
    }
}
