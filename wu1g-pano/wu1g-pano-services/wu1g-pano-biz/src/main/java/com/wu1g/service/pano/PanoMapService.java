package com.wu1g.service.pano;

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

@Service
@Slf4j
public class PanoMapService extends BaseService implements IPanoMapService {
    /**
     * 全景_导览图 Dao接口类
     */
    @Autowired
    private IPanoMapDao panoMapDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(PanoMap bean) throws Exception {
        String msg = "1";
        if (bean != null) {
            try {
                //判断数据是否存在
                if (panoMapDao.isDataYN(bean) != 0) {
                    //数据存在
                    panoMapDao.update(bean);
                } else {
                    //新增
                    panoMapDao.insert(bean);
                }
            } catch (Exception e) {
                msg = "信息保存失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    @Override
    public String deleteData(PanoMap bean) throws Exception {
        String msg = "1";
        if (bean != null) {
            try {
                panoMapDao.deleteByPrimaryKey(bean);
            } catch (Exception e) {
                msg = "信息删除失败,数据库处理错误!";
                log.error(msg, e);
            }
        }
        return msg;
    }

    @Override
    public List<PanoMap> findDataIsList(PanoMap bean) {
        List<PanoMap> beans = null;
        try {
            beans = (List<PanoMap>) panoMapDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }
}