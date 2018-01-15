package com.hsd.account.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.finance.api.IAccountLogOperationalService;
import com.hsd.account.finance.dao.IAccountLogOperationalDao;
import com.hsd.account.finance.dto.AccountLogOperationalDto;
import com.hsd.account.finance.entity.AccountLogOperational;
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
public class AccountLogOperationalService extends FinanceBaseService implements IAccountLogOperationalService {
    @Autowired
    private IAccountLogOperationalDao accountLogOperationalDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody AccountLogOperationalDto dto) {
            Response result = new Response(0,"success");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                AccountLogOperational entity = copyTo(dto, AccountLogOperational.class);
                //判断数据是否存在
                if (accountLogOperationalDao.isDataYN(entity) != 0) {
                    //数据存在
                    accountLogOperationalDao.update(entity);
                } else {
                    //新增
                     accountLogOperationalDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }



        @Override
        public PageInfo findDataIsPage(@RequestBody AccountLogOperationalDto dto) {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               AccountLogOperational entity = copyTo(dto, AccountLogOperational.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = accountLogOperationalDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), AccountLogOperationalDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<AccountLogOperationalDto> findDataIsList(@RequestBody AccountLogOperationalDto dto) {
            List<AccountLogOperationalDto>  results = null;
            try {
                AccountLogOperational entity = copyTo(dto, AccountLogOperational.class);
                 results = copyTo(accountLogOperationalDao.findDataIsList(entity), AccountLogOperationalDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public AccountLogOperationalDto findDataById(@RequestBody AccountLogOperationalDto dto) {
            AccountLogOperationalDto result = null;
            try {
                AccountLogOperational entity = copyTo(dto, AccountLogOperational.class);
                result = copyTo(accountLogOperationalDao.selectByPrimaryKey(entity),AccountLogOperationalDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


}