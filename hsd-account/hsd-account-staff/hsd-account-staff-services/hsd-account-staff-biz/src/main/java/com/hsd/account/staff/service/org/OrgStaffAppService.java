package com.hsd.account.staff.service.org;

import com.hsd.account.staff.api.org.IOrgStaffAppService;
import com.hsd.account.staff.dao.org.IOrgStaffAppDao;
import com.hsd.account.staff.dto.org.OrgStaffAppDto;
import com.hsd.account.staff.entity.org.OrgStaffApp;
import com.hsd.framework.IdGenerator;
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

@FeignService
@Slf4j
public class OrgStaffAppService extends BaseService implements IOrgStaffAppService {
    @Autowired
    private IOrgStaffAppDao orgStaffAppDao;
    @Autowired
    private IdGenerator idGenerator;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response saveOrUpdateData(@RequestBody OrgStaffAppDto dto) {
        Response result = new Response(0,"success");
        try {
            if (dto == null) throw new RuntimeException("参数异常!");
            OrgStaffApp entity = copyTo(dto, OrgStaffApp.class);
            //判断数据是否存在
            if (orgStaffAppDao.isDataYN(entity) != 0) {
                //数据存在
            } else {
                //新增
                orgStaffAppDao.insert(entity);
                result.data = entity.getId();
            }
        } catch (Exception e) {
            log.error("信息保存异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }
    @Override
    public OrgStaffAppDto getSaveDataByAppIdAndStaffId(@RequestBody OrgStaffAppDto dto) {
        OrgStaffAppDto result = null;
        try {
            OrgStaffApp entity = copyTo(dto, OrgStaffApp.class);
            result = copyTo(orgStaffAppDao.findDataByAppIdAndStaffId(entity), OrgStaffAppDto.class);
            if (result == null) {
                entity.setId(idGenerator.nextId());
                orgStaffAppDao.insert(entity);
                dto.setId(entity.getId());
                result=dto;
            }
        } catch (Exception e) {
            log.error("信息查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }


}