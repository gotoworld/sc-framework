package com.wu1g.api.sys;

import com.wu1g.vo.sys.SysSensitiveWords;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>系统_敏感词 业务处理接口类。
 */
@FeignClient(name = "${feign.name}")//, fallback = TestServiceHystrix.class)
public interface ISysSensitiveWordsService {
    String actPrefix = "/api/ISysSensitiveWordsService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(value = actPrefix + "/saveOrUpdateData")
    public String saveOrUpdateData(SysSensitiveWords dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(value = actPrefix + "/deleteData")
    public String deleteData(SysSensitiveWords dto) throws Exception;



    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(value = actPrefix + "/findDataIsPage")
    public List<SysSensitiveWords> findDataIsPage(SysSensitiveWords dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(value = actPrefix + "/findDataIsList")
    public List<SysSensitiveWords> findDataIsList(SysSensitiveWords dto) throws Exception;

    /**
     * <p>信息详情。
     */
    @RequestMapping(value = actPrefix + "/findDataById")
    public SysSensitiveWords findDataById(SysSensitiveWords dto) throws Exception;
}