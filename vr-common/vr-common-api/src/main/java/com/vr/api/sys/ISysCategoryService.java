package com.vr.api.sys;

import com.vr.vo.sys.SysCategory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <p>系统_类目 业务处理接口类。
 */
@FeignClient(name = "${feign.name}")//, fallback = TestServiceHystrix.class)
public interface ISysCategoryService {
    String actPrefix = "/api/ISysCategoryService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/saveOrUpdateData")
    public String saveOrUpdateData(SysCategory dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/deleteData")
    public String deleteData(SysCategory dto) throws Exception;

    /**
     * <li>恢复逻辑删除的数据。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/recoveryDataById")
    public String recoveryDataById(SysCategory dto) throws Exception;

    /**
     * <li>逻辑删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/deleteDataById")
    public String deleteDataById(SysCategory dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataIsPage")
    public List<SysCategory> findDataIsPage(SysCategory dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataIsList")
    public List<SysCategory> findDataIsList(SysCategory dto) throws Exception;

    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataById")
    public SysCategory findDataById(SysCategory dto) throws Exception;

    /**
     * <p>栏目树
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataTree")
    public List<SysCategory> findDataTree(SysCategory bean);
}