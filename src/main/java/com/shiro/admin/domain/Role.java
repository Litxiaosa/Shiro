package com.shiro.admin.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class Role implements Serializable {
    private Integer id;

    private String roleName;

    /**
     * 权限
     */
    private List<Permission> permissionList;

    private static final long serialVersionUID = 1L;
}