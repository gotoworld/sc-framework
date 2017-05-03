package com.wu1g.api.sys;

import com.wu1g.vo.sys.SysMessage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>系统_站内信 业务处理接口类。
 */
@FeignClient(name = "${feign.name}")//, fallback = TestServiceHystrix.class)
public interface ISysMessageService {
    String actPrefix = "/api/ISysMessageService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(value = actPrefix + "/saveOrUpdateData")
    public String saveOrUpdateData(SysMessage dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(value = actPrefix + "/deleteData")
    public String deleteData(SysMessage dto) throws Exception;


    /**
     * <li>逻辑删除。
     */
    @RequestMapping(value = actPrefix + "/deleteDataById")
    public String deleteDataById(SysMessage dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(value = actPrefix + "/findDataIsPage")
    public List<SysMessage> findDataIsPage(SysMessage dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(value = actPrefix + "/findDataIsList")
    public List<SysMessage> findDataIsList(SysMessage dto) throws Exception;

    /**
     * <p>信息详情。
     */
    @RequestMapping(value = actPrefix + "/findDataById")
    public SysMessage findDataById(SysMessage dto) throws Exception;
}