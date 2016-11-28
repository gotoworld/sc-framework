/*	
 * 角色资源 业务处理实现类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.06.29      wuxiaogang         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 baseos System. - All Rights Reserved.		
 *	
 */
package com.wu1g.account.service;

import com.wu1g.account.api.IRoleSourceService;
import com.wu1g.account.dao.IAuthPermDao;
import com.wu1g.account.dao.IAuthRoleDao;
import com.wu1g.account.vo.AuthPerm;
import com.wu1g.account.vo.AuthRole;
import com.wu1g.framework.service.BaseService;
import com.wu1g.org.dao.IOrgUserDao;
import com.wu1g.org.vo.OrgUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>角色资源 业务处理实现类。</p>	
 * <ol>[功能概要] 
 *</ol> 
 * @author wuxiaogang
 */
@Service("roleSourceService")
@Slf4j
public class RoleSourceService extends BaseService implements IRoleSourceService {

	/**角色信息表 Dao接口类*/
	@Autowired
	private IAuthRoleDao authRoleDao;
	/**权限信息表 Dao接口类*/
	@Autowired
	private IAuthPermDao authPermDao;
	/**系统用户表 Dao接口类*/
	@Autowired
	private IOrgUserDao orgUserDao;
	/**	
	 * 根据用户id,判断用户是否为超级管理员,要的就是特权.
	 */	
	@Override
	public int isSuperAdmin(Map dto){
		try {
			return authRoleDao.isSuperAdmin(dto);
		} catch (Exception e) {
			log.error("根据用户id,判断用户是否为超级管理员,要特权.,数据库处理异常!",e);
		}
		return 0;
	}
	@Override
	public List<AuthRole> getRoleListByUId(Map dto){
		try {
			return authRoleDao.getRoleListByUId(dto);
		} catch (Exception e) {
			log.error("角色信息列表>根据用户id,数据库处理异常!",e);
		}
		return null;
	}
	@Override
	public List<AuthPerm> getPermListByUId(Map dto) {
		try {
			return authPermDao.getPermListByUId(dto);
		} catch (Exception e) {
			log.error("角色权限信息列表>根据用户id,数据库处理异常!",e);
		}
		return null;
	}
	@Override
	public OrgUser findUserByLoginName(String loginName) {
		try {
			Map dto=new HashMap();
			dto.put("userid", loginName);
			return orgUserDao.findUserByLoginName(dto);
		} catch (Exception e) {
			log.error("用户信息>根据用户登录名,数据库处理异常!",e);
		}
		return null;
	}
}