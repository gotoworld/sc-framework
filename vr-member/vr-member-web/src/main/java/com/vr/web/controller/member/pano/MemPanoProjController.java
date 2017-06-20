/*	
 * 全景_项目 ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.02      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 pano System. - All Rights Reserved.
 *	
 */

package com.vr.web.controller.member.pano;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.vr.api.pano.IPanoMapService;
import com.vr.api.pano.IPanoProjService;
import com.vr.api.pano.IPanoSceneService;
import com.vr.api.sys.ISysCategoryService;
import com.vr.framework.Response;
import com.vr.framework.annotation.ALogOperation;
import com.vr.framework.annotation.RfAccount2Bean;
import com.vr.framework.util.CommonConstant;
import com.vr.framework.util.KrSceneImageUtil;
import com.vr.framework.util.ValidatorUtil;
import com.vr.vo.pano.PanoMap;
import com.vr.vo.pano.PanoProj;
import com.vr.vo.pano.PanoScene;
import com.vr.vo.pano.PanoSpots;
import com.vr.vo.sys.SysCategory;
import com.vr.web.controller.BaseController;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * <p>全景_项目 ACTION类。
 */
@Controller
@Slf4j
public class MemPanoProjController extends BaseController {
	private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IPanoProjService panoProjService;
    @Autowired
    private IPanoMapService panoMapService;
    @Autowired
    private IPanoSceneService panoSceneService;
    @Autowired
    protected ISysCategoryService sysCategoryService;
    // 全景_项目
    private static final String acPrefix = "/m/pano/proj/";
    private static final String init = "member/pano/pano_proj";
    private static final String edit = "member/pano/pano_proj_edit";
    private static final String list = "member/pano/pano_proj_list";
    private static final String success = "redirect:" + acPrefix + "init";
    private static final String touredit = "member/pano/tour_editor";

    /**
     * <p>初始化处理。
     */
    @RequiresPermissions("memPanoProj:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "init")
    public String init() {
        log.info("MemPanoProjController init.........");
        return init;
    }

    /**
     * <p>信息列表 (未删除)。
     */
    @RequiresPermissions("memPanoProj:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "list")
    public String list(PanoProj bean) {
        log.info("MemPanoProjController list.........");
        if (bean == null) {
            bean = new PanoProj();
        }
        bean.setType(0);
        bean.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
        setMember(bean);//会员标记
        // 信息列表
        PageInfo<?> page = new PageInfo<>(panoProjService.findDataIsPage(bean));
        request.setAttribute("beans", page.getList());
        // 分页对象-JSP标签使用-
        request.setAttribute(CommonConstant.PAGEROW_OBJECT_KEY, page);
        return list;
    }

    /**
     * <p> 编辑。
     */
    @RequiresPermissions("memPanoProj:edit")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "edit/{id}")
    public String edit(PanoProj bean, @PathVariable("id") Long id) {
        log.info("MemPanoProjController edit.........");
        int pageNum = getPageNum(bean);
        if (id != 0) {
            if (bean == null) {
                bean = new PanoProj();
            }
            bean.setId(id);
            bean = panoProjService.findDataById(bean);
            if (bean != null) {
                PanoScene scene = new PanoScene();
                scene.setProjId(bean.getId());
                scene.setProjCode(bean.getCode());
                bean.setScenes(panoSceneService.findDataIsList(scene));
            }
        }
        if (bean == null || 0 == id) {
            bean = new PanoProj();
            bean.setCode(""+System.currentTimeMillis()+ (int)(Math.random()*1000000+100000));
            bean.setNewFlag(1);
        }
        bean.setPageNum(pageNum);
        setMember(bean);//会员标记
        request.setAttribute("bean", bean);
        //获取类目列表
        SysCategory sysCategory = new SysCategory();
        sysCategory.setType(2);
        setMember(sysCategory);//会员标记
        request.setAttribute("nodes",sysCategoryService.findDataTree(sysCategory));
        return edit;
    }

    /**
     * <p>逻辑删除。
     */
    @RequiresPermissions("memPanoProj:del")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "del/{id}")
    @ALogOperation(type = "删除", desc = "全景项目")
    public String del(@PathVariable("id") Long id, RedirectAttributesModelMap modelMap) {
        log.info("MemPanoProjController del.........");
        Response result = new Response();
        try {
            PanoProj bean = new PanoProj();
            bean.setId(id);// id
            setMember(bean);//会员标记
            result.message = panoProjService.deleteDataById(bean);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        modelMap.addFlashAttribute("result", result);
        return success;
    }

    /**
     * <p>信息保存
     */
    @RequiresPermissions(value = {"memPanoProj:add", "memPanoProj:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "全景项目信息")
    public String save(@Validated PanoProj bean, BindingResult bindingResult, RedirectAttributesModelMap modelMap) {
        log.info("MemPanoProjController save.........");
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
                    if ("1".equals(request.getParameter("refreshSceneFlag"))||"1".equals(request.getParameter("makePanoFlag"))) {
                        List<PanoScene> scenes = new ArrayList<PanoScene>();
                        String[] scene_id_arr = request.getParameterValues("scene_id");
                        if (scene_id_arr != null && scene_id_arr.length > 0) {
                            for (int i = 0; i < scene_id_arr.length; i++) {
                                String scene_id = (scene_id_arr[i]);
                                PanoScene scene = new PanoScene();
                                scene.setId(scene_id);
                                scene.setOrderNo(i);
                                scene.setSceneSrc(request.getParameter(scene_id + "_scene_src"));
                                scene.setOrderNo(Integer.parseInt("" + request.getParameter(scene_id + "_scene_order_no")));
                                scene.setKeyword(request.getParameter(scene_id + "_scene_key"));
                                scene.setSceneTitle(request.getParameter(scene_id + "_scene_tit"));
                                scene.setCreateId(getUser().getId());
                                scenes.add(scene);
                            }
                        }
                        bean.setScenes(scenes);
                    }
                    if ("1".equals(request.getParameter("makePanoFlag"))) {
                        bean.setMakePanoFlag(true);
                    } else {
                        bean.setMakePanoFlag(false);
                    }

                    if(bean.getIsComments()==null) bean.setIsComments(0);
                    if(bean.getIsPlanetoid()==null) bean.setIsPlanetoid(0);
                    if(bean.getIsFps()==null) bean.setIsFps(0);

                    setMember(bean);//会员标记

                    result.message = panoProjService.saveOrUpdateData(bean);
                    result.data = bean.getId();

                    bean.setStr(getBasePath());
                    request.getSession().setAttribute(acPrefix + "save." + bean.getToken(), "1");
                }
            } catch (Exception e) {
                result = Response.error(e.getMessage());
            }
        } else {
            result = Response.error("信息保存失败!");
        }
        modelMap.addFlashAttribute("result", result);
        return success;
    }

    @RequiresPermissions(value = {"panoProj:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "xmlsave")
    @ResponseBody
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "漫游场景信息")
    public String xmlsave() {
        log.info("PanoProjController xmlsave.........");
        Map msg = new HashMap();
        msg.put("status", 1);
        msg.put("msg", "保存成功");
        try {
            String pid = request.getParameter("pid");//项目id
            String scene_str = request.getParameter("data");//场景信息
            String radars_str = request.getParameter("radars");//导览图信息
            PanoProj bean = new PanoProj();
            bean.setId(Long.parseLong(pid));
            bean.setTourEditJson(scene_str);
            bean.setScenes(panoProjService.getScenesByjson(Long.parseLong(pid), scene_str));
            bean.setRadars(panoProjService.getRadarsByjson(Long.parseLong(pid), radars_str));
            panoProjService.saveXmlData(bean);
            setMember(bean);//会员标记
            bean.setStr(getBasePath());
            bean.setMakePanoFlag(false);
            //生成全景图
            panoProjService.makePano(bean);

        } catch (Exception e) {
            log.error("保存场景编辑信息失败!", e);
            msg = new HashMap();
            msg.put("status", 0);
            msg.put("msg", e.getMessage());
        }
        return JSON.toJSONString(msg);
    }

    @RequiresPermissions(value = {"panoProj:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/m/touredit/{id}")
    public String touredit(@PathVariable("id") Long id) {
        log.info("PanoProjController touredit.........");
        PanoProj bean = new PanoProj();
        bean.setId(id);
        bean.setType(0);
        setMember(bean);//会员标记
        PanoProj proj = panoProjService.findDataById(bean);
        PanoScene sceneDto = new PanoScene();
        sceneDto.setProjId(proj.getId());
        List<PanoScene> sceneList = (List<PanoScene>) panoSceneService.findDataIsList(sceneDto);
        if (sceneList != null && sceneList.size() > 0) {
            PanoSpots panoSpots = null;
            for (PanoScene scene : sceneList) {
                scene.setBreakdownImg(KrSceneImageUtil.getBreakdownImg(proj.getCode(), scene.getSceneSrc()));
            }
        }
        //--获取导览图坐标信息
        if (ValidatorUtil.notEmpty(proj.getMapSrc())) {
            PanoMap panoMap = new PanoMap();
            panoMap.setProjId(proj.getId());
            proj.setRadars((List<PanoMap>) panoMapService.findDataIsList(panoMap));
        }
        proj.setScenes(sceneList);
        request.setAttribute("proj", proj);
        request.setAttribute("basePath", getBasePath());
        request.setAttribute("panoPath", getBasePath() + "/plugins/krpano/");
        return touredit;
    }
}