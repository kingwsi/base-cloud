package com.example.service.entity.role;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.service.entity.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description: 角色<br>
 * Comments Name: Role<br>
 * Date: 2019/7/11 16:29<br>
 * Author: wangshu<br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_roles")
public class Role extends BaseEntity {
    private String name;
    private String status;
    private String description;
}
