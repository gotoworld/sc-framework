package com.hsd.account.actor.service.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.actor.api.user.IUserSnapshotService;
import com.hsd.account.actor.dao.user.IUserSnapshotDao;
import com.hsd.account.actor.dto.user.UserSnapshotDto;
import com.hsd.account.actor.entity.user.UserSnapshot;
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
public class UserSnapshotService extends BaseService implements IUserSnapshotService {
    @Autowired
    private IUserSnapshotDao userSnapshotDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response saveOrUpdateData(@RequestBody UserSnapshotDto dto) throws Exception {
        Response result = new Response(0,"success");
        try {
            if (dto == null)throw new RuntimeException("参数异常!");
            UserSnapshot entity = copyTo(dto, UserSnapshot.class);
            //判断数据是否存在
            if (userSnapshotDao.isDataYN(entity) != 0) {
                //数据存在
                userSnapshotDao.update(entity);
            } else {
                //新增
                userSnapshotDao.insert(entity);
                result.data=entity.getUserId();
            }
        } catch (Exception e) {
            log.error("信息保存异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    @Override
    public String deleteData(@RequestBody UserSnapshotDto dto) throws Exception {
        String result = "success";
        try {
            if (dto == null)throw new RuntimeException("参数异常!");
            UserSnapshot entity = copyTo(dto, UserSnapshot.class);
            if(userSnapshotDao.deleteByPrimaryKey(entity)==0){
                throw new RuntimeException("数据不存在!");
            }
        } catch (Exception e) {
            log.error("物理删除异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }


    @Override
    public PageInfo findDataIsPage(@RequestBody UserSnapshotDto dto) throws Exception {
        PageInfo pageInfo=null;
        try {
            if (dto == null)throw new RuntimeException("参数异常!");
            UserSnapshot entity = copyTo(dto, UserSnapshot.class);
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            List list = userSnapshotDao.findDataIsPage(entity);
            pageInfo=new PageInfo(list);
            pageInfo.setList(copyTo(pageInfo.getList(), UserSnapshotDto.class));
        } catch (Exception e) {
            log.error("信息[分页]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return pageInfo;
    }

    @Override
    public List<UserSnapshotDto> findDataIsList(@RequestBody UserSnapshotDto dto) throws Exception {
        List<UserSnapshotDto>  results = null;
        try {
            UserSnapshot entity = copyTo(dto, UserSnapshot.class);
            results = copyTo(userSnapshotDao.findDataIsList(entity), UserSnapshotDto.class);
        } catch (Exception e) {
            log.error("信息[列表]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return  results;
    }

    @Override
    public UserSnapshotDto findDataById(@RequestBody UserSnapshotDto dto) throws Exception {
        UserSnapshotDto result = null;
        try {
            UserSnapshot entity = copyTo(dto, UserSnapshot.class);
            result = copyTo(userSnapshotDao.selectByPrimaryKey(entity),UserSnapshotDto.class);
        } catch (Exception e) {
            log.error("信息[详情]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }


}