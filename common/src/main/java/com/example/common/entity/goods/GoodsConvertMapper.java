package com.example.common.entity.goods;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* description: 商品 转换工具 <br>
* date: 2021-07-15 <br>
* author: ws <br>
*/
@Component
@Mapper(componentModel = "spring")
public interface GoodsConvertMapper {
    Goods toGoods(GoodsVO vo);

    Goods toGoods(GoodsEditVO vo);

    List<Goods> toGoodsList(List<GoodsVO> vo);

    GoodsVO toGoodsVO(Goods goods);

    List<GoodsVO> toGoodsVOList(List<Goods> vo);
}