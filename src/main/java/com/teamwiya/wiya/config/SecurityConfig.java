package com.teamwiya.wiya.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
/*import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;*/

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

//@Order(1)
//@Slf4j
//@Configuration
//@EnableWebSecurity(debug = false)
public class SecurityConfig {
/*
    //@Bean
    public WebSecurityCustomizer configure() {
        return web -> web.ignoring().antMatchers(
                "/",
                "/login",
                "/assets/**"
        );
    }

    //@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                //인가 정책
                .authorizeRequests() // 요청에 대한 보안 검사
                    .antMatchers("/", "/assets/**", "/member/register", "/member", "/memMailCheck").permitAll()
                    .anyRequest().authenticated() // 어떤 요청도 인증처리
                    .and()
                //인증 정책
                //.csrf()
                    //.disable()
                //form 로그인
                .formLogin() //formLogin 인증 방식 설정
                    .loginPage("/login").permitAll()  			// 사용자 정의 로그인 페이지
                    //.defaultSuccessUrl("/")			// 로그인 성공 후 이동 페이지
                    .failureUrl("/login?auth=fail")	        // 로그인 실패 후 이동 페이지)
                    *//*.usernameParameter("username")			// 아이디 파라미터명 설정
                    .passwordParameter("password")*//*			// 패스워드 파라미터명 설정
                    //.loginProcessingUrl("/login")		    // 로그인 Form Action Url
                    *//*.successHandler(
                        new AuthenticationSuccessHandler() {
                            @Override
                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                log.info("성공");
                                response.sendRedirect("/");
                            }
                        }
                    )		// 로그인 성공 후 핸들러
                    .failureHandler(
                        new AuthenticationFailureHandler() {
                            @Override
                            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                                log.info("실패");
                                response.sendRedirect("/login");
                            }
                        }
                    )*//*		// 로그인 실패 후 핸들러
                    .and()
                //로그아웃
                .logout()
                    .logoutSuccessUrl("/")
                    .and()
                //빌드
                .build();
    }*/



        //테스트
        //@Bean
        /*public UserDetailsService userDetailsService() {
            UserDetails user =
                    User.withDefaultPasswordEncoder()
                            .username("user")
                            .password("password")
                            .roles("USER")
                            .build();

            return new InMemoryUserDetailsManager(user);
        }*/

}
