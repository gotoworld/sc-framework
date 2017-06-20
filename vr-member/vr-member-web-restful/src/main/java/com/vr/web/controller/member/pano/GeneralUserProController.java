package com.vr.web.controller.member.pano;

import com.github.pagehelper.PageInfo;
import com.vr.api.pano.IPanoProjService;
import com.vr.framework.util.CommonConstant;
import com.vr.vo.pano.PanoProj;
import com.vr.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yjl on 2017/5/11.
 * desc 游客访问入口
 */
@RestController
@Slf4j
public class GeneralUserProController extends BaseController {

    @Autowired
    private IPanoProjService panoProjService;
    // 全景_项目
    private static final String acPrefix = "/g/pano/proj/";

    /**
     * <p>信息列表 (未删除)。
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "list",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public PageInfo list(PanoProj bean) {
        log.info("GeneralUserProController list.........");
        if (bean == null) {
            bean = new PanoProj();
        }
        bean.setType(0);
        bean.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
        // 信息列表
        PageInfo<?> page = new PageInfo<>(panoProjService.findDataIsPage(bean));
        return page;
    }
}
