package com.example.common.entity.customer;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CustomerConvertMapper {
    Customer toCustomer(CustomerVO vo);

    List<Customer> toCustomerList(List<CustomerVO> vo);

    CustomerVO toCustomerVO(Customer customer);

    List<CustomerVO> toCustomerVOList(List<Customer> vo);
}