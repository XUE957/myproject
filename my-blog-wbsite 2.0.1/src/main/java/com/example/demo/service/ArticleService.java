package com.example.demo.service;

import com.example.demo.mapper.ArticleMapper;
import com.example.demo.model.ArticleInfo;
import com.example.demo.model.vo.UserInfoVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author XUE_957
 * @Date: 2023/07/02 21:45
 * @Version 2020.3.3
 */

@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    public Integer getCountByUid(Integer userId) {
        return articleMapper.getCountByUid(userId);
    }

    public List<ArticleInfo> getMyList(Integer userId) {
        return articleMapper.getMyList(userId);
    }

    public ArticleInfo getArtByArtId(Integer artId) {
        return articleMapper.getMyDetail(artId);
    }

    public Integer delArticleById(Integer artId, Integer uid) {
        return articleMapper.del(artId, uid);
    }

    public Integer addArticle(ArticleInfo articleInfo) {
        return articleMapper.addArticle(articleInfo);
    }

    public List<UserInfoVO> getAllArticle() {
        return articleMapper.getAllArticle();
    }

    public Integer updateRcount(Integer artId) {
        return articleMapper.updateRcount(artId);
    }
}
