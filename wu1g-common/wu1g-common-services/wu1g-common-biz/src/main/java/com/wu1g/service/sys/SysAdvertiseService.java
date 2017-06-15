package com.wu1g.service.sys;

import com.wu1g.api.sys.ISysAdvertiseService;
import com.wu1g.dao.sys.ISysAdvertiseDao;
import com.wu1g.framework.annotation.FeignService;
import com.wu1g.vo.sys.SysAdvertise;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
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
public class SysAdvertiseService extends BaseService implements ISysAdvertiseService {
    @Autowired
    private ISysAdvertiseDao sysAdvertiseDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(SysAdvertise dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                //判断数据是否存在
                if (dto.getId() != null && sysAdvertiseDao.isDataYN(dto) != 0) {
                    //数据存在
                    sysAdvertiseDao.update(dto);
                } else {
                    //新增
                     sysAdvertiseDao.insert(dto);
                }
            } catch (Exception e) {
                log.error("信息保存失败!", e);
                throw new RuntimeException("信息保存失败!");
            }
        }
        return result;
    }

    @Override
    public String deleteData(SysAdvertise dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                if(sysAdvertiseDao.deleteByPrimaryKey(dto)==0){
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
    public String deleteDataById(SysAdvertise dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                if(sysAdvertiseDao.deleteById(dto)==0){
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
    public List<SysAdvertise> findDataIsPage(SysAdvertise dto) throws Exception {
        List<SysAdvertise> results = null;
        try {
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            results = (List<SysAdvertise>) sysAdvertiseDao.findDataIsPage(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new RuntimeException("信息查询失败!");
        }
        return results;
    }

    @Override
    public List<SysAdvertise> findDataIsList(SysAdvertise dto) throws Exception {
        List<SysAdvertise> results = null;
        try {
            results = (List<SysAdvertise>) sysAdvertiseDao.findDataIsList(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new RuntimeException("信息查询失败!");
        }
        return results;
    }

    @Override
    @RfAccount2Bean
    public SysAdvertise findDataById(SysAdvertise dto) throws Exception {
        SysAdvertise result = null;
        try {
            result = (SysAdvertise) sysAdvertiseDao.selectByPrimaryKey(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new RuntimeException("信息查询失败!");
        }
        return result;
    }

    @Override
    public String recoveryDataById(SysAdvertise dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                if(sysAdvertiseDao.recoveryDataById(dto)==0){
					throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("数据恢复失败!", e);
                throw new RuntimeException(e.getMessage());
            }
        }
        return result;
    }
}