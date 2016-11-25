package com.wu1g.framework.service;

import com.github.pagehelper.PageInfo;
import com.wu1g.framework.IVO;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

public interface IBaseService {
    /**
     * <p>信息编辑。
     * <ol>[功能概要]
     * <li>新增信息。
     * <li>修改信息。
     */
    @RequestMapping(value = "/saveOrUpdateData")
    public String saveOrUpdateData(IVO vo) throws Exception;
    /**
     * <p>信息编辑。
     * <ol>[功能概要]
     * <li>物理删除。
     */
    @RequestMapping(value = "/deleteData")
    public String deleteData(IVO vo) throws Exception;
    /**
     * <p>信息 单条。
     * <ol>[功能概要]
     * <li>恢复逻辑删除的数据。
     */
    @RequestMapping(value = "/recoveryDataById")
    public String recoveryDataById(IVO vo) throws Exception;
    /**
     * <p>信息 单条。
     * <ol>[功能概要]
     * <li>逻辑删除。
     */
    @RequestMapping(value = "/deleteDataById")
    public String deleteDataById(IVO vo) throws Exception;
    /**
     * <p>信息列表 分页。
     * <ol>[功能概要]
     * <li>信息检索。
     * <li>分页。
     */
    @RequestMapping(value = "/findDataIsPage")
    public PageInfo findDataIsPage(IVO vo);
    /**
     * <p>信息列表。
     * <ol>[功能概要]
     * <li>信息检索。
     * <li>列表。
     */
    @RequestMapping(value = "/findDataIsList")
    public List<?> findDataIsList(IVO vo);
    /**
     * <p>信息详情。
     * <ol>[功能概要]
     * <li>信息检索。
     * <li>详情。
     */
    @RequestMapping(value = "/findDataById")
    public Objects findDataById(IVO vo);
}
