package com.example.demo.common;

/**
 * Created with IntelliJ IDEA
 *
 * @Author XUE_957
 * @Date: 2023/07/04 19:03
 * @Version 2020.3.3
 */

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * 加密算法公共类
 */
public class PasswordUtils {
    /**
     * 方法一: 生成 [salt + & + 使用md5对{ salt+原始密码 } 加密] 的一个最终密码, 并存入到数据库中
     */
    public static String encoding(String password) {
        // 1.使用 UUID 来生成一个 salt值
        // 注意:UUID生成的字符串是36位字符, 并且中间有 "----" 四条横杠, 因此要去掉这四个字符
        String salt = UUID.randomUUID().toString().replace("-", "");
        // 2.使用 md5 对 [salt+原始密码]加密
        String saltPassword = DigestUtils.md5DigestAsHex((salt + password).getBytes());
        // 3.最终密码的格式: [32位salt + & + 32位md5加密后的值]
        String finalPassword = salt + "&" + saltPassword;

        return finalPassword;
    }

    /**
     * 方法二: 根据 [已知salt 和 明文密码password] + salt来生成一个最终密码
     * 这是一个辅助方法~
     */
    public static String encoding(String password, String salt) {
        // 根据salt和 password 加密一遍
        String saltPassword = DigestUtils.md5DigestAsHex((salt + password).getBytes());
        return salt + "&" + saltPassword;
    }

    /**
     * 方法三: 根据 [已知salt 和 明文密码password] 和 传过来的最终密码进行比对
     */
    public static boolean checkPassword(String rawPassword, String finalPassword) {
        // 1.对参数进行有效性校验
        if (StringUtils.hasLength(rawPassword) && StringUtils.hasLength(finalPassword) &&
            finalPassword.length() == 65) {
            // 2.从数据库中的 finalPassword 取出 salt
            String salt = finalPassword.split("&")[0];
            // 3.有了 salt 和 rawPassword 就可以按照之前的逻辑来复现第一次加密的密码了, 然后再和数据库中的 finalPassword 比对
            String confirmPassword = PasswordUtils.encoding(rawPassword, salt);
            return finalPassword.equals(confirmPassword);
        }
        return false;
    }

//    public static void main(String[] args) {
//        String password = "333";
//        String finalPasswd = PasswordUtils.encoding(password);
//        System.out.println(finalPasswd);
//
//        String inputPassword = "123456";
//        System.out.println(PasswordUtils.checkPassword(inputPassword, finalPasswd));
//    }

}
