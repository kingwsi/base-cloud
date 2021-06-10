package com.example.common.entity.apiwhitelist;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.example.common.entity.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.example.common.entity.common.BaseEntityVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* description: api白名单 <br>
* date: 2021-06-10 <br>
* author: ws <br>
*/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="ApiWhitelistVO", description="api白名单")
public class ApiWhitelistVO extends BaseEntityVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "描述")
    @TableField("DESCRIPTION")
    private String description;

    @ApiModelProperty(value = "地址")
    @TableField("PATH")
    private String path;


}
