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


import com.wu1g.framework.api.BaseService;
import com.wu1g.panorama.dto.panoramaContactDto;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>商户信息录入 业务处理接口类。
 */
@FeignClient(value = "wu1g-services")
public interface IpanoramaContactService extends BaseService {

	/**	
	 * <p>信息编辑。
	 * <ol>[功能概要] 	
	 * <li>新增信息。	
	 * <li>修改信息。	
	 */
	@RequestMapping(value = "/saveOrUpdateData")
	public String saveOrUpdateData(panoramaContactDto dto) throws Exception;
	/**	
	 * <p>信息编辑。
	 * <ol>[功能概要] 	
	 * <li>物理删除。	
	 */
	@RequestMapping(value = "/deleteData")
	public String deleteData(panoramaContactDto dto) throws Exception;
	/**	
	 * <p>信息 单条。
	 * <ol>[功能概要] 	
	 * <li>恢复逻辑删除的数据。	
	 */
	@RequestMapping(value = "/recoveryDataById")
	public String recoveryDataById(panoramaContactDto dto) throws Exception;
	/**	
	 * <p>信息 单条。
	 * <ol>[功能概要] 	
	 * <li>逻辑删除。	
	 */
	@RequestMapping(value = "/deleteDataById")
	public String deleteDataById(panoramaContactDto dto) throws Exception;
	/**	
	 * <p>信息列表 分页。
	 * <ol>[功能概要] 	
	 * <li>信息检索。	
	 * <li>分页。	
	 */
	@RequestMapping(value = "/findDataIsPage")
	public PageInfo findDataIsPage(panoramaContactDto dto);
	/**	
	 * <p>信息列表。
	 * <ol>[功能概要] 	
	 * <li>信息检索。	
	 * <li>列表。	
	 */
	@RequestMapping(value = "/findDataIsList")
	public List<panoramaContactDto> findDataIsList(panoramaContactDto dto);
	/**	
	 * <p>信息详情。
	 * <ol>[功能概要] 	
	 * <li>信息检索。	
	 * <li>详情。	
	 */
	@RequestMapping(value = "/findDataById")
	public panoramaContactDto findDataById(panoramaContactDto dto);

	/**
	 * <p>信息状态变更。
	 */
	@RequestMapping(value = "/updateState")
	public String updateState(panoramaContactDto dto) throws Exception;


	/**
	 * <p>信息列表。
	 * <li>xls导出数据。
	 */
	@RequestMapping(value = "/findXlsDataIsList")
	public List<?> findXlsDataIsList(panoramaContactDto dto);

}