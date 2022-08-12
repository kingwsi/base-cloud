package com.example.common.entity.goodsdetail;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
* description: 商品明细 <br>
* date: 2021-07-15 <br>
* author: ws <br>
*/

@Data
@Accessors(chain = true)
@TableName("goods_detail")
@ApiModel(value="GoodsDetail对象", description="商品明细")
public class GoodsDetail {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "富文本")
    private String richText;

    @ApiModelProperty(value = "分享图片")
    private String shareImage;

    @ApiModelProperty(value = "轮播图")
    private String slideshow;

    @ApiModelProperty(value = "是否包邮")
    private Boolean ship;

    @ApiModelProperty(value = "标签")
    private String tags;

    @ApiModelProperty(value = "服务")
    private String services;

    @ApiModelProperty(value = "是否可退货")
    private Boolean salesReturn;

    private Boolean autoOnSale;

    private LocalDateTime onSaleTime;

    private LocalDateTime offSaleTime;
}
