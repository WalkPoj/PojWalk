package com.walk;

import com.walk.util.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 商家登录拦截器
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/center/**","/SystemServlet").excludePathPatterns("/MX/indexss","/center/login.html","/center/js/**","/center/css/**","/center/fonts/**","/center/img/**");
    }
}
