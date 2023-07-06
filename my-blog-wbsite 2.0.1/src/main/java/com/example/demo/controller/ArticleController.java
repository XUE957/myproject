package com.example.demo.controller;

import com.example.demo.common.MyResponseFormat;
import com.example.demo.common.UserSessionUtils;
import com.example.demo.model.ArticleInfo;
import com.example.demo.model.UserInfo;
import com.example.demo.model.vo.UserInfoVO;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author XUE_957
 * @Date: 2023/07/03 17:09
 * @Version 2020.3.3
 */

@RequestMapping("/article")
@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/mylist")
    public MyResponseFormat getMyList(HttpServletRequest request) {
        UserInfo userInfo = UserSessionUtils.getUser(request);
        if (userInfo == null) {
            return MyResponseFormat.fail(-1, "非法请求");
        }
        List<ArticleInfo> list = articleService.getMyList(userInfo.getId());
        return MyResponseFormat.success(list);
    }

    @RequestMapping("/allarticle")
    public MyResponseFormat getAllList(HttpServletRequest request) {
//        UserInfo userInfo = UserSessionUtils.getUser(request);
//        if (userInfo == null) {
//            return MyResponseFormat.fail(-1, "非法请求");
//        }
        List<UserInfoVO> list = articleService.getAllArticle();
        return MyResponseFormat.success(list);
    }

    @RequestMapping("/mydetail")
    public MyResponseFormat getMyDetail(@RequestParam("id") Integer artId) {
        // 1.参数校验
        if (artId == null || artId <= 0) {
            return MyResponseFormat.fail(-1, "参数异常");
        }
        ArticleInfo article = articleService.getArtByArtId(artId);
        return MyResponseFormat.success(article);
    }

    // 删除文章, 要求: 登录的用户和该文章的作者是同一人才有权限删除!
    // 在删除 sql 中, 条件改为 where id = artId and uid = uid
    // 文章id 和 uid 都为 true 删除条件才成立
    @RequestMapping("/del")
    public MyResponseFormat getDelArticle(HttpServletRequest request, @RequestParam("aid") Integer artId) {
        // 1.参数校验
        if (artId == null || artId <= 0) {
            return MyResponseFormat.fail(-1, "参数异常");
        }
        UserInfo userInfo = UserSessionUtils.getUser(request);
        if (userInfo == null) {
            return MyResponseFormat.fail(-2, "用户未登录");
        }
        Integer result = articleService.delArticleById(artId, userInfo.getId());
        return MyResponseFormat.success(result);
    }



    @RequestMapping("/publish")
    public MyResponseFormat publishArticle(HttpServletRequest request, ArticleInfo articleInfo) {
        UserInfo userInfo = UserSessionUtils.getUser(request);
        if (userInfo == null) {
            return MyResponseFormat.fail(-2, "用户未登录");
        }
        if (articleInfo == null || articleInfo.getTitle() == null
                || articleInfo.getContent() == null) {
            return MyResponseFormat.fail(-1, "参数异常");
        }
        articleInfo.setUid(userInfo.getId());
        Integer result = articleService.addArticle(articleInfo);
        return MyResponseFormat.success(result);
    }

    @RequestMapping("/update")
    public MyResponseFormat updateReadCount(HttpServletRequest request,
                                            @RequestParam("id") Integer artId) {
        UserInfo userInfo = UserSessionUtils.getUser(request);
        if (userInfo == null) {
            return MyResponseFormat.fail(-2, "非法请求");
        }
        return MyResponseFormat.success(articleService.updateRcount(artId));
    }
}
