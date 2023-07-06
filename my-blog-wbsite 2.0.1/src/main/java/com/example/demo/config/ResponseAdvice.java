package com.example.demo.config;

import com.example.demo.common.MyResponseFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created with IntelliJ IDEA
 *
 * @Author XUE_957
 * @Date: 2023/07/01 23:12
 * @Version 2020.3.3
 */

/**
 * 实现统一数据格式返回的保底类, 这是返回数据的最后一道关卡, 如果检测到没有返回 MyResponseFormat
 * 这个统一类型, 那么就在 beforeBodyWrite 中转化成 MyResponseFormat
 */
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof MyResponseFormat) {
            return body;
        }
        if (body instanceof String) {
            // 使用 jackson 中的 ObjectMapper对象 来转化成 json 返回给前端
            return objectMapper.writeValueAsString(MyResponseFormat.success(body));
        }
        return MyResponseFormat.success(body);
    }
}
