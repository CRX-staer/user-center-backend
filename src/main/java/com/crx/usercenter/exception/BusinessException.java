package com.crx.usercenter.exception;

import com.crx.usercenter.common.ErrorCode;


/**
 * 自定义异常类
 * @author Cai Rongxin
 * @date 2022/12/18 - 13:17
 */
public class BusinessException extends RuntimeException {
    //因为RuntimeException的错误参数不能满足我们的需求，所以需要自定义异常
    //不需要set 所以定义成final
    private final int code;
    private final String description;

    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BusinessException(ErrorCode errorCode,String description){
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
