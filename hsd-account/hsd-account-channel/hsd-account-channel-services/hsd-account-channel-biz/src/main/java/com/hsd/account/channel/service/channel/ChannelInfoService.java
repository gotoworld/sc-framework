package com.hsd.account.channel.service.channel;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.channel.api.channel.IChannelInfoService;
import com.hsd.account.channel.dao.channel.IChannelInfoDao;
import com.hsd.account.channel.dto.channel.ChannelInfoDto;
import com.hsd.account.channel.entity.channel.ChannelInfo;
import com.hsd.common.util.excel.ExcelUtil;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.config.AppConfig;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.security.MD5;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.JwtUtil;
import com.hsd.framework.util.StrUtil;
import com.hsd.framework.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FeignService
@Slf4j
public class ChannelInfoService extends BaseService implements IChannelInfoService {
    @Autowired
    private IChannelInfoDao channelInfoDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody ChannelInfoDto dto) throws Exception {
            Response result = new Response(0,"success");
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
            String result = "success";
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
            String result = "success";
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
			  String result = "success";
	            try {
	                if (dto == null)throw new RuntimeException("参数异常!");
	               channelInfoDao.recoveryData(copyTo(dto, ChannelInfo.class));
	            } catch (Exception e) {
	                log.error("逻辑删除异常!", e);
	                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
	            }
	            return result;
		}

		@Override
		public String resetPwd(@RequestBody ChannelInfoDto dto) throws Exception {
			String result = "success";
			try {
				if (dto == null)throw new RuntimeException("参数异常!");
				dto.setPwd("7fb4771b47dda67c83e499cc42d01707");
				channelInfoDao.resetPwd(copyTo(dto,ChannelInfo.class));
			} catch (Exception e) {
				 log.error("重置密码异常!", e);
	             throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
			}
			return result;
		}

		@Override
		public ChannelInfoDto findUserByAccount(@RequestParam("account") String account){
			try {
	            Map dto = new HashMap();
	            dto.put("account", account);
	            return copyTo(channelInfoDao.findUserByAccount(dto),ChannelInfoDto.class);
	        } catch (Exception e) {
	            log.error("用户信息>根据用户登录名,数据库处理异常!", e);
	        }
	        return null;
		}

		@Override
		public Response modifyPwd(@RequestBody ChannelInfoDto dto) throws Exception {
			Response result = new Response(0,"success");
			try {
				if (dto == null)throw new RuntimeException("参数异常!");
				ChannelInfo entity = copyTo(dto, ChannelInfo.class);
				channelInfoDao.update(entity);
			} catch (Exception e) {
				 log.error("信息修改异常!", e);
	             throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
			}
			return result;
		}

		@RequiresPermissions("channelInfo:edit:batch")
	    public Response addBatch(@RequestParam(name = "fileUrl") String fileUrl) throws Exception {
	        Response result = new Response(0,"success");
	        try {
	            if (fileUrl == null) throw new RuntimeException("文件路径不存在!");
	            Map<String,List> map= ExcelUtil.readExcelIsList(StrUtil.replaceAll(fileUrl, AppConfig.getProperty("common.fileServer.download"),AppConfig.getProperty("common.fileServer.upload")),true);
	            if(map==null)  throw new RuntimeException("excel读取失败!");
	            List titles=map.get("titles");
	            List datas=map.get("datas");
	            log.info("excel->title->"+titles);
	            Long createId=JwtUtil.getSubject().getLong("id");
	           final StringBuffer finalMessage = new StringBuffer("");
	            if(datas!=null) {
	                List<ChannelInfo> channelList=new ArrayList<>();
	                for (int i = 0; i < datas.size(); i++) {
	                    List data= (List) datas.get(i);
	                    ChannelInfo channel=new ChannelInfo();
	                    if(ValidatorUtil.isEmpty(data.get(0))){
	                        finalMessage.append("<br/>空行:"+(i+1));
	                        continue;
	                    }
	                    channel.setAccount((String) data.get(0));
	                    channel.setPwd(MD5.pwdMd5Hex(MD5.md5Hex((String) data.get(1))) );
	                    channel.setChannelName((String) data.get(2));
	                    channel.setGender(getGender((String) data.get(3)));
	                    channel.setPhone1((String) data.get(4));
	                    channel.setRelation(getRelation((String) data.get(5)));
	                    channel.setStaus(getstatus((String) data.get(6)));
	                    channel.setRemark("批量导入");
	                    channelList.add(channel);
	                    if((i+1)%100==0||(i+1)==datas.size()){
	                        try {
	                            channelInfoDao.insertBatch(channelList);
	                        } catch (Exception e) {
	                        	channelList.forEach(ou -> {
	                                try {
	                                	channelInfoDao.insert(ou);
	                                } catch (Exception e1) {
	                                    String msg=""+e1.getMessage();
	                                    int indexOf=e1.getMessage().indexOf("for key");
	                                    finalMessage.append("<br/>异常:"+(indexOf!=-1?msg.substring(0,indexOf):msg));
	                                    finalMessage.append("<br/>==>"+ou.getAccount()+","+ou.getChannelName()+","+ou.getPhone1());
	                                }
	                            });
	                        }
	                        channelList=new ArrayList<>();
	                    }
	                }
	            }
	            result.message=finalMessage.toString();
	        } catch (Exception e) {
	            log.error("信息保存失败!", e);
	            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
	        }
	        return result;
	    }

	    private int getGender(String gender){
	        int val=3;
	        switch (gender){
	            case "男":val=0;break;
	            case "女":val=1;break;
	        }
	        return val;
	    }
	    private int getRelation(String Relation){
	        int val=0;
	        switch (Relation){
	            case "否":val=1;break;
	        }
	        return val;
	    }
	    private int getstatus(String status){
	        int val=0;
	        switch (status){
	            case "启用":val=1;break;
	        }
	        return val;
	    }

	    @Override
		public Response updataChannel(@RequestBody ChannelInfoDto dto) throws Exception {
			Response result = new Response(0,"success");
			try {
				if (dto == null)throw new RuntimeException("参数异常!");
				ChannelInfo entity = copyTo(dto, ChannelInfo.class);
				channelInfoDao.update(entity);
			} catch (Exception e) {
				 log.error("信息修改异常!", e);
	             throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
			}
			return result;
		}

}