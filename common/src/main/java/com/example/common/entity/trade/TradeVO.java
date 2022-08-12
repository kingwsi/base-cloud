package com.example.common.entity.trade;

import com.example.common.entity.common.BaseEntityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
* description:  <br>
* date: 2021-08-05 <br>
* author: ws <br>
*/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="TradeVO", description="")
public class TradeVO extends BaseEntityVO {

    private static final long serialVersionUID = 1L;

    private Integer memberId;

    @ApiModelProperty(value = "总金额")
    private Integer totalAmount;

    @ApiModelProperty(value = "实际支付金额")
    private Integer payAmount;

    @ApiModelProperty(value = "支付时间")
    private LocalDateTime payDatetime;

    @ApiModelProperty(value = "支付渠道")
    private String payChannel;

    @ApiModelProperty(value = "客户留言")
    private String memberMessage;

    @ApiModelProperty(value = "确认收货时间")
    private LocalDateTime receiptDatetime;

    @ApiModelProperty(value = "单号")
    private String tradeNo;

    @ApiModelProperty(value = "外部订单号")
    private String outTradeNo;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "收货人")
    private String consignee;

    @ApiModelProperty(value = "收货人地址")
    private String consigneeAddress;

    @ApiModelProperty(value = "收货人手机号")
    private String consigneeMobile;
}
