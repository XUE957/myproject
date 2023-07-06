package com.example.demo.mapper;

import com.example.demo.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA
 *
 * @Author XUE_957
 * @Date: 2023/07/06 8:53
 * @Version 2020.3.3
 */

@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void reg() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("test");
        userInfo.setPassword("333");
        int result = userMapper.reg(userInfo);
        System.out.println(result);
    }
}