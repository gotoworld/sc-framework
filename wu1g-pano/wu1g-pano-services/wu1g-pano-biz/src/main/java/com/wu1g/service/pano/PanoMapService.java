package com.wu1g.service.pano;

import com.wu1g.framework.annotation.FeignService;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.api.pano.IPanoMapService;
import com.wu1g.dao.pano.IPanoMapDao;
import com.wu1g.vo.pano.PanoMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@FeignService
@Slf4j
public class PanoMapService extends BaseService implements IPanoMapService {
    /**
     * 全景_导览图 Dao接口类
     */
    @Autowired
    private IPanoMapDao panoMapDao;
    @Override
    public List<PanoMap> findDataIsList(PanoMap bean) {
        List<PanoMap> beans = null;
        try {
            beans = (List<PanoMap>) panoMapDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return beans;
    }
}