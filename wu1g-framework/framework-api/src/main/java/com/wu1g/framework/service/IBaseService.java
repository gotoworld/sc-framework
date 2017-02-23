package com.wu1g.framework.service;

import com.github.pagehelper.PageInfo;
import com.wu1g.framework.IVO;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

public interface IBaseService {
    /**
     * <p>信息编辑。
     */
    @RequestMapping(value = "/saveOrUpdateData")
    public String saveOrUpdateData(IVO vo) throws Exception;
    /**
     * <li>物理删除。
     */
    @RequestMapping(value = "/deleteData")
    public String deleteData(IVO vo) throws Exception;
    /**
     * <li>恢复逻辑删除的数据。
     */
    @RequestMapping(value = "/recoveryDataById")
    public String recoveryDataById(IVO vo) throws Exception;
    /**
     * <li>逻辑删除。
     */
    @RequestMapping(value = "/deleteDataById")
    public String deleteDataById(IVO vo) throws Exception;
    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(value = "/findDataIsPage")
    public PageInfo findDataIsPage(IVO vo);
    /**
     * <p>信息列表。
     */
    @RequestMapping(value = "/findDataIsList")
    public List<?> findDataIsList(IVO vo);
    /**
     * <p>信息详情。
     */
    @RequestMapping(value = "/findDataById")
    public Objects findDataById(IVO vo);
}
