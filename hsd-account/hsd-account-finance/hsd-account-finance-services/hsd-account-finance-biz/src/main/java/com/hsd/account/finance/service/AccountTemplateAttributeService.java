package com.hsd.account.finance.service;

import com.hsd.account.finance.api.IAccountTemplateAttributeService;
import com.hsd.account.finance.dao.IAccountTemplateAttributeDao;
import com.hsd.account.finance.dto.AccountTemplateAttributeDto;
import com.hsd.account.finance.entity.AccountTemplateAttribute;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignService
@Slf4j
public class AccountTemplateAttributeService extends FinanceBaseService implements IAccountTemplateAttributeService {
    @Autowired
    private IAccountTemplateAttributeDao accountTemplateAttributeDao;

    @Override
    public List<AccountTemplateAttributeDto> findDataIsList(@RequestBody AccountTemplateAttributeDto dto) {
        List<AccountTemplateAttributeDto> results = null;
        try {
            AccountTemplateAttribute entity = copyTo(dto, AccountTemplateAttribute.class);
            results = copyTo(accountTemplateAttributeDao.findDataIsList(entity), AccountTemplateAttributeDto.class);
        } catch (Exception e) {
            log.error("信息[列表]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return results;
    }

}