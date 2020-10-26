package com.example.service.entity.role;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description: 角色权限关联实体<br>
 * Comments Name: RolesAndResources<br>
 * Date: 2019/7/11 16:35<br>
 * Author: wangshu<br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_roles_and_resources")
public class RolesAndResources implements Serializable {

    private String roleId;

    private String resourceId;
}
