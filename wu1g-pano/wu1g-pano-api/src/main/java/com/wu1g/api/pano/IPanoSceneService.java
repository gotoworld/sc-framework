package com.wu1g.api.pano;

import com.wu1g.vo.pano.PanoScene;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>全景_场景 业务处理接口类。
 */
@FeignClient(name = "${spring.application.name}")
public interface IPanoSceneService {

    /**
     * <p>信息列表。
     */
    @RequestMapping(value = "/findDataIsList")
    public List<PanoScene> findDataIsList(PanoScene bean);
}