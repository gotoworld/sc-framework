package com.hsd.account.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.finance.api.IAccountBindThirdpartyService;
import com.hsd.account.finance.dao.IAccountBindThirdpartyDao;
import com.hsd.account.finance.dto.AccountBindThirdpartyDto;
import com.hsd.account.finance.entity.AccountBindThirdparty;
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
public class AccountBindThirdpartyService extends BaseService implements IAccountBindThirdpartyService {
    @Autowired
    private IAccountBindThirdpartyDao accountBindThirdpartyDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody AccountBindThirdpartyDto dto) {
            Response result = new Response(0,"success");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                AccountBindThirdparty entity = copyTo(dto, AccountBindThirdparty.class);
                //判断数据是否存在
                if (accountBindThirdpartyDao.isDataYN(entity) != 0) {
                    //数据存在
                    accountBindThirdpartyDao.update(entity);
                } else {
                    //新增
                     accountBindThirdpartyDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }



        @Override
        public PageInfo findDataIsPage(@RequestBody AccountBindThirdpartyDto dto) {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               AccountBindThirdparty entity = copyTo(dto, AccountBindThirdparty.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = accountBindThirdpartyDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), AccountBindThirdpartyDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<AccountBindThirdpartyDto> findDataIsList(@RequestBody AccountBindThirdpartyDto dto) {
            List<AccountBindThirdpartyDto>  results = null;
            try {
                AccountBindThirdparty entity = copyTo(dto, AccountBindThirdparty.class);
                 results = copyTo(accountBindThirdpartyDao.findDataIsList(entity), AccountBindThirdpartyDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public AccountBindThirdpartyDto findDataById(@RequestBody AccountBindThirdpartyDto dto) {
            AccountBindThirdpartyDto result = null;
            try {
                AccountBindThirdparty entity = copyTo(dto, AccountBindThirdparty.class);
                result = copyTo(accountBindThirdpartyDao.selectByPrimaryKey(entity),AccountBindThirdpartyDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


}