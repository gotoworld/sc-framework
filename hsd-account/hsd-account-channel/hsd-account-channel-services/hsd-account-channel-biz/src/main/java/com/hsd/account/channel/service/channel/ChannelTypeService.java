package com.hsd.account.channel.service.channel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.channel.api.channel.IChannelTypeService;
import com.hsd.account.channel.dao.channel.IChannelTypeDao;
import com.hsd.account.channel.dto.channel.ChannelTypeDto;
import com.hsd.account.channel.entity.channel.ChannelType;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;

import lombok.extern.slf4j.Slf4j;

@FeignService
@Slf4j
public class ChannelTypeService extends BaseService implements IChannelTypeService {
    @Autowired
    private IChannelTypeDao channelTypeDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody ChannelTypeDto dto) throws Exception {
            Response result = new Response(0,"seccuss");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                ChannelType entity = copyTo(dto, ChannelType.class);
                //判断数据是否存在
                if (channelTypeDao.isDataYN(entity) != 0) {
                    //数据存在
                    channelTypeDao.update(entity);
                } else {
                    //新增
                     channelTypeDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public String deleteData(@RequestBody ChannelTypeDto dto) throws Exception {
            String result = "seccuss";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                ChannelType entity = copyTo(dto, ChannelType.class);
                if(channelTypeDao.deleteByPrimaryKey(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("物理删除异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public String deleteDataById(@RequestBody ChannelTypeDto dto) throws Exception {
            String result = "seccuss";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                ChannelType entity = copyTo(dto, ChannelType.class);
                if(channelTypeDao.deleteById(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("逻辑删除异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public PageInfo findDataIsPage(@RequestBody ChannelTypeDto dto) throws Exception {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               ChannelType entity = copyTo(dto, ChannelType.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = channelTypeDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), ChannelTypeDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<ChannelTypeDto> findDataIsList(@RequestBody ChannelTypeDto dto) throws Exception {
            List<ChannelTypeDto>  results = null;
            try {
                ChannelType entity = copyTo(dto, ChannelType.class);
                 results = copyTo(channelTypeDao.findDataIsList(entity), ChannelTypeDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public ChannelTypeDto findDataById(@RequestBody ChannelTypeDto dto) throws Exception {
            ChannelTypeDto result = null;
            try {
                ChannelType entity = copyTo(dto, ChannelType.class);
                result = copyTo(channelTypeDao.selectByPrimaryKey(entity),ChannelTypeDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


}