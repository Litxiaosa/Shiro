package com.shiro.admin.domain;

import lombok.Data;

import java.io.Serializable;


@Data
public class RolePermission implements Serializable {
    private Integer id;

    private Integer roleId;

    private Integer permissionId;

    private static final long serialVersionUID = 1L;
}