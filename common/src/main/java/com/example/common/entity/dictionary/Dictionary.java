package com.example.common.entity.dictionary;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.example.common.entity.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* description: 字典数据 <br>
* date: 2021-06-14 <br>
* author:  <br>
*/

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dictionaries")
@ApiModel(value="Dictionary对象", description="字典数据")
public class Dictionary extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "值")
    @TableField("VALUE")
    private String value;

    @ApiModelProperty(value = "编码")
    @TableField("CODE")
    private String code;

    @ApiModelProperty(value = "描述")
    @TableField("DESCRIPTION")
    private String description;

    @ApiModelProperty(value = "分组CODE")
    @TableField("GROUP_CODE")
    private String groupCode;

    @ApiModelProperty(value = "排序")
    @TableField("SORT")
    private Integer sort;


}
