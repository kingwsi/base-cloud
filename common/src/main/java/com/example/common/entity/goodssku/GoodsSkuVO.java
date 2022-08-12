package com.example.common.entity.goodssku;

import com.example.common.entity.goodsattribute.GoodsAttributeVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
* description: SKU <br>
* date: 2021-07-15 <br>
* author: ws <br>
*/
@Data
@ApiModel(value="GoodsSkuVO", description="SKU")
public class GoodsSkuVO {

    private static final long serialVersionUID = 1L;

    private Integer id;

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

    @ApiModelProperty(value = "规格明细")
    List<GoodsAttributeVO> attrs;

    @ApiModelProperty(value = "属性")
    private String propertyDescription;

    public GoodsSku toGoodsSku(){
        GoodsSku goodsSku = new GoodsSku();
        goodsSku.setEnable(this.enable);
        goodsSku.setGoodsId(this.goodsId);
        goodsSku.setPrice(this.price);
        goodsSku.setSort(this.sort);
        goodsSku.setId(this.id);
        goodsSku.setPurchaseLimit(this.purchaseLimit);
        goodsSku.setThumbnail(this.thumbnail);
        goodsSku.setStock(this.stock);
        return goodsSku;
    }
}
