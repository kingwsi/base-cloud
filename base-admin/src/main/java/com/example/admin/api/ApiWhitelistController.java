package com.example.admin.api;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.example.common.entity.apiwhitelist.ApiWhitelistVO;
import com.example.service.ApiWhitelistService;
import com.example.common.bean.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
* description: api白名单 controller <br>
* date: 2021-06-10 <br>
* author: ws <br>
*/

@Api(tags = "api白名单")
@RestController
@RequestMapping("/api/api-whitelist")
public class ApiWhitelistController {
    private final ApiWhitelistService apiWhitelistService;

    public ApiWhitelistController(ApiWhitelistService apiWhitelistService) {
        this.apiWhitelistService = apiWhitelistService;
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseData<String> updateById(@RequestBody ApiWhitelistVO apiWhitelistVO) {
        if (StringUtils.isEmpty(apiWhitelistVO.getId())) {
            return ResponseData.FAIL("ID不能为空");
        }
        boolean result = apiWhitelistService.updateById(apiWhitelistVO);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @PostMapping
    @ApiOperation("新增")
    public ResponseData<String> create(@RequestBody ApiWhitelistVO apiWhitelistVO) {
        boolean result = apiWhitelistService.create(apiWhitelistVO);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public ResponseData<String> deleteById(@PathVariable Integer id) {
        if (StringUtils.isEmpty(id)) {
            return ResponseData.FAIL("ID不能为空");
        }
        boolean result = apiWhitelistService.removeById(id);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @GetMapping("/page")
    @ApiOperation("获取分页")
    public ResponseData<IPage<ApiWhitelistVO>> listOfPage(Page<ApiWhitelistVO> page, ApiWhitelistVO apiWhitelistVO) {
        IPage<ApiWhitelistVO> pageInfo = apiWhitelistService.listOfPage(page, apiWhitelistVO);
        return ResponseData.OK(pageInfo);
    }
}
