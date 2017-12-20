package com.hsd.account.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.finance.api.IAccountLogCapitalService;
import com.hsd.account.finance.dao.IAccountLogCapitalDao;
import com.hsd.account.finance.dto.AccountLogCapitalDto;
import com.hsd.account.finance.entity.AccountLogCapital;
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
public class AccountLogCapitalService extends BaseService implements IAccountLogCapitalService {
    @Autowired
    private IAccountLogCapitalDao accountLogCapitalDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody AccountLogCapitalDto dto) {
            Response result = new Response(0,"success");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                AccountLogCapital entity = copyTo(dto, AccountLogCapital.class);
                //判断数据是否存在
                if (accountLogCapitalDao.isDataYN(entity) != 0) {
                    //数据存在
                    accountLogCapitalDao.update(entity);
                } else {
                    //新增
                     accountLogCapitalDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }



        @Override
        public PageInfo findDataIsPage(@RequestBody AccountLogCapitalDto dto) {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               AccountLogCapital entity = copyTo(dto, AccountLogCapital.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = accountLogCapitalDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), AccountLogCapitalDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<AccountLogCapitalDto> findDataIsList(@RequestBody AccountLogCapitalDto dto) {
            List<AccountLogCapitalDto>  results = null;
            try {
                AccountLogCapital entity = copyTo(dto, AccountLogCapital.class);
                 results = copyTo(accountLogCapitalDao.findDataIsList(entity), AccountLogCapitalDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public AccountLogCapitalDto findDataById(@RequestBody AccountLogCapitalDto dto) {
            AccountLogCapitalDto result = null;
            try {
                AccountLogCapital entity = copyTo(dto, AccountLogCapital.class);
                result = copyTo(accountLogCapitalDao.selectByPrimaryKey(entity),AccountLogCapitalDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


}