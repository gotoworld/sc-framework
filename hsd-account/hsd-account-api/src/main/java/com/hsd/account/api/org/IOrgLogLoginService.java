package com.hsd.account.api.org;

import com.github.pagehelper.PageInfo;
import com.hsd.account.dto.org.OrgLogLoginDto;
import com.hsd.framework.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
/**
 * <p>组织架构_员工登录日志 业务处理接口类。
 */
@FeignClient(value = "${feign.name.account}")
public interface IOrgLogLoginService {
    String acPrefix = "/feign/IOrgLogLoginService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    public Response saveOrUpdateData(OrgLogLoginDto dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteData")
    public String deleteData(OrgLogLoginDto dto) throws Exception;



    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    public PageInfo findDataIsPage(OrgLogLoginDto dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    public List<OrgLogLoginDto> findDataIsList(OrgLogLoginDto dto) throws Exception;

    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    public OrgLogLoginDto findDataById(OrgLogLoginDto dto) throws Exception;
}