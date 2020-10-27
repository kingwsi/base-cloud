package com.example.common.entity.customer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 客户
 * </p>
 *
 * @author ws
 * @since 2020-10-22
 */
@Data
@ApiModel(value="CustomerVO", description="客户")
public class CustomerVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "真是姓名")
    private String realName;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "介绍")
    private String introduce;

    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "最后登录ip")
    private String lastLoginIp;


}
