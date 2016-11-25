/*	
 * 权限_权限信息   业务处理接口类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.auth.api;
import com.wu1g.auth.vo.AuthPerm;

import java.util.List;
import java.util.Map;

/**
 * <p>权限_权限信息   业务处理接口类。</p>	
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
public interface IAuthPermService{

	/**
	 * <p>信息编辑。</p>
	 * <ol>[功能概要]
	 * <li>新增信息。
	 * <li>修改信息。
	 * </ol>
	 * @return 处理结果
	 */
	public String saveOrUpdateData(AuthPerm bean) throws Exception;
	/**
	 * <p>信息编辑。</p>
	 * <ol>[功能概要]
	 * <li>物理删除。
	 * </ol>
	 * @return 处理结果
	 */
	public String deleteData(AuthPerm bean) throws Exception;
	/**
	 * <p>信息 单条。</p>
	 * <ol>[功能概要]
	 * <li>恢复逻辑删除的数据。
	 * </ol>
	 * @return 处理结果
	 */
	public String recoveryDataById(AuthPerm bean) throws Exception;
	/**
	 * <p>信息 单条。</p>
	 * <ol>[功能概要]
	 * <li>逻辑删除。
	 * </ol>
	 * @return 处理结果
	 */
	public String deleteDataById(AuthPerm bean) throws Exception;
	/**
	 * <p>信息列表 分页。</p>
	 * <ol>[功能概要]
	 * <li>信息检索。
	 * <li>分页。
	 * </ol>
	 * @return 处理结果
	 */
	public List<AuthPerm> findDataIsPage(AuthPerm bean);
	/**
	 * <p>信息列表。</p>
	 * <ol>[功能概要]
	 * <li>信息检索。
	 * <li>列表。
	 * </ol>
	 * @return 处理结果
	 */
	public List<AuthPerm> findDataIsList(AuthPerm bean);
	/**
	 * <p>信息详情。</p>
	 * <ol>[功能概要]
	 * <li>信息检索。
	 * <li>详情。
	 * </ol>
	 * @return 处理结果
	 */
	public AuthPerm findDataById(AuthPerm bean);
	/**
	 * <p>信息列表。</p>	
	 * <ol>[功能概要] 	
	 * <li>信息检索 根据xx查询所有圈圈。	
	 * </ol>	
	 * @return 处理结果	
	 */	
	public List<AuthPerm> findDataTree(AuthPerm bean);
	
	/**	
	 * <p>根据角色id获取对应的权限信息列表。</p>	
	 * <ol>[功能概要] 	
	 * <li>信息检索。	
	 * <li>列表。	
	 * </ol>	
	 */	
	public List<AuthPerm> findPermDataIsListByRoleId(Map dto);
	
}