package com.example.common.entity.operatelog;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
* description: 操作记录 <br>
* date: 2021-08-05 <br>
* author: ws <br>
*/

@Data
@Accessors(chain = true)
@TableName("operate_log")
@ApiModel(value="OperateLog对象", description="操作记录")
public class OperateLog {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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
