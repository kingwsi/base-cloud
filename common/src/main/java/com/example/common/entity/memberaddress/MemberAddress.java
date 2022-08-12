package com.example.common.entity.memberaddress;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.entity.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
* description: 会员地址 <br>
* date: 2021-07-09 <br>
* author: ws <br>
*/

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("member_address")
@ApiModel(value="MemberAddress对象", description="会员地址")
public class MemberAddress extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "会员唯一标识")
    private Integer memberId;

    @ApiModelProperty(value = "省")
    @NotEmpty
    private String province;

    @ApiModelProperty(value = "市")
    @NotEmpty
    private String city;

    @ApiModelProperty(value = "镇")
    @NotEmpty
    private String town;

    @ApiModelProperty(value = "详细地址")
    @NotEmpty
    private String detail;

    @ApiModelProperty(value = "收件人电话")
    @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}")
    private String addresseeMobile;

    @ApiModelProperty(value = "完整地址")
    @NotEmpty
    private String fullAddress;

    @ApiModelProperty(value = "是否默认")
    private Boolean isDefault;

    @ApiModelProperty(value = "标签")
    private String tag;

    @ApiModelProperty(value = "收件人名字")
    private String addresseeName;
}
