/*	
 * 全景_评论 ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.02      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 pano System. - All Rights Reserved.
 *	
 */

package com.wu1g.web.controller.member.pano;

import com.github.pagehelper.PageInfo;
import com.wu1g.api.pano.IPanoCommentsService;
import com.wu1g.framework.Response;
import com.wu1g.framework.annotation.ALogOperation;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.vo.pano.PanoComments;
import com.wu1g.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

/**
 * <p>全景_评论 ACTION类。
 */
@Controller
@Slf4j
public class MemPanoCommentsController extends BaseController {

    private static final long serialVersionUID = -893683902158611114L;
    @Autowired
    private IPanoCommentsService panoCommentsService;

    // 全景_评论
    private static final String acPrefix = "/m/pano/comments/";
    private static final String init = "member/pano/pano_comments";
    private static final String list = "member/pano/pano_comments_list";
    private static final String success = "redirect:" + acPrefix + "init";

    /**
     * <p>初始化处理。
     */
    @RequiresPermissions("memPanoComment:menu")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "init")
    public String init() {
        log.info("PanoCommentsController init.........");
        return init;
    }

    /**
     * <p>信息列表 (未删除)。
     */
    @RequiresPermissions("memPanoComment:menu")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "list")
    public String list(PanoComments bean) {
        log.info("PanoCommentsController list.........");
        if (bean == null) {
            bean = new PanoComments();
        }
        bean.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
        setMember(bean);//会员标记
        // 信息列表
        PageInfo<?> page = new PageInfo<>(panoCommentsService.findDataIsPage(bean));
        request.setAttribute("beans", page.getList());
        // 分页对象-JSP标签使用-
        request.setAttribute(CommonConstant.PAGEROW_OBJECT_KEY, page);
        return list;
    }

    /**
     * <p>逻辑删除。
     */
    @RequiresPermissions("memPanoComment:del")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "del/{id}")
    @ALogOperation(type = "删除", desc = "全景评论信息")
    public String del(@PathVariable("id") Long id, RedirectAttributesModelMap modelMap) {
        log.info("PanoCommentsController del.........");
        Response result = new Response();
        try {
            PanoComments bean = new PanoComments();
            bean.setId(id);
            setMember(bean);//会员标记
            result.message = panoCommentsService.deleteDataById(bean);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        modelMap.addFlashAttribute("msg", result);
        return success;
    }
}