package com.hsd.account.staff.api.sys;

import com.github.pagehelper.PageInfo;
import com.hsd.account.staff.dto.sys.UserAppDto;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
/**
 * <p>APP用户表 业务处理接口类。
 */
@FeignClient(value = "${feign.name.account.staff}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IUserAppService {
    String acPrefix = "/feign/account/staff/IUserAppService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    Response saveOrUpdateData(UserAppDto dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteData")
    String deleteData(UserAppDto dto) throws Exception;



    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    PageInfo findDataIsPage(UserAppDto dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    List<UserAppDto> findDataIsList(UserAppDto dto) throws Exception;


    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    UserAppDto findDataById(UserAppDto dto) throws Exception;


    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataByAppIdAndUserId")
    UserAppDto findDataByAppIdAndUserId(UserAppDto dto)throws Exception;

}