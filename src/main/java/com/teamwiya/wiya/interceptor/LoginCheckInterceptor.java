package com.teamwiya.wiya.interceptor;

import com.teamwiya.wiya.config.SessionConst;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;

public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();

        /*로그인 실패*/
        if(session == null || session.getAttribute(SessionConst.LOGIN_EMAIL) == null) {
            response.sendRedirect("/member/login?redirectURL=" + requestURI);
            return false;
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {

        }
        return true;

    }
}
