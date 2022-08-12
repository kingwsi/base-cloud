package com.example.rest.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.bean.ResponseData;
import com.example.common.entity.goods.GoodsVO;
import com.example.service.GoodsService;
import com.example.service.GoodsSkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 商品接口 <br>
 * date: 2021/07/15 16:53 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Api(tags = "商品")
@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    private final GoodsService goodsService;
    private final GoodsSkuService goodsSpecService;

    public GoodsController(GoodsService goodsService, GoodsSkuService goodsSpecService) {
        this.goodsService = goodsService;
        this.goodsSpecService = goodsSpecService;
    }

    @ApiOperation("获取商品列表")
    @GetMapping("/list")
    public ResponseData<?> listGoods(Page<GoodsVO> page, GoodsVO vo){
        return ResponseData.OK(goodsService.page(page, vo));
    }

    @ApiOperation("获取商品详情")
    @GetMapping("/detail/{id}")
    public ResponseData<?> getDetailById(@PathVariable Integer id){
        return ResponseData.OK(goodsService.getGoodsWithDetailById(id));
    }

    @ApiOperation("获取商品sku")
    @GetMapping("/sku/list")
    public ResponseData<?> listSpecs(Integer goodsId){
        return ResponseData.OK(goodsSpecService.listSpecs(goodsId));
    }
}
