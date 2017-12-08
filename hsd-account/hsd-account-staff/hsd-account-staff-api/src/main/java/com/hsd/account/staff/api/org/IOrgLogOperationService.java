package com.hsd.account.staff.api.org;

import com.github.pagehelper.PageInfo;
import com.hsd.account.staff.dto.org.OrgLogOperationDto;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>系统_管理员操作日志   业务处理接口类。
 */
@FeignClient(name = "${feign.name.account.staff}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IOrgLogOperationService {
    String acPrefix = "/feign/account/staff/ISysStaffLogService";

    /**
     * 管理员操作日志记录。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/info")
    void info(@RequestParam("type") String type, @RequestParam("memo") String memo, @RequestParam("appId") String appId, @RequestParam("detailInfo") String detailInfo, @RequestParam("staffId") Long staffId, @RequestParam("staffName") String staffName, @RequestParam("ip") String ip);
    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    PageInfo findDataIsPage(OrgLogOperationDto dto);

    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    OrgLogOperationDto findDataById(OrgLogOperationDto dto) throws Exception;
}