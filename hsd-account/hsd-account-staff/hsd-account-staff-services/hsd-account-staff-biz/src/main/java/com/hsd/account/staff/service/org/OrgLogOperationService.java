package com.hsd.account.staff.service.org;

import com.github.pagehelper.PageHelper;
import com.hsd.account.staff.api.org.IOrgLogOperationService;
import com.hsd.account.staff.dao.org.IOrgLogOperationDao;
import com.hsd.account.staff.dto.org.OrgLogOperationDto;
import com.hsd.account.staff.entity.org.OrgLogOperation;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignService
@Slf4j
public class OrgLogOperationService extends BaseService implements IOrgLogOperationService {

    @Autowired
    private IOrgLogOperationDao logOperationDao;

    public List<OrgLogOperationDto> findDataIsPage(@RequestBody OrgLogOperationDto dto) {
        List<OrgLogOperationDto> results = null;
        try {
            PageHelper.startPage(PN(dto.getPageNum()), PS( dto.getPageSize()));
            results = copyTo(logOperationDao.findDataIsPage(copyTo(dto,OrgLogOperation.class)),OrgLogOperationDto.class);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }
    public void info(@RequestParam("type") String type,
                     @RequestParam("memo")String memo,
                     @RequestParam("domainCode")String domainCode,
                     @RequestParam("detailInfo")String detailInfo,
                     @RequestParam("userId")Long userId,
                     @RequestParam("userName")String userName,
                     @RequestParam("ip")String ip) {
        try {
            OrgLogOperation entity = new OrgLogOperation();
            entity.setType(type);// 操作类型(a增d删u改q查)
            entity.setMemo(memo);// 描述
            entity.setDomainCode(domainCode);// 所属系统域
            entity.setDetailInfo(detailInfo);// 具体
            entity.setCreateId(userId);// 操作人id
            entity.setCreateName(userName);// 操作人姓名
            entity.setCreateIp(ip);// 建立者IP
            // 新增
            logOperationDao.insert(entity);
        } catch (Exception e) {
            log.error("操作日志信息保存失败!", e);
        }
    }
}