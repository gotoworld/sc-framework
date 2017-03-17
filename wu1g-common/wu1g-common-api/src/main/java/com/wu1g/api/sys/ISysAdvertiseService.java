package com.wu1g.api.sys;

import com.wu1g.vo.sys.SysAdvertise;
import java.util.List;

/**
 * <p>广告管理 业务处理接口类。
 */
//@FeignClient(value = "${spring.application.name}")
public interface ISysAdvertiseService {
    /**
     * <p>信息编辑。
     */
    //@RequestMapping(value = "/saveOrUpdateData")
    public String saveOrUpdateData(SysAdvertise dto) throws Exception;

    /**
     * <p>物理删除。
     */
    //@RequestMapping(value = "/deleteData")
    public String deleteData(SysAdvertise dto) throws Exception;

    /**
     * <li>恢复逻辑删除的数据。
     */
    //@RequestMapping(value = "/recoveryDataById")
    public String recoveryDataById(SysAdvertise dto) throws Exception;

    /**
     * <li>逻辑删除。
     */
    //@RequestMapping(value = "/deleteDataById")
    public String deleteDataById(SysAdvertise dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    //@RequestMapping(value = "/findDataIsPage")
    public List<SysAdvertise> findDataIsPage(SysAdvertise dto) throws Exception;

    /**
     * <p>信息列表。
     */
    //@RequestMapping(value = "/findDataIsList")
    public List<SysAdvertise> findDataIsList(SysAdvertise dto) throws Exception;

    /**
     * <p>信息详情。
     */
    //@RequestMapping(value = "/findDataById")
    public SysAdvertise findDataById(SysAdvertise dto) throws Exception;
}