package com.example.service.entity.authority;

import com.example.service.common.enumerate.FilterRule;
import com.example.service.entity.role.Role;
import com.example.service.entity.user.User;
import lombok.Data;

import java.util.List;

/**
 * 通过认证的用户主体
 * 每个请求创建时创建
 * 请求结束时候销毁
 */
@Data
public class Principal {
    // 用户
    private User user;
    // 角色
    private List<Role> roles;
    // 过滤规则
    private FilterRule filterRule;
    // 请求地址
    private String uri;
    // 请求方式
    private String method;
}
