package com.hsd.account.actor.api.actor;

import com.github.pagehelper.PageInfo;
import com.hsd.account.actor.dto.actor.MemberDto;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
/**
 * <p>会员信息表 业务处理接口类。
 */
@FeignClient(value = "${feign.name.account.actor}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IMemberService {
    String acPrefix = "/feign/account/actor/IMemberService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    public Response saveOrUpdateData(MemberDto dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteData")
    public String deleteData(MemberDto dto) throws Exception;

    /**
     * <li>恢复逻辑删除的数据。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/recoveryDataById")
    public String recoveryDataById(MemberDto dto) throws Exception;

    /**
     * <li>逻辑删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteDataById")
    public String deleteDataById(MemberDto dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    public PageInfo findDataIsPage(MemberDto dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    public List<MemberDto> findDataIsList(MemberDto dto) throws Exception;


    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    public MemberDto findDataById(MemberDto dto) throws Exception;
    /**
     * <p>信息是否存在。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/isDataYN")
    public Integer isDataYN(MemberDto dto) throws Exception;
}