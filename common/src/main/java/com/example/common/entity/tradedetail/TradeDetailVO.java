package com.example.common.entity.tradedetail;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* description:  <br>
* date: 2021-08-05 <br>
* author: ws <br>
*/
@Data
@ApiModel(value="TradeDetailVO", description="")
public class TradeDetailVO {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    private Integer goodsId;

    private Integer skuId;

    private String skuProperties;

    @ApiModelProperty(value = "sku图片")
    private String skuImage;

    @ApiModelProperty(value = "数量")
    private Integer num;

    @ApiModelProperty(value = "标价")
    private Integer price;

    @ApiModelProperty(value = "支付金额")
    private Integer payAmount;
}
