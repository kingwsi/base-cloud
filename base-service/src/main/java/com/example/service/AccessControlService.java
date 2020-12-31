package com.example.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.common.bean.RespCodeEnum;
import com.example.common.bean.AuthUser;
import com.example.common.entity.resource.ResourceConvertMapper;
import com.example.common.entity.resource.ResourceVO;
import com.example.common.entity.user.UserConvertMapper;
import com.example.common.entity.user.UserVO;
import com.example.common.exception.CustomException;
import com.example.common.utils.TokenUtils;
import com.example.common.entity.user.User;
import com.example.mapper.ResourceMapper;
import com.example.mapper.UserMapper;
import com.example.common.entity.resource.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * Description: 访问控制服务<br>
 * Comments Name: AccessControlService<br>
 * Date: 2019/7/12 10:14<br>
 * Author: wangshu<br>
 */
@Slf4j
@Service
public class AccessControlService {

    private final HttpServletRequest request;

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ResourceMapper resourceMapper;

    private final ResourceConvertMapper resourceConvertMapper;

    public AccessControlService(HttpServletRequest request, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder, ResourceMapper resourceMapper, ResourceConvertMapper resourceConvertMapper) {
        this.request = request;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.resourceMapper = resourceMapper;
        this.userMapper = userMapper;
        this.resourceConvertMapper = resourceConvertMapper;
    }

    /**
     * 查询用户所拥有的url类型资源，并按请求方式过滤
     *
     * @param userId
     * @param method
     * @return
     */
    public List<Resource> listByUserAndMethod(Integer userId, String method) {
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

    /**
     * 获取当前用户信息
     *
     * @return
     */
    public UserVO getUserInfo() {
        return Optional.of(Integer.valueOf(request.getHeader("x-id")))
                .map(userMapper::selectUsersWithRoles).get();

    }

    /**
     * 获取当前用户菜单资源
     *
     * @return
     */
    public List<ResourceVO> getCurrentUserRouters() {
        return Optional.of(Integer.valueOf(request.getHeader("x-id")))
                .map(resourceMapper::selectRouteByUserId).map(resourceConvertMapper::toResourceVOs).get();
    }
}
