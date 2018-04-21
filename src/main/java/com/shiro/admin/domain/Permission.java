package com.shiro.admin.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Permission implements Serializable {
    private Integer id;

    private String permissionName;

    private static final long serialVersionUID = 1L;
}