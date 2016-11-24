package com.wu1g.common.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *短信API服务调用示例代码 － 聚合数据
 *在线接口文档：http://www.juhe.cn/docs/54
 **/
@Slf4j
public class JuheSMSUtil {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    //配置您申请的KEY
    public static final String APPKEY ="eb96a77cc67c8b547e37a2af31e0ab82";

    //找回密码号
    private static final String ZhaoHuiMiMa ="9364";
    //注册模板号
    private static final String ZhuCe ="9363";
    //冻结账号
    private static final String DONGJIE ="13404";
    //提现申请
    private static final String TIXIANSHENQING ="13842";
    //退款
    private static final String TUIKUAN ="17379";
    //确认服务
    private static final String QUERENFUWU ="17377";
    //提现退款失败
    private static final String TIXIANTUIKUAN ="17380";
    //付款成功
    private static final String FUKUANCHENGGONG ="17376";
    //开始服务
    private static final String KAISIFUWU ="17678";
    //通用短信模板
    private static final String CURRENCY="17988";
    //发布需求时通知代理处理需求
    private static final String MESAGENT="18057";
    //异常退款，充值到余额
    private static final String EXCEPTION_REFUND_RECHARGE="19409";

    //2.发送短信
    public static String[] senMessage(String mobile, String message){
        String result =null;
        String url ="http://v.juhe.cn/sms/send";//请求接口地址
        String []returnStr = {"",""};
        String templateId = "";
        Map params = new HashMap();//请求参数
        params.put("mobile", mobile);//接收短信的手机号码
        params.put("tpl_id", templateId);//短信模板ID，请参考个人中心短信模板设置
        //params.put("tpl_value", "#code#=" + intCount);//变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlencode编码后再传递，<a href="http://www.juhe.cn/news/index/id/50" target="_blank">详细说明></a>
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","json");//返回数据的格式,xml或json，默认json

        try {
            result =net(url, params, "GET");

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode object = objectMapper.readTree(result);
            String errorCode = object.path("error_code").asText();
            if(!StringUtils.isEmpty(errorCode) && !"0".equals(errorCode)){
                log.debug(object.path("result").asText());
            }else{
                log.debug(object.path("error_code").asText()+":"+object.path("reason").asText());
            }
            returnStr[0] = errorCode+"";
            return returnStr;
        } catch (Exception e) {
            e.printStackTrace();
        }

        //发送失败
        return returnStr;
    }

    //2.发送短信
    public static String[] getRequest2(String num,String amount,String mobile, int type/*0：注册，1：找回密码*/,String intCount){
        if (!ObjectUtils.isEmpty(amount))amount=Double.parseDouble(amount)+"";
        String result =null;
        String url ="http://v.juhe.cn/sms/send";//请求接口地址

        String []returnStr = {"",""};
        String templateId = "";

        Map params = new HashMap();//请求参数
        switch (type) {
            case 0:
                params.put("tpl_value", "#code#=" + intCount);//变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlencode编码后再传递，<a href="http://www.juhe.cn/news/index/id/50" target="_blank">详细说明></a>
                templateId = ZhuCe;
                break;
            case 1:
                params.put("tpl_value", "#code#=" + intCount);//变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlencode编码后再传递，<a href="http://www.juhe.cn/news/index/id/50" target="_blank">详细说明></a>
                templateId = ZhaoHuiMiMa;
                break;
            case 2:
                templateId = TUIKUAN;
                params.put("tpl_value", "#orderNum#=" + num+"&#amount#="+amount);
                break;
            case 3:
                templateId = QUERENFUWU;
                params.put("tpl_value", "#orderNum#=" + num+"&#amount#="+amount);
                break;
            case 4:
                templateId = TIXIANTUIKUAN;
                params.put("tpl_value", "#tradeNum#=" + num+"&#amount#="+amount);
                break;
            case 5:
                templateId = FUKUANCHENGGONG;
                params.put("tpl_value", "#orderNum#=" + num+"&#amount#="+amount);
                break;
            case 6:
                templateId = KAISIFUWU;
                params.put("tpl_value", "#orderNum#=" + num);
                break;
            case 7:
                params.put("tpl_value", "#code#=" + intCount);//变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlencode编码后再传递，<a href="http://www.juhe.cn/news/index/id/50" target="_blank">详细说明></a>
                templateId = CURRENCY;
                break;
            case 8:
                params.put("tpl_value", "#code#=" + intCount);//变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlencode编码后再传递，<a href="http://www.juhe.cn/news/index/id/50" target="_blank">详细说明></a>
                templateId = MESAGENT;
                break;
            case 9:
                // 【车当当】您的订单#orderNum#出现异常，已办理退款至您的余额，给您造成的不便，我们很抱歉，如有问题请联系客服：4008333955。
                templateId = EXCEPTION_REFUND_RECHARGE;
                params.put("tpl_value", "#orderNum#=" + num);
                break;
            default:
                break;
        }
        params.put("mobile", mobile);//接收短信的手机号码
        params.put("tpl_id", templateId);//短信模板ID，请参考个人中心短信模板设置
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","json");//返回数据的格式,xml或json，默认json
        try {
            result =net(url, params, "GET");
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode object = objectMapper.readTree(result);
            String errorCode = object.path("error_code").asText();
            if(!StringUtils.isEmpty(errorCode) && !"0".equals(errorCode)){
                log.debug(object.path("result").asText());
            }else{
                log.debug(object.path("error_code").asText()+":"+object.path("reason").asText());
            }
            returnStr[0] = errorCode+"";
            returnStr[1] = intCount + "";
            return returnStr;
        } catch (Exception e) {
            e.printStackTrace();
        }

        //发送失败
        return returnStr;
    }



    public static void main(String[] args) {
//    	getRequest2("13036699917",0);
//    	getDuanXin("13558806850","hehe呵呵");
//    	getDongjie("13558806850");
        //getTiXian("13558806850", new Date().toString(), "0", "0");
        //getRequest2("123","10","13018281023",4);

        System.out.println(Double.parseDouble("1000.0000")+"");
    }

    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    //将map型转为请求参数型
    public static String urlencode(Map<String,Object>data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    //2.发送短信冻结账号
    public static String[] getDongjie(String mobile){
        String result =null;
        String url ="http://v.juhe.cn/sms/send";//请求接口地址

        String []returnStr = {"",""};
        String templateId = DONGJIE;

        Map params = new HashMap();//请求参数
        params.put("mobile", mobile);//接收短信的手机号码
        params.put("tpl_id", templateId);//短信模板ID，请参考个人中心短信模板设置
        params.put("tpl_value", "");//变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlencode编码后再传递，<a href="http://www.juhe.cn/news/index/id/50" target="_blank">详细说明></a>
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","json");//返回数据的格式,xml或json，默认json

        try {
            result =net(url, params, "GET");
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode object = objectMapper.readTree(result);
            String errorCode = object.path("error_code").asText();
            if(!StringUtils.isEmpty(errorCode) && !"0".equals(errorCode)){
                log.debug(object.path("result").asText());
            }else{
                log.debug(object.path("error_code").asText()+":"+object.path("reason").asText());
            }
            returnStr[0] = errorCode+"";
            returnStr[1] = "";
            return returnStr;
        } catch (Exception e) {
            e.printStackTrace();
        }

        //发送失败
        return returnStr;
    }


    //2.发送通知短信给代理。及时处理商家OA消息
    public static String[] getMesAgent(String mobile){
        String result =null;
        String url ="http://v.juhe.cn/sms/send";//请求接口地址

        String []returnStr = {"",""};
        String templateId = MESAGENT;

        Map params = new HashMap();//请求参数
        params.put("mobile", mobile);//接收短信的手机号码
        params.put("tpl_id", templateId);//短信模板ID，请参考个人中心短信模板设置
        params.put("tpl_value", "");//变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlencode编码后再传递，<a href="http://www.juhe.cn/news/index/id/50" target="_blank">详细说明></a>
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","json");//返回数据的格式,xml或json，默认json

        try {
            result =net(url, params, "GET");
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode object = objectMapper.readTree(result);
            String errorCode = object.path("error_code").asText();
            if(!StringUtils.isEmpty(errorCode) && !"0".equals(errorCode)){
                log.debug(object.path("result").asText());
            }else{
                log.debug(object.path("error_code").asText()+":"+object.path("reason").asText());
            }
            returnStr[0] = errorCode+"";
            returnStr[1] = "";
            return returnStr;
        } catch (Exception e) {
            e.printStackTrace();
        }

        //发送失败
        return returnStr;
    }



    //2.发送提现短信消息
    public static String[] getTiXian(String mobile, String time,String money,String balance/*发送的内容*/){
        String result =null;
        String url ="http://v.juhe.cn/sms/send";//请求接口地址

        String []returnStr = {"",""};



        Map params = new HashMap();//请求参数
        params.put("mobile", mobile);//接收短信的手机号码
        params.put("tpl_id", TIXIANSHENQING);//短信模板ID，请参考个人中心短信模板设置
        params.put("tpl_value", "#time#=" + time+"&#money#="+money+"&#balance#="+balance);//#code#=1234&#company#=聚合数据
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","json");//返回数据的格式,xml或json，默认json

        try {
            result =net(url, params, "GET");
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode object = objectMapper.readTree(result);
            String errorCode = object.path("error_code").asText();
            if(!StringUtils.isEmpty(errorCode) && !"0".equals(errorCode)){
                log.debug(object.path("result").asText());
            }else{
                log.debug(object.path("error_code").asText()+":"+object.path("reason").asText());
            }
            returnStr[0] = errorCode+"";
            returnStr[1] = time+":" +money+":" +balance+ "";
            return returnStr;
        } catch (Exception e) {
            e.printStackTrace();
        }

        //发送失败
        return returnStr;
    }
}