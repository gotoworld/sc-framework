package com.hsd.account.staff.api.org;

import com.hsd.account.staff.dto.org.OrgStaffAppDto;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataByAppIdAndStaffId")
    OrgStaffAppDto findDataByAppIdAndStaffId(OrgStaffAppDto dto)throws Exception;

}