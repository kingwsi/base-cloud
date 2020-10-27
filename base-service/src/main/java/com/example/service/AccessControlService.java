package com.example.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.common.bean.RespCodeEnum;
import com.example.common.bean.AuthUser;
import com.example.common.exception.CustomException;
import com.example.common.utils.TokenUtils;
import com.example.common.entity.user.User;
import com.example.mapper.ResourceMapper;
import com.example.mapper.RoleMapper;
import com.example.mapper.UserMapper;
import com.example.common.entity.resource.Resource;
import com.example.common.entity.role.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: 访问控制服务<br>
 * Comments Name: AccessControlService<br>
 * Date: 2019/7/12 10:14<br>
 * Author: wangshu<br>
 */
@Slf4j
@Service
public class AccessControlService {

    private final RoleMapper roleMapper;

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ResourceMapper resourceMapper;

    public AccessControlService(RoleMapper roleMapper,  UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder, ResourceMapper resourceMapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.resourceMapper = resourceMapper;
        this.roleMapper = roleMapper;
        this.userMapper = userMapper;
    }

    /**
     * 查询用户拥有的角色列表
     *
     * @param userId
     * @return
     */
    public List<Role> getRolesByUserId(String userId) {
        return roleMapper.selectByUserId(userId);
    }

    /**
     * 查询用户所拥有的url类型资源，并按请求方式过滤
     *
     * @param userId
     * @param method
     * @return
     */
    public List<Resource> listByUserAndMethod(String userId, String method) {
        return resourceMapper.selectByUserAndMethod(userId, method);
    }

    public String auth(AuthUser authUser) {
        User user = userMapper.selectOne(Wrappers.query(new User()).eq("username", authUser.getUsername()));
        if (user != null && bCryptPasswordEncoder.matches(authUser.getPassword(), user.getPassword())) {
            if (!"1".equals(user.getStatus())) {
                throw new CustomException(RespCodeEnum.USER_DISABLE);
            }
            return TokenUtils.createToken(user.toAuthUser());
        }
        throw new CustomException(RespCodeEnum.AUTH_FAILED);
    }
}
