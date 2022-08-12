package com.example.common.entity.brand;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.entity.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* description: 品牌 <br>
* date: 2021-07-15 <br>
* author: ws <br>
*/

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("brand")
@ApiModel(value="Brand对象", description="品牌")
public class Brand extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "LOGO")
    private String logo;

    @ApiModelProperty(value = "启用")
    private Boolean enable;

    @ApiModelProperty(value = "介绍")
    private String introduction;

    @ApiModelProperty(value = "排序")
    private Integer sort;


}
