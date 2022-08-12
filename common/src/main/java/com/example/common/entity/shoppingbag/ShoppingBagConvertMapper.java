package com.example.common.entity.shoppingbag;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* description:  转换工具 <br>
* date: 2021-08-03 <br>
* author: ws <br>
*/
@Component
@Mapper(componentModel = "spring")
public interface ShoppingBagConvertMapper {
    ShoppingBag toShoppingbag(ShoppingBagVO vo);

    List<ShoppingBag> toShoppingbagList(List<ShoppingBagVO> vo);

    ShoppingBagVO toShoppingbagVO(ShoppingBag shoppingbag);

    List<ShoppingBagVO> toShoppingbagVOList(List<ShoppingBag> vo);
}