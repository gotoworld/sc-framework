package com.hsd.service.sys;

import com.github.pagehelper.PageHelper;
import com.hsd.api.sys.ISysVariableService;
import com.hsd.dao.sys.ISysVariableDao;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.NodeTree;
import com.hsd.dto.sys.SysVariable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


/**
 * <p>数据字典 业务处理实现类。
 */
@FeignService
@Slf4j
public class SysVariableService extends BaseService implements ISysVariableService {
    /**
     * 数据字典 Dao接口类
     */
    @Autowired
    private ISysVariableDao sysVariableDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response saveOrUpdateData(@RequestBody SysVariable dto) throws Exception {
        Response result = new Response(0,"seccuss");
        if (dto != null) {
            try {
                // 判断数据是否存在
                if (sysVariableDao.isDataYN(dto) != 0) {
                    // 数据存在
                    sysVariableDao.update(dto);
                } else {
                    sysVariableDao.insert(dto);
                    result.data=dto.getId();
                }
            } catch (Exception e) {
                log.error("信息保存失败!", e);
                throw new RuntimeException("信息保存失败!");
            }
        }
        return result;
    }

    public String deleteData(@RequestBody SysVariable dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                sysVariableDao.deleteByPrimaryKey(dto);
            } catch (Exception e) {
                result = "信息删除失败!";
                log.error(result, e);
            }
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String deleteDataById(@RequestBody SysVariable dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                sysVariableDao.deleteById(dto);
            } catch (Exception e) {
                result = "信息删除失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    public List<SysVariable> findDataIsPage(@RequestBody SysVariable dto) {
        List<SysVariable> results = null;
        try {
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            results = (List<SysVariable>) sysVariableDao.findDataIsPage(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return results;
    }

    public List<SysVariable> findDataIsList(@RequestBody SysVariable dto) {
        List<SysVariable> results = null;
        try {
            results = (List<SysVariable>) sysVariableDao.findDataIsList(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return results;
    }

    public SysVariable findDataById(@RequestBody SysVariable dto) {
        SysVariable result = null;
        try {
            result = (SysVariable) sysVariableDao.selectByPrimaryKey(dto);
        } catch (Exception e) {
            log.error("信息详情查询失败!", e);
        }
        return result;
    }

    public String recoveryDataById(@RequestBody SysVariable dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                sysVariableDao.recoveryDataById(dto);
            } catch (Exception e) {
                result = "信息恢复失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    public List<SysVariable> findDataTree(@RequestBody SysVariable dto) {
        List<SysVariable> results = findDataIsList(dto);
        if (results == null) {
            return null;
        }
        NodeTree<SysVariable> tree = new NodeTree(results, "id", "parentId", "nodes");
        return tree.buildTree();
    }
}
