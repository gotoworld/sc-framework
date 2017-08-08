package com.hsd.framework.util;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * Created by wu1g119 on 2017/2/8.
 */
public class JwtUtil {
//    @Value("${spring.profiles.active}")
    private static String profiles="";

    /**
     * 由字符串生成加密key
     */
    public static SecretKey generalKey() {
        String stringKey = profiles + CommonConstant.JWT_SECRET_KEY;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
    /**
     * 创建jwt
     */
    public static String createJWT(String id, String subject, long ttlMillis,String sid) throws Exception {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .claim(CommonConstant.JWT_HEADER_SHIRO_KEY,sid)
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
    public static Claims parseJWT(String jwt) throws Exception {
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
//        jo.put("sid", ReflectUtil.getValueByFieldName(user,"sid"));
        return jo.toJSONString();
    }
}
