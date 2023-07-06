package com.example.demo.common;

/**
 * Created with IntelliJ IDEA
 *
 * @Author XUE_957
 * @Date: 2023/07/01 20:49
 * @Version 2020.3.3
 */

import lombok.Data;

import java.io.Serializable;

/**
 * 用这个类来定义返回给前端的数据格式
 */
@Data
public class MyResponseFormat implements Serializable {
    // 错误码
    private Integer code;
    // 错误信息
    private String msg;
    // 返回的数据
    private Object data;

    /**
     * 返回成功结果
     */
    public static MyResponseFormat success(Object data) {
        MyResponseFormat format = new MyResponseFormat();
        format.setCode(200);
        format.setMsg("success");
        format.setData(data);
        return format;
    }

    public static MyResponseFormat success(Integer code, Object data) {
        MyResponseFormat format = new MyResponseFormat();
        format.setCode(code);
        format.setMsg("success");
        format.setData(data);
        return format;
    }

    public static MyResponseFormat success(Integer code, String msg, Object data) {
        MyResponseFormat format = new MyResponseFormat();
        format.setCode(code);
        format.setMsg(msg);
        format.setData(data);
        return format;
    }

    /**
     * 返回失败结果
     */
    public static MyResponseFormat fail(Integer code, String msg) {
        MyResponseFormat format = new MyResponseFormat();
        format.setCode(code);
        format.setMsg(msg);
        format.setData(null);
        return format;
    }

    public static MyResponseFormat fail(Integer code, String msg, Object data) {
        MyResponseFormat format = new MyResponseFormat();
        format.setCode(code);
        format.setMsg(msg);
        format.setData(data);
        return format;
    }

}
