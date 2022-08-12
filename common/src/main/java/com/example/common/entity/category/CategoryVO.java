package com.example.common.entity.category;

import com.example.common.entity.common.BaseEntityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* description: 分类 <br>
* date: 2021-07-12 <br>
* author:  <br>
*/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CategoryVO", description="分类")
public class CategoryVO extends BaseEntityVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "上级id -1为根")
    private Integer parentId;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "排序")
    private Integer sort;

}
