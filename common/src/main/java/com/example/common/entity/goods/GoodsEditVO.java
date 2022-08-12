package com.example.common.entity.goods;

import com.example.common.entity.goodsdetail.GoodsDetail;
import com.example.common.entity.goodssku.GoodsSkuVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * description: 商品编辑 <br>
 * date: 2021/07/16 09:55 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Data
@ApiModel(value="GoodsEditVO", description="商品编辑")
public class GoodsEditVO {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "分类Id")
    @NotEmpty
    private Integer categoryId;

    @ApiModelProperty(value = "品牌id")
    @NotEmpty
    private Integer brandId;

    @ApiModelProperty(value = "名称")
    @NotEmpty
    private String name;

    @ApiModelProperty(value = "标题")
    @NotEmpty
    private String title;

    @ApiModelProperty(value = "标签")
    private String tags;

    @ApiModelProperty(value = "最高价")
    private Integer minPrice;

    @ApiModelProperty(value = "最低价")
    private Integer maxPrice;

    @ApiModelProperty(value = "封面图")
    @NotEmpty
    private String coverImage;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "关键字")
    private String keyword;

    private Integer sort;

    @ApiModelProperty(value = "商品规格")
    private List<GoodsSkuVO> sku;

    @ApiModelProperty(value = "detail")
    private GoodsDetail detail;
}
