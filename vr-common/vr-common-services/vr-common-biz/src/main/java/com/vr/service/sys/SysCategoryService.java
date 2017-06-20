package com.vr.service.sys;

import com.github.pagehelper.PageHelper;
import com.vr.api.sys.ISysCategoryService;
import com.vr.dao.sys.ISysCategoryDao;
import com.vr.framework.annotation.FeignService;
import com.vr.framework.annotation.RfAccount2Bean;
import com.vr.framework.service.BaseService;
import com.vr.framework.util.CommonConstant;
import com.vr.framework.util.ReflectUtil;
import com.vr.framework.util.ValidatorUtil;
import com.vr.vo.NodeTree;
import com.vr.vo.sys.SysCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@FeignService
@Slf4j
public class SysCategoryService extends BaseService implements ISysCategoryService {
    @Autowired
    private ISysCategoryDao sysCategoryDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(SysCategory dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                //判断数据是否存在
                if (dto.getId() != null && sysCategoryDao.isDataYN(dto) != 0) {
                    //数据存在
                    sysCategoryDao.update(dto);
                } else {
                    //新增
                    sysCategoryDao.insert(dto);
                }
            } catch (Exception e) {
                log.error("信息保存失败!", e);
                throw new RuntimeException("信息保存失败!");
            }
        }
        return result;
    }

    @Override
    public String deleteData(SysCategory dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                if (sysCategoryDao.deleteByPrimaryKey(dto) == 0) {
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
    public String deleteDataById(SysCategory dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                if (sysCategoryDao.deleteById(dto) == 0) {
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
    public List<SysCategory> findDataIsPage(SysCategory dto) throws Exception {
        List<SysCategory> results = null;
        try {
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            results = (List<SysCategory>) sysCategoryDao.findDataIsPage(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new RuntimeException("信息查询失败!");
        }
        return results;
    }

    @Override
    public List<SysCategory> findDataIsList(SysCategory dto) throws Exception {
        List<SysCategory> results = null;
        try {
            results = (List<SysCategory>) sysCategoryDao.findDataIsList(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new RuntimeException("信息查询失败!");
        }
        return results;
    }

    @Override
    @RfAccount2Bean
    public SysCategory findDataById(SysCategory dto) throws Exception {
        SysCategory result = null;
        try {
            result = (SysCategory) sysCategoryDao.selectByPrimaryKey(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new RuntimeException("信息查询失败!");
        }
        return result;
    }

    @Override
    public String recoveryDataById(SysCategory dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                if (sysCategoryDao.recoveryDataById(dto) == 0) {
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("数据恢复失败!", e);
                throw new RuntimeException(e.getMessage());
            }
        }
        return result;
    }

    public List<SysCategory> findDataTree(SysCategory bean) {
        try {
            List<SysCategory> results = findDataIsList(bean);
            if (results == null) {
                return null;
            }
            NodeTree<SysCategory> tree = new NodeTree<>(results,"id","parentId","nodes");
            return tree.buildTree();
        } catch (Exception e) {
        }
        return null;
    }
}
