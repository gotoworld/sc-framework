/*	
 * 全景_评论 业务处理实现类	
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

import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.pano.api.IPanoCommentsService;
import com.wu1g.pano.dao.IPanoCommentsDao;
import com.wu1g.pano.vo.PanoComments;
import com.wu1g.sys.api.ISysUserLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import javax.annotation.Resource;

@Service
@Slf4j
public class PanoCommentsService extends BaseService implements IPanoCommentsService {
    //	@Autowired
    //protected ISysUserLogService alog;
    /**
     * 全景_评论 Dao接口类
     */
    @Autowired
    private IPanoCommentsDao panoCommentsDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(PanoComments bean) throws Exception {
        String msg = "1";
        if (bean != null) {
            try {
                //判断数据是否存在
                if (panoCommentsDao.isDataYN(bean) != 0) {
                    //数据存在
                    panoCommentsDao.updateByPrimaryKeySelective(bean);
                    //alog.info("修改", "用户["+getUid()+"]修改,全景_评论信息,id["+bean.getId()+"],数据["+bean.getContent()+"]", bean.getCreateId(), bean.getCreateIp());
                } else {
                    //新增
                    if (ValidatorUtil.isEmpty(bean.getId())) {
                        bean.setId(IdUtil.createUUID(32));//id
                    }

                    panoCommentsDao.insert(bean);
                    //alog.info("新增", "用户["+getUid()+"]新增,全景_评论信息,id["+bean.getId()+"],数据["+bean.getContent()+"]", bean.getCreateId(), bean.getCreateIp());
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
        String msg = "1";
        if (bean != null) {
            try {
                panoCommentsDao.deleteByPrimaryKey(bean);
                //alog.info("删除", "用户["+getUid()+"]物理删除,全景_评论信息,id["+bean.getId()+"],数据["+bean.getContent()+"]", bean.getCreateId(), bean.getCreateIp());
            } catch (Exception e) {
                msg = "信息删除失败,数据库处理错误!";
                log.error(msg, e);
            }
        }
        return msg;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {
            Exception.class, RuntimeException.class})
    public String deleteDataById(PanoComments bean) throws Exception {
        String msg = "1";
        if (bean != null) {
            try {
                panoCommentsDao.deleteById(bean);
                //alog.info("删除", "用户["+getUid()+"]删除,全景_评论信息,id["+bean.getId()+"],数据["+bean.getContent()+"]", bean.getCreateId(), bean.getCreateIp());
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
            PageHelper.startPage((Integer) bean.getPageNum(), (Integer) bean.getPageSize());
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
            //if(bean1!=null  && ValidatorUtil.notEmpty(bean1.getDetailInfo())){
            //bean1.setDetailInfo(IOHelper.readHtml(bean1.getDetailInfo()));
            //}
        } catch (Exception e) {
            log.error("信息详情查询失败,数据库错误!", e);
        }
        return bean1;
    }

    @Override
    public String recoveryDataById(PanoComments bean) throws Exception {
        String msg = "1";
        if (bean != null) {
            try {
                panoCommentsDao.recoveryDataById(bean);
                //alog.info("恢复", "用户["+getUid()+"]恢复,全景_评论信息,id["+bean.getId()+"],数据["+bean.getContent()+"]", bean.getCreateId(), bean.getCreateIp());
            } catch (Exception e) {
                msg = "信息恢复失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }
}