package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.role.Role;
import com.example.common.entity.role.RoleVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleMapper extends BaseMapper<Role> {
    IPage<RoleVO> selectPageWithResources(Page<RoleVO> page, @Param("roleVO") RoleVO roleVO);

    RoleVO selectRoleWithResourceIds(@Param("id") Integer id);

    RoleVO selectRoleWithResources(@Param("id") Integer id);

    List<Role> selectByUserId(@Param("userId") Integer userId);
}
