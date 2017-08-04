package com.hsd.account.service.org;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.api.org.IOrgLogLoginService;
import com.hsd.account.dao.org.IOrgLogLoginDao;
import com.hsd.account.dto.org.OrgLogLoginDto;
import com.hsd.account.entity.org.OrgLogLogin;
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
public class OrgLogLoginService extends BaseService implements IOrgLogLoginService {
    @Autowired
    private IOrgLogLoginDao orgLogLoginDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody OrgLogLoginDto dto) throws Exception {
            Response result = new Response(0,"seccuss");
            try {
                if (dto == null) throw new RuntimeException("参数异常!");
                OrgLogLogin entity = copyTo(dto, OrgLogLogin.class);
                //判断数据是否存在
                if (orgLogLoginDao.isDataYN(entity) != 0) {
                    //数据存在
                    orgLogLoginDao.update(entity);
                } else {
                    //新增
                    orgLogLoginDao.insert(entity);
                    dto.setId(entity.getId());
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public String deleteData(@RequestBody OrgLogLoginDto dto) throws Exception {
            String result = "seccuss";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                OrgLogLogin entity = copyTo(dto, OrgLogLogin.class);
                if(orgLogLoginDao.deleteByPrimaryKey(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("物理删除异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


        @Override
        public PageInfo findDataIsPage(@RequestBody OrgLogLoginDto dto) throws Exception {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               OrgLogLogin entity = copyTo(dto, OrgLogLogin.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = orgLogLoginDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), OrgLogLoginDto.class));
           } catch (Exception e) {
               log.error("信息查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<OrgLogLoginDto> findDataIsList(@RequestBody OrgLogLoginDto dto) throws Exception {
            List<OrgLogLoginDto>  results = null;
            try {
                OrgLogLogin entity = copyTo(dto, OrgLogLogin.class);
                 results = copyTo(orgLogLoginDao.findDataIsList(entity), OrgLogLoginDto.class);
            } catch (Exception e) {
                log.error("信息查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public OrgLogLoginDto findDataById(@RequestBody OrgLogLoginDto dto) throws Exception {
            OrgLogLoginDto result = null;
            try {
                OrgLogLogin entity = copyTo(dto, OrgLogLogin.class);
                result = copyTo(orgLogLoginDao.selectByPrimaryKey(entity),OrgLogLoginDto.class);
            } catch (Exception e) {
                log.error("信息查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

}