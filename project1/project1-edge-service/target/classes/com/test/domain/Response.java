package com.test.domain;

import java.io.Serializable;

public class Response implements Serializable {
    //获取数据失败
    public static final Response GET_DATA_FAIL = new Response("101", "get data fail");
    //操作成功
    public static final Response SUCCESS = new Response();

    private String code;
    private String message;

    public Response() {
        this.code = "0";
        this.message = "success";
    }

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Response exception(Exception e) {
        // 比较好的方式，将整个的异常e都返回，获取详细的异常信息。更好的方法，完成异常拦截器。
        return new Response("444", e.getMessage());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
