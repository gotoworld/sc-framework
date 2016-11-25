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
package com.wu1g.auth.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.baseos.bean.auth.AuthPerm;
import cn.com.baseos.bean.auth.AuthRole;
import cn.com.baseos.bean.org.OrgUser;
import cn.com.baseos.dao.daointer.auth.IAuthPermDao;
import cn.com.baseos.dao.daointer.auth.IAuthRoleDao;
import cn.com.baseos.dao.daointer.org.IOrgUserDao;
import cn.com.baseos.service.BaseService;
import cn.com.baseos.service.auth.IRoleSourceService;
/**
 * <p>角色资源 业务处理实现类。</p>	
 * <ol>[功能概要] 
 *</ol> 
 * @author wuxiaogang
 */
@Service("roleSourceService")
public class RoleSourceService extends BaseService implements IRoleSourceService{

	private static final transient Logger log = Logger.getLogger(RoleSourceService.class);
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