package com.example.common.entity.goods;

import com.example.common.entity.common.BaseEntityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* description: 商品 <br>
* date: 2021-07-15 <br>
* author: ws <br>
*/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="GoodsVO", description="商品")
public class GoodsVO extends BaseEntityVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类Id")
    private Integer categoryId;

    @ApiModelProperty(value = "品牌id")
    private Integer brandId;

    @ApiModelProperty(value = "分类")
    private String categoryName;

    @ApiModelProperty(value = "品牌")
    private String brandName;

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
    private String status;

    @ApiModelProperty(value = "关键字")
    private String keyword;

    private Integer sort;

}
