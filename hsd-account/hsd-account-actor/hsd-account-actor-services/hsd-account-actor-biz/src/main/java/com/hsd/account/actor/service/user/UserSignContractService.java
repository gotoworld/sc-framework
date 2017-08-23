package com.hsd.account.actor.service.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.actor.api.user.IUserSignContractService;
import com.hsd.account.actor.dao.user.IUserSignContractDao;
import com.hsd.account.actor.dto.user.UserSignContractDto;
import com.hsd.account.actor.entity.user.UserSignContract;
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
public class UserSignContractService extends BaseService implements IUserSignContractService {
    @Autowired
    private IUserSignContractDao userSignContractDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody UserSignContractDto dto) throws Exception {
            Response result = new Response(0,"seccuss");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                UserSignContract entity = copyTo(dto, UserSignContract.class);
                //判断数据是否存在
                if (userSignContractDao.isDataYN(entity) != 0) {
                    //数据存在
                    userSignContractDao.update(entity);
                } else {
                    //新增
                     userSignContractDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public String deleteData(@RequestBody UserSignContractDto dto) throws Exception {
            String result = "seccuss";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                UserSignContract entity = copyTo(dto, UserSignContract.class);
                if(userSignContractDao.deleteByPrimaryKey(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("物理删除异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


        @Override
        public PageInfo findDataIsPage(@RequestBody UserSignContractDto dto) throws Exception {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               UserSignContract entity = copyTo(dto, UserSignContract.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = userSignContractDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), UserSignContractDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<UserSignContractDto> findDataIsList(@RequestBody UserSignContractDto dto) throws Exception {
            List<UserSignContractDto>  results = null;
            try {
                UserSignContract entity = copyTo(dto, UserSignContract.class);
                 results = copyTo(userSignContractDao.findDataIsList(entity), UserSignContractDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public UserSignContractDto findDataById(@RequestBody UserSignContractDto dto) throws Exception {
            UserSignContractDto result = null;
            try {
                UserSignContract entity = copyTo(dto, UserSignContract.class);
                result = copyTo(userSignContractDao.selectByPrimaryKey(entity),UserSignContractDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


}