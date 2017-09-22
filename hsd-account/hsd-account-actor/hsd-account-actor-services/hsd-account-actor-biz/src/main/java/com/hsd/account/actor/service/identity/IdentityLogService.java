package com.hsd.account.actor.service.identity;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.actor.api.identity.IIdentityLogService;
import com.hsd.account.actor.dao.identity.IIdentityLogDao;
import com.hsd.account.actor.dto.identity.IdentityLogDto;
import com.hsd.account.actor.entity.identity.IdentityLog;
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
public class IdentityLogService extends BaseService implements IIdentityLogService {
    @Autowired
    private IIdentityLogDao identityLogDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response saveOrUpdateData(@RequestBody IdentityLogDto dto) throws Exception {
        Response result = new Response(0,"success");
        try {
            if (dto == null)throw new RuntimeException("参数异常!");
            IdentityLog entity = copyTo(dto, IdentityLog.class);
            //判断数据是否存在
            if (identityLogDao.isDataYN(entity) != 0) {
                //数据存在
                identityLogDao.update(entity);
            } else {
                //新增
                identityLogDao.insert(entity);
                result.data=entity.getId();
            }
        } catch (Exception e) {
            log.error("信息保存异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    @Override
    public String deleteData(@RequestBody IdentityLogDto dto) throws Exception {
        String result = "success";
        try {
            if (dto == null)throw new RuntimeException("参数异常!");
            IdentityLog entity = copyTo(dto, IdentityLog.class);
            if(identityLogDao.deleteByPrimaryKey(entity)==0){
                throw new RuntimeException("数据不存在!");
            }
        } catch (Exception e) {
            log.error("物理删除异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }


    @Override
    public PageInfo findDataIsPage(@RequestBody IdentityLogDto dto) throws Exception {
        PageInfo pageInfo=null;
        try {
            if (dto == null)throw new RuntimeException("参数异常!");
            IdentityLog entity = copyTo(dto, IdentityLog.class);
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            List list = identityLogDao.findDataIsPage(entity);
            pageInfo=new PageInfo(list);
            pageInfo.setList(copyTo(pageInfo.getList(), IdentityLogDto.class));
        } catch (Exception e) {
            log.error("信息[分页]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return pageInfo;
    }

    @Override
    public List<IdentityLogDto> findDataIsList(@RequestBody IdentityLogDto dto) throws Exception {
        List<IdentityLogDto>  results = null;
        try {
            IdentityLog entity = copyTo(dto, IdentityLog.class);
            results = copyTo(identityLogDao.findDataIsList(entity), IdentityLogDto.class);
        } catch (Exception e) {
            log.error("信息[列表]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return  results;
    }

    @Override
    public IdentityLogDto findDataById(@RequestBody IdentityLogDto dto) throws Exception {
        IdentityLogDto result = null;
        try {
            IdentityLog entity = copyTo(dto, IdentityLog.class);
            result = copyTo(identityLogDao.selectByPrimaryKey(entity),IdentityLogDto.class);
        } catch (Exception e) {
            log.error("信息[详情]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }


}