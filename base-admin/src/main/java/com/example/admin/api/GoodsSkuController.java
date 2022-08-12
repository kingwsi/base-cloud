package com.example.admin.api;

import com.example.common.bean.ResponseData;
import com.example.common.entity.goodssku.GoodsSkuVO;
import com.example.service.GoodsSkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * description: 商品规格相关 controller <br>
 * date: 2021-07-18 <br>
 * author: ws <br>
 */

@Api(tags = "商品规格相关")
@RestController
@RequestMapping("/api/goods-sku")
public class GoodsSkuController {

    private final GoodsSkuService goodsSpecService;

    public GoodsSkuController(GoodsSkuService goodsSpecService) {
        this.goodsSpecService = goodsSpecService;
    }

    @PostMapping
    @ApiOperation("新增规格")
    public ResponseData<?> create(@RequestBody GoodsSkuVO vo) {
        goodsSpecService.create(vo);
        return ResponseData.OK();
    }

    @PutMapping("/{goodsId}/list")
    @ApiOperation("新增规格 多个")
    public ResponseData<?> createList(@PathVariable Integer goodsId, @RequestBody List<GoodsSkuVO> skuVOS) {
        goodsSpecService.updateList(goodsId, skuVOS);
        return ResponseData.OK();
    }

    @PutMapping
    @ApiOperation("更新单个规格")
    public ResponseData<?> updateGoods(@RequestBody GoodsSkuVO vo) {
        return ResponseData.OK(goodsSpecService.updateById(vo));
    }

    @DeleteMapping
    @ApiOperation("删除规格")
    public ResponseData<?> deleteById(Integer id) {
        return ResponseData.OK(goodsSpecService.deleteSkuAndSpec(id));
    }

    @ApiOperation("获取商品sku")
    @GetMapping("/list")
    public ResponseData<?> listSpecs(Integer goodsId){
        return ResponseData.OK(goodsSpecService.listSpecs(goodsId));
    }
}
