package com.wu1g.service.sys;

import com.wu1g.api.sys.ISysSensitiveWordsService;
import com.wu1g.dao.sys.ISysSensitiveWordsDao;
import com.wu1g.framework.annotation.FeignService;
import com.wu1g.vo.sys.SysSensitiveWords;
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
public class SysSensitiveWordsService extends BaseService implements ISysSensitiveWordsService {
    @Autowired
    private ISysSensitiveWordsDao sysSensitiveWordsDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(SysSensitiveWords dto) throws Exception {
        String msg = "seccuss";
        if (dto != null) {
            try {
                //判断数据是否存在
                if (dto.getId() != null && sysSensitiveWordsDao.isDataYN(dto) != 0) {
                    //数据存在
                    sysSensitiveWordsDao.update(dto);
                } else {
                    //新增
                     sysSensitiveWordsDao.insert(dto);
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new Exception("信息保存异常!");
            }
        }
        return msg;
    }

    @Override
    public String deleteData(SysSensitiveWords dto) throws Exception {
        String msg = "seccuss";
        if (dto != null) {
            try {
                if(sysSensitiveWordsDao.deleteByPrimaryKey(dto)==0){
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
    public List<SysSensitiveWords> findDataIsPage(SysSensitiveWords dto) throws Exception {
        List<SysSensitiveWords> dtos = null;
        try {
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            dtos = (List<SysSensitiveWords>) sysSensitiveWordsDao.findDataIsPage(dto);
        } catch (Exception e) {
            log.error("信息查询异常!", e);
            throw new Exception("信息查询异常!");
        }
        return dtos;
    }

    @Override
    public List<SysSensitiveWords> findDataIsList(SysSensitiveWords dto) throws Exception {
        List<SysSensitiveWords> dtos = null;
        try {
            dtos = (List<SysSensitiveWords>) sysSensitiveWordsDao.findDataIsList(dto);
        } catch (Exception e) {
            log.error("信息查询异常!", e);
            throw new Exception("信息查询异常!");
        }
        return dtos;
    }

    @Override
    @RfAccount2Bean
    public SysSensitiveWords findDataById(SysSensitiveWords dto) throws Exception {
        SysSensitiveWords dto1 = null;
        try {
            dto1 = (SysSensitiveWords) sysSensitiveWordsDao.selectByPrimaryKey(dto);
        } catch (Exception e) {
            log.error("信息查询异常!", e);
            throw new Exception("信息查询异常!");
        }
        return dto1;
    }

}