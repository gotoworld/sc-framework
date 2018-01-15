package com.hsd.account.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.finance.api.IAccountLogFreezeService;
import com.hsd.account.finance.dao.IAccountLogFreezeDao;
import com.hsd.account.finance.dto.AccountLogFreezeDto;
import com.hsd.account.finance.entity.AccountLogFreeze;
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
public class AccountLogFreezeService extends FinanceBaseService implements IAccountLogFreezeService {
    @Autowired
    private IAccountLogFreezeDao accountLogFreezeDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody AccountLogFreezeDto dto) {
            Response result = new Response(0,"success");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                AccountLogFreeze entity = copyTo(dto, AccountLogFreeze.class);
                //判断数据是否存在
                if (accountLogFreezeDao.isDataYN(entity) != 0) {
                    //数据存在
                    accountLogFreezeDao.update(entity);
                } else {
                    //新增
                     accountLogFreezeDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }



        @Override
        public PageInfo findDataIsPage(@RequestBody AccountLogFreezeDto dto) {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               AccountLogFreeze entity = copyTo(dto, AccountLogFreeze.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = accountLogFreezeDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), AccountLogFreezeDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<AccountLogFreezeDto> findDataIsList(@RequestBody AccountLogFreezeDto dto) {
            List<AccountLogFreezeDto>  results = null;
            try {
                AccountLogFreeze entity = copyTo(dto, AccountLogFreeze.class);
                 results = copyTo(accountLogFreezeDao.findDataIsList(entity), AccountLogFreezeDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public AccountLogFreezeDto findDataById(@RequestBody AccountLogFreezeDto dto) {
            AccountLogFreezeDto result = null;
            try {
                AccountLogFreeze entity = copyTo(dto, AccountLogFreeze.class);
                result = copyTo(accountLogFreezeDao.selectByPrimaryKey(entity),AccountLogFreezeDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


}