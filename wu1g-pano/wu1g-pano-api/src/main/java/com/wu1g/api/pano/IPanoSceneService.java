package com.wu1g.api.pano;

import com.wu1g.vo.pano.PanoScene;

import java.util.List;

/**
 * <p>全景_场景 业务处理接口类。
 */
public interface IPanoSceneService {

    /**
     * <p>信息编辑。
     */
    public String saveOrUpdateData(PanoScene bean) throws Exception;

    /**
     * <p>物理删除。
     */
    public String deleteData(PanoScene bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    public String recoveryDataById(PanoScene bean) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    public String deleteDataById(PanoScene bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    public List<PanoScene> findDataIsPage(PanoScene bean);

    /**
     * <p>信息列表。
     */
    public List<PanoScene> findDataIsList(PanoScene bean);

    /**
     * <p>信息详情。
     */
    public PanoScene findDataById(PanoScene bean);
}