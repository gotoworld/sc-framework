/*	
 * banner信息 业务处理接口类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.11.07      wuxiaogang       程序.发布
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 panorama System. - All Rights Reserved.
 *	
 */

package com.wu1g.panorama.api;


import com.wu1g.framework.service.IBaseService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * <p> 业务处理接口类。
 */
@FeignClient(value = "wu1g-services")
public interface IPanoramaContactService extends IBaseService {



}