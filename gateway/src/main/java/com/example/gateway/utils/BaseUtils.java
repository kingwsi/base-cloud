package com.example.gateway.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.util.AntPathMatcher;

/**
 * description: BaseUtils <br>
 * date: 2020/9/28 9:57 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
public class BaseUtils {

    private static final String KEY = "";

    public static boolean pathMatch(String[] paths, String pattern) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (String path : paths) {
            if (antPathMatcher.match(path, pattern)) {
                return true;
            }
        }
        return false;
    }

    public static void parser(String token) {
        Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        claims.get("id", String.class);
        claims.get("type", String.class);
        claims.get("name", String.class);
    }
}
