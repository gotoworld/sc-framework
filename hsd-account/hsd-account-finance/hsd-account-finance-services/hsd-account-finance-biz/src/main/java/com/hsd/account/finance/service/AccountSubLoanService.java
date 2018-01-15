package com.hsd.account.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.finance.api.IAccountSubLoanService;
import com.hsd.account.finance.dao.IAccountSubLoanDao;
import com.hsd.account.finance.dto.AccountSubLoanDto;
import com.hsd.account.finance.entity.AccountSubLoan;
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
public class AccountSubLoanService extends FinanceBaseService implements IAccountSubLoanService {
    @Autowired
    private IAccountSubLoanDao accountSubLoanDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody AccountSubLoanDto dto) {
            Response result = new Response(0,"success");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                AccountSubLoan entity = copyTo(dto, AccountSubLoan.class);
                //判断数据是否存在
                if (accountSubLoanDao.isDataYN(entity) != 0) {
                    //数据存在
                    accountSubLoanDao.update(entity);
                } else {
                    //新增
                     accountSubLoanDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }



        @Override
        public PageInfo findDataIsPage(@RequestBody AccountSubLoanDto dto) {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               AccountSubLoan entity = copyTo(dto, AccountSubLoan.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = accountSubLoanDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), AccountSubLoanDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<AccountSubLoanDto> findDataIsList(@RequestBody AccountSubLoanDto dto) {
            List<AccountSubLoanDto>  results = null;
            try {
                AccountSubLoan entity = copyTo(dto, AccountSubLoan.class);
                 results = copyTo(accountSubLoanDao.findDataIsList(entity), AccountSubLoanDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public AccountSubLoanDto findDataById(@RequestBody AccountSubLoanDto dto) {
            AccountSubLoanDto result = null;
            try {
                AccountSubLoan entity = copyTo(dto, AccountSubLoan.class);
                result = copyTo(accountSubLoanDao.selectByPrimaryKey(entity),AccountSubLoanDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public AccountSubLoanDto findDataByUserId(AccountSubLoanDto dto) throws Exception {
            AccountSubLoanDto result = null;
            try {
                AccountSubLoan entity = copyTo(dto, AccountSubLoan.class);
                result = copyTo(accountSubLoanDao.selectByUserId(entity),AccountSubLoanDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }
}