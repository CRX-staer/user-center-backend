package com.crx.usercenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//在 Spring Boot 启动类中添加 @MapperScan 注解，扫描 Mapper 文件夹 把文件的增删改查注入到项目中
@SpringBootApplication
@MapperScan("com.crx.usercenter.mapper")
public class UserCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplication.class, args);
    }

}
