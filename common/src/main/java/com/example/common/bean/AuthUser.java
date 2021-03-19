package com.example.common.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * description: User <br>
 * date: 2020/9/29 13:23 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Data
@NoArgsConstructor
public class AuthUser {
    private Integer id;
    private String username;
    private String mobile;
    private String type;
    private String password;
    private String verifyCode;
    private String key;
    private List<String> roles;

    public AuthUser(Integer id, String username, String type) {
        this.id = id;
        this.username = username;
        this.type = type;
    }
}
