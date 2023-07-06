package com.example.demo.mapper;

import com.example.demo.model.UserInfo;
import com.example.demo.model.vo.UserInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created with IntelliJ IDEA
 *
 * @Author XUE_957
 * @Date: 2023/07/01 23:44
 * @Version 2020.3.3
 */

@Mapper
public interface UserMapper {

    // 新用户注册
    int reg(UserInfo userInfo);

    // 根据 username 拿到这个用户的所有信息, 有两个作用:
    // 1.在注册的时候, 比对该用户名是否被注册了
    // 2.在登录的时候, 比对前端传来的密码和数据库中的密码是否一致
    UserInfo getUserByName(@Param("username") String username);

    // 根据用户的 id 来查询该用户的信息
    UserInfoVO getUserinfoByUid(@Param("uid") Integer uid);

}
