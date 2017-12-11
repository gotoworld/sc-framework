package com.hsd.account.staff.service.org;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.staff.api.org.IOrgLogOperationService;
import com.hsd.account.staff.dao.org.IOrgLogOperationDao;
import com.hsd.account.staff.dto.org.OrgLogOperationDto;
import com.hsd.account.staff.entity.org.OrgLogOperation;
import com.hsd.framework.IdGenerator;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignService
@Slf4j
public class OrgLogOperationService extends BaseService implements IOrgLogOperationService {

    @Autowired
    private IOrgLogOperationDao logOperationDao;
    @Autowired
    private IdGenerator idGenerator;

    @Override
    public PageInfo findDataIsPage(@RequestBody OrgLogOperationDto dto) {
        PageInfo pageInfo=null;
        try {
            if (dto == null)throw new RuntimeException("参数异常!");
            OrgLogOperation entity = copyTo(dto, OrgLogOperation.class);
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            List list = logOperationDao.findDataIsPage(entity);
            pageInfo=new PageInfo(list);
            pageInfo.setList(copyTo(pageInfo.getList(), OrgLogOperationDto.class));
        } catch (Exception e) {
            log.error("信息[分页]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return pageInfo;
    }
    public void log(@RequestBody Map<String,Object> map) {
        try {
            OrgLogOperation orgLogOperation= new OrgLogOperation(){{
                setId(idGenerator.nextId());
                setType((String)map.get("type"));// 操作类型a增d删u改q查)
                setMemo((String)map.get("memo"));// 描述
                setAppId((String)map.get("appId"));// 所属系统域
                setAppName((String)map.get("appName"));// 所属系统域
                setDetailInfo((String)map.get("detailInfo"));// 具体
                setCreateId(Long.parseLong(""+map.get("createId")));// 操作人id
                setCreateName((String)map.get("createName"));// 操作人姓名
                setCreateIp((String)map.get("createIp"));// 建立者IP
            }};
            // 新增
            logOperationDao.insert(orgLogOperation);
        } catch (Exception e) {
            log.error("操作日志信息保存失败!", e);
        }
    }
    @Override
    public OrgLogOperationDto findDataById(@RequestBody OrgLogOperationDto dto) {
        OrgLogOperationDto result = null;
        try {
            OrgLogOperation entity = copyTo(dto, OrgLogOperation.class);
            result = copyTo(logOperationDao.selectByPrimaryKey(entity),OrgLogOperationDto.class);
        } catch (Exception e) {
            log.error("信息[详情]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
}