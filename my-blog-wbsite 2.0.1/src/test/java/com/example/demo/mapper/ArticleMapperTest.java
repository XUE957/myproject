package com.example.demo.mapper;

import com.example.demo.model.ArticleInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA
 *
 * @Author XUE_957
 * @Date: 2023/07/04 12:00
 * @Version 2020.3.3
 */

@SpringBootTest
class ArticleMapperTest {
    @Autowired
    private ArticleMapper articleMapper;

    @Test
    void addArticle() {
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setTitle("我是test");
        articleInfo.setContent("这是一次测试...............");
        articleInfo.setUid(3);
        System.out.println(articleMapper.addArticle(articleInfo));
        System.out.println(articleInfo);
    }
}