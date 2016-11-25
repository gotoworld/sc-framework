/*
 * 行政区划管理  service 接口类
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00     2015.01.13  wuxiaogang      程序・发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2015 baseos  System. - All Rights Reserved.
 *
 */
package com.wu1g.adi.api;

import org.springframework.cloud.netflix.feign.FeignClient;


/**
 * <p> 行政区划管理   service 接口类。
 */
@FeignClient(value = "wu1g-services")
public interface IAreaService {

}
