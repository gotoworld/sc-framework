package com.hsd.account.actor.service.actor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.actor.api.actor.IMemberService;
import com.hsd.account.actor.dao.actor.IMemberDao;
import com.hsd.account.actor.dao.user.IUserExtInfoDao;
import com.hsd.account.actor.dto.actor.MemberDto;
import com.hsd.account.actor.dto.user.UserExtInfoDto;
import com.hsd.account.actor.entity.actor.Member;
import com.hsd.account.actor.entity.user.UserExtInfo;
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
public class MemberService extends BaseService implements IMemberService {
    @Autowired
    private IMemberDao memberDao;
    @Autowired
    private IUserExtInfoDao userExtInfoDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response saveOrUpdateData(@RequestBody MemberDto dto) throws Exception {
        Response result = new Response(0,"success");
        try {
            if (dto == null) throw new RuntimeException("参数异常!");
            Member entity = copyTo(dto, Member.class);
            //判断数据是否存在
            if (memberDao.isDataYN(entity) != 0) {
                if (entity.getChildrenState() == null) entity.setChildrenState(0);
                if (entity.getMaritalState() == null) entity.setMaritalState(0);
                if (entity.getDebtState() == null) entity.setDebtState(0);
                if (entity.getLenderState() == null) entity.setLenderState(0);
                if (entity.getRelation() == null) entity.setRelation(0);
                if (entity.getDomicileLiveDiff() == null) entity.setDomicileLiveDiff(0);
                //数据存在
                memberDao.update(entity);
            } else {
                //新增
                memberDao.insert(entity);
                result.data = entity.getUserId();
            }
            //清除客户扩展数据
            userExtInfoDao.deleteByUserId(entity);

            //添加客户扩展数据
            if(dto.getExtInfos()!=null && dto.getExtInfos().size()>0){
                for (UserExtInfoDto extInfoDto : dto.getExtInfos()) {
                    if(extInfoDto.getUserId()==null) extInfoDto.setUserId(entity.getUserId());
                    extInfoDto.setCreateId(dto.getCreateId());
                    userExtInfoDao.insert(copyTo(extInfoDto, UserExtInfo.class));
                }
            }

        } catch (Exception e) {
            log.error("信息保存异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }

    @Override
    public String deleteData(@RequestBody MemberDto dto) throws Exception {
        String result = "success";
        try {
            if (dto == null) throw new RuntimeException("参数异常!");
            Member entity = copyTo(dto, Member.class);
            if (memberDao.deleteByPrimaryKey(entity) == 0) {
                throw new RuntimeException("数据不存在!");
            }
        } catch (Exception e) {
            log.error("物理删除异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String deleteDataById(@RequestBody MemberDto dto) throws Exception {
        String result = "success";
        try {
            if (dto == null) throw new RuntimeException("参数异常!");
            Member entity = copyTo(dto, Member.class);
            if (memberDao.deleteById(entity) == 0) {
                throw new RuntimeException("数据不存在!");
            }
        } catch (Exception e) {
            log.error("逻辑删除异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }

    @Override
    public PageInfo findDataIsPage(@RequestBody MemberDto dto) throws Exception {
        PageInfo pageInfo = null;
        try {
            if (dto == null) throw new RuntimeException("参数异常!");
            Member entity = copyTo(dto, Member.class);
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            List list = memberDao.findDataIsPage(entity);
            pageInfo = new PageInfo(list);
            pageInfo.setList(copyTo(pageInfo.getList(), MemberDto.class));
        } catch (Exception e) {
            log.error("信息[分页]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return pageInfo;
    }

    @Override
    public List<MemberDto> findDataIsList(@RequestBody MemberDto dto) throws Exception {
        List<MemberDto> results = null;
        try {
            Member entity = copyTo(dto, Member.class);
            results = copyTo(memberDao.findDataIsList(entity), MemberDto.class);
        } catch (Exception e) {
            log.error("信息[列表]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return results;
    }

    @Override
    public MemberDto findDataById(@RequestBody MemberDto dto) throws Exception {
        MemberDto result = null;
        try {
            Member entity = copyTo(dto, Member.class);
            result = copyTo(memberDao.selectByPrimaryKey(entity), MemberDto.class);
            if(result!=null){
                UserExtInfo extInfo=new UserExtInfo(){{}};
                extInfo.setUserId(result.getUserId());
                result.setExtInfos(copyTo(userExtInfoDao.findDataIsList(extInfo),UserExtInfoDto.class));
            }
        } catch (Exception e) {
            log.error("信息[详情]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }
    @Override
    public String recoveryDataById(@RequestBody MemberDto dto) throws Exception {
        String result = "success";
        try {
            if (dto == null) throw new RuntimeException("参数异常!");
            Member entity = copyTo(dto, Member.class);
            if (memberDao.recoveryDataById(entity) == 0) {
                throw new RuntimeException("数据不存在!");
            }
        } catch (Exception e) {
            log.error("数据恢复异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }

    @Override
    public Integer isDataYN(@RequestBody MemberDto dto) throws Exception {
        Integer result = 0;
        try {
            Member entity = copyTo(dto, Member.class);
            result=memberDao.isDataYN(entity);
        } catch (Exception e) {
            log.error("信息[详情]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }
}