package com.hsd.framework.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by wu1g119 on 2017/2/8.
 */
public class JwtUtil {
    //    @Value("${spring.profiles.active}")
    private static String profiles = "";

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
    public static String createJWT(String id, String subject, long ttlMillis, String sid) throws Exception {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .claim(CommonConstant.JWT_HEADER_SHIRO_KEY, sid)
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
        jo.put("id", ReflectUtil.getValueByFieldName(user, "id"));
        jo.put("account", ReflectUtil.getValueByFieldName(user, "account"));
        jo.put("name", ReflectUtil.getValueByFieldName(user, "name"));
        jo.put("authorizationInfoPerms", ReflectUtil.getValueByFieldName(user, "authorizationInfoPerms"));
        jo.put("authorizationInfoRoles", ReflectUtil.getValueByFieldName(user, "authorizationInfoRoles"));
//        jo.put("sid", ReflectUtil.getValueByFieldName(user,"sid"));
        return jo.toJSONString();
    }

    public static boolean isPermitted(String authStr) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        final String authorizationToken = request.getHeader(CommonConstant.JWT_HEADER_TOKEN_KEY);
        if (ValidatorUtil.isEmpty(authorizationToken)) {
            throw new SignatureException("token头缺失");
        }
        final Claims claims = parseJWT(authorizationToken);
        JSONObject jobj = JSON.parseObject(claims.getSubject());
        String authorizationInfoPerms = jobj.getString("authorizationInfoPerms");
        if (authorizationInfoPerms != null) {
            List<String> permsArr = Arrays.asList(authorizationInfoPerms.split(","));
            //console.info("permsArr="+permsArr)
            //console.info("permsstr="+str)
            if (permsArr != null && permsArr.contains(authStr)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
