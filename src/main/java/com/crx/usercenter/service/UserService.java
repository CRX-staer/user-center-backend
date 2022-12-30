package com.crx.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crx.usercenter.model.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
* @author Cai Rongxin
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2022-12-14 19:50:44
*/
public interface UserService extends IService<User> {
    /**
     * 用户注册
     * @param userAccount 账户
     * @param userPassword 密码
     * @param checkPassword 确认密码
     * @return 返回用户的id
     */
    long register(String userAccount,String userPassword,String checkPassword,String planetCode);

    /**
     * 用户登录
     * @param userAccount
     * @param userPassword
     * @param request 设置登录态
     * @return
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 返回脱敏后的User
     * @param user
     * @return
     */
    User getSafetyUser(User user);

    /**
     * 用户注销
     * @param request
     * @return 返回int
     */
    int userLogout(HttpServletRequest request);

}
