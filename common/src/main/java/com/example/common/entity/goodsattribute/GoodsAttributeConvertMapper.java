package com.example.common.entity.goodsattribute;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* description:  转换工具 <br>
* date: 2021-07-15 <br>
* author: ws <br>
*/
@Component
@Mapper(componentModel = "spring")
public interface GoodsAttributeConvertMapper {
    GoodsAttribute toGoodsAttribute(GoodsAttributeVO vo);

    List<GoodsAttribute> toGoodsAttributeList(List<GoodsAttributeVO> vo);

    GoodsAttributeVO toGoodsAttributeVO(GoodsAttribute goodsattribute);

    List<GoodsAttributeVO> toGoodsAttributeVOList(List<GoodsAttribute> vo);
}