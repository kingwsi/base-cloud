package com.example.common.entity.role;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * description: RoleConvertMapper <br>
 * date: 2020/8/10 14:51 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */

@Component
@Mapper(componentModel = "spring")
public interface RoleConvertMapper {
    Role toRole(RoleVO roleVO);

    List<RoleVO> rolesToRoleVOs(List<Role> roles);
}
