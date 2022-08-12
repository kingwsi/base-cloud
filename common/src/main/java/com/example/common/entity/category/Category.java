package com.example.common.entity.category;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.entity.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* description: 分类 <br>
* date: 2021-07-12 <br>
* author:  <br>
*/

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("category")
@ApiModel(value="Category对象", description="分类")
public class Category extends BaseEntity {

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
