package com.example.common.entity.trade;

import com.example.common.entity.common.BaseEntityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
* description:  <br>
* date: 2021-08-05 <br>
* author: ws <br>
*/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="TradeVO", description="")
public class CreateTradeVO extends BaseEntityVO {

    private static final long serialVersionUID = 1L;

    private Integer memberId;

    private Integer num;

    @ApiModelProperty(value = "支付渠道")
    private String payChannel;

    @ApiModelProperty(value = "客户留言")
    private String memberMessage;

    @ApiModelProperty(value = "收货人地址ID")
    private Integer addressId;

    @ApiModelProperty(value = "sku")
    private Integer skuId;

    @ApiModelProperty(value = "购物袋id列表")
    private List<Integer> shoppingBagIds;
}
