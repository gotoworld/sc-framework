package com.wu1g.api.pano;

import com.wu1g.vo.pano.PanoComments;

import java.util.List;

/**
 * <p>全景_评论 业务处理接口类。
 */
public interface IPanoCommentsService {

    /**
     * <p>信息编辑。
     */
    public String saveOrUpdateData(PanoComments bean) throws Exception;

    /**
     * <p>物理删除。
     */
    public String deleteData(PanoComments bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    public String recoveryDataById(PanoComments bean) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    public String deleteDataById(PanoComments bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    public List<PanoComments> findDataIsPage(PanoComments bean);

    /**
     * <p>信息列表。
     */
    public List<PanoComments> findDataIsList(PanoComments bean);

    /**
     * <p>信息详情。
     */
    public PanoComments findDataById(PanoComments bean);
}