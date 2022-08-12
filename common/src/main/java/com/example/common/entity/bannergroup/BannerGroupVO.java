package com.example.common.entity.bannergroup;

import com.example.common.entity.common.BaseEntityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* description: banner分组 <br>
* date: 2021-07-12 <br>
* author: ws <br>
*/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="BannerGroupVO", description="banner分组")
public class BannerGroupVO extends BaseEntityVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "code")
    private String code;

    @ApiModelProperty(value = "组名")
    private String name;

    @ApiModelProperty(value = "备注")
    private String remark;


}
