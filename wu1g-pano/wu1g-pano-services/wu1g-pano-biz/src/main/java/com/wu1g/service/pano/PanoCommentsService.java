package com.wu1g.service.pano;

import com.github.pagehelper.PageHelper;
import com.wu1g.api.pano.IPanoCommentsService;
import com.wu1g.dao.pano.IPanoCommentsDao;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.vo.pano.PanoComments;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PanoCommentsService extends BaseService implements IPanoCommentsService {
    @Autowired
    private IPanoCommentsDao panoCommentsDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(PanoComments bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                //判断数据是否存在
                if (panoCommentsDao.isDataYN(bean) != 0) {
                    //数据存在
                    panoCommentsDao.update(bean);
                } else {
                    //新增
                    panoCommentsDao.insert(bean);
                }
            } catch (Exception e) {
                msg = "信息保存失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String deleteDataById(PanoComments bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                panoCommentsDao.deleteById(bean);
            } catch (Exception e) {
                msg = "信息删除失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    @Override
    public List<PanoComments> findDataIsPage(PanoComments bean) {
        List<PanoComments> beans = null;
        try {
            PageHelper.startPage(PN(bean.getPageNum()), PS(bean.getPageSize()));
            beans = (List<PanoComments>) panoCommentsDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return beans;
    }

    @Override
    public List<PanoComments> findDataIsList(PanoComments bean) {
        List<PanoComments> beans = null;
        try {
            beans = (List<PanoComments>) panoCommentsDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return beans;
    }
}