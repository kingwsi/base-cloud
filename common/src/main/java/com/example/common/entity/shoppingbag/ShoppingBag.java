package com.example.common.entity.shoppingbag;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.entity.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* description:  <br>
* date: 2021-08-03 <br>
* author: ws <br>
*/

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("shopping_bag")
@ApiModel(value="ShoppingBag对象", description="")
public class ShoppingBag extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer goodsId;

    private Integer skuId;

    private Integer memberId;

    private String skuName;

    private Integer num;

}
