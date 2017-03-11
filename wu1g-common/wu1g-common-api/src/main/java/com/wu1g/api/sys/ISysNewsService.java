package com.wu1g.api.sys;

import com.wu1g.vo.sys.SysNews;

import java.util.List;

/**
 * <p>新闻资讯   业务处理接口类。
 */
public interface ISysNewsService {

    /**
     * <p>信息编辑。
     */
    public String saveOrUpdateData(SysNews bean) throws Exception;

    /**
     * <p>物理删除。
     */
    public String deleteData(SysNews bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    public String recoveryDataById(SysNews bean) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    public String deleteDataById(SysNews bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    public List<SysNews> findDataIsPage(SysNews bean);

    /**
     * <p>信息列表。
     */
    public List<SysNews> findDataIsList(SysNews bean);

    /**
     * <p>信息详情。
     */
    public SysNews findDataById(SysNews bean);
}