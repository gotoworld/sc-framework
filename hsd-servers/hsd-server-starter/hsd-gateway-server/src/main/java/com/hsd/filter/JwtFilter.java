package com.hsd.filter;


import com.alibaba.fastjson.JSONObject;
import com.hsd.framework.Response;
import com.hsd.framework.util.DateUtil;
import com.hsd.framework.util.JwtUtil;
import com.hsd.framework.util.WebUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "jwtFilter", urlPatterns = {"/*"})
@Slf4j
public class JwtFilter implements Filter {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("===============“授权签名”过滤=============");
    }
    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) {
        if (response instanceof HttpServletResponse) {
            HttpServletRequest httpServletRequest = ((HttpServletRequest) request);
            HttpServletResponse httpServletResponse = ((HttpServletResponse) response);

            String authorizationToken=httpServletRequest.getHeader("Authorization");
            try {
                if(authorizationToken!=null && !"".equals(authorizationToken)){
                    final Claims claims = JwtUtil.parseJWT(authorizationToken);
                    if(chain!=null){
                        JSONObject jsonObject=JSONObject.parseObject(claims.getSubject());
                        //强制下线时间
                        String key="u:offline:"+jsonObject.getString("id");
                        if(redisTemplate.opsForValue().get(key)!=null){
                            if(claims.getIssuedAt().getTime()<Long.parseLong(""+redisTemplate.opsForValue().get(key))){
                                throw new SignatureException("凭证已被强制吊销！");
                            }
                        }

                        key="u:"+jsonObject.getString("id")+":"+jsonObject.getString("appUserId");
                        if(redisTemplate.opsForValue().get(key)==null || claims.getIssuedAt().getTime()!=Long.parseLong(""+redisTemplate.opsForValue().get(key))){//用户最新token签发时间
                            WebUtil.sendJson(httpServletResponse, Response.error(110, "您的授权已失效或已在其它地方登录!"));
                            return;
                        }

                        chain.doFilter(request, response);
                    }
                }
            } catch (final SignatureException e) {
                WebUtil.sendJson(httpServletResponse,Response.error(403, "签名验证失败!" + e.getMessage()));
            } catch (ExpiredJwtException e) {
                WebUtil.sendJson(httpServletResponse,Response.error(403, "授权过期!" + e.getMessage()));
            } catch (Throwable e) {
                log.error("授权检查异常", e);
                WebUtil.sendJson(httpServletResponse,Response.error(403, "授权检查异常!" + e.getMessage()));
            }
        }
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