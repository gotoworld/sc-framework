package com.hsd.framework.util;

import com.alibaba.fastjson.JSONObject;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.ReflectUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * Created by wu1g119 on 2017/2/8.
 */
@Component
public class JwtUtil {
    @Value("${spring.profiles.active}")
    private String profiles;

    /**
     * 由字符串生成加密key
     */
    public SecretKey generalKey() {
        String stringKey = profiles + CommonConstant.JWT_SECRET_KEY;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 创建jwt
     */
    public String createJWT(String id, String subject, long ttlMillis) throws Exception {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, key);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 解密jwt
     */
    public Claims parseJWT(String jwt) throws Exception {
        SecretKey key = generalKey();
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    /**
     * 生成subject信息
     */
    public static String generalSubject(Object user) {
        JSONObject jo = new JSONObject();
        jo.put("id", ReflectUtil.getValueByFieldName(user,"id"));
        jo.put("name", ReflectUtil.getValueByFieldName(user,"name"));
        return jo.toJSONString();
    }
}
