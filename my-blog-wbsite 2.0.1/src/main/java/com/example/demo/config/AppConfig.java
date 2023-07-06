package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA
 *
 * @Author XUE_957
 * @Date: 2023/07/01 23:10
 * @Version 2020.3.3
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**") // 拦截所有接口
                //.excludePathPatterns("/**/*.html")
                .excludePathPatterns("/blog_reg.html") // 将哪些html以及资源排除
                .excludePathPatterns("/blog_login.html")
                .excludePathPatterns("/blog_list.html")
                .excludePathPatterns("/blog_detail.html")
                .excludePathPatterns("/blog_edit.html")
                .excludePathPatterns("/blog_center.html")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/editor.md/**")
                .excludePathPatterns("/image/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/user/reg") // 将哪些接口排除
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/logout")
                .excludePathPatterns("/user/bloginfo")
                .excludePathPatterns("/user/status")
                .excludePathPatterns("/article/mylist")
                .excludePathPatterns("/article/allarticle")
                .excludePathPatterns("/article/del")
                .excludePathPatterns("/article/mydetail")
                .excludePathPatterns("/article/update")
                .excludePathPatterns("/article/publish");
//                .excludePathPatterns("/user/**")
//                .excludePathPatterns("/article/**");
    }

//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        WebMvcConfigurer.super.configurePathMatch(configurer);
//    }
}
