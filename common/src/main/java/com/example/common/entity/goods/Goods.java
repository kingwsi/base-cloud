package com.example.common.entity.goods;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.entity.common.BaseEntity;
import com.example.common.enumerate.GoodsStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* description: 商品 <br>
* date: 2021-07-15 <br>
* author: ws <br>
*/

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("goods")
@ApiModel(value="Goods对象", description="商品")
public class Goods extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类Id")
    private Integer categoryId;

    @ApiModelProperty(value = "品牌id")
    private Integer brandId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "标签")
    private String tags;

    @ApiModelProperty(value = "最高价")
    private Integer minPrice;

    @ApiModelProperty(value = "最低价")
    private Integer maxPrice;

    @ApiModelProperty(value = "封面图")
    private String coverImage;

    @ApiModelProperty(value = "状态")
    private GoodsStatus status;

    @ApiModelProperty(value = "关键字")
    private String keyword;

    private Integer sort;


}
