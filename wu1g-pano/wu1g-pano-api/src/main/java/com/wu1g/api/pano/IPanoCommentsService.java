package com.wu1g.api.pano;

import com.wu1g.vo.pano.PanoComments;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>全景_评论 业务处理接口类。
 */
@FeignClient(name = "wu1g-service-server")
public interface IPanoCommentsService {

    /**
     * <p>信息编辑。
     */
    @RequestMapping(value = "/saveOrUpdateData")
    public String saveOrUpdateData(PanoComments bean) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    @RequestMapping(value = "/deleteDataById")
    public String deleteDataById(PanoComments bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(value = "/findDataIsPage")
    public List<PanoComments> findDataIsPage(PanoComments bean);

    /**
     * <p>信息列表。
     */
    @RequestMapping(value = "/findDataIsList")
    public List<PanoComments> findDataIsList(PanoComments bean);
}