package com.crx.usercenter.common;


import lombok.Data;

import java.io.Serializable;

/**
 * 定义通用返回类
 * @author Cai Rongxin
 * @date 2022/12/17 - 22:00
 */
@Data
public class BaseResponse<T> implements Serializable {
    /**
     * 返回的状态码
     */
    private int code;
    /**
     * 返回的数据
     */
    private T data;
    /**
     * 返回的信息
     */
    private String message;

    private String description;

    public BaseResponse(int code, T data, String message,String description) {//？
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public BaseResponse(int code, T data,String message) {
        this(code,data,message,"");
    }

    public BaseResponse(int code, T data) {
        this(code,data,"","");
    }

    public BaseResponse(ErrorCode errorCode){
        this(errorCode.getCode(),null,errorCode.getMessage(),errorCode.getDescription());
    }
}
