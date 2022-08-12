package com.example.common.entity.memberaddress;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* description: 会员地址 转换工具 <br>
* date: 2021-07-09 <br>
* author: ws <br>
*/
@Component
@Mapper(componentModel = "spring")
public interface MemberAddressConvertMapper {
    MemberAddress toMemberAddress(MemberAddressVO vo);

    List<MemberAddress> toMemberAddressList(List<MemberAddressVO> vo);

    MemberAddressVO toMemberAddressVO(MemberAddress memberaddress);

    List<MemberAddressVO> toMemberAddressVOList(List<MemberAddress> vo);
}