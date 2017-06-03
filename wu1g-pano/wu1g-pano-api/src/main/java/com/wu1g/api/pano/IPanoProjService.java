package com.wu1g.api.pano;

import com.wu1g.vo.pano.PanoMap;
import com.wu1g.vo.pano.PanoProj;
import com.wu1g.vo.pano.PanoScene;
import com.wu1g.vo.pano.PanoSpots;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <p>全景_项目 业务处理接口类。
 */
@FeignClient(name = "${feign.name}")//, fallback = TestServiceHystrix.class)
public interface IPanoProjService {
    String actPrefix = "/api/IPanoProjService";

    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/saveOrUpdateData")
    public String saveOrUpdateData(PanoProj bean) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/deleteData")
    public String deleteData(PanoProj bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/recoveryDataById")
    public String recoveryDataById(PanoProj bean) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/deleteDataById")
    public String deleteDataById(PanoProj bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataIsPage")
    public List<PanoProj> findDataIsPage(PanoProj bean);

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataIsList")
    public List<PanoProj> findDataIsList(PanoProj bean);

    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataById")
    public PanoProj findDataById(PanoProj bean);

    /**
     * <p>生成全景图。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/makePano")
    public void makePano(PanoProj bean);

    /**
     * <p>保存xml信息
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/saveXmlData")
    public String saveXmlData(PanoProj bean) throws Exception;

    /**
     * <p>点赞
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/thumbsUpNum")
    public String thumbsUpNum(PanoProj bean) throws Exception;

    /**
     * <p>浏览量+1
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/pvNum")
    public String pvNum(PanoProj bean) throws Exception;

    /**
     * <p>生成视频文件。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/makeVideo")
    public void makeVideo(PanoProj bean) throws Exception;

    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/getScenesByjson")
    public List<PanoScene> getScenesByjson(Long pid, String scene_str);
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/getSpotsObject")
    public List<PanoSpots>  getSpotsObject(Integer htype, String spotsStr, PanoScene scene );
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/getRadarsByjson")
    public List<PanoMap> getRadarsByjson(Long pid, String radars_str) ;
}