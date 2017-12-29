package com.hsd.account.staff.api.sys;

import com.github.pagehelper.PageInfo;
import com.hsd.account.staff.dto.sys.SysAppDto;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
/**
 * <p>APP应用表 业务处理接口类。
 */
@FeignClient(value = "${feign.name.account.staff}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface ISysAppService {
    String acPrefix = "/feign/account/staff/ISysAppService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    Response saveOrUpdateData(SysAppDto dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteData")
    String deleteData(SysAppDto dto) throws Exception;

    /**
     * <li>逻辑删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteDataById")
    String deleteDataById(SysAppDto dto) throws Exception;
    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/recoveryDataById")
    String recoveryDataById(SysAppDto dto) throws Exception;


    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    PageInfo findDataIsPage(SysAppDto dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    List<SysAppDto> findDataIsList(SysAppDto dto) throws Exception;


    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    SysAppDto findDataById(SysAppDto dto) throws Exception;

    /**
     *
     * <p>根据名称查询
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix +"/findAppByName")
    SysAppDto findAppByName(@RequestParam("appname") String appname)throws  Exception;
}