package com.example.service;

import com.example.common.bean.AuthUser;
import com.example.common.entity.customer.Customer;
import org.springframework.stereotype.Service;

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

    public String getToken(AuthUser authUser) {
        return "null";
    }

    public Customer getCustomerInfo(String token) {
        return new Customer();
    }
}
