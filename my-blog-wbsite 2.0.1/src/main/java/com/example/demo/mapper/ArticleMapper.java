package com.example.demo.mapper;

import com.example.demo.model.ArticleInfo;
import com.example.demo.model.vo.UserInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author XUE_957
 * @Date: 2023/07/02 21:38
 * @Version 2020.3.3
 */
@Mapper
public interface ArticleMapper {
    Integer getCountByUid(@Param("uid") Integer uid);

    List<ArticleInfo> getMyList(@Param("uid") Integer uid);

    // 根据文章id 来查询文章
    ArticleInfo getMyDetail(@Param("artId") Integer artId);

    // 根据文章id 来删除文章
    Integer del(@Param("artId") Integer artId, @Param("uid") Integer uid);

    // 添加文章
    Integer addArticle(ArticleInfo articleInfo);

    // 返回所有文章, 包括作者信息
    List<UserInfoVO> getAllArticle();

    // 更新文章的 rcount
    Integer updateRcount(@Param("artId") Integer artId);
}

