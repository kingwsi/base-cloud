package com.example.common.entity.goodsattribute;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
* description:  <br>
* date: 2021-07-15 <br>
* author: ws <br>
*/
@Data
@ApiModel(value="GoodsAttributeVO")
public class GoodsAttributeVO {

    private Integer id;

    private static final long serialVersionUID = 1L;

    private String attributeName;

    private String attributeValue;

    private Integer skuId;
}
