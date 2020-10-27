package com.example.common.entity.customer;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerConvertMapper {
    Customer CustomerVoToCustomer(CustomerVO vo);

    CustomerVO CustomerToCustomerVO(CustomerVO vo);
}