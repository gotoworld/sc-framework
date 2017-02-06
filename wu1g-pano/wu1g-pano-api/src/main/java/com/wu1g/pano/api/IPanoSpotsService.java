package com.wu1g.pano.api;

import com.wu1g.pano.vo.PanoSpots;

import java.util.List;

/**
 * <p>全景_热点 业务处理接口类。
 */
public interface IPanoSpotsService {

    /**
     * <p>信息编辑。
     */
    public String saveOrUpdateData(PanoSpots bean) throws Exception;

    /**
     * <p>物理删除。
     */
    public String deleteData(PanoSpots bean) throws Exception;

    /**
     * <p>信息列表。
     */
    public List<PanoSpots> findDataIsList(PanoSpots bean);
}