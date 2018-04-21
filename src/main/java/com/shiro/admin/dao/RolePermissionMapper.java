package com.shiro.admin.dao;

import com.shiro.admin.domain.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);


    /**
     * @description: 批量插入角色权限表
     * @author 潇洒
     * @date 2018/4/20 上午10:54
     */
    void insertRolePermission(@Param("roleId") int roleId, @Param("permissionIds") List<Integer> permissionIdList);
}