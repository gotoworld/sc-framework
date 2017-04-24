package com.wu1g.api.pano;

import com.wu1g.vo.pano.PanoProj;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>全景_项目 业务处理接口类。
 */
@FeignClient(name = "wu1g-service-server")
public interface IPanoProjService {

    /**
     * <p>信息编辑。
     */
    @RequestMapping(value = "/saveOrUpdateData")
    public String saveOrUpdateData(PanoProj bean) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(value = "/deleteData")
    public String deleteData(PanoProj bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    @RequestMapping(value = "/recoveryDataById")
    public String recoveryDataById(PanoProj bean) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    @RequestMapping(value = "/deleteDataById")
    public String deleteDataById(PanoProj bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(value = "/findDataIsPage")
    public List<PanoProj> findDataIsPage(PanoProj bean);

    /**
     * <p>信息列表。
     */
    @RequestMapping(value = "/findDataIsList")
    public List<PanoProj> findDataIsList(PanoProj bean);

    /**
     * <p>信息详情。
     */
    @RequestMapping(value = "/findDataById")
    public PanoProj findDataById(PanoProj bean);

    /**
     * <p>生成全景图。
     */
    @RequestMapping(value = "/makePano")
    public void makePano(PanoProj bean);

    /**
     * <p>保存xml信息
     */
    @RequestMapping(value = "/saveXmlData")
    public String saveXmlData(PanoProj bean) throws Exception;

    /**
     * <p>点赞
     */
    @RequestMapping(value = "/thumbsUpNum")
    public String thumbsUpNum(PanoProj bean) throws Exception;

    /**
     * <p>浏览量+1
     */
    @RequestMapping(value = "/pvNum")
    public String pvNum(PanoProj bean) throws Exception;

    /**
     * <p>生成视频文件。
     */
    @RequestMapping(value = "/makeVideo")
    public void makeVideo(PanoProj bean) throws Exception;
}