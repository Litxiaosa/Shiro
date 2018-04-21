package com.shiro.admin.service.impl;

import com.shiro.admin.dao.*;
import com.shiro.admin.domain.Permission;
import com.shiro.admin.domain.Role;
import com.shiro.admin.domain.User;
import com.shiro.admin.service.IShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: xiaosa
 * @date: 2018-04-21 下午2:17
 */

@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED )
public class ShiroServiceImpl implements IShiroService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * @description: 查询用户
     * @author 潇洒
     * @date 2018/4/19 下午6:15
     */
    @Override
    public User getUserByName(String userName) {
        return userMapper.getUserByName(userName);
    }


    /**
     * @description: 根据用户查询他拥有什么角色
     * @author 潇洒
     * @date 2018/4/19 下午6:21
     */
    @Override
    public List<Role> listRoleByUserId(Integer userId) {
        return roleMapper.listRoleByUserId(userId);
    }


    /**
     * @description: 查询该角色拥有那些权限
     * @author 潇洒
     * @date 2018/4/19 下午6:26
     */
    @Override
    public List<Permission> listPermissionByRoleId(Integer roleId) {
        return permissionMapper.listPermissionByRoleId(roleId);
    }


    /**
     * @description: 为指定用户分配角色
     * @author 潇洒
     * @date 2018/4/20 上午10:20
     */
    @Override
    public void addRole(int userId, List<Integer> roleIdList) {
        //批量插入用户角色表
        userRoleMapper.insertUserRole(userId, roleIdList);
    }


    /**
     * @description: 为指定角色分配权限
     * @author 潇洒
     * @date 2018/4/20 上午10:50
     */
    @Override
    public void addPermission(int roleId, List<Integer> permissionIdList) {
        //批量插入角色权限表
        rolePermissionMapper.insertRolePermission(roleId, permissionIdList);
    }


    /**
     * @description: 试某个用户他所具有的角色和权限
     * @author 潇洒
     * @date 2018/4/20 上午11:02
     * @param userId
     */
    @Override
    public User getUser(int userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        //查询Role
        List<Role> roleList = roleMapper.listRoleByUserId(user.getId());
        if(roleList.size()>0){
            for (Role role : roleList){
                List<Permission> permissionList = permissionMapper.listPermissionByRoleId(role.getId());
                if(permissionList.size()>0){
                    role.setPermissionList(permissionList);
                }
            }
            user.setRoleList(roleList);
        }
        return user;
    }
}
