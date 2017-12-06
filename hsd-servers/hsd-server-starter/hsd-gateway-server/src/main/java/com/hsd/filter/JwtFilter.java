package com.hsd.filter;


import com.alibaba.fastjson.JSONObject;
import com.hsd.framework.Response;
import com.hsd.framework.util.DateUtil;
import com.hsd.framework.util.JwtUtil;
import com.hsd.framework.util.WebUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebFilter(filterName = "jwtFilter", urlPatterns = {"/boss/*", "/api/*"})
@Slf4j
public class JwtFilter implements Filter {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${hsd.message.forceOffline}")
    String forceOffline;
    @Value("${hsd.message.tokenExpire}")
    String tokenExpire;

    @Autowired
    JwtFilterProperties jwtFilterProperties;
    @Override
    public void init(FilterConfig filterConfig) {
        log.info("===============“授权签名”过滤=============");
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        if (response instanceof HttpServletResponse) {
            HttpServletRequest httpServletRequest = ((HttpServletRequest) request);
            HttpServletResponse httpServletResponse = ((HttpServletResponse) response);
            boolean flag=false;
            if(jwtFilterProperties.getExcludeUrlPatterns()!=null && jwtFilterProperties.getExcludeUrlPatterns().size()>0){
                String url=httpServletRequest.getServletPath();
                for(int i=0;i<jwtFilterProperties.getExcludeUrlPatterns().size();i++){
                    if(isLike(url,jwtFilterProperties.getExcludeUrlPatterns().get(i))){
                        flag=true;break;
                    }
                }
            }
            if(flag) return;
            String authorizationToken = httpServletRequest.getHeader("Authorization");
            try {
                if (authorizationToken != null && !"".equals(authorizationToken)) {
                    final Claims claims = JwtUtil.parseJWT(authorizationToken);
                    if (chain != null) {
                        JSONObject jsonObject = JSONObject.parseObject(claims.getSubject());
                        //强制下线时间
                        String key = "u:offline:" + jsonObject.getString("id");
                        if (redisTemplate.opsForValue().get(key) != null) {
                            if (claims.getIssuedAt().getTime() < Long.parseLong("" + redisTemplate.opsForValue().get(key))) {
                                redisTemplate.opsForValue().getOperations().delete(key);
                                throw new SignatureException(forceOffline);
                            }
                        }
                        key = "u:" + jsonObject.getString("id") + ":" + jsonObject.getString("appUserId");
                        if (redisTemplate.opsForValue().get(key) != null && claims.getIssuedAt().getTime() != Long.parseLong("" + redisTemplate.opsForValue().get(key))) {//用户最新token签发时间
                            WebUtil.sendJson(httpServletResponse, Response.error(110, tokenExpire));
                            return;
                        }
                    }
                }
                chain.doFilter(request, response);
            } catch (final SignatureException e) {
                WebUtil.sendJson(httpServletResponse, Response.error(403, "签名验证失败!" + e.getMessage()));
            } catch (ExpiredJwtException e) {
                WebUtil.sendJson(httpServletResponse, Response.error(403, "授权过期!" + e.getMessage()));
            } catch (Throwable e) {
                log.error("授权检查异常", e);
                WebUtil.sendJson(httpServletResponse, Response.error(403, "授权检查异常!" + e.getMessage()));
            }
        }
    }

    public boolean isLike(String str, String regex) {
        if (regex.indexOf("?") != -1) return WenHao(str, regex);
        else if (regex.indexOf("*") != -1) return XingHao(str, regex);
        return false;
    }

    private boolean WenHao(String str, String regex) {
        // ?号匹配
        if (str.length() != regex.length()) return false;
        for (int i = 0; i < regex.length(); i++)
            if (str.charAt(i) != regex.charAt(i) && regex.charAt(i) != '?')
                return false;
        return true;
    }

    private boolean XingHao(String Str, String regex) {
        // *号匹配
        int Lstr = Str.length();
        int Lreg = regex.length();
        int x1 = regex.indexOf("*");
        switch (x1) {
            case -1: {
                //x1=-1    regex 中没有 * 号，不需要跌归计算
                if (Lstr == Lreg) {
                    if (Lstr == 0) return true;
                    for (int kk = 0; kk < Lreg; kk++)//检测字符串是否匹配
                        if (Str.charAt(kk) != regex.charAt(kk)) return false;
                    return true;
                } else
                    return false;
            }
            case 0: {//x1=0 regex 中 * 号在首位
                if (Lreg == 1) return true;//只有一个星号，自然是匹配的，如 regex="*"
                boolean right = false;
                int p = 0;
                // *号在首位，定位 * 号 后一位
                for (int k = 0; k < Lstr; k++)
                    if (Str.charAt(k) == regex.charAt(x1 + 1) || regex.charAt(x1 + 1) == '*') {
                        p = k;
                        right = true;
                        break;
                    }//遇到 ** 就直接 right=true;
                if (right == false) return false;
                else {
                    if (p == Lstr) return true;
                    return XingHao(Str.substring(p, Lstr), regex.substring(x1 + 1, Lreg));
                }
            }
            default: {    //x1>0
                for (int i = 0; i < x1; i++)
                    if (Str.charAt(i) != regex.charAt(i)) return false;
                return XingHao(Str.substring(x1, Lstr), regex.substring(x1, Lreg));
            }
        }
    }

    @Data
    @Configuration
    @ConfigurationProperties(prefix = "hsd.jwtFilter")
    public static class JwtFilterProperties {
        private List<String> excludeUrlPatterns;
    }

    public static void main(String[] args) throws Exception {
        String authorizationToken="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1MTIzNzc5ODAsInN1YiI6IntcImF1dGhvcml6YXRpb25JbmZvUGVybXNcIjpbXCJvcmdMb2dPcGVyYXRpb246aW5mb1wiLFwib3JnU3RhZmY6ZWRpdDpwd2RcIixcIm9yZ0xvZ0xvZ2luOmluZm9cIixcInN5czptZW51XCIsXCJhY3RvcjptZW51XCIsXCJhZG1pbjptZW51XCIsXCJhdXRoUGVybTppbmZvXCJdLFwibmFtZVwiOlwi5rWL6K-VQTAyN1wiLFwiaWRcIjo1NzMsXCJhdXRob3JpemF0aW9uSW5mb1JvbGVzXCI6W1wi5rWL6K-V6KeS6ImyXCIsXCJDVE9cIl0sXCJhY2NvdW50XCI6XCJhMDI3XCJ9IiwidXNlclR5cGUiOiJTVEFGRiIsImV4cCI6MTUxMjM4NTE4MH0._WojFuJ5fUcuvp9btXoaJ0QWylifj_1PIwGWtAfIONk";

        Claims claims =JwtUtil.parseJWT(authorizationToken);
        System.out.println(claims);
        System.out.println(DateUtil.getDateTimeStr(claims.getIssuedAt()));
        System.out.println(claims.getIssuedAt().getTime());
        System.out.println(DateUtil.getDateTimeStr(claims.getExpiration()));
    }
}