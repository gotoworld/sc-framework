package com.wu1g.api.sys;

import com.wu1g.vo.sys.SysMessage;

import java.util.List;

/**
 * <p>系统消息   业务处理接口类。
 */
public interface ISysMessageService {

    /**
     * <p>信息编辑。
     */
    public String saveOrUpdateData(SysMessage bean) throws Exception;

    /**
     * <p>物理删除。
     */
    public String deleteData(SysMessage bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    public String recoveryDataById(SysMessage bean) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    public String deleteDataById(SysMessage bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    public List<SysMessage> findDataIsPage(SysMessage bean);

    /**
     * <p>信息列表。
     */
    public List<SysMessage> findDataIsList(SysMessage bean);

    /**
     * <p>信息详情。
     */
    public SysMessage findDataById(SysMessage bean);
}