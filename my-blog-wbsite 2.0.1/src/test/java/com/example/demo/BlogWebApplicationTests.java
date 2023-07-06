package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.Arrays;

//@SpringBootTest
class BlogWebApplicationTests {

	@Test
	void contextLoads() {
	}

	public static void main(String[] args) {
		// md5 的加密方式
		String passwd = "123456";
		String mdPasswd = DigestUtils.md5DigestAsHex(passwd.getBytes());
		System.out.println(mdPasswd);
		System.out.println(mdPasswd.length());
	}

}
