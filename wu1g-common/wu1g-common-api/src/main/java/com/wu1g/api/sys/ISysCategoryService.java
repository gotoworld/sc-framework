package com.wu1g.api.sys;

import com.wu1g.vo.sys.SysCategory;
import java.util.List;

/**
 * <p>系统_类目 业务处理接口类。
 */
//@FeignClient(value = "${spring.application.name}")
public interface ISysCategoryService {
    /**
     * <p>信息编辑。
     */
    //@RequestMapping(value = "/saveOrUpdateData")
    public String saveOrUpdateData(SysCategory dto) throws Exception;

    /**
     * <p>物理删除。
     */
    //@RequestMapping(value = "/deleteData")
    public String deleteData(SysCategory dto) throws Exception;

    /**
     * <li>恢复逻辑删除的数据。
     */
    //@RequestMapping(value = "/recoveryDataById")
    public String recoveryDataById(SysCategory dto) throws Exception;

    /**
     * <li>逻辑删除。
     */
    //@RequestMapping(value = "/deleteDataById")
    public String deleteDataById(SysCategory dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    //@RequestMapping(value = "/findDataIsPage")
    public List<SysCategory> findDataIsPage(SysCategory dto) throws Exception;

    /**
     * <p>信息列表。
     */
    //@RequestMapping(value = "/findDataIsList")
    public List<SysCategory> findDataIsList(SysCategory dto) throws Exception;

    /**
     * <p>信息详情。
     */
    //@RequestMapping(value = "/findDataById")
    public SysCategory findDataById(SysCategory dto) throws Exception;
}