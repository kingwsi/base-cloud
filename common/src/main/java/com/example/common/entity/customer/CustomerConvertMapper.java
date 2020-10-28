package com.example.common.entity.customer;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CustomerConvertMapper {
    Customer CustomerVoToCustomer(CustomerVO vo);

    CustomerVO CustomerToCustomerVO(CustomerVO vo);
}