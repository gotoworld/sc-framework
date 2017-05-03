package com.wu1g.api.sys;

import com.wu1g.vo.sys.SysAdvertise;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>广告管理 业务处理接口类。
 */
@FeignClient(name = "${feign.name}")//, fallback = TestServiceHystrix.class)
public interface ISysAdvertiseService {
    String actPrefix = "/api/ISysAdvertiseService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(value = actPrefix + "/saveOrUpdateData")
    public String saveOrUpdateData(SysAdvertise dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(value = actPrefix + "/deleteData")
    public String deleteData(SysAdvertise dto) throws Exception;

    /**
     * <li>恢复逻辑删除的数据。
     */
    @RequestMapping(value = actPrefix + "/recoveryDataById")
    public String recoveryDataById(SysAdvertise dto) throws Exception;

    /**
     * <li>逻辑删除。
     */
    @RequestMapping(value = actPrefix + "/deleteDataById")
    public String deleteDataById(SysAdvertise dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(value = actPrefix + "/findDataIsPage")
    public List<SysAdvertise> findDataIsPage(SysAdvertise dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(value = actPrefix + "/findDataIsList")
    public List<SysAdvertise> findDataIsList(SysAdvertise dto) throws Exception;

    /**
     * <p>信息详情。
     */
    @RequestMapping(value = actPrefix + "/findDataById")
    public SysAdvertise findDataById(SysAdvertise dto) throws Exception;
}