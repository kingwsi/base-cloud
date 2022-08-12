package com.example.common.entity.bannergroup;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.entity.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* description: banner分组 <br>
* date: 2021-07-12 <br>
* author: ws <br>
*/

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("banner_group")
@ApiModel(value="BannerGroup对象", description="banner分组")
public class BannerGroup extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "code")
    private String code;

    @ApiModelProperty(value = "组名")
    private String name;

    @ApiModelProperty(value = "备注")
    private String remark;


}
