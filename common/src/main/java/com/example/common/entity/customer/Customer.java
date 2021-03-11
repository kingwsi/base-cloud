package com.example.common.entity.customer;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.example.common.bean.AuthUser;
import com.example.common.entity.common.BaseEntity;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 客户
 * </p>
 *
 * @author ws
 * @since 2020-12-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("customer")
@ApiModel(value="Customer对象", description="客户")
public class Customer extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "真实姓名")
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

    private String password;

    @ApiModelProperty(value = "账户状态 1 正常 0 停用")
    private String accountStatus;
}
