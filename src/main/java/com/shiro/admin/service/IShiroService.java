package com.shiro.admin.service;

import com.shiro.admin.domain.Permission;
import com.shiro.admin.domain.Role;
import com.shiro.admin.domain.User;

import java.util.List;

/**
 * @description:
 * @author: xiaosa
 * @date: 2018-04-21 下午2:16
 */
public interface IShiroService {

    /**
     * @description: 查询用户
     * @author 潇洒
     * @date 2018/4/19 下午6:14
     */
    User getUserByName(String userName);


    /**
     * @description: 根据用户查询他拥有什么角色
     * @author 潇洒
     * @date 2018/4/19 下午6:20
     */
    List<Role> listRoleByUserId(Integer userId);


    /**
     * @description: 查询该角色拥有那些权限
     * @author 潇洒
     * @date 2018/4/19 下午6:26
     */
    List<Permission> listPermissionByRoleId(Integer roleId);


    /**
     * @description: 为指定用户分配角色
     * @author 潇洒
     * @date 2018/4/20 上午10:20
     */
    void addRole(int userId, List<Integer> roleIdList);


    /**
     * @description: 为指定角色分配权限
     * @author 潇洒
     * @date 2018/4/20 上午10:50
     */
    void addPermission(int roleId, List<Integer> permissionIdList);


    /**
     * @param userId
     * @description: 测试某个用户他所具有的角色和权限
     * @author 潇洒
     * @date 2018/4/20 上午10:58
     */
    User getUser(int userId);
}
