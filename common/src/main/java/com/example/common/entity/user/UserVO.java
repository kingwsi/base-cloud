package com.example.common.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.Instant;
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
    private String id;

    @Size(groups = {Update.class, Insert.class}, min = 5, max = 15, message = "用户名长度需在5-15之间")
    @NotBlank(groups = {Update.class, Insert.class}, message = "用户名不能为空")
    private String username;

    @Size(groups = {Insert.class},min = 6, max = 20, message = "密码长度需在6-20之间")
    @NotBlank(groups = {Insert.class},message = "密码不能为空")
    private String password;
    private Boolean remember;
    @NotBlank(groups = {Update.class, Insert.class}, message = "请输入全称")
    private String fullName;
    private String avatar;
    private String introduction;
    private String nickname;

    @NotEmpty(groups = {Update.class, Insert.class}, message = "请选择角色")
    private List<String> roles;
    private String creator;
    private Instant createdDate;
    private String lastUpdater;
    private Instant lastUpdateDate;

    // 状态 0 禁用 1 启用
    private String status;
}
