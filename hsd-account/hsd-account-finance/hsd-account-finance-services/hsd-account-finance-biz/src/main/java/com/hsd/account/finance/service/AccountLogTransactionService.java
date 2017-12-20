package com.hsd.account.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.finance.api.IAccountLogTransactionService;
import com.hsd.account.finance.dao.IAccountLogTransactionDao;
import com.hsd.account.finance.dto.AccountLogTransactionDto;
import com.hsd.account.finance.entity.AccountLogTransaction;
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
public class AccountLogTransactionService extends BaseService implements IAccountLogTransactionService {
    @Autowired
    private IAccountLogTransactionDao accountLogTransactionDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody AccountLogTransactionDto dto) {
            Response result = new Response(0,"success");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                AccountLogTransaction entity = copyTo(dto, AccountLogTransaction.class);
                //判断数据是否存在
                if (accountLogTransactionDao.isDataYN(entity) != 0) {
                    //数据存在
                    accountLogTransactionDao.update(entity);
                } else {
                    //新增
                     accountLogTransactionDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }



        @Override
        public PageInfo findDataIsPage(@RequestBody AccountLogTransactionDto dto) {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               AccountLogTransaction entity = copyTo(dto, AccountLogTransaction.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = accountLogTransactionDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), AccountLogTransactionDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<AccountLogTransactionDto> findDataIsList(@RequestBody AccountLogTransactionDto dto) {
            List<AccountLogTransactionDto>  results = null;
            try {
                AccountLogTransaction entity = copyTo(dto, AccountLogTransaction.class);
                 results = copyTo(accountLogTransactionDao.findDataIsList(entity), AccountLogTransactionDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public AccountLogTransactionDto findDataById(@RequestBody AccountLogTransactionDto dto) {
            AccountLogTransactionDto result = null;
            try {
                AccountLogTransaction entity = copyTo(dto, AccountLogTransaction.class);
                result = copyTo(accountLogTransactionDao.selectByPrimaryKey(entity),AccountLogTransactionDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


}