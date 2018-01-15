package com.hsd.account.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.finance.api.IAccountLogRechargeService;
import com.hsd.account.finance.dao.IAccountLogRechargeDao;
import com.hsd.account.finance.dto.AccountLogRechargeDto;
import com.hsd.account.finance.entity.AccountLogRecharge;
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
public class AccountLogRechargeService extends FinanceBaseService implements IAccountLogRechargeService {
    @Autowired
    private IAccountLogRechargeDao accountLogRechargeDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody AccountLogRechargeDto dto) {
            Response result = new Response(0,"success");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                AccountLogRecharge entity = copyTo(dto, AccountLogRecharge.class);
                //判断数据是否存在
                if (accountLogRechargeDao.isDataYN(entity) != 0) {
                    //数据存在
                    accountLogRechargeDao.update(entity);
                } else {
                    //新增
                     accountLogRechargeDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }



        @Override
        public PageInfo findDataIsPage(@RequestBody AccountLogRechargeDto dto) {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               AccountLogRecharge entity = copyTo(dto, AccountLogRecharge.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = accountLogRechargeDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), AccountLogRechargeDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<AccountLogRechargeDto> findDataIsList(@RequestBody AccountLogRechargeDto dto) {
            List<AccountLogRechargeDto>  results = null;
            try {
                AccountLogRecharge entity = copyTo(dto, AccountLogRecharge.class);
                 results = copyTo(accountLogRechargeDao.findDataIsList(entity), AccountLogRechargeDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public AccountLogRechargeDto findDataById(@RequestBody AccountLogRechargeDto dto) {
            AccountLogRechargeDto result = null;
            try {
                AccountLogRecharge entity = copyTo(dto, AccountLogRecharge.class);
                result = copyTo(accountLogRechargeDao.selectByPrimaryKey(entity),AccountLogRechargeDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


}