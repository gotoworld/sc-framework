package com.wu1g.api.pano;

import com.wu1g.vo.pano.PanoCategory;

import java.util.List;

/**
 * <p>全景_类目 业务处理接口类。
 */

public interface IPanoCategoryService {

    /**
     * <p>信息编辑。
     */
    public String saveOrUpdateData(PanoCategory bean) throws Exception;

    /**
     * <p>物理删除。
     */
    public String deleteData(PanoCategory bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    public String recoveryDataById(PanoCategory bean) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    public String deleteDataById(PanoCategory bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    public List<PanoCategory> findDataIsPage(PanoCategory bean);

    /**
     * <p>信息列表。
     */
    public List<PanoCategory> findDataIsList(PanoCategory bean);

    /**
     * <p>信息详情。
     */
    public PanoCategory findDataById(PanoCategory bean);

    /**
     * 信息列表树。
     */
    public List<PanoCategory> findDataTree(PanoCategory bean);
}