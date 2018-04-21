package com.shiro.admin.shiro;

import com.shiro.admin.domain.Permission;
import com.shiro.admin.domain.Role;
import com.shiro.admin.domain.User;
import com.shiro.admin.service.IShiroService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @description: 验证，以及权限的添加MyShiroRealm, 实现AuthorizingRealm接口用户用户认证
 * @author: xiaosa
 * @date: 2018-04-21 下午2:18
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private IShiroService shiroService;

    /**
     * @description:  角色权限和对应权限添加
     * @author 潇洒
     * @date 2018/4/19 下午6:05
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String userName= (String) principalCollection.getPrimaryPrincipal();
        //查询用户
        User user = shiroService.getUserByName(userName);
        if(user == null){
            return null;
        }
        //根据用户查询他拥有什么角色
        List<Role> listRole = shiroService.listRoleByUserId(user.getId());
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if(listRole.size()>0){
            for (Role role: listRole) {
                //添加角色
                simpleAuthorizationInfo.addRole(role.getRoleName());
                //查询该角色拥有那些权限
                List<Permission> listPermission = shiroService.listPermissionByRoleId(role.getId());
                if(listPermission.size()>0){
                    for (Permission permission:listPermission) {
                        //添加权限
                        simpleAuthorizationInfo.addStringPermission(permission.getPermissionName());
                    }
                }
            }
        }
        return simpleAuthorizationInfo;
    }


    /**
     * @description:  用户认证
     * @author 潇洒
     * @date 2018/4/19 下午6:32
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (token.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String userName = token.getPrincipal().toString();
        User user = shiroService.getUserByName(userName);
        if(user == null){
            return null;
        }
        //这里验证authenticationToken和simpleAuthenticationInfo的信息
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUserName(),
                user.getPassword(),getName());
        return simpleAuthenticationInfo;
    }
}
