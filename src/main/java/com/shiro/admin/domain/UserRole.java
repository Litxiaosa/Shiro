package com.shiro.admin.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRole implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer roleId;

    private static final long serialVersionUID = 1L;
}