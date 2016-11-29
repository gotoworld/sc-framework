/*	
 * 权限_权限信息   业务处理实现类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.account.service;

import com.github.pagehelper.PageHelper;
import com.wu1g.account.api.IAuthPermService;
import com.wu1g.account.dao.IAuthPermDao;
import com.wu1g.account.vo.AuthPerm;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.sys.api.ISysUserLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
@Slf4j
public class AuthPermService extends BaseService implements IAuthPermService {
	//@Autowired
	//protected ISysUserLogService alog;
	@Autowired
	private IAuthPermDao authPermDao;
	 /**	
	 * <p>信息编辑。</p>	
	 * <ol>[功能概要] 	
	 * <li>新增信息。	
	 * <li>修改信息。	
	 * </ol>	
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {
			Exception.class, RuntimeException.class })	
	public String saveOrUpdateData(AuthPerm bean) throws Exception{
		String msg="1";	
		if(bean!=null){	
			try {	
				AuthPerm dto=new AuthPerm();	
				//判断数据是否存在	
				if(authPermDao.isDataYN(dto)!=0){	
					//数据存在	
					authPermDao.updateByPrimaryKeySelective(dto);	
					//alog.info("修改", "用户["+getUid()+"]修改,权限功能信息,id["+dto.getId()+"],名称["+dto.getName()+"]", dto.getCreateId(), dto.getCreateIp());
				}else{	
					//新增	
					if(ValidatorUtil.isEmpty(dto.getId())){
						dto.setId(IdUtil.createUUID(22));
					}	
					authPermDao.insert(dto);	
					//alog.info("新增", "用户["+getUid()+"]新增,权限功能信息,id["+dto.getId()+"],名称["+dto.getName()+"]", dto.getCreateId(), dto.getCreateIp());
				}
			} catch (Exception e) {	
				msg="信息保存失败,数据库处理错误!";	
				log.error(msg, e);	
				throw new Exception(msg);	
			}	
		}	
		return msg;	
	}	
	/**	
	 * <p>信息编辑。</p>	
	 * <ol>[功能概要] 	
	 * <li>物理删除。	
	 * </ol>	
	 * @return 处理结果	
	 */	
	public String deleteData(AuthPerm bean) throws Exception{	
		String msg="1";	
		if(bean!=null){	
			try {	
				AuthPerm dto=new AuthPerm();	
				dto.setId(bean.getId());//权限id	
				authPermDao.deleteByPrimaryKey(dto);	
			} catch (Exception e) {	
				msg="信息删除失败,数据库处理错误!";	
				log.error(msg, e);	
			}	
		}	
		return msg;	
	}	
	/**	
	 * <p>信息 单条。</p>	
	 * <ol>[功能概要] 	
	 * <li>逻辑删除。	
	 * </ol>	
	 * @return 处理结果	
	 */	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {	
			Exception.class, RuntimeException.class })	
	public String deleteDataById(AuthPerm bean) throws Exception{	
		String msg="1";	
		if(bean!=null){	
			try {	
				AuthPerm dto=new AuthPerm();	
				dto.setId(bean.getId());//权限id	
				authPermDao.deleteById(dto);	
				//alog.info("删除", "用户["+getUid()+"]删除,权限功能信息,id["+dto.getId()+"]", dto.getCreateId(), dto.getCreateIp());
			} catch (Exception e) {	
				msg="信息删除失败,数据库处理错误!";	
				log.error(msg, e);	
				throw new Exception(msg);	
			}	
		}	
		return msg;	
	}	
	/**	
	 * <p>信息列表 分页。</p>	
	 * <ol>[功能概要] 	
	 * <li>信息检索。	
	 * <li>分页。	
	 * </ol>	
	 * @return 处理结果	
	 */	
	public List<AuthPerm> findDataIsPage(AuthPerm bean){	
		List<AuthPerm> beans=null;	
		try {	
		   PageHelper.startPage( (Integer)bean.getPageNum(), (Integer)bean.getPageSize());
		   beans=(List<AuthPerm>) authPermDao.findDataIsPage(bean);	
		} catch (Exception e) {	
			log.error("信息查询失败,数据库错误!", e);	
		}	
		return beans;	
	}	
	/**	
	 * <p>信息列表。</p>	
	 * <ol>[功能概要] 	
	 * <li>信息检索。	
	 * <li>列表。	
	 * </ol>	
	 * @return 处理结果	
	 */	
	public List<AuthPerm> findDataIsList(AuthPerm bean){	
		List<AuthPerm> beans=null;	
		try {	
			beans=(List<AuthPerm>) authPermDao.findDataIsList(bean);	
		} catch (Exception e) {	
			log.error("信息查询失败,数据库错误!", e);	
		}	
		return beans;	
	}	
	/**	
	 * <p>信息详情。</p>	
	 * <ol>[功能概要] 	
	 * <li>信息检索。	
	 * <li>详情。	
	 * </ol>	
	 * @return 处理结果	
	 */	
	public AuthPerm findDataById(AuthPerm bean){	
	   AuthPerm bean1=null;	
	   try {	
			bean1=(AuthPerm) authPermDao.selectByPrimaryKey(bean);	
			//if(bean1!=null  && ValidatorUtil.notEmpty(bean1.getDetailInfo())){	
				//bean1.setDetailInfo(IOHelper.readHtml(bean1.getDetailInfo()));	
			//}	
		} catch (Exception e) {	
			log.error("信息详情查询失败,数据库错误!", e);	
		}	
		return bean1;	
	}	
	/**	
	 * <p>信息 单条。</p>	
	 * <ol>[功能概要] 	
	 * <li>恢复逻辑删除的数据。	
	 * </ol>	
	 * @return 处理结果	
	 */	
	public String recoveryDataById(AuthPerm bean) throws Exception{	
		String msg="1";	
		if(bean!=null){	
			try {	
				AuthPerm dto=new AuthPerm();	
				dto.setId(bean.getId());//权限id	
				authPermDao.recoveryDataById(dto);	
			} catch (Exception e) {	
				msg="信息恢复失败,数据库处理错误!";	
				log.error(msg, e);	
				throw new Exception(msg);	
			}	
		}	
		return msg;	
	}
	/**	
	 * <p>信息列表。</p>	
	 * <ol>[功能概要] 	
	 * <li>信息检索 根据xx查询所有圈圈。	
	 * </ol>	
	 * @return 处理结果	
	 */	
	public List<AuthPerm> findDataTree(AuthPerm bean){
		List<AuthPerm> beans=findDataIsList(bean);
		if(beans==null){
			return null;
		}
		AuthPermBeanTree tree=new AuthPermBeanTree(beans);
		return	tree.buildTree();
	}
	/**	
	 * <p>根据角色id获取对应的权限信息列表。</p>	
	 * <ol>[功能概要] 	
	 * <li>信息检索。	
	 * <li>列表。	
	 * </ol>	
	 */	
	public List<AuthPerm> findPermDataIsListByRoleId(Map dto){
		List<AuthPerm> beans=null;	
		try {	
			beans=(List<AuthPerm>) authPermDao.findPermDataIsListByRoleId(dto);	
		} catch (Exception e) {	
			log.error("信息查询失败,数据库错误!", e);	
		}	
		return beans;	
	}
	/**	
	 * 信息DAO 接口类取得	
	 * @return 信息DAO 接口类	
	 */	
	public IAuthPermDao getAuthPermDao() {	
		return authPermDao;	
	}	
	/**	
	 * 信息DAO 接口类设定	
	 * @param authPermDao 信息DAO 接口类	
	 */	
	public void setAuthPermDao(IAuthPermDao authPermDao) {	
		this.authPermDao = authPermDao;	
	}	
}

class AuthPermBeanTree{
	private List<AuthPerm> new_nodes=new ArrayList<AuthPerm>();
	private List<AuthPerm> nodes;  
	public AuthPermBeanTree(List<AuthPerm> nodes){  
        this.nodes = nodes;  
    }  
      
    public List<AuthPerm> buildTree(){ 
        for (AuthPerm node : nodes) {  
//            String id = node.getCode();  
            if (ValidatorUtil.isNullEmpty(node.getParentid())) {  
                new_nodes.add(node);
                build(node);  
            }  
        }  
        return new_nodes;  
    }
	private void build(AuthPerm node){
        List<AuthPerm> children = getChildren(node);  
        if (!children.isEmpty()) { 
        	if(node.getBeans()==null){
        		node.setBeans(new ArrayList());
        	}
            for (AuthPerm child : children) {  
                String id = child.getId();  
                node.getBeans().add(child);
                build(child);  
            }  
        }   
    }
      
    private List<AuthPerm> getChildren(AuthPerm node){
        List<AuthPerm> children = new ArrayList<AuthPerm>();  
        String id = node.getId();  
        for (AuthPerm child : nodes) {  
            if (id.equals(child.getParentid())) {  
                children.add(child);  
            }  
        }  
        return children;  
    }  
}