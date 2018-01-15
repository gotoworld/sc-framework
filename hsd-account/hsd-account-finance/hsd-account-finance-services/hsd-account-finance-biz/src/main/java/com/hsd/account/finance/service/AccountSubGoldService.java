package com.hsd.account.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.finance.api.IAccountSubGoldService;
import com.hsd.account.finance.dao.IAccountSubGoldDao;
import com.hsd.account.finance.dto.AccountSubGoldDto;
import com.hsd.account.finance.entity.AccountSubGold;
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
public class AccountSubGoldService extends FinanceBaseService implements IAccountSubGoldService {
    @Autowired
    private IAccountSubGoldDao accountSubGoldDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody AccountSubGoldDto dto) {
            Response result = new Response(0,"success");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                AccountSubGold entity = copyTo(dto, AccountSubGold.class);
                //判断数据是否存在
                if (accountSubGoldDao.isDataYN(entity) != 0) {
                    //数据存在
                    accountSubGoldDao.update(entity);
                } else {
                    //新增
                     accountSubGoldDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }



        @Override
        public PageInfo findDataIsPage(@RequestBody AccountSubGoldDto dto) {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               AccountSubGold entity = copyTo(dto, AccountSubGold.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = accountSubGoldDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), AccountSubGoldDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<AccountSubGoldDto> findDataIsList(@RequestBody AccountSubGoldDto dto) {
            List<AccountSubGoldDto>  results = null;
            try {
                AccountSubGold entity = copyTo(dto, AccountSubGold.class);
                 results = copyTo(accountSubGoldDao.findDataIsList(entity), AccountSubGoldDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public AccountSubGoldDto findDataById(@RequestBody AccountSubGoldDto dto) {
            AccountSubGoldDto result = null;
            try {
                AccountSubGold entity = copyTo(dto, AccountSubGold.class);
                result = copyTo(accountSubGoldDao.selectByPrimaryKey(entity),AccountSubGoldDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public AccountSubGoldDto findDataByUserId(AccountSubGoldDto dto) throws Exception {
            AccountSubGoldDto result = null;
            try {
                AccountSubGold entity = copyTo(dto, AccountSubGold.class);
                result = copyTo(accountSubGoldDao.selectByUserId(entity),AccountSubGoldDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }
}