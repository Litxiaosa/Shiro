package com.shiro.admin.dao;

import com.shiro.admin.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);


    /**
     * @description: 根据UserId查询Role
     * @author 潇洒
     * @date 2018/4/20 上午11:05
     */
    List<Role> listRoleByUserId(@Param("userId") Integer userId);
}