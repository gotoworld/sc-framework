package com.wu1g.pano.api;

import com.wu1g.pano.vo.PanoProj;

import java.util.List;

/**
 * <p>全景_项目 业务处理接口类。
 */
public interface IPanoProjService {

    /**
     * <p>信息编辑。
     */
    public String saveOrUpdateData(PanoProj bean) throws Exception;

    /**
     * <p>物理删除。
     */
    public String deleteData(PanoProj bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    public String recoveryDataById(PanoProj bean) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    public String deleteDataById(PanoProj bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    public List<PanoProj> findDataIsPage(PanoProj bean);

    /**
     * <p>信息列表。
     */
    public List<PanoProj> findDataIsList(PanoProj bean);

    /**
     * <p>信息详情。
     */
    public PanoProj findDataById(PanoProj bean);

    /**
     * <p>生成全景图。
     */
    public void makePano(PanoProj bean);

    /**
     * <p>保存xml信息
     */
    public String saveXmlData(PanoProj bean) throws Exception;

    /**
     * <p>点赞
     */
    public String thumbsUpNum(PanoProj bean) throws Exception;

    /**
     * <p>浏览量+1
     */
    public String pvNum(PanoProj bean) throws Exception;

    /**
     * <p>生成视频文件。
     */
    public void makeVideo(PanoProj bean) throws Exception;
}