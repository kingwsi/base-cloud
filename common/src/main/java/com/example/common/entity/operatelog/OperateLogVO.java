package com.example.common.entity.operatelog;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
* description: 操作记录 <br>
* date: 2021-08-05 <br>
* author: ws <br>
*/
@Data
@ApiModel(value="OperateLogVO", description="操作记录")
public class OperateLogVO {

    private static final long serialVersionUID = 1L;

    Integer id;

    private String code;

    @ApiModelProperty(value = "操作前的值")
    private String beforeValue;

    @ApiModelProperty(value = "操作后的值")
    private String afterValue;

    @ApiModelProperty(value = "备注")
    private String remark;

    private Integer outId;

    @ApiModelProperty(value = "操作者")
    private Integer operator;

    @ApiModelProperty(value = "日期")
    private LocalDateTime operateDate;

    @ApiModelProperty(value = "表名")
    private String targetTableName;


}
