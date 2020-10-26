package com.example.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.service.entity.organization.Organization;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OrganizationMapper extends BaseMapper<Organization> {
}
