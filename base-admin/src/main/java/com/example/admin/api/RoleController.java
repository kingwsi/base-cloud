package com.example.admin.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.role.Role;
import com.example.common.entity.role.RoleVO;
import com.example.service.RoleService;
import com.example.common.bean.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "角色信息")
@RestController
@RequestMapping("/api/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseData<?> create(@RequestBody RoleVO roleVO) {
        roleService.createRoleVO(roleVO);
        return ResponseData.OK();
    }

    @GetMapping("/resources")
    public ResponseData<?> listByRoleId(@RequestParam Integer id) {
        RoleVO roleVO = roleService.getRoleWithResources(id);
        return ResponseData.OK(roleVO);
    }

    @DeleteMapping("/{id}")
    public ResponseData<?> delete(@PathVariable("id") Integer id) {
        roleService.deleteById(id);
        return ResponseData.OK();
    }

    @PutMapping
    public ResponseData<?> updateById(@RequestBody RoleVO roleVO) {
        return ResponseData.OK(roleService.updateById(roleVO));
    }

    @PutMapping("/resources")
    public ResponseData<?> updateRoleResources(@RequestBody RoleVO roleVO) {
        roleService.updateRoleResources(roleVO);
        return ResponseData.OK();
    }

    @GetMapping("/page")
    @ApiOperation("获取分页")
    public ResponseData<?> page(Page<Role> page, RoleVO vo) {
        return ResponseData.OK(roleService.listOfPages(page, vo));
    }

    @GetMapping("/list")
    @ApiOperation("获取列表")
    public ResponseData<?> getList(RoleVO vo) {
        return ResponseData.OK(roleService.list(vo));
    }
}
