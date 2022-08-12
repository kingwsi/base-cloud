package com.example.common.entity.banner;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.entity.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* description: banner <br>
* date: 2021-07-12 <br>
* author: ws <br>
*/

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("banner")
@ApiModel(value="Banner对象", description="banner")
public class Banner extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "链接")
    private String link;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "分组")
    private String groupCode;


}
