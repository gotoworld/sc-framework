/*	
 * 全景_视频 ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.05      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 pano System. - All Rights Reserved.
 *	
 */

package com.wu1g.web.controller.admin.pano;

import com.github.pagehelper.PageInfo;
import com.wu1g.api.pano.IPanoProjService;
import com.wu1g.api.pano.IPanoSceneService;
import com.wu1g.api.sys.ISysCategoryService;
import com.wu1g.framework.Response;
import com.wu1g.framework.annotation.ALogOperation;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.vo.pano.PanoProj;
import com.wu1g.vo.pano.PanoScene;
import com.wu1g.vo.sys.SysCategory;
import com.wu1g.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 全景_视频 ACTION类。
 */
@Controller
@Slf4j
public class PanoVideoController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    /**
     * 全景_项目 业务处理
     */
    @Autowired
    private IPanoProjService panoProjService;
    @Autowired
    private IPanoSceneService panoSceneService;
    @Autowired
    protected ISysCategoryService sysCategoryService;
    // 全景_项目
    private static final String acPrefix = "/h/video/";
    private static final String init = "admin/pano/video";
    private static final String edit = "admin/pano/video_edit";
    private static final String list = "admin/pano/video_list";
    private static final String success = "redirect:" + acPrefix + "init";

    /**
     * <p>
     * 初始化处理。
     */
    @RequiresPermissions("video:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "init")
    public String init() {
        log.info("PanoVideoController init.........");
//        SysCategory dto = new SysCategory();
//        dto.setType(2);
//        request.setAttribute("nodes", sysCategoryService.findDataTree(dto));
        return init;
    }

    /**
     * <p>
     * 信息列表 (未删除)。
     */
    @RequiresPermissions("video:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "list")
    public String list(PanoProj bean) {
        log.info("PanoVideoController list.........");
        if (bean == null) {
            bean = new PanoProj();
        }
        bean.setType(1);
        bean.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
        // 信息列表
        PageInfo<?> page = new PageInfo<>(panoProjService.findDataIsPage(bean));
        request.setAttribute("beans", page.getList());
        // 分页对象-JSP标签使用-
        request.setAttribute(CommonConstant.PAGEROW_OBJECT_KEY, page);
        return list;
    }

    /**
     * <p>编辑。
     */
    @RequiresPermissions("video:edit")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "edit/{id}")
    public String edit(PanoProj bean, @PathVariable("id") Long id) {
        log.info("PanoVideoController edit.........");
        int pageNum = getPageNum(bean);
        if (id != 0) {
            if (bean == null) {
                bean = new PanoProj();
            }
            bean.setId(id);// id
            bean = panoProjService.findDataById(bean);
            if (bean != null) {
                PanoScene scene = new PanoScene();
                scene.setProjId(bean.getId());
                bean.setScenes(panoSceneService.findDataIsList(scene));
            }
        }
        bean.setPageNum(pageNum);
        request.setAttribute("bean", bean);
        //获取类目列表
        SysCategory sysCategory = new SysCategory();
        sysCategory.setType(2);
        request.setAttribute("nodes",sysCategoryService.findDataTree(sysCategory));
        return edit;
    }

    /**
     * <p>逻辑删除。
     */
    @RequiresPermissions("video:del")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "del/{id}")
    @ALogOperation(type = "删除", desc = "全景视频信息")
    public String del(@PathVariable("id") Long id, RedirectAttributesModelMap modelMap) {
        log.info("PanoVideoController del.........");
        Response result = new Response();
        try {
            PanoProj bean = new PanoProj();
            bean.setId(id);// id
            result.message = panoProjService.deleteDataById(bean);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        modelMap.addFlashAttribute("msg", result);
        return success;
    }

    /**
     * <p>信息保存
     */
    @RequiresPermissions(value = {"video:add", "video:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "全景视频信息")
    public String save(@Validated PanoProj bean, BindingResult bindingResult, RedirectAttributesModelMap modelMap) {
        log.info("PanoVideoController save.........");
        Response result = new Response();
        if (bean != null) {
            try {
                if ("1".equals(request.getSession().getAttribute(acPrefix + "save." + bean.getToken()))) {
                    throw new RuntimeException("请不要重复提交!");
                }
                if (bindingResult.hasErrors()) {
                    String errorMsg = "";
                    List<ObjectError> errorList = bindingResult.getAllErrors();
                    for (ObjectError error : errorList) {
                        errorMsg += (error.getDefaultMessage()) + ";";
                    }
                    result = Response.error(errorMsg);
                } else {
                    List<PanoScene> scenes = new ArrayList<PanoScene>();
                    String[] scene_id_arr = request.getParameterValues("scene_id");
                    if (scene_id_arr != null && scene_id_arr.length > 0) {
                        for (int i = 0; i < scene_id_arr.length; i++) {
                            String scene_id = scene_id_arr[i];
                            PanoScene scene = new PanoScene();
                            scene.setId(scene_id);
                            scene.setProjId(bean.getId());
                            scene.setOrderNo(i);
                            scene.setSceneSrc(request.getParameter(scene_id + "_scene_src"));
                            scene.setSceneTitle(request.getParameter(scene_id + "_scene_tit"));
                            scene.setCreateId(bean.getId());
                            scenes.add(scene);
                        }
                    }
                    bean.setScenes(scenes);
                    result.message = panoProjService.saveOrUpdateData(bean);
                    result.data = bean.getId();

                    bean.setStr(getBasePath());
                    panoProjService.makeVideo(bean);

                    request.getSession().setAttribute(acPrefix + "save." + bean.getToken(), "1");
                }
                result.data = bean.getId();
            } catch (Exception e) {
                result = Response.error(e.getMessage());
            }
        } else {
            result = Response.error("信息保存失败!");
        }
        modelMap.addFlashAttribute("msg", result);
        return success;
    }
}