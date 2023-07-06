package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA
 *
 * @Author XUE_957
 * @Date: 2023/07/01 20:44
 * @Version 2020.3.3
 */

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int reg(UserInfo userInfo) {
        return userMapper.reg(userInfo);
    }

    public UserInfo getUserByName(String name) {
        return userMapper.getUserByName(name);
    }
}
