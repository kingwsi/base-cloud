package com.example.common.entity.memberaddress;

import com.example.common.entity.common.BaseEntityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
* description: 会员地址 <br>
* date: 2021-07-09 <br>
* author: ws <br>
*/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="MemberAddressVO", description="会员地址")
public class MemberAddressVO extends BaseEntityVO {

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
    @NotEmpty
    private String addresseeMobile;

    @ApiModelProperty(value = "完整地址")
    private String fullAddress;

    @ApiModelProperty(value = "是否默认")
    private Boolean isDefault;

    @ApiModelProperty(value = "标签")
    private String tag;

    @ApiModelProperty(value = "收件人名称")
    private String addresseeName;
}
