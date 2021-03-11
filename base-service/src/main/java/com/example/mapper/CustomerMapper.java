package com.example.mapper;

import com.example.common.entity.customer.Customer;
import com.example.common.entity.customer.CustomerVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 客户 Mapper 接口
 * </p>
 *
 * @author ws
 * @since 2020-12-29
 */
@Component
public interface CustomerMapper extends BaseMapper<Customer> {
    IPage<CustomerVO> selectPage(Page<CustomerVO> page, @Param("vo") CustomerVO vo);
}
