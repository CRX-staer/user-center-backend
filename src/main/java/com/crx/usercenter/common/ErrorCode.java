package com.crx.usercenter.common;

/**
 * 错误码
 * @author Cai Rongxin
 * @date 2022/12/18 - 12:35
 */
public enum ErrorCode {
    //根据业务情况定义
    SUCCESS(0,"ok",""),
    //可以理解用一个ErrorCode 可以对应很多错误，具体错误我们可以用description来描述，这个description是给前端用的
    PARAMS_ERROR(40000,"请求参数错误",""),
    NULL_ERROR(40001,"请求的数据为空",""),
    NOT_LOGIN(40100,"未登录",""),
    NOT_AUTH(40101,"无权限",""),
    SYSTEM_ERROR(50000,"系统内部异常","");
    /**
     * 状态码
     */
    private final int code;
    /**
     * 状态码信息
     */
    private final String message;
    /**
     * 状态码描述（详情）
     */
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
