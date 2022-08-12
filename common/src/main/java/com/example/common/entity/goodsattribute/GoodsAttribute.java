package com.example.common.entity.goodsattribute;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * description:  <br>
 * date: 2021-07-15 <br>
 * author: ws <br>
 */

@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("goods_attribute")
@ApiModel(value = "GoodsAttribute对象", description = "")
public class GoodsAttribute {

    private static final long serialVersionUID = 1L;

    private String attributeName;

    private String attributeValue;

    private Integer skuId;

    private Integer goodsId;


    public GoodsAttribute(Integer skuId, Integer goodsId, String name, String value) {
        this.skuId = skuId;
        this.goodsId = goodsId;
        this.attributeName = name;
        this.attributeValue = value;
    }

}
