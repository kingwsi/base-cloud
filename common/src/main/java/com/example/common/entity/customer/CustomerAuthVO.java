package com.example.common.entity.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: CustomerAuthVO <br>
 * date: 2021/3/11 15:25 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAuthVO {
    private String mobile;
    private String password;
    private String verifyCode;
}
