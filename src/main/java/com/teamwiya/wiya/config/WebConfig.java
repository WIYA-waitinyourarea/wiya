package com.teamwiya.wiya.config;

import com.teamwiya.wiya.interceptor.LoginCheckInterceptor;
import com.teamwiya.wiya.interceptor.MemberFindInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/",
                        "/member/login",
                        "/member/logout",
                        "/member/mailcheck",
                        "/member/register",
                        "/assets/**",
                        "/*.ico",
                        "/error",
                        "/*.js",
                        "/*.css",
                        "/images/**",
                        "/member/sendmail",
                        "/member/findpwd",
                        "/member/changepwd"
                );

        /*비밀찾기 후 변경하는 인터셉터*/
        registry.addInterceptor(new MemberFindInterceptor())
                .order(2)
                .addPathPatterns("/member/changepwd");
    }

    /*@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        //registry.addViewController("/login").setViewName("login");
    }*/
}
