package com.walkBAM.util;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

public class WebConf implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] as={ "model.html"};
        List<String> patterns=new ArrayList<String>();
        for (String string : as) {
            patterns.add(string);
        }
        // addPathPatterns 配置要拦截的地址
        registry.addInterceptor(new ErrorInterceptor()).addPathPatterns("/**/**").excludePathPatterns(patterns);
    }
}
