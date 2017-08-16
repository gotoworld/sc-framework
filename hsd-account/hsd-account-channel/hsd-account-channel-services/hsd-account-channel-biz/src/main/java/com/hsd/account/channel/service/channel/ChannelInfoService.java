package com.hsd.account.channel.service.channel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.channel.api.channel.IChannelInfoService;
import com.hsd.account.channel.dao.channel.IChannelInfoDao;
import com.hsd.account.channel.dto.channel.ChannelInfoDto;
import com.hsd.account.channel.entity.channel.ChannelInfo;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;

import lombok.extern.slf4j.Slf4j;

@FeignService
@Slf4j
public class ChannelInfoService extends BaseService implements IChannelInfoService {
    @Autowired
    private IChannelInfoDao channelInfoDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody ChannelInfoDto dto) throws Exception {
            Response result = new Response(0,"seccuss");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                ChannelInfo entity = copyTo(dto, ChannelInfo.class);
                //判断数据是否存在
                if (channelInfoDao.isDataYN(entity) != 0) {
                    //数据存在
                    channelInfoDao.update(entity);
                } else {
                    //新增
                     channelInfoDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public String deleteData(@RequestBody ChannelInfoDto dto) throws Exception {
            String result = "seccuss";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                ChannelInfo entity = copyTo(dto, ChannelInfo.class);
                if(channelInfoDao.deleteByPrimaryKey(entity)==0){
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
        public String deleteDataById(@RequestBody ChannelInfoDto dto) throws Exception {
            String result = "seccuss";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                ChannelInfo entity = copyTo(dto, ChannelInfo.class);
                if(channelInfoDao.deleteById(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("逻辑删除异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public PageInfo findDataIsPage(@RequestBody ChannelInfoDto dto) throws Exception {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               ChannelInfo entity = copyTo(dto, ChannelInfo.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = channelInfoDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), ChannelInfoDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<ChannelInfoDto> findDataIsList(@RequestBody ChannelInfoDto dto) throws Exception {
            List<ChannelInfoDto>  results = null;
            try {
                ChannelInfo entity = copyTo(dto, ChannelInfo.class);
                 results = copyTo(channelInfoDao.findDataIsList(entity), ChannelInfoDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public ChannelInfoDto findDataById(@RequestBody ChannelInfoDto dto) throws Exception {
            ChannelInfoDto result = null;
            try {
                ChannelInfo entity = copyTo(dto, ChannelInfo.class);
                result = copyTo(channelInfoDao.selectByPrimaryKey(entity),ChannelInfoDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

		@Override
		public String recoveryData(@RequestBody ChannelInfoDto dto) throws Exception{
			  String result = "seccuss";
	            try {
	                if (dto == null)throw new RuntimeException("参数异常!");
	               channelInfoDao.recoveryData(copyTo(dto, ChannelInfo.class));
	            } catch (Exception e) {
	                log.error("逻辑删除异常!", e);
	                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
	            }
	            return result;
		}


}