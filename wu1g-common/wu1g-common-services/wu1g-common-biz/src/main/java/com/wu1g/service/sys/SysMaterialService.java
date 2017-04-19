package com.wu1g.service.sys;

import com.github.pagehelper.PageHelper;
import com.wu1g.api.sys.ISysMaterialService;
import com.wu1g.dao.sys.ISysMaterialDao;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.vo.sys.SysMaterial;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class SysMaterialService extends BaseService implements ISysMaterialService {
    @Autowired
    private ISysMaterialDao sysMaterialDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(SysMaterial dto) throws Exception {
        String msg = "seccuss";
        if (dto != null) {
            try {
                //判断数据是否存在
                if (dto.getId() != null && sysMaterialDao.isDataYN(dto) != 0) {
                    //数据存在
                    sysMaterialDao.update(dto);
                } else {
                    //新增
                     sysMaterialDao.insert(dto);
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new Exception("信息保存异常!");
            }
        }
        return msg;
    }

    @Override
    public String deleteData(SysMaterial dto) throws Exception {
        String msg = "seccuss";
        if (dto != null) {
            try {
                if(sysMaterialDao.deleteByPrimaryKey(dto)==0){
					throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("物理删除异常!", e);
                throw new Exception(e.getMessage());
            }
        }
        return msg;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String deleteDataById(SysMaterial dto) throws Exception {
        String msg = "seccuss";
        if (dto != null) {
            try {
                if(sysMaterialDao.deleteById(dto)==0){
					throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("逻辑删除异常!", e);
                throw new Exception(e.getMessage());
            }
        }
        return msg;
    }

    @Override
    public List<SysMaterial> findDataIsPage(SysMaterial dto) throws Exception {
        List<SysMaterial> dtos = null;
        try {
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            dtos = (List<SysMaterial>) sysMaterialDao.findDataIsPage(dto);
        } catch (Exception e) {
            log.error("信息查询异常!", e);
            throw new Exception("信息查询异常!");
        }
        return dtos;
    }

    @Override
    public List<SysMaterial> findDataIsList(SysMaterial dto) throws Exception {
        List<SysMaterial> dtos = null;
        try {
            dtos = (List<SysMaterial>) sysMaterialDao.findDataIsList(dto);
        } catch (Exception e) {
            log.error("信息查询异常!", e);
            throw new Exception("信息查询异常!");
        }
        return dtos;
    }

    @Override
    @RfAccount2Bean
    public SysMaterial findDataById(SysMaterial dto) throws Exception {
        SysMaterial dto1 = null;
        try {
            dto1 = (SysMaterial) sysMaterialDao.selectByPrimaryKey(dto);
        } catch (Exception e) {
            log.error("信息查询异常!", e);
            throw new Exception("信息查询异常!");
        }
        return dto1;
    }

}