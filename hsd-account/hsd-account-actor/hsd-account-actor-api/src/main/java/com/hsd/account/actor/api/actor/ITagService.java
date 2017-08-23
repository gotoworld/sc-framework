package com.hsd.account.actor.api.actor;

import com.hsd.account.actor.dto.actor.TagDto;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;
/**
 * <p>标签 业务处理接口类。
 */
@FeignClient(value = "${feign.name.account.actor}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface ITagService {
    String acPrefix = "/feign/account/actor/ITagService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    public Response saveOrUpdateData(TagDto dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteData")
    public String deleteData(TagDto dto) throws Exception;



    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    public PageInfo findDataIsPage(TagDto dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    public List<TagDto> findDataIsList(TagDto dto) throws Exception;


    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    public TagDto findDataById(TagDto dto) throws Exception;
}