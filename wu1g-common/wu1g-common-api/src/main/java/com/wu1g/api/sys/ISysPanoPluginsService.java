package com.wu1g.api.sys;

import com.wu1g.vo.sys.SysPanoPlugins;

import java.util.List;

/**
 * <p>全景插件   业务处理接口类。
 */
public interface ISysPanoPluginsService {

    /**
     * <p>信息编辑。
     */
    public String saveOrUpdateData(SysPanoPlugins bean) throws Exception;

    /**
     * <p>物理删除。
     */
    public String deleteData(SysPanoPlugins bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    public String recoveryDataById(SysPanoPlugins bean) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    public String deleteDataById(SysPanoPlugins bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    public List<SysPanoPlugins> findDataIsPage(SysPanoPlugins bean);

    /**
     * <p>信息列表。
     */
    public List<SysPanoPlugins> findDataIsList(SysPanoPlugins bean);

    /**
     * <p>信息详情。
     */
    public SysPanoPlugins findDataById(SysPanoPlugins bean);
}