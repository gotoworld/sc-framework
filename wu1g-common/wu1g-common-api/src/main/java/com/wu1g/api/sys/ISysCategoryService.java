package com.wu1g.api.sys;

import com.wu1g.vo.sys.SysCategory;

import java.util.List;

/**
 * <p>系统类目   业务处理接口类。
 */
public interface ISysCategoryService {

    /**
     * <p>信息编辑。
     */
    public String saveOrUpdateData(SysCategory bean) throws Exception;

    /**
     * <p>物理删除。
     */
    public String deleteData(SysCategory bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    public String recoveryDataById(SysCategory bean) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    public String deleteDataById(SysCategory bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    public List<SysCategory> findDataIsPage(SysCategory bean);

    /**
     * <p>信息列表。
     */
    public List<SysCategory> findDataIsList(SysCategory bean);

    /**
     * <p>信息详情。
     */
    public SysCategory findDataById(SysCategory bean);
}