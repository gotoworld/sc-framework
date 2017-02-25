package com.wu1g.pano.web.controller.client;

import com.alibaba.fastjson.JSON;
import com.wu1g.api.pano.IPanoCommentsService;
import com.wu1g.api.pano.IPanoProjService;
import com.wu1g.api.pano.IPanoSceneService;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.vo.pano.PanoComments;
import com.wu1g.vo.pano.PanoProj;
import com.wu1g.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 全景_图 ACTION类。
 */
@Controller
@Slf4j
public class TourController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IPanoProjService panoProjService;
    @Autowired
    private IPanoSceneService panoSceneService;
    @Autowired
    private IPanoCommentsService panoCommentsService;
    // 全景_项目
    private static final String acPrefix = "/s/pano/";

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "thumbsUpNum")
    @ResponseBody
    public String thumbsUpNum() {
        log.info("PanoImgController thumbsUpNum.........");
        Map msg = new HashMap();
        msg.put("status", 1);
        try {
            String pid = request.getParameter("pid");//项目id
            PanoProj bean = new PanoProj();
            bean.setId(Long.parseLong(pid));
            panoProjService.thumbsUpNum(bean);

            bean = panoProjService.findDataById(bean);
            if (bean != null) {
                msg.put("count", bean.getThumbsUpNum());
            } else {
                msg.put("count", 0);
            }
        } catch (Exception e) {
            log.error("点赞失败!", e);
            msg = new HashMap();
            msg.put("status", 0);
            msg.put("msg", e.getMessage());
        }
        return JSON.toJSONString(msg);
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "getComment")
    @ResponseBody
    public String getComment() {
        log.info("PanoImgController getComment.........");
        Map msg = new HashMap();
        msg.put("status", 1);
        try {
            String pid = request.getParameter("pid");//项目id
            String sceneId = request.getParameter("sceneId");//场景id
            PanoComments bean = new PanoComments();
            bean.setSceneId(sceneId);
            List beans = panoCommentsService.findDataIsList(bean);
            msg.put("list", beans);
        } catch (Exception e) {
            msg = new HashMap();
            msg.put("status", 0);
            msg.put("msg", e.getMessage());
        }
        return JSON.toJSONString(msg);
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "addComment")
    @ResponseBody
    public String addComment() {
        log.info("PanoImgController addComment.........");
        Map msg = new HashMap();
        msg.put("status", 1);
        try {
            String pid = request.getParameter("pid");//项目id
            String sceneId = request.getParameter("sceneId");//场景id
            String ath = request.getParameter("ath");//水平坐标
            String atv = request.getParameter("atv");//垂直坐标
            String content = request.getParameter("content");//评论内容

            if (ValidatorUtil.isNullEmpty(pid)
                    || ValidatorUtil.isNullEmpty(sceneId)
                    || ValidatorUtil.isNullEmpty(ath)
                    || ValidatorUtil.isNullEmpty(atv)
                    || ValidatorUtil.isNullEmpty(content)) {
                throw new RuntimeException();
            }

            PanoComments bean = new PanoComments();
            bean.setSceneId(sceneId);
            bean.setAth(ath);
            bean.setAtv(atv);
            bean.setContent(content);
            panoCommentsService.saveOrUpdateData(bean);
        } catch (Exception e) {
            log.error("评论失败!", e);
            msg = new HashMap();
            msg.put("status", 0);
            msg.put("msg", e.getMessage());
        }
        return JSON.toJSONString(msg);
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "pvNum")
    @ResponseBody
    public String pvNum() {
        log.info("PanoImgController pvNum.........");
        Map msg = new HashMap();
        msg.put("status", 1);
        try {
            String pid = request.getParameter("pid");//项目id
            PanoProj bean = new PanoProj();
            bean.setId(Long.parseLong(pid));
            panoProjService.pvNum(bean);

            bean = panoProjService.findDataById(bean);
            if (bean != null) {
                msg.put("count", bean.getPvNum());
            } else {
                msg.put("count", 0);
            }
        } catch (Exception e) {
            log.error("增加浏览量失败!", e);
            msg = new HashMap();
            msg.put("status", 0);
            msg.put("msg", e.getMessage());
        }
        return JSON.toJSONString(msg);
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/tour/{id}")
    public String tour(@PathVariable("id") Long id) {
        log.info("PanoImgController tour.........");
        PanoProj bean = new PanoProj();
        bean.setId(id);
        bean.setType(0);
        PanoProj proj = panoProjService.findDataById(bean);
        request.setAttribute("proj", proj);
        request.setAttribute("basePath", getBasePath());
        request.setAttribute("panoPath", getBasePath() + "/plugins/krpano/");
        return "client/pano/tour";
    }
}