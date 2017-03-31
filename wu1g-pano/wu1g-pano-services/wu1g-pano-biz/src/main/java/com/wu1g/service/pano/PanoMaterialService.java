package com.wu1g.service.pano;

import com.wu1g.api.pano.IPanoMaterialService;
import com.wu1g.dao.pano.IPanoMaterialDao;
import com.wu1g.vo.pano.PanoMaterial;
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

@Service
@Slf4j
public class PanoMaterialService extends BaseService implements IPanoMaterialService {
    @Autowired
    private IPanoMaterialDao panoMaterialDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(PanoMaterial dto) throws Exception {
        String msg = "seccuss";
        if (dto != null) {
            try {
                //判断数据是否存在
                if (dto.getId() != null && panoMaterialDao.isDataYN(dto) != 0) {
                    //数据存在
                    panoMaterialDao.update(dto);
                } else {
                    //新增
                     panoMaterialDao.insert(dto);
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new Exception("信息保存异常!");
            }
        }
        return msg;
    }

    @Override
    public String deleteData(PanoMaterial dto) throws Exception {
        String msg = "seccuss";
        if (dto != null) {
            try {
                if(panoMaterialDao.deleteByPrimaryKey(dto)==0){
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
    public String deleteDataById(PanoMaterial dto) throws Exception {
        String msg = "seccuss";
        if (dto != null) {
            try {
                if(panoMaterialDao.deleteById(dto)==0){
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
    public List<PanoMaterial> findDataIsPage(PanoMaterial dto) throws Exception {
        List<PanoMaterial> dtos = null;
        try {
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            dtos = (List<PanoMaterial>) panoMaterialDao.findDataIsPage(dto);
        } catch (Exception e) {
            log.error("信息查询异常!", e);
            throw new Exception("信息查询异常!");
        }
        return dtos;
    }

    @Override
    public List<PanoMaterial> findDataIsList(PanoMaterial dto) throws Exception {
        List<PanoMaterial> dtos = null;
        try {
            dtos = (List<PanoMaterial>) panoMaterialDao.findDataIsList(dto);
        } catch (Exception e) {
            log.error("信息查询异常!", e);
            throw new Exception("信息查询异常!");
        }
        return dtos;
    }

    @Override
    @RfAccount2Bean
    public PanoMaterial findDataById(PanoMaterial dto) throws Exception {
        PanoMaterial dto1 = null;
        try {
            dto1 = (PanoMaterial) panoMaterialDao.selectByPrimaryKey(dto);
        } catch (Exception e) {
            log.error("信息查询异常!", e);
            throw new Exception("信息查询异常!");
        }
        return dto1;
    }

}