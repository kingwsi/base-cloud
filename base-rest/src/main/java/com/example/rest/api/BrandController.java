package com.example.rest.api;

import com.example.common.bean.ResponseData;
import com.example.common.entity.brand.BrandVO;
import com.example.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description: 品牌查询接口 <br>
 * date: 2021/07/15 16:53 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Api(tags = "品牌")
@RestController
@RequestMapping("/api/brand")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @ApiOperation("获取品牌列表")
    @GetMapping("/list")
    public ResponseData<List<BrandVO>> listBrands(@RequestBody BrandVO brandVO){
        return ResponseData.OK(brandService.listBrands(brandVO.getName()));
    }
}
