package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    /**
     * 签名密钥
     */
    private static final String SIGNING_KEY = "aXRoZWltYQ==";
    private static final long EXPIRATION_TIME = 12*60*60*1000;

    /**
     * 生成 JWT 字符串
     * @param claims 自定义声明
     * @return JWT 字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .compact();
    }

    /**
     * 验证 JWT 字符串
     * @param token JWT 字符串
     * @return 是否有效
     */
    public static Claims parseToken(String token) throws Exception {
       return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
           }


}
