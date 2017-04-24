package com.wu1g.api.pano;

import com.wu1g.vo.pano.PanoMap;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>全景_导览图 业务处理接口类。
 */
@FeignClient(name = "wu1g-service-server")
public interface IPanoMapService {
    /**
     * <p>信息列表。
     */
    @RequestMapping(value = "/findDataIsList")
    public List<PanoMap> findDataIsList(PanoMap bean);
}