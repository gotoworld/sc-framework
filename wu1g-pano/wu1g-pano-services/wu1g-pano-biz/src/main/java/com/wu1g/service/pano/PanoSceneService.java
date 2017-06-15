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


import com.wu1g.api.pano.IPanoSceneService;
import com.wu1g.dao.pano.IPanoSceneDao;
import com.wu1g.framework.annotation.FeignService;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.KrSceneImageUtil;
import com.wu1g.vo.pano.PanoScene;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@FeignService
@Slf4j
public class PanoSceneService   extends BaseService implements IPanoSceneService {
	/**全景_场景 Dao接口类*/
	@Autowired
	private IPanoSceneDao panoSceneDao;
	@Override
	public List<PanoScene> findDataIsList(PanoScene bean){	
		List<PanoScene> results=null;
		try {
			results=(List<PanoScene>) panoSceneDao.findDataIsList(bean);
			if(results!=null)
				results.forEach(scene->{
					scene.setBreakdownImg(KrSceneImageUtil.getBreakdownImg(bean.getProjCode(), scene.getSceneSrc()));
				});
		} catch (Exception e) {	
			log.error("信息查询失败!", e);
		}	
		return results;
	}
}