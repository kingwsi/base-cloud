package com.example.admin.api;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.bean.ResponseData;
import com.example.common.entity.bannergroup.BannerGroupVO;
import com.example.service.BannerGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* description: banner分组 controller <br>
* date: 2021-07-12 <br>
* author: ws <br>
*/

@Api(tags = "banner分组")
@RestController
@RequestMapping("/api/banner-group")
public class BannerGroupController {
    private final BannerGroupService bannerGroupService;

    public BannerGroupController(BannerGroupService bannerGroupService) {
        this.bannerGroupService = bannerGroupService;
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseData<String> updateById(@RequestBody BannerGroupVO bannerGroupVO) {
        if (StringUtils.isEmpty(bannerGroupVO.getId())) {
            return ResponseData.FAIL("ID不能为空");
        }
        boolean result = bannerGroupService.updateById(bannerGroupVO);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @PostMapping
    @ApiOperation("新增")
    public ResponseData<String> create(@RequestBody BannerGroupVO bannerGroupVO) {
        boolean result = bannerGroupService.create(bannerGroupVO);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public ResponseData<String> deleteById(@PathVariable Integer id) {
        if (StringUtils.isEmpty(id)) {
            return ResponseData.FAIL("ID不能为空");
        }
        boolean result = bannerGroupService.removeById(id);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @GetMapping("/page")
    @ApiOperation("获取分页")
    public ResponseData<IPage<BannerGroupVO>> listOfPage(Page<BannerGroupVO> page, BannerGroupVO bannerGroupVO) {
        IPage<BannerGroupVO> pageInfo = bannerGroupService.listOfPage(page, bannerGroupVO);
        return ResponseData.OK(pageInfo);
    }

    @GetMapping("/list")
    @ApiOperation("获取list")
    public ResponseData<List<BannerGroupVO>> list() {
        List<BannerGroupVO> pageInfo = bannerGroupService.list();
        return ResponseData.OK(pageInfo);
    }
}
