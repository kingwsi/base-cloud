package com.example.admin.api;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.bean.ResponseData;
import com.example.common.entity.banner.BannerVO;
import com.example.service.BannerService;
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

/**
* description: banner controller <br>
* date: 2021-07-12 <br>
* author: ws <br>
*/

@Api(tags = "banner")
@RestController
@RequestMapping("/api/banner")
public class BannerController {
    private final BannerService bannerService;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseData<String> updateById(@RequestBody BannerVO bannerVO) {
        if (StringUtils.isEmpty(bannerVO.getId())) {
            return ResponseData.FAIL("ID不能为空");
        }
        boolean result = bannerService.updateById(bannerVO);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @PostMapping
    @ApiOperation("新增")
    public ResponseData<String> create(@RequestBody BannerVO bannerVO) {
        boolean result = bannerService.create(bannerVO);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public ResponseData<String> deleteById(@PathVariable Integer id) {
        if (StringUtils.isEmpty(id)) {
            return ResponseData.FAIL("ID不能为空");
        }
        boolean result = bannerService.removeById(id);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @GetMapping("/page")
    @ApiOperation("获取分页")
    public ResponseData<IPage<BannerVO>> listOfPage(Page<BannerVO> page, BannerVO bannerVO) {
        IPage<BannerVO> pageInfo = bannerService.listOfPage(page, bannerVO);
        return ResponseData.OK(pageInfo);
    }
}
