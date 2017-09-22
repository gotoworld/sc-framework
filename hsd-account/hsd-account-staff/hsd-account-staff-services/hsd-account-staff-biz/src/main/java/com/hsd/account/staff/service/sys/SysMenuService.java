package com.hsd.account.staff.service.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.staff.api.sys.ISysMenuService;
import com.hsd.account.staff.dao.sys.ISysMenuDao;
import com.hsd.account.staff.dto.sys.SysMenuDto;
import com.hsd.account.staff.entity.sys.SysMenu;
import com.hsd.framework.NodeTree;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
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
public class SysMenuService extends BaseService implements ISysMenuService {
    @Autowired
    private ISysMenuDao sysMenuDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response saveOrUpdateData(@RequestBody SysMenuDto dto) throws Exception {
        Response result = new Response(0,"success");
        try {
            if (dto == null)throw new RuntimeException("参数异常!");
            SysMenu entity = copyTo(dto, SysMenu.class);
            //判断数据是否存在
            if (sysMenuDao.isDataYN(entity) != 0) {
                //数据存在
                sysMenuDao.update(entity);
            } else {
                //新增
                sysMenuDao.insert(entity);
                result.data=entity.getId();
            }
        } catch (Exception e) {
            log.error("信息保存异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    @Override
    public String deleteData(@RequestBody SysMenuDto dto) throws Exception {
        String result = "success";
        try {
            if (dto == null)throw new RuntimeException("参数异常!");
            SysMenu entity = copyTo(dto, SysMenu.class);
            if(sysMenuDao.deleteByPrimaryKey(entity)==0){
                throw new RuntimeException("数据不存在!");
            }
        } catch (Exception e) {
            log.error("物理删除异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String deleteDataById(@RequestBody SysMenuDto dto) throws Exception {
        String result = "success";
        try {
            if (dto == null)throw new RuntimeException("参数异常!");
            SysMenu entity = copyTo(dto, SysMenu.class);
            if(sysMenuDao.deleteById(entity)==0){
                throw new RuntimeException("数据不存在!");
            }
        } catch (Exception e) {
            log.error("逻辑删除异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    @Override
    public PageInfo findDataIsPage(@RequestBody SysMenuDto dto) throws Exception {
        PageInfo pageInfo=null;
        try {
            if (dto == null)throw new RuntimeException("参数异常!");
            SysMenu entity = copyTo(dto, SysMenu.class);
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            List list = sysMenuDao.findDataIsPage(entity);
            pageInfo=new PageInfo(list);
            pageInfo.setList(copyTo(pageInfo.getList(), SysMenuDto.class));
        } catch (Exception e) {
            log.error("信息[分页]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return pageInfo;
    }

    @Override
    public List<SysMenuDto> findDataIsList(@RequestBody SysMenuDto dto) throws Exception {
        List<SysMenuDto>  results = null;
        try {
            SysMenu entity = copyTo(dto, SysMenu.class);
            results = copyTo(sysMenuDao.findDataIsList(entity), SysMenuDto.class);
        } catch (Exception e) {
            log.error("信息[列表]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return  results;
    }

    @Override
    public SysMenuDto findDataById(@RequestBody SysMenuDto dto) throws Exception {
        SysMenuDto result = null;
        try {
            SysMenu entity = copyTo(dto, SysMenu.class);
            result = copyTo(sysMenuDao.selectByPrimaryKey(entity),SysMenuDto.class);
        } catch (Exception e) {
            log.error("信息[详情]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }


    public List<SysMenuDto> findDataIsTree(@RequestBody(required = false) SysMenuDto dto) {
        try {
            List<SysMenuDto> results = findDataIsList(dto);
            if (results == null) {
                return null;
            }
            NodeTree<SysMenuDto> tree = new NodeTree(results, "id", "parentId", "nodes");
            return tree.buildTree();
        } catch (Exception e) {
            log.error("信息树获取失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
    }
}