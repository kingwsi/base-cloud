package com.example.common.entity.goods;

import com.example.common.entity.goodssku.GoodsSkuVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * description: 商品 <br>
 * date: 2021-07-15 <br>
 * author: ws <br>
 */
@Data
@ApiModel(value="GoodsInfoVO", description="商品信息")
public class GoodsInfoVO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "分类Id")
    private Integer categoryId;

    @ApiModelProperty(value = "商品名称")
    private String categoryName;

    @ApiModelProperty(value = "品牌id")
    private Integer brandId;

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

    @ApiModelProperty(value = "关键字")
    private String keyword;

    @ApiModelProperty(value = "富文本")
    private String richText;

    @ApiModelProperty(value = "分享图片")
    private String shareImage;

    @ApiModelProperty(value = "轮播图")
    private String slideshow;

    @ApiModelProperty(value = "商品规格")
    private List<GoodsSkuVO> sku;

    @ApiModelProperty(value = "是否收藏")
    private Boolean favorites;
}
