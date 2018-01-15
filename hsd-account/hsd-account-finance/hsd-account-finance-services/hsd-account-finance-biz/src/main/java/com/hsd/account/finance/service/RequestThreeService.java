package com.hsd.account.finance.service;

import com.alibaba.fastjson.JSONObject;
import com.hsd.framework.net.HttpRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Created by hsd7 on 2018/1/10.
 */
@Service
public class RequestThreeService {

    @Autowired
    private HttpRestClient httpRestClient;

    //风控接口地址
    @Value("${datacenter.url}")
    private String datacenterUrl;

    /**
     * 卡三要素
     * @param name 姓名
     * @param certNo 身份证号码
     * @param cardNo 银行卡号
     * @return
     */
    public JSONObject cardThreeElement(String name, String certNo, String cardNo) throws Exception{
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("userBankCard",cardNo);
        map.add("userIdCard",certNo);
        map.add("userName",name);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String responseStr =  httpRestClient.doPost(datacenterUrl+"bankcard3item/verify",map, String.class);
        JSONObject responseJson = JSONObject.parseObject(responseStr);
        return responseJson;
    }

    /**
     * 卡四要素
     * @param name 姓名
     * @param certNo 身份证号码
     * @param cardNo 银行卡号
     * @return
     */
    public JSONObject cardFourElement(String name, String certNo, String cardNo, String phone) throws Exception{
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("userBankCard",cardNo);
        map.add("userIdCard",certNo);
        map.add("userName",name);
        map.add("userPhoneNo",phone);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String responseStr =  httpRestClient.doPost(datacenterUrl+"bankcard4item/verify",map, String.class);
        JSONObject responseJson = JSONObject.parseObject(responseStr);
        return responseJson;
    }
}
