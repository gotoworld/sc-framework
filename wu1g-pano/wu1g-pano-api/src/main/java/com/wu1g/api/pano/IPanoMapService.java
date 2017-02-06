package com.wu1g.api.pano;

import com.wu1g.vo.pano.PanoMap;

import java.util.List;

/**
 * <p>全景_导览图 业务处理接口类。
 */
public interface IPanoMapService {

    /**
     * <p>信息编辑。
     */
    public String saveOrUpdateData(PanoMap bean) throws Exception;

    /**
     * <p>物理删除。
     */
    public String deleteData(PanoMap bean) throws Exception;

    /**
     * <p>信息列表。
     */
    public List<PanoMap> findDataIsList(PanoMap bean);
}