package com.example.common.entity.favorites;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.entity.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* description:  <br>
* date: 2021-07-27 <br>
* author: ws <br>
*/

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("favorites")
@ApiModel(value="Favorites对象", description="")
public class Favorites extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer goodsId;

    private Integer memberId;


}
