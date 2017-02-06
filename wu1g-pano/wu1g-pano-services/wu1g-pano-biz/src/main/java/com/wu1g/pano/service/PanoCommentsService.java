/*	
 * 全景_评论 业务处理实现类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.02      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 pano System. - All Rights Reserved.
 *	
 */

package com.wu1g.pano.service;


import com.github.pagehelper.PageHelper;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.pano.api.IPanoCommentsService;
import com.wu1g.pano.dao.IPanoCommentsDao;
import com.wu1g.pano.vo.PanoComments;
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
                    panoCommentsDao.updateByPrimaryKeySelective(bean);
                } else {
                    //新增
                    if (ValidatorUtil.isEmpty(bean.getId())) {
                        bean.setId(IdUtil.createUUID(32));
                    }
                    panoCommentsDao.insert(bean);
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
    public String deleteData(PanoComments bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                panoCommentsDao.deleteByPrimaryKey(bean);
            } catch (Exception e) {
                msg = "信息删除失败,数据库处理错误!";
                log.error(msg, e);
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
                msg = "信息删除失败,数据库处理错误!";
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
            PageHelper.startPage(PN(bean.getPageNum()), PS( bean.getPageSize()));
            beans = (List<PanoComments>) panoCommentsDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    @Override
    public List<PanoComments> findDataIsList(PanoComments bean) {
        List<PanoComments> beans = null;
        try {
            beans = (List<PanoComments>) panoCommentsDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    @Override
    public PanoComments findDataById(PanoComments bean) {
        PanoComments bean1 = null;
        try {
            bean1 = (PanoComments) panoCommentsDao.selectByPrimaryKey(bean);
        } catch (Exception e) {
            log.error("信息详情查询失败,数据库错误!", e);
        }
        return bean1;
    }

    @Override
    public String recoveryDataById(PanoComments bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                panoCommentsDao.recoveryDataById(bean);
            } catch (Exception e) {
                msg = "信息恢复失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }
}