package com.example.common.utils;

import com.example.common.bean.AuthUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * description: TokenUtils <br>
 * date: 2020/9/29 13:18 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
public class TokenUtils {

    private final static String KEY = "123456";

    public static String createToken(AuthUser authUser){
        return Jwts.builder()
                .claim("id", authUser.getId())
                .claim("name", authUser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
    }

    public static AuthUser parser(String token) {
        Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        String id = claims.get("id", String.class);
        String name = claims.get("name", String.class);
        AuthUser authUser = new AuthUser();
        authUser.setId(id);
        authUser.setUsername(name);
        return authUser;
    }

    public static AuthUser getCurrentUser(){

        return new AuthUser();
    }
}
