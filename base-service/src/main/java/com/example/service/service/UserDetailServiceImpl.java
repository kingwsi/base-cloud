package com.example.service.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.service.entity.user.User;
import com.example.service.mapper.UserMapper;
import com.example.service.service.auth.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * description: UserDetailServiceImpl <br>
 * date: 2020/7/30 15:08 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    public UserDetailServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return new UserDetailsImpl(user);
    }
}
