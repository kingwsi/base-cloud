package com.example.common.utils;

import com.example.common.bean.AuthUser;
import com.example.common.enumerate.RequestHeader;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.Optional;

/**
 * description: token处理工具类 <br>
 * date: 2020/9/29 13:18 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
public class TokenUtils {

    private final static String KEY = "123456";
    private final static String MEMBER_KEY = "member-test";

    public static String createToken(AuthUser authUser) {
        return Jwts.builder()
                .claim("id", authUser.getId())
                .claim("name", authUser.getUsername())
                .claim("type", "administrator")
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
    }

    public static String createMemberToken(AuthUser authUser) {
        return Jwts.builder()
                .claim("id", authUser.getId())
                .claim("name", authUser.getUsername())
                .claim("type", "member")
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
    }

    public static AuthUser parser(String token) {
        Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        Integer id = claims.get("id", Integer.class);
        String name = claims.get("name", String.class);
        AuthUser authUser = new AuthUser();
        authUser.setId(id);
        authUser.setUsername(name);
        return authUser;
    }

    public static AuthUser parserMember(String token) {
        Claims claims = Jwts.parser().setSigningKey(MEMBER_KEY).parseClaimsJws(token).getBody();
        Integer id = claims.get("id", Integer.class);
        String name = claims.get("name", String.class);
        AuthUser authUser = new AuthUser();
        authUser.setId(id);
        authUser.setUsername(name);
        return authUser;
    }

    public static AuthUser getCurrentUser() {
        return new AuthUser();
    }

    public static Integer getCurrentUserId() {
        // 获取当前用户
        String uid = Optional.ofNullable((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .map(ServletRequestAttributes::getRequest)
                .map(httpServletRequest -> httpServletRequest.getHeader(RequestHeader.PRINCIPAL_ID.name()))
                .orElse("-1");
        return Integer.parseInt(uid);
    }
}
