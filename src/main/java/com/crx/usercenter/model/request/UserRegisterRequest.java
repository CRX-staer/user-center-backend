package com.crx.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Cai Rongxin
 * @date 2022/12/15 - 20:19
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 8465129306464331916L;
    private String userAccount;
    private String userPassword;
    private String checkPassword;
    private String planetCode;
}
