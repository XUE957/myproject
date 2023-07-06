package com.example.demo.config;

/**
 * Created with IntelliJ IDEA
 *
 * @Author XUE_957
 * @Date: 2023/07/01 23:09
 * @Version 2020.3.3
 */

import com.example.demo.common.AppVariable;
import com.example.demo.model.UserInfo;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 自定义登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(AppVariable.USER_SESSION_KEY) != null) {
            // 用户已登录
            //System.out.println("当前登录用户为: " + ((UserInfo) session.getAttribute(AppVariable.USER_SESSION_KEY)).getUsername());
            return true;
        }
        response.sendRedirect("/blog_login.html");

        return false;
    }
}
