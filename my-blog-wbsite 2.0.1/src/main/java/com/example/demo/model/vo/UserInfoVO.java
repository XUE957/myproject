package com.example.demo.model.vo;

import com.example.demo.model.ArticleInfo;
import lombok.Data;

/**
 * Created with IntelliJ IDEA
 *
 * @Author XUE_957
 * @Date: 2023/07/02 22:42
 * @Version 2020.3.3
 */

/**
 * 这个表作为 bolg_list 的返回对象
 */

@Data
public class UserInfoVO extends ArticleInfo {
    // 需要额外添加的字段
    private Integer articleCount;
    private String username;
}
