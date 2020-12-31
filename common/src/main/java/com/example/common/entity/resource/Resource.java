package com.example.common.entity.resource;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.entity.common.BaseEntity;
import com.example.common.enumerate.ResourceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description: 资源<br>
 * Comments Name: Resource<br>
 * Date: 2019/7/11 15:45<br>
 * Author: wangshu<br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_resources")
public class Resource extends BaseEntity {

    // 名称
    private String name;

    // 地址
    private String uri;

    // 请求方式 GET;POST;OPTIONS;DELETE;PUT
    private String methods;

    // 描述
    private String description;

    // 上级id 若资源是菜单则需要
    private Integer parentId;

    // 排序
    private String sort;

    // 组件 菜单需要
    private String component;

    // 图标
    private String icon;

    // 类型
    private ResourceTypeEnum type;

    private String remark;
}
