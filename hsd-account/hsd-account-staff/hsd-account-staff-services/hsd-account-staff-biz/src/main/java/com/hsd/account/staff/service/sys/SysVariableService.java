package com.hsd.account.staff.service.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.staff.api.sys.ISysVariableService;
import com.hsd.account.staff.dao.sys.ISysVariableDao;
import com.hsd.account.staff.entity.sys.SysVariable;
import com.hsd.account.staff.dto.sys.SysVariableDto;
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
public class SysVariableService extends BaseService implements ISysVariableService {
    @Autowired
    private ISysVariableDao sysVariableDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody SysVariableDto dto) throws Exception {
            Response result = new Response(0,"seccuss");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                SysVariable entity = copyTo(dto, SysVariable.class);
                //判断数据是否存在
                if (sysVariableDao.isDataYN(entity) != 0) {
                    //数据存在
                    sysVariableDao.update(entity);
                } else {
                    //新增
                     sysVariableDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public String deleteData(@RequestBody SysVariableDto dto) throws Exception {
            String result = "seccuss";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                SysVariable entity = copyTo(dto, SysVariable.class);
                if(sysVariableDao.deleteByPrimaryKey(entity)==0){
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
        public String deleteDataById(@RequestBody SysVariableDto dto) throws Exception {
            String result = "seccuss";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                SysVariable entity = copyTo(dto, SysVariable.class);
                if(sysVariableDao.deleteById(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("逻辑删除异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public PageInfo findDataIsPage(@RequestBody SysVariableDto dto) throws Exception {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               SysVariable entity = copyTo(dto, SysVariable.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = sysVariableDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), SysVariableDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<SysVariableDto> findDataIsList(@RequestBody SysVariableDto dto) throws Exception {
            List<SysVariableDto>  results = null;
            try {
                SysVariable entity = copyTo(dto, SysVariable.class);
                 results = copyTo(sysVariableDao.findDataIsList(entity), SysVariableDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public SysVariableDto findDataById(@RequestBody SysVariableDto dto) throws Exception {
            SysVariableDto result = null;
            try {
                SysVariable entity = copyTo(dto, SysVariable.class);
                result = copyTo(sysVariableDao.selectByPrimaryKey(entity),SysVariableDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


        public List<SysVariableDto> findDataIsTree(@RequestBody(required = false) SysVariableDto dto) {
            try {
                List<SysVariableDto> results = findDataIsList(dto);
                if (results == null) {
                    return null;
                }
                NodeTree<SysVariableDto> tree = new NodeTree(results, "id", "parentId", "nodes");
                return tree.buildTree();
            } catch (Exception e) {
                log.error("信息树获取失败!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
        }
}