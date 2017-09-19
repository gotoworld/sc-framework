package com.hsd.util.service.msg;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
import com.hsd.util.api.msg.IMsgEmailService;
import com.hsd.util.dao.msg.IMsgEmailDao;
import com.hsd.util.dto.msg.MsgEmailDto;
import com.hsd.util.entity.msg.MsgEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignService
@Slf4j
public class MsgEmailService extends BaseService implements IMsgEmailService {
    @Autowired
    private IMsgEmailDao msgEmailDao;

    @Override
    public PageInfo findDataIsPage(@RequestBody MsgEmailDto dto) throws Exception {
        PageInfo pageInfo = null;
        try {
            if (dto == null) throw new RuntimeException("参数异常!");
            MsgEmail entity = copyTo(dto, MsgEmail.class);
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            List list = msgEmailDao.findDataIsPage(entity);
            pageInfo = new PageInfo(list);
            pageInfo.setList(copyTo(pageInfo.getList(), MsgEmailDto.class));
        } catch (Exception e) {
            log.error("信息[分页]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return pageInfo;
    }


    @Override
    public MsgEmailDto findDataById(@RequestBody MsgEmailDto dto) throws Exception {
        MsgEmailDto result = null;
        try {
            MsgEmail entity = copyTo(dto, MsgEmail.class);
            result = copyTo(msgEmailDao.selectByPrimaryKey(entity), MsgEmailDto.class);
        } catch (Exception e) {
            log.error("信息[详情]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }


}