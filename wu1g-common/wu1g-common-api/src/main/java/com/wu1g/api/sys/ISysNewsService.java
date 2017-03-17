package com.wu1g.api.sys;

import com.wu1g.vo.sys.SysNews;
import java.util.List;

/**
 * <p>系统_资讯 业务处理接口类。
 */
//@FeignClient(value = "${spring.application.name}")
public interface ISysNewsService {
    /**
     * <p>信息编辑。
     */
    //@RequestMapping(value = "/saveOrUpdateData")
    public String saveOrUpdateData(SysNews dto) throws Exception;

    /**
     * <p>物理删除。
     */
    //@RequestMapping(value = "/deleteData")
    public String deleteData(SysNews dto) throws Exception;

    /**
     * <li>恢复逻辑删除的数据。
     */
    //@RequestMapping(value = "/recoveryDataById")
    public String recoveryDataById(SysNews dto) throws Exception;

    /**
     * <li>逻辑删除。
     */
    //@RequestMapping(value = "/deleteDataById")
    public String deleteDataById(SysNews dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    //@RequestMapping(value = "/findDataIsPage")
    public List<SysNews> findDataIsPage(SysNews dto) throws Exception;

    /**
     * <p>信息列表。
     */
    //@RequestMapping(value = "/findDataIsList")
    public List<SysNews> findDataIsList(SysNews dto) throws Exception;

    /**
     * <p>信息详情。
     */
    //@RequestMapping(value = "/findDataById")
    public SysNews findDataById(SysNews dto) throws Exception;
}