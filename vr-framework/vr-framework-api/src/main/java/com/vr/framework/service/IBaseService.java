package com.vr.framework.service;

import com.github.pagehelper.PageInfo;
import com.vr.framework.IVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Objects;

public interface IBaseService {
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = "/saveOrUpdateData")
    public String saveOrUpdateData(IVO vo) throws Exception;
    /**
     * <li>物理删除。
     */
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = "/deleteData")
    public String deleteData(IVO vo) throws Exception;
    /**
     * <li>恢复逻辑删除的数据。
     */
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = "/recoveryDataById")
    public String recoveryDataById(IVO vo) throws Exception;
    /**
     * <li>逻辑删除。
     */
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = "/deleteDataById")
    public String deleteDataById(IVO vo) throws Exception;
    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = "/findDataIsPage")
    public PageInfo findDataIsPage(IVO vo);
    /**
     * <p>信息列表。
     */
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = "/findDataIsList")
    public List<?> findDataIsList(IVO vo);
    /**
     * <p>信息详情。
     */
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = "/findDataById")
    public Objects findDataById(IVO vo);
}
