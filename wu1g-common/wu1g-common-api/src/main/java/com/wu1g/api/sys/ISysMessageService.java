package com.wu1g.api.sys;

import com.wu1g.vo.sys.SysMessage;
import java.util.List;

/**
 * <p>系统_站内信 业务处理接口类。
 */
//@FeignClient(value = "${spring.application.name}")
public interface ISysMessageService {
    /**
     * <p>信息编辑。
     */
    //@RequestMapping(value = "/saveOrUpdateData")
    public String saveOrUpdateData(SysMessage dto) throws Exception;

    /**
     * <p>物理删除。
     */
    //@RequestMapping(value = "/deleteData")
    public String deleteData(SysMessage dto) throws Exception;


    /**
     * <li>逻辑删除。
     */
    //@RequestMapping(value = "/deleteDataById")
    public String deleteDataById(SysMessage dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    //@RequestMapping(value = "/findDataIsPage")
    public List<SysMessage> findDataIsPage(SysMessage dto) throws Exception;

    /**
     * <p>信息列表。
     */
    //@RequestMapping(value = "/findDataIsList")
    public List<SysMessage> findDataIsList(SysMessage dto) throws Exception;

    /**
     * <p>信息详情。
     */
    //@RequestMapping(value = "/findDataById")
    public SysMessage findDataById(SysMessage dto) throws Exception;
}