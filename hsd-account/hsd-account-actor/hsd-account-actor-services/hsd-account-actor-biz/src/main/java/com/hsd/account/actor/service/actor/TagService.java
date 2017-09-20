package com.hsd.account.actor.service.actor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.actor.api.actor.ITagService;
import com.hsd.account.actor.dao.actor.ITagDao;
import com.hsd.account.actor.dto.actor.TagDto;
import com.hsd.account.actor.entity.actor.Tag;
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
public class TagService extends BaseService implements ITagService {
    @Autowired
    private ITagDao tagDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody TagDto dto) throws Exception {
            Response result = new Response(0,"success");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                Tag entity = copyTo(dto, Tag.class);
                //判断数据是否存在
                if (tagDao.isDataYN(entity) != 0) {
                    //数据存在
                    tagDao.update(entity);
                } else {
                    //新增
                     tagDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public String deleteData(@RequestBody TagDto dto) throws Exception {
            String result = "success";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                Tag entity = copyTo(dto, Tag.class);
                if(tagDao.deleteByPrimaryKey(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("物理删除异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


        @Override
        public PageInfo findDataIsPage(@RequestBody TagDto dto) throws Exception {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               Tag entity = copyTo(dto, Tag.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = tagDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), TagDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<TagDto> findDataIsList(@RequestBody TagDto dto) throws Exception {
            List<TagDto>  results = null;
            try {
                Tag entity = copyTo(dto, Tag.class);
                 results = copyTo(tagDao.findDataIsList(entity), TagDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public TagDto findDataById(@RequestBody TagDto dto) throws Exception {
            TagDto result = null;
            try {
                Tag entity = copyTo(dto, Tag.class);
                result = copyTo(tagDao.selectByPrimaryKey(entity),TagDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


}