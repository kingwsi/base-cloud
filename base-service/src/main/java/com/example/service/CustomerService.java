package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.bean.AuthUser;
import com.example.common.entity.customer.Customer;
import com.example.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 客户 服务类
 * </p>
 *
 * @author ws
 * @since 2020-10-22
 */
@Service
public class CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    public String getToken(AuthUser authUser) {
        return "null";
    }

    public String authByPassword(AuthUser authUser) {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",authUser.getUsername());
        Customer customer = customerMapper.selectOne(queryWrapper);
        return "null";
    }

    public Customer getCustomerInfo(String token) {
        return new Customer();
    }
}
