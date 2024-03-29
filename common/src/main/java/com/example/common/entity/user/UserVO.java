package com.example.common.entity.user;

import com.example.common.entity.role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Description: []
 * Name: UserVO
 * Author: wangshu
 * Date: 2019/6/29 15:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private Integer id;

    @Size(groups = {Update.class, Insert.class}, min = 4, max = 15, message = "用户名长度需在5-15之间")
    private String username;

    @Size(min = 8, max = 15, message = "密码长度需在8-15之间")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$", message = "密码需要包含字母和数字")
    private String password;
    private Boolean remember;

    @Size(groups = {Update.class, Insert.class}, min = 2, max = 15, message = "全称长度需在2-15之间")
    private String fullName;
    private String avatar;
    @Size(min = 2, max = 15, message = "个人介绍长度需在50个字符以内")
    private String introduction;
    private String nickname;
    @NotEmpty
    private List<Integer> roleIds;
    private List<Role> roles;
    private String creator;
    private LocalDateTime createdDate;
    private String lastUpdater;
    private LocalDateTime lastUpdateDate;

    // 状态 0 禁用 1 启用
    private String status;
}
