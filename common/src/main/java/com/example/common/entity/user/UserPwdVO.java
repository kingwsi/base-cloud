package com.example.common.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.*;

/**
 * Description: []
 * Name: UserVO
 * Author: wangshu
 * Date: 2019/6/29 15:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPwdVO {

    private Integer id;

    private String password;

    @NotNull(message = "请输入旧密码")
    private String oldPassword;

    @Size(min = 2, max = 15, message = "全称长度需在2-15之间")
    private String fullName;
    private String avatar;
    private String introduction;
    private String nickname;
}
