package com.example.rest.api;

import com.example.common.bean.ResponseData;
import com.example.common.entity.banner.BannerVO;
import com.example.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description: TODO <br>
 * date: 2021/07/12 16:54 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Api(tags = "banner")
@RestController
@RequestMapping("/api/banner")
public class BannerController {
    private final BannerService bannerService;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping("/{code}/list")
    @ApiOperation("获取list（按分组code）")
    public ResponseData<List<BannerVO>> list(@PathVariable String code) {
        List<BannerVO> list = bannerService.listByCode(code);
        return ResponseData.OK(list);
    }
}
