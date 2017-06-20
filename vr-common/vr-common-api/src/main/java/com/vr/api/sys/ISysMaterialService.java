package com.vr.api.sys;

import com.vr.vo.sys.SysMaterial;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/insert")
    public String insert(SysMaterial dto) throws Exception;
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/saveOrUpdateData")
    public String saveOrUpdateData(SysMaterial dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/deleteData")
    public String deleteData(SysMaterial dto) throws Exception;


    /**
     * <li>逻辑删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/deleteDataById")
    public String deleteDataById(SysMaterial dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataIsPage")
    public List<SysMaterial> findDataIsPage(SysMaterial dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataIsList")
    public List<SysMaterial> findDataIsList(SysMaterial dto) throws Exception;

    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataById")
    public SysMaterial findDataById(SysMaterial dto) throws Exception;
}