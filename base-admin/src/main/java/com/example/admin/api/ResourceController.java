package com.example.admin.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.bean.ResponseData;
import com.example.common.entity.resource.ResourceQuery;
import com.example.common.entity.resource.ResourceVO;
import com.example.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 路由
 */
@Api(tags = "资源管理")
@RestController
@RequestMapping("/api/resource")
public class ResourceController {
    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @ApiOperation("获取菜单列表")
    @GetMapping("/list")
    public ResponseData list(HttpServletRequest request) {
        return ResponseData.OK(resourceService.list());
    }

    @ApiOperation("获取菜单列表")
    @GetMapping("/routes")
    public ResponseData listRoutes() {
        return ResponseData.OK(resourceService.currentUserRouters());
    }

    @ApiOperation("创建资源")
    @PostMapping
    public ResponseData create(@RequestBody ResourceVO resourceVO) {
        resourceService.create(resourceVO);
        return ResponseData.OK();
    }

    @ApiOperation("更新资源")
    @PutMapping
    public ResponseData updateById(@RequestBody ResourceVO resourceVO) {
        if (StringUtils.isEmpty(resourceVO.getId())){
            return ResponseData.FAIL("id不能为空");
        }
        resourceService.updateById(resourceVO);
        return ResponseData.OK();
    }

    @ApiOperation("获取资源分页")
    @GetMapping("/page")
    public ResponseData page(Page page, ResourceQuery resourceVO) {
        return ResponseData.OK(resourceService.listOfPage(page, resourceVO));
    }

    @ApiOperation("更新资源")
    @DeleteMapping("/{id}")
    public ResponseData deleteById(@PathVariable String id) {
        return resourceService.deleteById(id) ? ResponseData.OK() : ResponseData.FAIL("删除失败");
    }

    @ApiOperation("获取api列表")
    @GetMapping("/apis")
    public ResponseData listCurrentUserApis(String type, String userId) {
        List<String> list = resourceService.listUrisByMethodAndUser(type, userId);
        System.out.println("apis request");
        return ResponseData.OK(list);
    }
}
