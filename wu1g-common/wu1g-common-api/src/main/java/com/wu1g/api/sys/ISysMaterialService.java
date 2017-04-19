package com.wu1g.api.sys;

import com.wu1g.vo.sys.SysMaterial;

import java.util.List;

/**
 * <p>全景_素材 业务处理接口类。
 */
//@FeignClient(value = "${spring.application.name}")
public interface ISysMaterialService {
    /**
     * <p>信息编辑。
     */
    //@RequestMapping(value = "/saveOrUpdateData")
    public String saveOrUpdateData(SysMaterial dto) throws Exception;

    /**
     * <p>物理删除。
     */
    //@RequestMapping(value = "/deleteData")
    public String deleteData(SysMaterial dto) throws Exception;


    /**
     * <li>逻辑删除。
     */
    //@RequestMapping(value = "/deleteDataById")
    public String deleteDataById(SysMaterial dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    //@RequestMapping(value = "/findDataIsPage")
    public List<SysMaterial> findDataIsPage(SysMaterial dto) throws Exception;

    /**
     * <p>信息列表。
     */
    //@RequestMapping(value = "/findDataIsList")
    public List<SysMaterial> findDataIsList(SysMaterial dto) throws Exception;

    /**
     * <p>信息详情。
     */
    //@RequestMapping(value = "/findDataById")
    public SysMaterial findDataById(SysMaterial dto) throws Exception;
}