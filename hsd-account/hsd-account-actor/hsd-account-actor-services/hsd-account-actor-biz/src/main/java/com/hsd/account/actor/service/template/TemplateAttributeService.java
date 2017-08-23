package com.hsd.account.actor.service.template;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.actor.api.template.ITemplateAttributeService;
import com.hsd.account.actor.dao.template.ITemplateAttributeDao;
import com.hsd.account.actor.dto.template.TemplateAttributeDto;
import com.hsd.account.actor.entity.template.TemplateAttribute;
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
public class TemplateAttributeService extends BaseService implements ITemplateAttributeService {
    @Autowired
    private ITemplateAttributeDao templateAttributeDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody TemplateAttributeDto dto) throws Exception {
            Response result = new Response(0,"seccuss");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                TemplateAttribute entity = copyTo(dto, TemplateAttribute.class);
                //判断数据是否存在
                if (templateAttributeDao.isDataYN(entity) != 0) {
                    //数据存在
                    templateAttributeDao.update(entity);
                } else {
                    //新增
                     templateAttributeDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public String deleteData(@RequestBody TemplateAttributeDto dto) throws Exception {
            String result = "seccuss";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                TemplateAttribute entity = copyTo(dto, TemplateAttribute.class);
                if(templateAttributeDao.deleteByPrimaryKey(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("物理删除异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


        @Override
        public PageInfo findDataIsPage(@RequestBody TemplateAttributeDto dto) throws Exception {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               TemplateAttribute entity = copyTo(dto, TemplateAttribute.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = templateAttributeDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), TemplateAttributeDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<TemplateAttributeDto> findDataIsList(@RequestBody TemplateAttributeDto dto) throws Exception {
            List<TemplateAttributeDto>  results = null;
            try {
                TemplateAttribute entity = copyTo(dto, TemplateAttribute.class);
                 results = copyTo(templateAttributeDao.findDataIsList(entity), TemplateAttributeDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public TemplateAttributeDto findDataById(@RequestBody TemplateAttributeDto dto) throws Exception {
            TemplateAttributeDto result = null;
            try {
                TemplateAttribute entity = copyTo(dto, TemplateAttribute.class);
                result = copyTo(templateAttributeDao.selectByPrimaryKey(entity),TemplateAttributeDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


}