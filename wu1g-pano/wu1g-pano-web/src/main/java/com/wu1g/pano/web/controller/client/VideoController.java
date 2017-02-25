package com.wu1g.pano.web.controller.client;

import com.wu1g.api.pano.IPanoProjService;
import com.wu1g.vo.pano.PanoProj;
import com.wu1g.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 全景_视频 ACTION类。
 */
@Controller
@Slf4j
public class VideoController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IPanoProjService panoProjService;

    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = "/video/{id}")
    public String video(@PathVariable("id") Long id) {
        log.info("PanoVideoController video.........");
        PanoProj bean = new PanoProj();
        bean.setId(id);
        bean.setType(1);
        PanoProj proj = panoProjService.findDataById(bean);
        request.setAttribute("proj", proj);
        request.setAttribute("basePath", getBasePath());
        request.setAttribute("panoPath", getBasePath() + "/plugins/krpano/");
        return "client/pano/video";
    }
}