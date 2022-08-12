package com.example.common.entity.shoppingbag;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

/**
* description:  <br>
* date: 2021-08-03 <br>
* author: ws <br>
*/
@Data
@ApiModel(value="ShoppingBagVO", description="")
public class ShoppingBagVO {

    private Integer id;

    private static final long serialVersionUID = 1L;

    private Integer goodsId;

    private Integer skuId;

    private Integer memberId;

    private String skuName;

    private Integer num;

    private String goodsName;

    private String goodsTitle;

    private String status;

    private String skuThumbnail;

    private Integer price;

    private LocalDateTime lastUpdateDate;
}
