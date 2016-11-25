/*	
 * 全景_热点 业务处理实现类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.02      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 baseos System. - All Rights Reserved.
 *	
 */

package com.wu1g.pano.service;
 

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.baseos.bean.pano.PanoSpots;
import cn.com.baseos.common.CommonConstant;
import cn.com.baseos.common.IdUtil;
import cn.com.baseos.common.ValidatorUtil;
import cn.com.baseos.dao.daointer.pano.IPanoSpotsDao;
import cn.com.baseos.service.BaseService;
import cn.com.baseos.service.pano.IPanoSpotsService;
@Service
public abstract class PanoSpotsService   extends BaseService  implements IPanoSpotsService{
	private static final transient Logger log = Logger.getLogger(PanoSpotsService.class);
	/**全景_热点 Dao接口类*/
	@Autowired
	private IPanoSpotsDao panoSpotsDao;
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = { Exception.class, RuntimeException.class })	
	public String saveOrUpdateData(PanoSpots bean) throws Exception{	
		String msg="1";	
		if(bean!=null){	
			try {	
				//判断数据是否存在	
				if(panoSpotsDao.isDataYN(bean)!=0){	
					//数据存在	
					panoSpotsDao.updateByPrimaryKeySelective(bean);	
				}else{	
					//新增	
					if(ValidatorUtil.isEmpty(bean.getId())){
						bean.setId(IdUtil.createUUID(32));//id
					}

					panoSpotsDao.insert(bean);	
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
	public String deleteData(PanoSpots bean) throws Exception{	
		String msg="1";	
		if(bean!=null){	
			try {	
				panoSpotsDao.deleteByPrimaryKey(bean);	
			} catch (Exception e) {	
				msg="信息删除失败,数据库处理错误!";	
				log.error(msg, e);	
			}	
		}	
		return msg;	
	}
	@Override
	public List<PanoSpots> findDataIsList(PanoSpots bean){	
		List<PanoSpots> beans=null;	
		try {	
			beans=(List<PanoSpots>) panoSpotsDao.findDataIsList(bean);	
		} catch (Exception e) {	
			log.error("信息查询失败,数据库错误!", e);	
		}	
		return beans;	
	}	
}