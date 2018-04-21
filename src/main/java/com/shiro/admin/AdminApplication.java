package com.shiro.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @description: 后台管理，主要测试shiro权限.
 * @author 潇洒
 * @date 2018/4/19 下午4:30
 */
@EnableSwagger2
@MapperScan("com.shiro.admin.dao")
@SpringBootApplication
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
