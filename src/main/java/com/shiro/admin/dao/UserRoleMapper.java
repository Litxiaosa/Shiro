package com.shiro.admin.dao;

import com.shiro.admin.domain.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    /**
     * @description: 批量插入用户角色表
     * @author 潇洒
     * @date 2018/4/20 上午10:39
     */
    void insertUserRole(@Param("userId") int userId, @Param("roleIds") List<Integer> roleIds);
}