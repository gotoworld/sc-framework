/*
 * 验证码生成使用ActionClass
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00     2015.01.13  wuxiaogang      程序・发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2015 baseos System. - All Rights Reserved.
 *
 */
package com.wu1g.framework.web.controller;

import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.VerifyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>验证码生成 ActionClass</p>
 * <ol>[提供机能]
 * <li>图片验证码生成</li>
 * </ol>
 */
@Controller
@RequestMapping
@Slf4j
public class VerifyImageAction extends BaseController{
	private static final long serialVersionUID = 4090202479166010857L;

    /** 默认的构造函数 */
    public VerifyImageAction() {
    	log.info("VerifyImageAction constructed");
    }
    /**
     * <p>生成验证码图片处理。</p>
     * <ol>[功能概要]
     * <li>生成验证码图片
     * </ol>
     * @return String 
     * @throws Exception 
     */
    @RequestMapping(value="/verifyImage")
	public String verifyImage() throws Exception {
		VerifyUtil.createVerifyCode(response, CommonConstant.SESSION_VERIFY_CODE, request);
		return null;
	}
	
	
}
