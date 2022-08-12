package com.example.common.entity.brand;

import com.example.common.entity.common.BaseEntityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* description: 品牌 <br>
* date: 2021-07-15 <br>
* author: ws <br>
*/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="BrandVO", description="品牌")
public class BrandVO extends BaseEntityVO {

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
