package com.hsd.account.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.finance.api.IAccountLogWithdrawalService;
import com.hsd.account.finance.dao.IAccountLogWithdrawalDao;
import com.hsd.account.finance.dto.AccountLogWithdrawalDto;
import com.hsd.account.finance.entity.AccountLogWithdrawal;
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
public class AccountLogWithdrawalService extends FinanceBaseService implements IAccountLogWithdrawalService {
    @Autowired
    private IAccountLogWithdrawalDao accountLogWithdrawalDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody AccountLogWithdrawalDto dto) {
            Response result = new Response(0,"success");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                AccountLogWithdrawal entity = copyTo(dto, AccountLogWithdrawal.class);
                //判断数据是否存在
                if (accountLogWithdrawalDao.isDataYN(entity) != 0) {
                    //数据存在
                    accountLogWithdrawalDao.update(entity);
                } else {
                    //新增
                     accountLogWithdrawalDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }



        @Override
        public PageInfo findDataIsPage(@RequestBody AccountLogWithdrawalDto dto) {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               AccountLogWithdrawal entity = copyTo(dto, AccountLogWithdrawal.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = accountLogWithdrawalDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), AccountLogWithdrawalDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<AccountLogWithdrawalDto> findDataIsList(@RequestBody AccountLogWithdrawalDto dto) {
            List<AccountLogWithdrawalDto>  results = null;
            try {
                AccountLogWithdrawal entity = copyTo(dto, AccountLogWithdrawal.class);
                 results = copyTo(accountLogWithdrawalDao.findDataIsList(entity), AccountLogWithdrawalDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public AccountLogWithdrawalDto findDataById(@RequestBody AccountLogWithdrawalDto dto) {
            AccountLogWithdrawalDto result = null;
            try {
                AccountLogWithdrawal entity = copyTo(dto, AccountLogWithdrawal.class);
                result = copyTo(accountLogWithdrawalDao.selectByPrimaryKey(entity),AccountLogWithdrawalDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


}