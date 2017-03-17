package com.wu1g.api.sys;

import com.wu1g.vo.sys.SysPanoPlugins;
import java.util.List;

/**
 * <p>系统_全景插件 业务处理接口类。
 */
//@FeignClient(value = "${spring.application.name}")
public interface ISysPanoPluginsService {
    /**
     * <p>信息编辑。
     */
    //@RequestMapping(value = "/saveOrUpdateData")
    public String saveOrUpdateData(SysPanoPlugins dto) throws Exception;

    /**
     * <p>物理删除。
     */
    //@RequestMapping(value = "/deleteData")
    public String deleteData(SysPanoPlugins dto) throws Exception;

    /**
     * <li>恢复逻辑删除的数据。
     */
    //@RequestMapping(value = "/recoveryDataById")
    public String recoveryDataById(SysPanoPlugins dto) throws Exception;

    /**
     * <li>逻辑删除。
     */
    //@RequestMapping(value = "/deleteDataById")
    public String deleteDataById(SysPanoPlugins dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    //@RequestMapping(value = "/findDataIsPage")
    public List<SysPanoPlugins> findDataIsPage(SysPanoPlugins dto) throws Exception;

    /**
     * <p>信息列表。
     */
    //@RequestMapping(value = "/findDataIsList")
    public List<SysPanoPlugins> findDataIsList(SysPanoPlugins dto) throws Exception;

    /**
     * <p>信息详情。
     */
    //@RequestMapping(value = "/findDataById")
    public SysPanoPlugins findDataById(SysPanoPlugins dto) throws Exception;
}