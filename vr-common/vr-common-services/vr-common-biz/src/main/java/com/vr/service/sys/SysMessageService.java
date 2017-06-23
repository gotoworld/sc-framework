package com.vr.service.sys;

import com.vr.api.sys.ISysMessageService;
import com.vr.dao.sys.ISysMessageDao;
import com.vr.framework.annotation.FeignService;
import com.vr.vo.sys.SysMessage;
import com.vr.framework.annotation.RfAccount2Bean;
import com.vr.framework.service.BaseService;
import com.vr.framework.util.CommonConstant;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@FeignService
@Slf4j
public class SysMessageService extends BaseService implements ISysMessageService {
    @Autowired
    private ISysMessageDao sysMessageDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(SysMessage dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                //判断数据是否存在
                if (dto.getId() != null && sysMessageDao.isDataYN(dto) != 0) {
                    //数据存在
                    sysMessageDao.update(dto);
                } else {
                    //新增
                     sysMessageDao.insert(dto);
                }
            } catch (Exception e) {
                log.error("信息保存失败!", e);
                throw new RuntimeException("信息保存失败!");
            }
        }
        return result;
    }

    @Override
    public String deleteData(SysMessage dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                if(sysMessageDao.deleteByPrimaryKey(dto)==0){
					throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("物理删除失败!", e);
                throw new RuntimeException(e.getMessage());
            }
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String deleteDataById(SysMessage dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                if(sysMessageDao.deleteById(dto)==0){
					throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("逻辑删除失败!", e);
                throw new RuntimeException(e.getMessage());
            }
        }
        return result;
    }

    @Override
    public List<SysMessage> findDataIsPage(SysMessage dto) throws Exception {
        List<SysMessage> results = null;
        try {
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            results = (List<SysMessage>) sysMessageDao.findDataIsPage(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new RuntimeException("信息查询失败!");
        }
        return results;
    }

    @Override
    public List<SysMessage> findDataIsList(SysMessage dto) throws Exception {
        List<SysMessage> results = null;
        try {
            results = (List<SysMessage>) sysMessageDao.findDataIsList(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new RuntimeException("信息查询失败!");
        }
        return results;
    }

    @Override
    @RfAccount2Bean
    public SysMessage findDataById(SysMessage dto) throws Exception {
        SysMessage result = null;
        try {
            result = (SysMessage) sysMessageDao.selectByPrimaryKey(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new RuntimeException("信息查询失败!");
        }
        return result;
    }

}