package com.hsd.account.actor.service.identity;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.actor.api.identity.IIdentityService;
import com.hsd.account.actor.dao.identity.IIdentityDao;
import com.hsd.account.actor.dto.identity.IdentityDto;
import com.hsd.account.actor.entity.identity.Identity;
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
public class IdentityService extends BaseService implements IIdentityService {
    @Autowired
    private IIdentityDao identityDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody IdentityDto dto) throws Exception {
            Response result = new Response(0,"seccuss");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                Identity entity = copyTo(dto, Identity.class);
                //判断数据是否存在
                if (identityDao.isDataYN(entity) != 0) {
                    //数据存在
                    identityDao.update(entity);
                } else {
                    //新增
                     identityDao.insert(entity);
                     result.data=entity.getUserId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public String deleteData(@RequestBody IdentityDto dto) throws Exception {
            String result = "seccuss";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                Identity entity = copyTo(dto, Identity.class);
                if(identityDao.deleteByPrimaryKey(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("物理删除异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


        @Override
        public PageInfo findDataIsPage(@RequestBody IdentityDto dto) throws Exception {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               Identity entity = copyTo(dto, Identity.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = identityDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), IdentityDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<IdentityDto> findDataIsList(@RequestBody IdentityDto dto) throws Exception {
            List<IdentityDto>  results = null;
            try {
                Identity entity = copyTo(dto, Identity.class);
                 results = copyTo(identityDao.findDataIsList(entity), IdentityDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public IdentityDto findDataById(@RequestBody IdentityDto dto) throws Exception {
            IdentityDto result = null;
            try {
                Identity entity = copyTo(dto, Identity.class);
                result = copyTo(identityDao.selectByPrimaryKey(entity),IdentityDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }
        @Override
        public Integer isUserInentityYN(@RequestBody IdentityDto dto) throws Exception {
            Integer result = 0;
            try {
                Identity entity = copyTo(dto, Identity.class);
                result = identityDao.isDataYN(entity);
            } catch (Exception e) {
                log.error("实名认证检查异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


}