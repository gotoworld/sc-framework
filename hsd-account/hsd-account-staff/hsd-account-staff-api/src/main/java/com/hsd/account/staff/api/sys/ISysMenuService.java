package com.hsd.account.staff.api.sys;

import com.github.pagehelper.PageInfo;
import com.hsd.account.staff.dto.sys.SysMenuDto;
import com.hsd.framework.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
/**
 * <p>系统_菜单 业务处理接口类。
 */
@FeignClient(value = "${feign.name.account.staff}")
public interface ISysMenuService {
    String acPrefix = "/feign/account/staff/ISysMenuService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    public Response saveOrUpdateData(SysMenuDto dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteData")
    public String deleteData(SysMenuDto dto) throws Exception;


    /**
     * <li>逻辑删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteDataById")
    public String deleteDataById(SysMenuDto dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    public PageInfo findDataIsPage(SysMenuDto dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    public List<SysMenuDto> findDataIsList(SysMenuDto dto) throws Exception;

    /**
     * <p>信息树。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsTree")
    public List<SysMenuDto> findDataIsTree(SysMenuDto dto);
    
    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    public SysMenuDto findDataById(SysMenuDto dto) throws Exception;
}