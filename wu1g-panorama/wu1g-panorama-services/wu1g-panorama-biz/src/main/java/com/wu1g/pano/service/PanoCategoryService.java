/*	
 * 全景_类目 业务处理实现类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.02      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 baseos System. - All Rights Reserved.
 *	
 */

package com.wu1g.pano.service;
 

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.com.baseos.bean.pano.PanoCategory;
import cn.com.baseos.common.CommonConstant;
import cn.com.baseos.common.IdUtil;
import cn.com.baseos.common.ValidatorUtil;
import cn.com.baseos.dao.daointer.pano.IPanoCategoryDao;
import cn.com.baseos.service.BaseService;
import cn.com.baseos.service.pano.IPanoCategoryService;
@Service
public class PanoCategoryService   extends BaseService  implements IPanoCategoryService{
	private static final transient Logger log = Logger.getLogger(PanoCategoryService.class);
	/**全景_类目 Dao接口类*/
	@Autowired
	private IPanoCategoryDao panoCategoryDao;
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = { Exception.class, RuntimeException.class })	
	public String saveOrUpdateData(PanoCategory bean) throws Exception{	
		String msg="1";	
		if(bean!=null){	
			try {	
				//判断数据是否存在	
				if(panoCategoryDao.isDataYN(bean)!=0){	
					//数据存在	
					panoCategoryDao.updateByPrimaryKeySelective(bean);	
					alog.info("修改", "用户["+getUid()+"]修改,全景_类目信息,id["+bean.getId()+"],数据["+bean.getName()+"]", bean.getCreateId(), bean.getCreateIp());	
				}else{	
					//新增	
					if(ValidatorUtil.isEmpty(bean.getId())){
						bean.setId(IdUtil.createUUID(32));//ID
					}

					panoCategoryDao.insert(bean);	
					alog.info("新增", "用户["+getUid()+"]新增,全景_类目信息,id["+bean.getId()+"],数据["+bean.getName()+"]", bean.getCreateId(), bean.getCreateIp());	
				}	
			} catch (Exception e) {	
				msg="信息保存失败,数据库处理错误!";	
				log.error(msg, e);	
				throw new Exception(msg);	
			}	
		}	
		return msg;	
	}	
	@Override
	public String deleteData(PanoCategory bean) throws Exception{	
		String msg="1";	
		if(bean!=null){	
			try {	
				panoCategoryDao.deleteByPrimaryKey(bean);	
				alog.info("删除", "用户["+getUid()+"]物理删除,全景_类目信息,id["+bean.getId()+"],数据["+bean.getName()+"]", bean.getCreateId(), bean.getCreateIp());	
			} catch (Exception e) {	
				msg="信息删除失败,数据库处理错误!";	
				log.error(msg, e);	
			}	
		}	
		return msg;	
	}	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {	
			Exception.class, RuntimeException.class })	
	public String deleteDataById(PanoCategory bean) throws Exception{	
		String msg="1";	
		if(bean!=null){	
			try {	
				panoCategoryDao.deleteById(bean);	
				alog.info("删除", "用户["+getUid()+"]删除,全景_类目信息,id["+bean.getId()+"],数据["+bean.getName()+"]", bean.getCreateId(), bean.getCreateIp());	
			} catch (Exception e) {	
				msg="信息删除失败,数据库处理错误!";	
				log.error(msg, e);	
				throw new Exception(msg);	
			}	
		}	
		return msg;	
	}	
	@Override
	public List<PanoCategory> findDataIsPage(PanoCategory bean){	
		List<PanoCategory> beans=null;	
		try {	
			PageHelper.startPage( (Integer) bean.getPageNum(), (Integer) bean.getPageSize() );
			beans=(List<PanoCategory>) panoCategoryDao.findDataIsPage(bean);	
		} catch (Exception e) {	
			log.error("信息查询失败,数据库错误!", e);	
		}	
		return beans;	
	}	
	@Override
	public List<PanoCategory> findDataIsList(PanoCategory bean){	
		List<PanoCategory> beans=null;	
		try {	
			beans=(List<PanoCategory>) panoCategoryDao.findDataIsList(bean);	
		} catch (Exception e) {	
			log.error("信息查询失败,数据库错误!", e);	
		}	
		return beans;	
	}	
	@Override
	public PanoCategory findDataById(PanoCategory bean){	
	   PanoCategory bean1=null;	
	   try {	
			bean1=(PanoCategory) panoCategoryDao.selectByPrimaryKey(bean);	
			//if(bean1!=null  && ValidatorUtil.notEmpty(bean1.getDetailInfo())){	
				//bean1.setDetailInfo(IOHelper.readHtml(bean1.getDetailInfo()));	
			//}	
		} catch (Exception e) {	
			log.error("信息详情查询失败,数据库错误!", e);	
		}	
		return bean1;	
	}	
	@Override
	public String recoveryDataById(PanoCategory bean) throws Exception{	
		String msg="1";	
		if(bean!=null){	
			try {	
				panoCategoryDao.recoveryDataById(bean);	
				alog.info("恢复", "用户["+getUid()+"]恢复,全景_类目信息,id["+bean.getId()+"],数据["+bean.getName()+"]", bean.getCreateId(), bean.getCreateIp());	
			} catch (Exception e) {	
				msg="信息恢复失败,数据库处理错误!";	
				log.error(msg, e);	
				throw new Exception(msg);	
			}	
		}	
		return msg;	
	}
	public List<PanoCategory> findDataTree(PanoCategory bean) {
		List<PanoCategory> beans = findDataIsList( bean );
		if (beans == null) {
			return null;
		}
		PanoCategoryBeanTree tree = new PanoCategoryBeanTree( beans );
		return tree.buildTree();
	}
}

class PanoCategoryBeanTree {
	private List<PanoCategory>	new_nodes	= new ArrayList<PanoCategory>();
	private List<PanoCategory>	nodes;

	public PanoCategoryBeanTree(List<PanoCategory> nodes) {
		this.nodes = nodes;
	}

	public List<PanoCategory> buildTree() {
		for (PanoCategory node : nodes) {
			// String id = node.getCode();
			if (ValidatorUtil.isNullEmpty( node.getParentid() )) {
				new_nodes.add( node );
				build( node );
			}
		}
		return new_nodes;
	}

	private void build(PanoCategory node) {
		List<PanoCategory> children = getChildren( node );
		if (!children.isEmpty()) {
			if (node.getBeans() == null) {
				node.setBeans( new ArrayList() );
			}
			for (PanoCategory child : children) {
				String id = child.getId();
				node.getBeans().add( child );
				build( child );
			}
		}
	}

	private List<PanoCategory> getChildren(PanoCategory node) {
		List<PanoCategory> children = new ArrayList<PanoCategory>();
		String id = node.getId();
		for (PanoCategory child : nodes) {
			if (id.equals( child.getParentid() )) {
				children.add( child );
			}
		}
		return children;
	}
}