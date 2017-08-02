package com.hsd.service.sys;

import com.github.pagehelper.PageHelper;
import com.hsd.api.sys.ISysSensitiveWordsService;
import com.hsd.dao.sys.ISysSensitiveWordsDao;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;
import com.hsd.dto.sys.SysSensitiveWords;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignService
@Slf4j
public class SysSensitiveWordsService extends BaseService implements ISysSensitiveWordsService {
    @Autowired
    private ISysSensitiveWordsDao sysSensitiveWordsDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response saveOrUpdateData(@RequestBody SysSensitiveWords dto) throws Exception {
        Response result = new Response(0,"seccuss");
        if (dto != null) {
            try {
                //判断数据是否存在
                if (dto.getId() != null && sysSensitiveWordsDao.isDataYN(dto) != 0) {
                    //数据存在
                    sysSensitiveWordsDao.update(dto);
                } else {
                    //新增
                     sysSensitiveWordsDao.insert(dto);
                    result.data=dto.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new RuntimeException("信息保存异常!");
            }
        }
        return result;
    }

    @Override
    public String deleteData(@RequestBody SysSensitiveWords dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                if(sysSensitiveWordsDao.deleteByPrimaryKey(dto)==0){
					throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("物理删除异常!", e);
                throw new RuntimeException(e.getMessage());
            }
        }
        return result;
    }


    @Override
    public List<SysSensitiveWords> findDataIsPage(@RequestBody SysSensitiveWords dto) throws Exception {
        List<SysSensitiveWords> results = null;
        try {
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            results = (List<SysSensitiveWords>) sysSensitiveWordsDao.findDataIsPage(dto);
        } catch (Exception e) {
            log.error("信息查询异常!", e);
            throw new RuntimeException("信息查询异常!");
        }
        return results;
    }

    @Override
    public List<SysSensitiveWords> findDataIsList(@RequestBody SysSensitiveWords dto) throws Exception {
        List<SysSensitiveWords> results = null;
        try {
            results = (List<SysSensitiveWords>) sysSensitiveWordsDao.findDataIsList(dto);
        } catch (Exception e) {
            log.error("信息查询异常!", e);
            throw new RuntimeException("信息查询异常!");
        }
        return results;
    }

    @Override
    @RfAccount2Bean
    public SysSensitiveWords findDataById(@RequestBody SysSensitiveWords dto) throws Exception {
        SysSensitiveWords result = null;
        try {
            result = (SysSensitiveWords) sysSensitiveWordsDao.selectByPrimaryKey(dto);
        } catch (Exception e) {
            log.error("信息查询异常!", e);
            throw new RuntimeException("信息查询异常!");
        }
        return result;
    }

}