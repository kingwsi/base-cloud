package com.example.admin.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.bean.ResponseData;
import com.example.common.entity.goods.GoodsVO;
import com.example.common.entity.goodsdetail.GoodsDetail;
import com.example.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 商品相关 controller <br>
 * date: 2021-07-18 <br>
 * author: ws <br>
 */

@Api(tags = "商品相关")
@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/page")
    @ApiOperation("获取分页")
    public ResponseData<?> listGoods(Page<GoodsVO> page, GoodsVO goodsVO) {
        return ResponseData.OK(goodsService.listOfPage(page, goodsVO));
    }

    @PostMapping
    @ApiOperation("新增商品")
    public ResponseData<?> create(@RequestBody GoodsVO vo) {
        return ResponseData.OK(goodsService.create(vo));
    }

    @PutMapping
    @ApiOperation("更新商品")
    public ResponseData<?> updateGoods(@RequestBody GoodsVO vo) {
        goodsService.updateById(vo);
        return ResponseData.OK();
    }

    @PostMapping("/{id}/detail")
    @ApiOperation("新增商品详情")
    public ResponseData<?> createDetail(@PathVariable Integer id, @RequestBody GoodsDetail detail) {
        return ResponseData.OK(goodsService.createDetail(id, detail));
    }

    @PutMapping("/{id}/detail")
    @ApiOperation("更新商品详情")
    public ResponseData<?> updateGoodsDetail(@PathVariable Integer id, @RequestBody GoodsDetail detail) {
        detail.setId(id);
        return ResponseData.OK(goodsService.updateDetail(detail));
    }

    @GetMapping("/detail")
    @ApiOperation("获取商品详情")
    public ResponseData<?> updateGoodsDetail(Integer id) {
        return ResponseData.OK(goodsService.getDetailById(id));
    }

    @GetMapping
    @ApiOperation("获取商品信息")
    public ResponseData<?> getInfoById(Integer id) {
        return ResponseData.OK(goodsService.getById(id));
    }
}
