package com.wu1g.api.sys;

import com.wu1g.vo.sys.SysAdvertise;

import java.util.List;

/**
 * <p>系统广告   业务处理接口类。
 */
public interface ISysAdvertiseService {

    /**
     * <p>信息编辑。
     */
    public String saveOrUpdateData(SysAdvertise bean) throws Exception;

    /**
     * <p>物理删除。
     */
    public String deleteData(SysAdvertise bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    public String recoveryDataById(SysAdvertise bean) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    public String deleteDataById(SysAdvertise bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    public List<SysAdvertise> findDataIsPage(SysAdvertise bean);

    /**
     * <p>信息列表。
     */
    public List<SysAdvertise> findDataIsList(SysAdvertise bean);

    /**
     * <p>信息详情。
     */
    public SysAdvertise findDataById(SysAdvertise bean);
}