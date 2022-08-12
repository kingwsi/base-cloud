package com.example.common.entity.goodssku;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* description: SKU 转换工具 <br>
* date: 2021-07-15 <br>
* author: ws <br>
*/
@Component
@Mapper(componentModel = "spring")
public interface GoodsSkuConvertMapper {
    GoodsSku toGoodsSku(GoodsSkuVO vo);

    List<GoodsSku> toGoodsSkuList(List<GoodsSkuVO> vo);

    GoodsSkuVO toGoodsSkuVO(GoodsSku goodssku);

    List<GoodsSkuVO> toGoodsSkuVOList(List<GoodsSku> vo);
}