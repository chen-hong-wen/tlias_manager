package com.itheima;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGenerateJwt() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", "1");
        dataMap.put("usename", "admin");

        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "aXRoZWltYQ==") // 签名算法和密钥对
                .addClaims(dataMap)   // 添加自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 3600))  // 设置过期时间为 1 小时
                .compact();// 生成 JWT 字符串

        System.out.println(jwt);
    }


    /**
     * 测试解析 JWT 字符串
     */
    @Test
    public void testParseJwt() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJ1c2VuYW1lIjoiYWRtaW4iLCJleHAiOjE3ODQzMTkwOTV9.nODSHu6Dc69KGGhXK8WEBSKhb5ojyXriHeYB8lVYZOI";
        Claims claims = Jwts.parser().setSigningKey("aXRoZWltYQ==") // 制定签名密钥
                .parseClaimsJws(jwt)  //解析令牌
                .getBody(); // 获取有效载荷
        System.out.println(claims);
    }
}
