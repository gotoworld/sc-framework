package com.hsd.filter;


import com.alibaba.fastjson.JSONObject;
import com.hsd.framework.Response;
import com.hsd.framework.cache.util.RedisHelper;
import com.hsd.framework.util.JwtUtil;
import com.hsd.framework.util.ValidatorUtil;
import com.hsd.framework.util.WebUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "jwtFilter", urlPatterns = {"/boss/*", "/api/*"})
@Slf4j
public class JwtFilter implements Filter {
    @Autowired
    private RedisHelper redisHelper;
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
                PathMatcher matcher = new AntPathMatcher();
                for(int i=0;i<jwtFilterProperties.getExcludeUrlPatterns().size();i++){
                    if(matcher.match(jwtFilterProperties.getExcludeUrlPatterns().get(i),url)){
                        flag=true;break;
                    }
                }
            }
            String authorizationToken = httpServletRequest.getHeader("Authorization");
            try {
                if (!flag && ValidatorUtil.notEmpty(authorizationToken)) {
                    final Claims claims = JwtUtil.parseJWT(authorizationToken);
                    if (chain != null) {
                        JSONObject jsonObject = JSONObject.parseObject(claims.getSubject());
                        //强制下线时间
                        String key = "u:offline:" + jsonObject.getString("id");
                        if (redisHelper.get(key) != null) {
                            if (claims.getIssuedAt().getTime() < Long.parseLong("" + redisHelper.get(key))) {
                                redisHelper.del(key);
                                throw new RuntimeException(jwtFilterProperties.getMessage().getForceOffline());
                            }
                        }
                        String appUserId=jsonObject.getString("appUserId");
                        if(ValidatorUtil.isEmpty(appUserId)) appUserId=jsonObject.getString("appStaffId");
                        key = "u:" + jsonObject.getString("id") + ":" + appUserId;
                        String value= ""+redisHelper.get(key);
                        if (ValidatorUtil.notEmpty(value)
                                && claims.getIssuedAt().getTime() != Long.parseLong(value)) {//用户最新token签发时间
                            WebUtil.sendJson(httpServletResponse, Response.error(110, jwtFilterProperties.getMessage().getTokenExpire()));
                            return;
                        }
                    }
                }
                chain.doFilter(request, response);
            } catch (final SignatureException e) {
                WebUtil.sendJson(httpServletResponse, Response.error(403, "gt签名验证失败!"));
            } catch (ExpiredJwtException e) {
                WebUtil.sendJson(httpServletResponse, Response.error(403, "gt授权过期!"));
            } catch (Throwable e) {
                log.error("授权检查异常", e);
                WebUtil.sendJson(httpServletResponse, Response.error(403, "gt授权检查异常!"));
            }
        }
    }

    @Data
    @Configuration
    @ConfigurationProperties(prefix = "hsd.jwtFilter")
    public static class JwtFilterProperties {
        private List<String> excludeUrlPatterns;
        private Message message;
        @Data
        public static class Message{
            private String forceOffline;
            private String tokenExpire;
        }
    }

    public static void main(String[] args) {
//        String authorizationToken="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1MTI1NDY3ODAsInN1YiI6IntcImFwcE5hbWVcIjpcIueuoeeQhuezu-e7n1wiLFwiYXV0aG9yaXphdGlvbkluZm9QZXJtc1wiOltcInN5c01lbnU6bWVudVwiLFwiYnVzaW5lc3NUeXBlOmFkZFwiLFwidGVtcGxhdGVBdHRyaWJ1dGU6ZWRpdFwiLFwib3JnSW5mbzphZGRcIixcImRlcO-8mjE6NFwiLFwib3JnTG9nTG9naW46bWVudVwiLFwiY2hhbm5lbEluZm86aW5mb1wiLFwiZGVwXCIsXCJidXNpbmVzc1R5cGU6bWVudVwiLFwiY2hhbm5lbFR5cGU6YWRkXCIsXCJjaGFubmVsSW5mbzpkZWxcIixcImlkZW50aXR5TG9nOmluZm9cIixcInVzZXJTbmFwc2hvdDphZGRcIixcImF1dGhSb2xlOmVkaXQ6bWVudVwiLFwiYXV0aFJvbGU6ZGVsXCIsXCJjaGFubmVsVHlwZTptZW51XCIsXCJvcmdTdGFmZjplZGl0Om9yZ1wiLFwiYXV0aFBlcm06YWRkXCIsXCJvcmdJbmZvOm1lbnVcIixcInN5c0RvbWFpbjplZGl0XCIsXCJpZGVudGl0eTphZGRcIixcIm9yZ1N0YWZmOmFkZFwiLFwic3lzQXBwOmVkaXRcIixcIm1lbWJlcjpkZWxcIixcIm9yZ1N0YWZmOmVkaXQ6bGVhZGVyc2hpcFwiLFwidXNlclNpZ25Db250cmFjdDphZGRcIixcImlkZW50aXR5OmVkaXRcIixcInVzZXJTaWduQ29udHJhY3Q6bWVudVwiLFwic3lzRG9tYWluOmRlbFwiLFwib3JnU3RhZmY6aW5mb1wiLFwidXNlclNuYXBzaG90Om1lbnVcIixcInVzZXJMb2dpbkxvZzplZGl0XCIsXCJvYTpzd2FnZ2VyOmNvbW1vblwiLFwic3lzVmFyaWFibGU6aW5mb1wiLFwidGVtcGxhdGU6cGh5ZGVsXCIsXCJkZXA6N1wiLFwidGVtcGxhdGU6ZWRpdFwiLFwib3JnTG9nT3BlcmF0aW9uOmluZm9cIixcImNoYW5uZWxUeXBlOmVkaXRcIixcIm9yZ1N0YWZmOmFkZDpiYXRjaFwiLFwidXNlclNpZ25Db250cmFjdDpkZWxcIixcIm9yZ0xvZ0xvZ2luOmluZm9cIixcInRlbXBsYXRlQXR0cmlidXRlOmRlbFwiLFwib3JnU3RhZmY6ZGVsXCIsXCJpZGVudGl0eUxvZzplZGl0XCIsXCJtZW1iZXI6bWVudVwiLFwib3JnSW5mbzppbmZvXCIsXCJvcmdTdGFmZjpyZWNvdmVyeVwiLFwidGFnOm1lbnVcIixcInRlbXBsYXRlQXR0cmlidXRlOnBoeWRlbFwiLFwiaWRlbnRpdHlMb2c6ZGVsXCIsXCJvcmdTdGFmZjplZGl0XCIsXCJhZG1pbjptZW51XCIsXCJvcmdJbmZvOmVkaXQ6c3RhZmZcIixcImF1dGhQZXJtOmluZm9cIixcInN5c01lbnU6ZGVsXCIsXCJzeXNWYXJpYWJsZTplZGl0XCIsXCJ1c2VyU2lnbkNvbnRyYWN0OmVkaXRcIixcInRhZzphZGRcIixcIm9yZ0xvZ09wZXJhdGlvbjplZGl0XCIsXCJ1c2VyOmFkZFwiLFwiY2hhbm5lbFR5cGU6ZGVsXCIsXCJ0ZW1wbGF0ZTphZGRcIixcImRlcO-8mjNcIixcInVzZXI6ZWRpdDpibGFja2xpc3RcIixcIm9yZ0xvZ0xvZ2luOmRlbFwiLFwidGVtcGxhdGU6bWVudVwiLFwiZGVw77yaMlwiLFwiZGVw77yaMVwiLFwidXNlcjplZGl0OnRhZ1wiLFwidXNlckxvZ2luTG9nOmluZm9cIixcImNoYW5uZWw6bWVudVwiLFwic3lzQXBwOmFkZFwiLFwib3JnU3RhZmY6ZWRpdDpyb2xlXCIsXCJhdXRoUm9sZTplZGl0XCIsXCJ1c2VyTG9naW5Mb2c6ZGVsXCIsXCJjaGFubmVsSW5mbzpyZXNldFwiLFwib3JnTG9nT3BlcmF0aW9uOmRlbFwiLFwidXNlcjplZGl0XCIsXCJzeXM6bWVudVwiLFwic3lzRG9tYWluOmluZm9cIixcInVzZXJFeHRJbmZvOmFkZFwiLFwidXNlcjpwaHlkZWxcIixcInVzZXJFeHRJbmZvOmluZm9cIixcInVzZXJTbmFwc2hvdDplZGl0XCIsXCJvcmdJbmZvOmVkaXQ6c2V0TWFuYWdlclwiLFwidGFnOmVkaXRcIixcInVzZXJTaWduQ29udHJhY3Q6aW5mb1wiLFwibWVtYmVyOmVkaXRcIixcImNoYW5uZWxJbmZvOmFkZDpiYXRjaFwiLFwidXNlckV4dEluZm86ZGVsXCIsXCJhdXRoUGVybTpkZWxcIixcImNoYW5uZWxJbmZvOmFkZFwiLFwiaWRlbnRpdHk6ZGVsXCIsXCJ1c2VyU25hcHNob3Q6ZGVsXCIsXCJhdXRoUGVybTptZW51XCIsXCJpZGVudGl0eTppbmZvXCIsXCJvcmdJbmZvOmRlbFwiLFwidGVtcGxhdGU6aW5mb1wiLFwidXNlckxvZ2luTG9nOm1lbnVcIixcIm9yZ1N0YWZmOnJlY3ljbGVcIixcInVzZXJFeHRJbmZvOmVkaXRcIixcInVzZXI6bWVudVwiLFwic3lzQXBwOm1lbnVcIixcInRlbXBsYXRlQXR0cmlidXRlOmluZm9cIixcImJ1c2luZXNzVHlwZTppbmZvXCIsXCJhdXRoUm9sZTptZW51XCIsXCJpZGVudGl0eUxvZzptZW51XCIsXCJzeXNEb21haW46YWRkXCIsXCJjaGFubmVsSW5mbzplZGl0XCIsXCJhdXRoUm9sZTplZGl0OnBlcm1cIixcImlkZW50aXR5TG9nOmFkZFwiLFwiYXV0aFJvbGU6YWRkXCIsXCJ1c2VyRXh0SW5mbzptZW51XCIsXCIvY29tbW9uL2dldHN0YWZCeUpvYk5vXCIsXCJvYTptZW51XCIsXCJzeXNEb21haW46bWVudVwiLFwiYmQ6bWVudVwiLFwic3lzTWVudTplZGl0XCIsXCJzeXNWYXJpYWJsZTpkZWxcIixcIm9yZ0luZm86cmVjeWNsZVwiLFwibWVtYmVyOmFkZFwiLFwidGFnOnBoeWRlbFwiLFwib3JnTG9nT3BlcmF0aW9uOmFkZFwiLFwiYnVzaW5lc3NUeXBlOmVkaXRcIixcInVzZXJMb2dpbkxvZzphZGRcIixcIm9yZ1N0YWZmOm9mZmxpbmVcIixcInN5c0FwcDppbmZvXCIsXCJ0ZW1wbGF0ZTpkZWxcIixcIm9yZ0xvZ0xvZ2luOmFkZFwiLFwiYXV0aFJvbGU6aW5mb1wiLFwidXNlcjppbmZvXCIsXCJjaGFubmVsSW5mbzptZW51XCIsXCJhY3RvcjptZW51XCIsXCJzeXNWYXJpYWJsZTphZGRcIixcInN5c01lbnU6aW5mb1wiLFwib3JnSW5mbzplZGl0OnJvbGVcIixcInRlbXBsYXRlQXR0cmlidXRlOm1lbnVcIixcIm9yZ1N0YWZmOmVkaXQ6cmVzZXRwd2RcIixcImF1dGhSb2xlOnN1cGVyXCIsXCJhdXRoUGVybTplZGl0XCIsXCJpZGVudGl0eTptZW51XCIsXCJvcmdMb2dMb2dpbjplZGl0XCIsXCJvcmdTdGFmZjplZGl0OnB3ZFwiLFwib3JnSW5mbzpyZWNvdmVyeVwiLFwib3JnSW5mbzplZGl0XCIsXCJ0YWc6aW5mb1wiLFwiYnVzaW5lc3NUeXBlOmRlbFwiLFwiY2hhbm5lbFR5cGU6aW5mb1wiLFwidXNlclNuYXBzaG90OmluZm9cIixcIm9yZ0xvZ09wZXJhdGlvbjptZW51XCIsXCJzeXNNZW51OmFkZFwiLFwic3lzQXBwOnBoeWRlbFwiLFwiY2hhbm5lbEluZm86cmVjb3ZlcnlcIixcInN5c1ZhcmlhYmxlOm1lbnVcIixcIm9yZ1N0YWZmOm1lbnVcIixcIm1lbWJlcjppbmZvXCIsXCJ0ZW1wbGF0ZUF0dHJpYnV0ZTphZGRcIl0sXCJuYW1lXCI6XCLotoXnuqfnrqHnkIblkZhcIixcImlkXCI6MSxcImFwcFVzZXJJZFwiOjEsXCJhdXRob3JpemF0aW9uSW5mb1JvbGVzXCI6W1wi55S16K-d6JCl6ZSA5Lit5b-D57uP55CGXCIsXCLooYzmlL_kuLvnrqFcIixcIue7vOenn-i1geS4muWKoeS4reW_g-aAu-ebkVwiLFwi5rWL6K-V6KeS6ImyXCIsXCLkuJrliqHlia_mgLvoo4FcIixcIuaVsOaNruWIhuaekOS4reW_g-e7j-eQhlwiLFwi5a6i5oi35pyN5Yqh5Lit5b-D5Li7566hXCIsXCLpo47pmankuqTmmJPkuK3lv4PmgLvnm5FcIixcIue9kee7nOaOqOW5v-S4reW_g-S4k-WRmFwiLFwi6L-Q6JCl566h55CG5Lit5b-D5Li7566hXCIsXCLluLjliqHlia_mgLvoo4FcIixcIuS6uuS6i-S4k-WRmFwiLFwi5Lqn5ZOB6L-Q6JCl5Lit5b-D5LiT5ZGYXCIsXCLpo47pmanmjqfliLbkuK3lv4PmgLvnm5FcIixcIumDqOmXqDLotJ_otKPkurpcIixcIuS6p-WTgeaKgOacr-S4reW_g-e7j-eQhlwiLFwi572R57uc5o6o5bm_5Lit5b-D57uP55CGXCIsXCLnlLXor53okKXplIDkuK3lv4PkuJPlkZhcIixcIumjjumZqeS6pOaYk-S4reW_g-e7j-eQhlwiLFwi6LSi5Yqh566h55CG5Lit5b-D5oC755uRXCIsXCLotKLliqHmgLvnm5FcIixcIuS6p-WTgeaKgOacr-S4reW_g-aAu-ebkVwiLFwi6KGM5pS_5LiT5ZGYXCIsXCJvYS10ZXN0XCIsXCLnvZHnu5zmjqjlub_kuK3lv4PkuLvnrqFcIixcIui0ouWKoeeuoeeQhuS4reW_g-S4u-euoVwiLFwi5pWw5o2u5YiG5p6Q5Lit5b-D5LiT5ZGYXCIsXCLpo47pmanmjqfliLbkuK3lv4PkuLvnrqFcIixcIuihjOaUv-WJr-aAu-ijgVwiLFwi5Lqn5ZOB6L-Q6JCl5Lit5b-D57uP55CGXCIsXCLnu7znp5_otYHkuJrliqHkuK3lv4PkuLvnrqFcIixcIueUteivneiQpemUgOS4reW_g-S4u-euoVwiLFwi5oub6IGY5Li7566hXCIsXCLlrqLmiLfmnI3liqHkuK3lv4PmgLvnm5FcIixcIumjjumZqeS6pOaYk-S4reW_g-S4u-euoVwiLFwi5Lqn5ZOB56CU5Y-R5Lit5b-D5oC755uRXCIsXCLov5DokKXnrqHnkIbkuK3lv4Pnu4_nkIZcIixcIuWuouaIt-acjeWKoeS4reW_g-e7j-eQhlwiLFwi5pWw5o2u5YiG5p6Q5Lit5b-D5Li7566hXCIsXCLkuqflk4HnoJTlj5HkuK3lv4PkuJPlkZhcIixcIumDqOmXqDTotJ_otKPkurpcIixcIuS6uuS6i-WJr-e7j-eQhlwiLFwi57u856ef6LWB5Lia5Yqh5Lit5b-D5LiT5ZGYXCIsXCLkuqflk4Hov5DokKXkuK3lv4PmgLvnm5FcIixcIumjjumZqeaOp-WItuS4reW_g-S4k-WRmFwiLFwi5Ymv5oC76KOBXCIsXCLnu7zlkIjnrqHnkIbkuK3lv4PotJ_otKPkurpcIixcIuS6p-WTgeaKgOacr-S4reW_g-S4u-euoVwiLFwieXVhbmt1blwiLFwi57u85ZCI566h55CG5Ymv5oC755uRXCIsXCLpo47pmankuqTmmJPkuK3lv4PkuJPlkZhcIixcIui0ouWKoeeuoeeQhuS4reW_g-e7j-eQhlwiLFwi55S16K-d6JCl6ZSA5Lit5b-D5oC755uRXCIsXCLkuqflk4HnoJTlj5HkuK3lv4Pnu4_nkIZcIixcIue7vOenn-i1geS4muWKoeS4reW_g-e7j-eQhlwiLFwi6L-Q6JCl566h55CG5Lit5b-D5oC755uRXCIsXCLlrqLmiLfmnI3liqHkuK3lv4PkuJPlkZhcIixcIuS6p-WTgeeglOWPkeS4reW_g-S4u-euoVwiLFwi6L-Q6JCl566h55CG5Lit5b-D5LiT5ZGYXCIsXCLpo47pmanmjqfliLbkuK3lv4Pnu4_nkIZcIixcIuS6p-WTgei_kOiQpeS4reW_g-S4u-euoVwiLFwi6YOo6ZeoMei0n-i0o-S6ulwiLFwi5Ye657qzXCIsXCLotKLliqHnrqHnkIbkuK3lv4PkuJPlkZhcIixcIuS6p-WTgeaKgOacr-S4reW_g-S4k-WRmFwiLFwi572R57uc5o6o5bm_5Lit5b-D5oC755uRXCIsXCLmlbDmja7liIbmnpDkuK3lv4PmgLvnm5FcIixcIkNUT1wiXSxcImFjY291bnRcIjpcImFkbWluXCJ9IiwidXNlclR5cGUiOiJTVEFGRiIsImV4cCI6MTUxMjU1Mzk4MH0.neSQoxhk7UExHO_k9iOjXIQF-BS9F-1Fa-k59nNk0ys";
//        Claims claims =JwtUtil.parseJWT(authorizationToken);
//        System.out.println(claims);
//        System.out.println(DateUtil.getDateTimeStr(claims.getIssuedAt()));
//        System.out.println(claims.getIssuedAt().getTime());
//        System.out.println(DateUtil.getDateTimeStr(claims.getExpiration()));

//        List<String> excludeUrlPatterns = new ArrayList<String>() {{
//            add("/hsd-account-staff-web-boss/boss/account/staff/sign/**");
//            add("/api/account/channel/sign/**");
//            add("/api/account/actor/findpwd/**");
//            add("/boss/account/actor/sign/**");
//            add("/*/boss/**/orgInfo/recyclePage/1");
//        }};
//        PathMatcher matcher = new AntPathMatcher();
//        String url = "/hsd-account-staff-web-boss/boss/account/staff/org/orgInfo/recyclePage/1";
//        excludeUrlPatterns.forEach(s -> {
//            System.out.println(matcher.match(s, url));
//        });
    }
}