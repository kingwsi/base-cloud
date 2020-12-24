package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.entity.customer.Customer;
import org.springframework.stereotype.Component;

/**
 * description: CustomerMapper <br>
 * date: 2020/11/19 9:45 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Component
public interface CustomerMapper extends BaseMapper<Customer> {
}
