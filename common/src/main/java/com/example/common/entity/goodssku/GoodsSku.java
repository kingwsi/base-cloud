package com.example.common.entity.goodssku;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.entity.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* description: SKU <br>
* date: 2021-07-15 <br>
* author: ws <br>
*/

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("goods_sku")
@ApiModel(value="GoodsSku对象", description="SKU")
public class GoodsSku extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "缩略图")
    private String thumbnail;

    private Integer goodsId;

    @ApiModelProperty(value = "价格")
    private Integer price;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "购买限制")
    private Integer purchaseLimit;

    @ApiModelProperty(value = "生效")
    private Boolean enable;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "属性描述")
    private String propertyDescription;
}
