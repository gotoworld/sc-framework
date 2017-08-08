package com.hsd.account.api.org;

import com.hsd.account.dto.org.OrgLogOperationDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>系统_管理员操作日志   业务处理接口类。
 */
@FeignClient(name = "${feign.name.account}")//, fallback = TestServiceHystrix.class)
public interface IOrgLogOperationService {
    String actPrefix = "/feign/ISysUserLogService";

    /**
     * 管理员操作日志记录。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/info")
    public void info(@RequestParam("type") String type,@RequestParam("memo")  String memo,@RequestParam("domainCode")String domainCode,@RequestParam("detailInfo")  String detailInfo,@RequestParam("userId")  Long userId,@RequestParam("userName")  String userName,@RequestParam("ip")  String ip);
    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataIsPage")
    public List<OrgLogOperationDto> findDataIsPage(OrgLogOperationDto dto);
}