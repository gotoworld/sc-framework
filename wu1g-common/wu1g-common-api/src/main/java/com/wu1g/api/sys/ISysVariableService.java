package com.wu1g.api.sys;

import com.wu1g.vo.sys.SysVariable;

import java.util.List;

/**
 * <p>数据字典   业务处理接口类。
 */
public interface ISysVariableService {

    /**
     * <p>信息编辑。
     */
    public String saveOrUpdateData(SysVariable bean) throws Exception;

    /**
     * <p>物理删除。
     */
    public String deleteData(SysVariable bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    public String recoveryDataById(SysVariable bean) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    public String deleteDataById(SysVariable bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    public List<SysVariable> findDataIsPage(SysVariable bean);

    /**
     * <p>信息列表。
     */
    public List<SysVariable> findDataIsList(SysVariable bean);

    /**
     * <p>信息详情。
     */
    public SysVariable findDataById(SysVariable bean);

    /**
     * <p>字典树
     */
    public List<SysVariable> findDataTree(SysVariable bean);
}