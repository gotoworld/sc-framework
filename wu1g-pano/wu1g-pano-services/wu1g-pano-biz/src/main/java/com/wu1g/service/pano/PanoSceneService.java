/*	
 * 全景_场景 业务处理实现类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.02      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 pano System. - All Rights Reserved.
 *	
 */

package com.wu1g.service.pano;


import com.github.pagehelper.PageHelper;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.api.pano.IPanoSceneService;
import com.wu1g.dao.pano.IPanoSceneDao;
import com.wu1g.framework.util.KrSceneImageUtil;
import com.wu1g.vo.pano.PanoScene;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PanoSceneService   extends BaseService implements IPanoSceneService {
	/**全景_场景 Dao接口类*/
	@Autowired
	private IPanoSceneDao panoSceneDao;
//	@Override
//	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = { Exception.class, RuntimeException.class })
//	public String saveOrUpdateData(PanoScene bean) throws Exception{
//		String msg="1";
//		if(bean!=null){
//			try {
//				//判断数据是否存在
//				if(panoSceneDao.isDataYN(bean)!=0){
//					//数据存在
//					panoSceneDao.update(bean);
//				}else{
//					//新增
//					panoSceneDao.insert(bean);
//				}
//			} catch (Exception e) {
//				msg="信息保存失败!";
//				log.error(msg, e);
//				throw new Exception(msg);
//			}
//		}
//		return msg;
//	}
//	@Override
//	public String deleteData(PanoScene bean) throws Exception{
//		String msg="1";
//		if(bean!=null){
//			try {
//				panoSceneDao.deleteByPrimaryKey(bean);
//			} catch (Exception e) {
//				msg="信息删除失败!";
//				log.error(msg, e);
//			}
//		}
//		return msg;
//	}
//	@Override
//	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {
//			Exception.class, RuntimeException.class })
//	public String deleteDataById(PanoScene bean) throws Exception{
//		String msg="1";
//		if(bean!=null){
//			try {
//				panoSceneDao.deleteById(bean);
//			} catch (Exception e) {
//				msg="信息删除失败!";
//				log.error(msg, e);
//				throw new Exception(msg);
//			}
//		}
//		return msg;
//	}
//	@Override
//	public List<PanoScene> findDataIsPage(PanoScene bean){
//		List<PanoScene> beans=null;
//		try {
//			PageHelper.startPage(PN(bean.getPageNum()), PS( bean.getPageSize()));
//			beans=(List<PanoScene>) panoSceneDao.findDataIsPage(bean);
//		} catch (Exception e) {
//			log.error("信息查询失败!", e);
//		}
//		return beans;
//	}
	@Override
	public List<PanoScene> findDataIsList(PanoScene bean){	
		List<PanoScene> beans=null;	
		try {	
			beans=(List<PanoScene>) panoSceneDao.findDataIsList(bean);
			if(beans!=null)
				beans.forEach(scene->{
					scene.setBreakdownImg(KrSceneImageUtil.getBreakdownImg(bean.getProjCode(), scene.getSceneSrc()));
				});
		} catch (Exception e) {	
			log.error("信息查询失败!", e);
		}	
		return beans;	
	}
//	@Override
//	public PanoScene findDataById(PanoScene bean){
//	   PanoScene bean1=null;
//	   try {
//			bean1=(PanoScene) panoSceneDao.selectByPrimaryKey(bean);
//		} catch (Exception e) {
//			log.error("信息详情查询失败!", e);
//		}
//		return bean1;
//	}
//	@Override
//	public String recoveryDataById(PanoScene bean) throws Exception{
//		String msg="1";
//		if(bean!=null){
//			try {
//				panoSceneDao.recoveryDataById(bean);
//			} catch (Exception e) {
//				msg="信息恢复失败!";
//				log.error(msg, e);
//				throw new Exception(msg);
//			}
//		}
//		return msg;
//	}
}