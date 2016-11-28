/*	
 * 权限_角色信息   业务处理接口类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.account.api;


import com.wu1g.account.vo.AuthRole;

import java.util.List;
/**
 * <p>权限_角色信息   业务处理接口类。</p>	
 * <ol>[功能概要] 
 * <li>编辑(新增or修改)。 
 * <li>详情检索。 
 * <li>分页检索。 
 * <li>列表检索。 
 * <li>逻辑删除。 
 * <li>物理删除。 
 * <li>恢复逻辑删除。 
 *</ol> 
 * @author easycode
 */
public interface IAuthRoleService{

	/**	
	 * <p>信息编辑。</p>	
	 * <ol>[功能概要] 	
	 * <li>新增信息。	
	 * <li>修改信息。	
	 * </ol>	
	 * @return 处理结果	
	 */	
	public String saveOrUpdateData(AuthRole bean) throws Exception;
	/**	
	 * <p>信息编辑。</p>	
	 * <ol>[功能概要] 	
	 * <li>物理删除。	
	 * </ol>	
	 * @return 处理结果	
	 */	
	public String deleteData(AuthRole bean) throws Exception;	
	/**	
	 * <p>信息 单条。</p>	
	 * <ol>[功能概要] 	
	 * <li>恢复逻辑删除的数据。	
	 * </ol>	
	 * @return 处理结果	
	 */	
	public String recoveryDataById(AuthRole bean) throws Exception;	
	/**	
	 * <p>信息 单条。</p>	
	 * <ol>[功能概要] 	
	 * <li>逻辑删除。	
	 * </ol>	
	 * @return 处理结果	
	 */	
	public String deleteDataById(AuthRole bean) throws Exception;	
	/**	
	 * <p>信息列表 分页。</p>	
	 * <ol>[功能概要] 	
	 * <li>信息检索。	
	 * <li>分页。	
	 * </ol>	
	 * @return 处理结果	
	 */	
	public List<AuthRole> findDataIsPage(AuthRole bean);	
	/**	
	 * <p>信息列表。</p>	
	 * <ol>[功能概要] 	
	 * <li>信息检索。	
	 * <li>列表。	
	 * </ol>	
	 * @return 处理结果	
	 */	
	public List<AuthRole> findDataIsList(AuthRole bean);	
	/**	
	 * <p>信息详情。</p>	
	 * <ol>[功能概要] 	
	 * <li>信息检索。	
	 * <li>详情。	
	 * </ol>	
	 * @return 处理结果	
	 */	
	public AuthRole findDataById(AuthRole bean);	
}