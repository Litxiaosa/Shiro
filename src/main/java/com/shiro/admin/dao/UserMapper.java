package com.shiro.admin.dao;

import com.shiro.admin.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * @description:  查询用户
     * @author 潇洒
     * @date 2018/4/19 下午6:15
     */
    @Select("SELECT id, user_name,  `password` FROM `user` WHERE user_name = #{userName}")
    @ResultMap("BaseResultMap")
    User getUserByName(@Param("userName") String userName);
}