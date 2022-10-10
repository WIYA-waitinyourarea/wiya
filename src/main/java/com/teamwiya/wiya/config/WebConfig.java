package com.teamwiya.wiya.config;

import com.teamwiya.wiya.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/logout", "/memMailCheck", "/member/register", "/assets/**","/*.ico","/error", "/*.js", "/*.css");

    }

    //@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        //registry.addViewController("/login").setViewName("login");
    }
}
