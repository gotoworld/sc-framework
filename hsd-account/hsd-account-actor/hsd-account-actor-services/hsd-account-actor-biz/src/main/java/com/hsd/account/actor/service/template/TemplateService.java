package com.hsd.account.actor.service.template;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.actor.api.template.ITemplateService;
import com.hsd.account.actor.dao.template.ITemplateDao;
import com.hsd.account.actor.dto.template.TemplateDto;
import com.hsd.account.actor.entity.template.Template;
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
public class TemplateService extends BaseService implements ITemplateService {
    @Autowired
    private ITemplateDao templateDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody TemplateDto dto) throws Exception {
            Response result = new Response(0,"seccuss");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                Template entity = copyTo(dto, Template.class);
                //判断数据是否存在
                if (templateDao.isDataYN(entity) != 0) {
                    //数据存在
                    templateDao.update(entity);
                } else {
                    //新增
                     templateDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public String deleteData(@RequestBody TemplateDto dto) throws Exception {
            String result = "seccuss";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                Template entity = copyTo(dto, Template.class);
                if(templateDao.deleteByPrimaryKey(entity)==0){
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
        public String deleteDataById(@RequestBody TemplateDto dto) throws Exception {
            String result = "seccuss";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                Template entity = copyTo(dto, Template.class);
                if(templateDao.deleteById(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("逻辑删除异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public PageInfo findDataIsPage(@RequestBody TemplateDto dto) throws Exception {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               Template entity = copyTo(dto, Template.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = templateDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), TemplateDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<TemplateDto> findDataIsList(@RequestBody TemplateDto dto) throws Exception {
            List<TemplateDto>  results = null;
            try {
                Template entity = copyTo(dto, Template.class);
                 results = copyTo(templateDao.findDataIsList(entity), TemplateDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public TemplateDto findDataById(@RequestBody TemplateDto dto) throws Exception {
            TemplateDto result = null;
            try {
                Template entity = copyTo(dto, Template.class);
                result = copyTo(templateDao.selectByPrimaryKey(entity),TemplateDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


}