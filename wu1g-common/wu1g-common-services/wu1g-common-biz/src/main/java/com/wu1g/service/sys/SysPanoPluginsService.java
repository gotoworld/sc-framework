package com.wu1g.service.sys;

import com.wu1g.api.sys.ISysPanoPluginsService;
import com.wu1g.dao.sys.ISysPanoPluginsDao;
import com.wu1g.framework.annotation.FeignService;
import com.wu1g.vo.sys.SysPanoPlugins;
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
public class SysPanoPluginsService extends BaseService implements ISysPanoPluginsService {
    @Autowired
    private ISysPanoPluginsDao sysPanoPluginsDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(SysPanoPlugins dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                //判断数据是否存在
                if (dto.getId() != null && sysPanoPluginsDao.isDataYN(dto) != 0) {
                    //数据存在
                    sysPanoPluginsDao.update(dto);
                } else {
                    //新增
                     sysPanoPluginsDao.insert(dto);
                }
            } catch (Exception e) {
                log.error("信息保存失败!", e);
                throw new RuntimeException("信息保存失败!");
            }
        }
        return result;
    }

    @Override
    public String deleteData(SysPanoPlugins dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                if(sysPanoPluginsDao.deleteByPrimaryKey(dto)==0){
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
    public String deleteDataById(SysPanoPlugins dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                if(sysPanoPluginsDao.deleteById(dto)==0){
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
    public List<SysPanoPlugins> findDataIsPage(SysPanoPlugins dto) throws Exception {
        List<SysPanoPlugins> results = null;
        try {
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            results = (List<SysPanoPlugins>) sysPanoPluginsDao.findDataIsPage(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new RuntimeException("信息查询失败!");
        }
        return results;
    }

    @Override
    public List<SysPanoPlugins> findDataIsList(SysPanoPlugins dto) throws Exception {
        List<SysPanoPlugins> results = null;
        try {
            results = (List<SysPanoPlugins>) sysPanoPluginsDao.findDataIsList(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new RuntimeException("信息查询失败!");
        }
        return results;
    }

    @Override
    @RfAccount2Bean
    public SysPanoPlugins findDataById(SysPanoPlugins dto) throws Exception {
        SysPanoPlugins result = null;
        try {
            result = (SysPanoPlugins) sysPanoPluginsDao.selectByPrimaryKey(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new RuntimeException("信息查询失败!");
        }
        return result;
    }

    @Override
    public String recoveryDataById(SysPanoPlugins dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                if(sysPanoPluginsDao.recoveryDataById(dto)==0){
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