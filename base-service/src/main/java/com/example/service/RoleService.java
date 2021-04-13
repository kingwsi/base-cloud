package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.role.Role;
import com.example.common.entity.role.RoleConvertMapper;
import com.example.common.entity.role.RoleVO;
import com.example.common.entity.role.RolesAndResources;
import com.example.mapper.RoleMapper;
import com.example.mapper.RolesAndResourcesMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Description: 角色服务<br>
 * Comments Name: RoleService<br>
 * Date: 2019/7/11 16:52<br>
 * Author: Administrator<br>
 */
@Service
public class RoleService {

    private final RoleMapper roleMapper;

    private final RoleConvertMapper roleConvertMapper;

    private final RolesAndResourcesMapper rolesAndResourcesMapper;

    public RoleService(RoleMapper roleMapper, RoleConvertMapper roleConvertMapper, RolesAndResourcesMapper rolesAndResourcesMapper) {
        this.roleMapper = roleMapper;
        this.roleConvertMapper = roleConvertMapper;
        this.rolesAndResourcesMapper = rolesAndResourcesMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public void createRoleVO(RoleVO roleVO) {
        this.roleMapper.insert(roleConvertMapper.toRole(roleVO));
    }

    public void deleteById(Integer id) {
        this.roleMapper.deleteById(id);
    }


    public Role updateById(RoleVO roleVO) {
        Role role = roleConvertMapper.toRole(roleVO);
        roleMapper.updateById(role);
        return role;
    }

    /**
     * 更新角色资源
     *
     * @param roleVO
     */
    public void updateRoleResources(RoleVO roleVO) {
        rolesAndResourcesMapper.delete(Wrappers.lambdaQuery(RolesAndResources.class).eq(RolesAndResources::getRoleId, roleVO.getId()));
        rolesAndResourcesMapper.batchInsertRoleResources(roleVO.getId(), roleVO.getResourceIdList());
    }

    public IPage<Role> listOfPages(Page<Role> page, RoleVO roleVO) {
        return roleMapper.selectPage(page, Wrappers.lambdaQuery(Role.class).eq(!StringUtils.isEmpty(roleVO.getStatus()), Role::getStatus, roleVO.getStatus()));
    }

    /**
     * 根据id获取角色信息
     *
     * @param id
     * @return
     */
    public RoleVO getRoleWithResources(Integer id) {
        return roleMapper.selectRoleWithResources(id);
    }

    public List<RoleVO> list(RoleVO vo) {
        List<Role> roles = roleMapper.selectList(Wrappers.emptyWrapper());
        return roleConvertMapper.rolesToRoleVOs(roles);
    }
}
