package com.example.rest.api;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.bean.ResponseData;
import com.example.common.entity.favorites.FavoritesVO;
import com.example.service.FavoritesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* description:  controller <br>
* date: 2021-07-27 <br>
* author: ws <br>
*/

@Api(tags = "收藏夹")
@RestController
@RequestMapping("/api/favorites")
public class FavoritesController {
    private final FavoritesService favoritesService;

    public FavoritesController(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    @PostMapping("/{goodsId}")
    @ApiOperation("收藏或取消收藏")
    public ResponseData<Boolean> create(@PathVariable Integer goodsId) {
        return ResponseData.OK(favoritesService.createOrRemove(goodsId));
    }

    @GetMapping("/page")
    @ApiOperation("获取分页")
    public ResponseData<IPage<FavoritesVO>> listOfPage(Page<FavoritesVO> page, FavoritesVO favoritesVO) {
        IPage<FavoritesVO> pageInfo = favoritesService.listOfPage(page, favoritesVO);
        return ResponseData.OK(pageInfo);
    }
}
