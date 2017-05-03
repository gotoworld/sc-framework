package com.wu1g.api.sys;

import com.wu1g.vo.sys.SysVariable;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>数据字典   业务处理接口类。
 */
@FeignClient(name = "${feign.name}")//, fallback = TestServiceHystrix.class)
public interface ISysVariableService {
    String actPrefix = "/api/ISysVariableService";

    /**
     * <p>信息编辑。
     */
    @RequestMapping(value = actPrefix + "/saveOrUpdateData")
    public String saveOrUpdateData(SysVariable bean) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(value = actPrefix + "/deleteData")
    public String deleteData(SysVariable bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    @RequestMapping(value = actPrefix + "/recoveryDataById")
    public String recoveryDataById(SysVariable bean) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    @RequestMapping(value = actPrefix + "/deleteDataById")
    public String deleteDataById(SysVariable bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(value = actPrefix + "/findDataIsPage")
    public List<SysVariable> findDataIsPage(SysVariable bean);

    /**
     * <p>信息列表。
     */
    @RequestMapping(value = actPrefix + "/findDataIsList")
    public List<SysVariable> findDataIsList(SysVariable bean);

    /**
     * <p>信息详情。
     */
    @RequestMapping(value = actPrefix + "/findDataById")
    public SysVariable findDataById(SysVariable bean);

    /**
     * <p>字典树
     */
    @RequestMapping(value = actPrefix + "/findDataTree")
    public List<SysVariable> findDataTree(SysVariable bean);
}