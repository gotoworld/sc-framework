package com.wu1g.api.sys;

import com.wu1g.vo.sys.SysMaterial;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>全景_素材 业务处理接口类。
 */
@FeignClient(name = "${feign.name}")//, fallback = TestServiceHystrix.class)
public interface ISysMaterialService {
    String actPrefix = "/api/ISysMaterialService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(value = actPrefix + "/insert")
    public String insert(SysMaterial dto) throws Exception;
    /**
     * <p>信息编辑。
     */
    @RequestMapping(value = actPrefix + "/saveOrUpdateData")
    public String saveOrUpdateData(SysMaterial dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(value = actPrefix + "/deleteData")
    public String deleteData(SysMaterial dto) throws Exception;


    /**
     * <li>逻辑删除。
     */
    @RequestMapping(value = actPrefix + "/deleteDataById")
    public String deleteDataById(SysMaterial dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(value = actPrefix + "/findDataIsPage")
    public List<SysMaterial> findDataIsPage(SysMaterial dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(value = actPrefix + "/findDataIsList")
    public List<SysMaterial> findDataIsList(SysMaterial dto) throws Exception;

    /**
     * <p>信息详情。
     */
    @RequestMapping(value = actPrefix + "/findDataById")
    public SysMaterial findDataById(SysMaterial dto) throws Exception;
}