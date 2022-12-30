package com.crx.usercenter.service;

import com.crx.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author Cai Rongxin
 * @date 2022/12/14 - 19:17
 *
 */
@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testAddUser(){
        User user = new User();
        user.setUsername("crx");
        user.setUserAccount("123");
        user.setAvatarUrl("C:\\Users\\惠普战66\\Pictures\\Saved Pictures");
        user.setGender(0);
        user.setUserPassword("xxx");
        user.setPhone("123");
        user.setEmail("456");
        boolean result = userService.save(user);
        System.out.println(user.getUsername());
        System.out.println(result);

    }

    @Test
    void register() {
        String userAccount = "";
        String userPassword = "12345678";
        String checkPassword = "12345678";
        String planetCode = "2";
        long register1 = userService.register(userAccount, userAccount, checkPassword,planetCode);
        Assertions.assertEquals(-1,register1);
        userAccount = "crx";
        register1 = userService.register(userAccount, userAccount, checkPassword,planetCode);
        Assertions.assertEquals(-1,register1);
        userAccount = "yupi";
        userPassword = "1234567";
        register1 = userService.register(userAccount, userAccount, checkPassword,planetCode);
        Assertions.assertEquals(-1,register1);
        userAccount = "yu pi";
        userPassword = "12345678";
        register1 = userService.register(userAccount, userAccount, checkPassword,planetCode);
        Assertions.assertEquals(-1,register1);
        checkPassword = "123456789";
        register1 = userService.register(userAccount, userAccount, checkPassword,planetCode);
        Assertions.assertEquals(-1,register1);
        userAccount = "yupi";
        checkPassword = "12345678";
        register1 = userService.register(userAccount, userAccount, checkPassword,planetCode);
        Assertions.assertEquals(-1,register1);
        userAccount = "cairx";
        register1 = userService.register(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertTrue(register1 > 0);
    }
}