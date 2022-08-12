package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.memberaddress.MemberAddress;
import com.example.common.entity.memberaddress.MemberAddressVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
* description: 会员地址 Mapper接口 <br>
* date: 2021-07-09 <br>
* author: ws <br>
*/
@Component
public interface MemberAddressMapper extends BaseMapper<MemberAddress> {
    IPage<MemberAddressVO> selectPage(Page<MemberAddressVO> page, @Param("vo") MemberAddressVO vo);
}
