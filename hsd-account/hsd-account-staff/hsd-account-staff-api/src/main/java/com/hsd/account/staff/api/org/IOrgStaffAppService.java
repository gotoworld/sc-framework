package com.hsd.account.staff.api.org;

import com.github.pagehelper.PageInfo;
import com.hsd.account.staff.dto.org.OrgStaffAppDto;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
/**
 * <p>APP员工表 业务处理接口类。
 */
@FeignClient(value = "${feign.name.account.staff}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IOrgStaffAppService {
    String acPrefix = "/feign/account/staff/IOrgStaffAppService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    Response saveOrUpdateData(OrgStaffAppDto dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteData")
    String deleteData(OrgStaffAppDto dto) throws Exception;



    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    PageInfo findDataIsPage(OrgStaffAppDto dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    List<OrgStaffAppDto> findDataIsList(OrgStaffAppDto dto) throws Exception;


    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    OrgStaffAppDto findDataById(OrgStaffAppDto dto) throws Exception;


    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataByAppIdAndUserId")
    OrgStaffAppDto findDataByAppIdAndUserId(OrgStaffAppDto dto)throws Exception;

}