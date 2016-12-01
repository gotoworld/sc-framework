/*	
 * 全景_热点 业务处理实现类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.02      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 pano System. - All Rights Reserved.
 *	
 */

package com.wu1g.pano.service;


import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.pano.api.IPanoSpotsService;
import com.wu1g.pano.dao.IPanoSpotsDao;
import com.wu1g.pano.vo.PanoSpots;
import com.wu1g.sys.api.ISysUserLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public abstract class PanoSpotsService   extends BaseService implements IPanoSpotsService {
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