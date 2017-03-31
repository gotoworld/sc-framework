package com.wu1g.api.sys;

import com.wu1g.vo.sys.SysSensitiveWords;
import java.util.List;

/**
 * <p>系统_敏感词 业务处理接口类。
 */
//@FeignClient(value = "${spring.application.name}")
public interface ISysSensitiveWordsService {
    /**
     * <p>信息编辑。
     */
    //@RequestMapping(value = "/saveOrUpdateData")
    public String saveOrUpdateData(SysSensitiveWords dto) throws Exception;

    /**
     * <p>物理删除。
     */
    //@RequestMapping(value = "/deleteData")
    public String deleteData(SysSensitiveWords dto) throws Exception;



    /**
     * <p>信息列表 分页。
     */
    //@RequestMapping(value = "/findDataIsPage")
    public List<SysSensitiveWords> findDataIsPage(SysSensitiveWords dto) throws Exception;

    /**
     * <p>信息列表。
     */
    //@RequestMapping(value = "/findDataIsList")
    public List<SysSensitiveWords> findDataIsList(SysSensitiveWords dto) throws Exception;

    /**
     * <p>信息详情。
     */
    //@RequestMapping(value = "/findDataById")
    public SysSensitiveWords findDataById(SysSensitiveWords dto) throws Exception;
}