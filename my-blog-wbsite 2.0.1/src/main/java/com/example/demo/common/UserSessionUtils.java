package com.example.demo.common;

/**
 * Created with IntelliJ IDEA
 *
 * @Author XUE_957
 * @Date: 2023/07/03 0:45
 * @Version 2020.3.3
 */

import com.example.demo.model.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 通过session拿到当前登录用户的信息(因为这个多个地方需要使用,因此封装在一个公共类中)
 */
public class UserSessionUtils {
    public static UserInfo getUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(AppVariable.USER_SESSION_KEY) != null) {
            return (UserInfo) session.getAttribute(AppVariable.USER_SESSION_KEY);
        }

        return null;
    }
}
