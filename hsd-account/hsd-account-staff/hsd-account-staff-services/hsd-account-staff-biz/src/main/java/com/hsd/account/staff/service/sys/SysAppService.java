package com.hsd.account.staff.service.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.staff.api.sys.ISysAppService;
import com.hsd.account.staff.dao.sys.ISysAppDao;
import com.hsd.account.staff.dto.sys.SysAppDto;
import com.hsd.account.staff.entity.sys.SysApp;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignService
@Slf4j
public class SysAppService extends BaseService implements ISysAppService {
    @Autowired
    private ISysAppDao sysAppDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody SysAppDto dto) throws Exception {
            Response result = new Response(0,"success");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                SysApp entity = copyTo(dto, SysApp.class);
                //判断数据是否存在
                if (sysAppDao.isDataYN(entity) != 0) {
                    //数据存在
                    sysAppDao.update(entity);
                } else {
                    //新增

                     entity.setId(IdUtil.createUUID(22));
                     sysAppDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public String deleteData(@RequestBody SysAppDto dto) throws Exception {
            String result = "success";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                SysApp entity = copyTo(dto, SysApp.class);
                if(sysAppDao.deleteByPrimaryKey(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("物理删除异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


        @Override
        public PageInfo findDataIsPage(@RequestBody SysAppDto dto) throws Exception {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               SysApp entity = copyTo(dto, SysApp.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = sysAppDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), SysAppDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<SysAppDto> findDataIsList(@RequestBody SysAppDto dto) throws Exception {
            List<SysAppDto>  results = null;
            try {
                SysApp entity = copyTo(dto, SysApp.class);
                 results = copyTo(sysAppDao.findDataIsList(entity), SysAppDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public SysAppDto findDataById(@RequestBody SysAppDto dto) throws Exception {
            SysAppDto result = null;
            try {
                SysApp entity = copyTo(dto, SysApp.class);
                result = copyTo(sysAppDao.selectByPrimaryKey(entity),SysAppDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


}