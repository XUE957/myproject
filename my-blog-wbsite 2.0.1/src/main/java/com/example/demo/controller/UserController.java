package com.example.demo.controller;

import com.example.demo.common.AppVariable;
import com.example.demo.common.MyResponseFormat;
import com.example.demo.common.PasswordUtils;
import com.example.demo.common.UserSessionUtils;
import com.example.demo.model.UserInfo;
import com.example.demo.model.vo.UserInfoVO;
import com.example.demo.service.ArticleService;
import com.example.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA
 *
 * @Author XUE_957
 * @Date: 2023/07/01 20:44
 * @Version 2020.3.3
 */

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/reg")
    public MyResponseFormat reg(UserInfo userInfo) {
        // 调用 getUserByName 接口, 得到数据库中的user信息, 来与前端参数username比对
        // 如果根据用户名在数据库中查到了该用户的信息, 则该用户名已经被注册

        // 2.非空校验 and 参数有效性检验
//        if (userInfo == null || !StringUtils.hasLength(userInfo.getUsername())
//                || !StringUtils.hasLength(userInfo.getPassword())) {
//            return MyResponseFormat.fail(-1, "非法参数");
//        }

        if (userInfo == null) return MyResponseFormat.fail(-1, "非法参数");
        System.out.println(userInfo);
        if (!StringUtils.hasLength(userInfo.getUsername())) return MyResponseFormat.fail(-1, "未输入用户名");
        if (!StringUtils.hasLength(userInfo.getPassword())) return MyResponseFormat.fail(-1, "未输入密码");
        UserInfo userInDatabase = userService.getUserByName(userInfo.getUsername());
        if (userInDatabase != null && userInDatabase.getUsername() != null) {
            return MyResponseFormat.fail(-1, "该用户名已被注册!");
        }
        // 密码加盐处理
        userInfo.setPassword(PasswordUtils.encoding(userInfo.getPassword()));

        return MyResponseFormat.success(userService.reg(userInfo));
    }

    @RequestMapping("/login")
    public MyResponseFormat login(UserInfo userInfo, HttpServletRequest request) {
        // 1.非空校验
        if (userInfo == null || !StringUtils.hasLength(userInfo.getUsername())
                || !StringUtils.hasLength(userInfo.getPassword())) {
            return MyResponseFormat.fail(-1, "非法参数");
        }
        // 2.查询数据库并且使用 session, 有则不必再次登录, 无则创建新session
        UserInfo userInDatabase = userService.getUserByName(userInfo.getUsername());
        if (userInDatabase != null && userInDatabase.getId() > 0) {
            // 说明数据库中有该用户名
            // 验证密码
            //if (userInDatabase.getPassword().equals(userInfo.getPassword())) {
            if (PasswordUtils.checkPassword(userInfo.getPassword(), userInDatabase.getPassword())) {
                // 登录成功, 并且将 session 存储起来
                userInDatabase.setPassword(""); // 隐藏敏感(密码)信息

                HttpSession session = request.getSession(true);
                session.setAttribute(AppVariable.USER_SESSION_KEY, userInDatabase);

                return MyResponseFormat.success(userInDatabase);
            }
        }

        return MyResponseFormat.fail(-1, "登录失败", null);
    }

    @RequestMapping("/logout")
    public MyResponseFormat logout(HttpSession session) {
        session.removeAttribute(AppVariable.USER_SESSION_KEY);
        return MyResponseFormat.success(1);
    }

    @RequestMapping("/bloginfo")
    public MyResponseFormat showInfo(HttpServletRequest request) {
        // 需要一个 UserInfoVO 对象来存储 result
        UserInfoVO userInfoVO = new UserInfoVO();
        // 通过session拿到当前登录用户的信息(因为这个多个地方需要使用,因此封装在一个公共类中)
//        HttpSession session = request.getSession(false);
//        UserInfo userInfo = (UserInfo) session.getAttribute(AppVariable.USER_SESSION_KEY);
        UserInfo userInfo = UserSessionUtils.getUser(request);
        if (userInfo == null) {
            return MyResponseFormat.fail(-1, "非法请求");
        }
        Integer result = articleService.getCountByUid(userInfo.getId());
        // spring 提供了深克隆的方法
        //userInfoVO.setUsername(userInfo.getUsername());
        BeanUtils.copyProperties(userInfo, userInfoVO);
        userInfoVO.setArticleCount(result);
        return MyResponseFormat.success(userInfoVO);
    }

    @RequestMapping("/status")
    public MyResponseFormat getLoginStatus(HttpServletRequest request) {
        UserInfo userInfo = UserSessionUtils.getUser(request);
        if (userInfo == null) {
            return MyResponseFormat.fail(-1, "非法请求");
        }
        return MyResponseFormat.success(userInfo);
    }

}
