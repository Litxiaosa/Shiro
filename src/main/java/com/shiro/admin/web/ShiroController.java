package com.shiro.admin.web;

import com.shiro.admin.domain.User;
import com.shiro.admin.service.IShiroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 权限
 * @author: xiaosa
 * @date: 2018-04-21 下午2:22
 */

@RestController
@RequestMapping("/shiro")
@Api("shiro相关接口")
public class ShiroController {

    @Autowired
    private IShiroService shiroService;


    /**
     * @description: 为指定用户分配角色，userId：用户Id， roleIdList：角色ID
     * @author 潇洒
     * @date 2018/4/20 上午10:17
     */
    @PostMapping(value = "/addRole")
    @ApiOperation(value = "为指定用户分配角色")
    public void addRole(@RequestParam(value = "userId") int userId,
                        @RequestParam(value = "roleIdList") List<Integer> roleIdList) {
        shiroService.addRole(userId, roleIdList);
    }


    /**
     * @description: 为指定角色分配权限， roleId:角色Id,  permissionIdList:权限ID
     * @author 潇洒
     * @date 2018/4/20 上午10:46
     */
    @PostMapping(value = "/addPermission")
    @ApiOperation(value = "为指定角色分配权限")
    public void addPermission(@RequestParam(value = "roleId") int roleId,
                              @RequestParam(value = "permissionIdList") List<Integer> permissionIdList) {
        shiroService.addPermission(roleId, permissionIdList);
    }


    /**
     * @description: 测试某个用户他所具有的角色和权限
     * RequiresAuthentication: 当前Subject必须在当前session中已经过认证, 也就是说必须登录。
     * RequiresRoles: 当前Subject必须拥有所有指定的角色时，才能访问被该注解标注的方法。
     * @author 潇洒
     * @date 2018/4/20 上午10:56
     */
    @PostMapping(value = "/user")
    @ApiOperation(value = "测试某个用户他所具有的角色和权限")
    @RequiresAuthentication
    @RequiresRoles("角色1")
    public User user(@RequestParam(value = "userId") int userId) {
        return shiroService.getUser(userId);
    }


    /**
     * @description: 登录
     * @author 潇洒
     * @date 2018/4/20 下午5:43
     */
    @PostMapping(value = "/login")
    @ApiOperation(value = "登录")
    public String login(@RequestParam(value = "userName") String userName,
                        @RequestParam(value = "password") String password) {
        //省略了用户名、密码不正确等等的校验
        //构造一个用户密码令牌
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        //记住我,shiro的一个重要功能
        token.setRememberMe(true);
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        //省略储存用户信息等等。。
        return "登录成功";
    }
}
