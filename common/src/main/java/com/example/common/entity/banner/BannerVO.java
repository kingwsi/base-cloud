package com.example.common.entity.banner;

import com.example.common.entity.common.BaseEntityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* description: banner <br>
* date: 2021-07-12 <br>
* author: ws <br>
*/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="BannerVO", description="banner")
public class BannerVO extends BaseEntityVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "链接")
    private String link;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "分组")
    private String groupCode;

    @ApiModelProperty(value = "组名")
    private String groupName;
}
