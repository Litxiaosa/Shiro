package com.shiro.admin.dao;

import com.shiro.admin.domain.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);


    /**
     * @description: 根据roleId 查询权限
     * @author 潇洒
     * @date 2018/4/20 上午11:16
     */
    List<Permission> listPermissionByRoleId(@Param("roleId") Integer roleId);
}