package com.crx.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Cai Rongxin
 * @date 2022/12/17 - 17:02
 */
@Data
public class UserSearchRequest implements Serializable {
    private static final long serialVersionUID = 147075017626824850L;
    /**
     * 用户昵称
     */
    private String username;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态 0 - 正常
     */
    private Integer userStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 用户权限
     * 0 普通用户
     * 1 管理员
     */
    private Integer userRole;
}
