package com.example.common.entity.favorites;

import com.example.common.entity.common.BaseEntityVO;
import com.example.common.enumerate.GoodsStatus;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
* description:  <br>
* date: 2021-07-27 <br>
* author: ws <br>
*/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="FavoritesVO", description="")
public class FavoritesVO extends BaseEntityVO {

    private static final long serialVersionUID = 1L;

    @NotEmpty
    private Integer goodsId;

    private Integer memberId;

    private String goodsName;

    private Integer minPrice;

    private String goodsTitle;

    private String coverImage;

    private GoodsStatus status;

}
