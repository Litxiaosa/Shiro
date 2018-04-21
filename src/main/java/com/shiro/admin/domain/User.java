package com.shiro.admin.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class User implements Serializable {
    private Integer id;

    private String userName;

    private String password;

    /**
     * 角色
     */
    private List<Role> roleList;

    private static final long serialVersionUID = 1L;
}