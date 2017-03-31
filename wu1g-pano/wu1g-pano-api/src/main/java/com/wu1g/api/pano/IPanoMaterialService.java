package com.wu1g.api.pano;

import com.wu1g.vo.pano.PanoMaterial;
import java.util.List;

/**
 * <p>全景_素材 业务处理接口类。
 */
//@FeignClient(value = "${spring.application.name}")
public interface IPanoMaterialService {
    /**
     * <p>信息编辑。
     */
    //@RequestMapping(value = "/saveOrUpdateData")
    public String saveOrUpdateData(PanoMaterial dto) throws Exception;

    /**
     * <p>物理删除。
     */
    //@RequestMapping(value = "/deleteData")
    public String deleteData(PanoMaterial dto) throws Exception;


    /**
     * <li>逻辑删除。
     */
    //@RequestMapping(value = "/deleteDataById")
    public String deleteDataById(PanoMaterial dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    //@RequestMapping(value = "/findDataIsPage")
    public List<PanoMaterial> findDataIsPage(PanoMaterial dto) throws Exception;

    /**
     * <p>信息列表。
     */
    //@RequestMapping(value = "/findDataIsList")
    public List<PanoMaterial> findDataIsList(PanoMaterial dto) throws Exception;

    /**
     * <p>信息详情。
     */
    //@RequestMapping(value = "/findDataById")
    public PanoMaterial findDataById(PanoMaterial dto) throws Exception;
}