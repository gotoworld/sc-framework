package com.vr.service.pano;

import com.vr.framework.annotation.FeignService;
import com.vr.framework.service.BaseService;
import com.vr.framework.util.CommonConstant;
import com.vr.api.pano.IPanoMapService;
import com.vr.dao.pano.IPanoMapDao;
import com.vr.vo.pano.PanoMap;
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