package com.example.common.pojo.wechat;

import lombok.Data;

/**
 * 如果开发者拥有多个移动应用、网站应用、和公众帐号（包括小程序）
 * 可通过 UnionID 来区分用户的唯一性，因为只要是同一个微信开放平台帐号下的移动应用、网站应用和公众帐号（包括小程序），
 * 用户的 UnionID 是唯一的。换句话说，同一用户，对同一个微信开放平台下的不同应用，UnionID是相同的。
 */
@Data
public class PaidUnionId {

    /**
     * 用户唯一标识，调用成功后返回
     **/
    private String unionid;

    /**
     * -1	系统繁忙，此时请开发者稍候再试
     * 0	请求成功
     * 40003	openid 错误
     * 89002	没有绑定开放平台帐号
     * 89300	订单无效
     */
    private int errcode;

    private String errmsg;
}
