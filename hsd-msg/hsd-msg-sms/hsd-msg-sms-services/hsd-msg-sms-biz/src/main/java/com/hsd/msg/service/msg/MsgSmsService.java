package com.hsd.msg.service.msg;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
import com.hsd.msg.api.msg.IMsgSmsService;
import com.hsd.msg.dao.msg.IMsgSmsDao;
import com.hsd.msg.dto.msg.MsgSmsDto;
import com.hsd.msg.entity.msg.MsgSms;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignService
@Slf4j
public class MsgSmsService extends BaseService implements IMsgSmsService {
    @Autowired
    private IMsgSmsDao msgSmsDao;

    @Override
    public PageInfo findDataIsPage(@RequestBody MsgSmsDto dto) {
        PageInfo pageInfo = null;
        try {
            if (dto == null) throw new RuntimeException("参数异常!");
            MsgSms entity = copyTo(dto, MsgSms.class);
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            List list = msgSmsDao.findDataIsPage(entity);
            pageInfo = new PageInfo(list);
            pageInfo.setList(copyTo(pageInfo.getList(), MsgSmsDto.class));
        } catch (Exception e) {
            log.error("信息[分页]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return pageInfo;
    }

    @Override
    public MsgSmsDto findDataById(@RequestBody MsgSmsDto dto) {
        MsgSmsDto result = null;
        try {
            MsgSms entity = copyTo(dto, MsgSms.class);
            result = copyTo(msgSmsDao.selectByPrimaryKey(entity), MsgSmsDto.class);
        } catch (Exception e) {
            log.error("信息[详情]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }


}