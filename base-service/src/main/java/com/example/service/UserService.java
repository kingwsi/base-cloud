package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.user.User;
import com.example.common.entity.user.UserConvertMapper;
import com.example.common.entity.user.UserVO;
import com.example.mapper.RoleMapper;
import com.example.mapper.UserMapper;
import com.example.mapper.UsersAndRolesMapper;
import com.example.common.exception.CustomException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description: []
 * Name: UserRepository
 * Author: wangshu
 * Date: 2019/6/29 15:52
 */
@Service
@Transactional(rollbackForClassName = {"Exception"})
public class UserService {

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserConvertMapper userConvertMapper;

    private final UsersAndRolesMapper usersAndRolesMapper;

    public UserService(UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder, UserConvertMapper userConvertMapper, UsersAndRolesMapper usersAndRolesMapper) {
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userConvertMapper = userConvertMapper;
        this.usersAndRolesMapper = usersAndRolesMapper;
    }

    public void createUser(UserVO vo) {
        User user = userConvertMapper.toUser(vo);
        user.setPassword(bCryptPasswordEncoder.encode(vo.getPassword()));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", vo.getUsername());
        if (!userMapper.selectList(queryWrapper).isEmpty()) throw new CustomException("用户名已存在");
        userMapper.insert(user);
        usersAndRolesMapper.batchInsert(user.getId(), vo.getRoleIds());
    }

    public IPage<UserVO> listUsersOfPage(Page<User> page, UserVO userVO) {
        return userMapper.selectUsersOfPage(page, userVO);
    }

    /**
     * 更新用户
     * 用户名称不可修改
     *
     * @param userVO
     */
    public void updateUser(UserVO userVO) {
        userVO.setPassword(null);
        usersAndRolesMapper.deleteByUserId(userVO.getId());
        User user = userConvertMapper.toUser(userVO);
        usersAndRolesMapper.batchInsert(user.getId(), userVO.getRoleIds());
        userMapper.updateById(user);
    }

    public UserVO getUserById(Integer id) {
        return userConvertMapper.toVO(userMapper.selectById(id));
    }

    public UserVO getUserWithRolesByUserId(Integer id) {
        return userMapper.selectUsersWithRoles(id);
    }

    public String resetPasswordById(Integer id) {
        String tmpPassword = String.valueOf(System.currentTimeMillis());
        User user = new User();
        user.setId(id);
        user.setPassword(bCryptPasswordEncoder.encode(tmpPassword));
        if (userMapper.updateById(user) == 0) {
            throw new CustomException("重置失败");
        }
        return tmpPassword;
    }
}
