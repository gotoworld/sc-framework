package com.hsd.account.staff.service.org;

import com.github.pagehelper.PageHelper;
import com.hsd.account.staff.api.org.IOrgInfoService;
import com.hsd.account.staff.dto.org.OrgInfoDto;
import com.hsd.account.staff.dao.org.IOrgInfoDao;
import com.hsd.account.staff.entity.org.OrgInfo;
import com.hsd.framework.NodeTree;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignService
@Slf4j
public class OrgInfoService extends BaseService implements IOrgInfoService {
    @Autowired
    private IOrgInfoDao orgInfoDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public Response saveOrUpdateData(@RequestBody OrgInfoDto dto) throws Exception {
        Response result = new Response(0,"seccuss");
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            OrgInfo entity=copyTo(dto,OrgInfo.class);
            // 判断数据是否存在
            if (orgInfoDao.isDataYN(entity) != 0) {
                // 数据存在
                orgInfoDao.update(entity);
            } else {
                // 新增
                orgInfoDao.insert(entity);
                result.data=entity.getId();
            }
        } catch (Exception e) {
            log.error("信息保存失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    @RfAccount2Bean
    public String deleteData(@RequestBody OrgInfoDto dto) throws Exception {
        String result = "seccuss";
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            orgInfoDao.deleteByPrimaryKey(copyTo(dto,OrgInfo.class));
        } catch (Exception e) {
            log.error("信息删除失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public String deleteDataById(@RequestBody OrgInfoDto dto) throws Exception {
        String result = "seccuss";
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            orgInfoDao.deleteById(copyTo(dto,OrgInfo.class));
        } catch (Exception e) {
            log.error("信息删除失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    public List<OrgInfoDto> findDataIsPage(@RequestBody OrgInfoDto dto) {
        List<OrgInfoDto> results = null;
        try {
            PageHelper.startPage(PN(dto.getPageNum()), PS( dto.getPageSize()));
            results = copyTo(orgInfoDao.findDataIsPage(copyTo(dto,OrgInfo.class)),OrgInfoDto.class);
        } catch (Exception e) {
            log.error("信息分页获取失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }

    public List<OrgInfoDto> findDataIsList(@RequestBody OrgInfoDto dto) {
        List<OrgInfoDto> results = null;
        try {
            results = copyTo(orgInfoDao.findDataIsList(copyTo(dto,OrgInfo.class)),OrgInfoDto.class);
        } catch (Exception e) {
            log.error("信息列表获取失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }

    public OrgInfoDto findDataById(@RequestBody OrgInfoDto dto) {
        OrgInfoDto result = null;
        try {
            result = copyTo(orgInfoDao.selectByPrimaryKey(copyTo(dto,OrgInfo.class)),OrgInfoDto.class);
        } catch (Exception e) {
            log.error("信息详情获取失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    public String recoveryDataById(@RequestBody OrgInfoDto dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                orgInfoDao.recoveryDataById(copyTo(dto,OrgInfo.class));
            } catch (Exception e) {
                log.error("信息恢复失败!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
        }
        return result;
    }

    public List<OrgInfoDto> findDataIsTree(@RequestBody(required = false) OrgInfoDto dto) {
        try {
            List<OrgInfoDto> results = findDataIsList(dto);
            if (results == null) {
                return null;
            }
            NodeTree<OrgInfoDto> tree = new NodeTree(results, "id", "parentId", "nodes");
            return tree.buildTree();
        } catch (Exception e) {
            log.error("信息树获取失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
    }
}