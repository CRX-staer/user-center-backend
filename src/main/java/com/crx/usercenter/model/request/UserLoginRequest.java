package com.crx.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Cai Rongxin
 * @date 2022/12/15 - 20:34
 */
@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = 1932937941675007495L;
    private String userAccount;
    private String userPassword;
}
