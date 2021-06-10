package com.example.common.entity.apiwhitelist;

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
* description: api白名单 <br>
* date: 2021-06-10 <br>
* author: ws <br>
*/

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("SYS_API_WHITELIST")
@ApiModel(value="ApiWhitelist对象", description="api白名单")
public class ApiWhitelist extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "描述")
    @TableField("DESCRIPTION")
    private String description;

    @ApiModelProperty(value = "地址")
    @TableField("PATH")
    private String path;


}
