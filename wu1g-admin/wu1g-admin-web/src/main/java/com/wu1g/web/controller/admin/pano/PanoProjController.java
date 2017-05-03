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

package com.wu1g.web.controller.admin.pano;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.wu1g.api.pano.IPanoMapService;
import com.wu1g.api.pano.IPanoProjService;
import com.wu1g.api.pano.IPanoSceneService;
import com.wu1g.api.sys.ISysCategoryService;
import com.wu1g.framework.Response;
import com.wu1g.framework.annotation.ALogOperation;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.vo.pano.PanoMap;
import com.wu1g.vo.pano.PanoProj;
import com.wu1g.vo.pano.PanoScene;
import com.wu1g.vo.pano.PanoSpots;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.*;
import java.util.Map.Entry;

/**
 * <p>全景_项目 ACTION类。
 */
@Controller
@Slf4j
public class PanoProjController extends BaseController {
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
    private static final String acPrefix = "/h/pano/proj/";
    private static final String init = "admin/pano/pano_proj";
    private static final String edit = "admin/pano/pano_proj_edit";
    private static final String list = "admin/pano/pano_proj_list";
    private static final String success = "redirect:" + acPrefix + "init";
    private static final String touredit = "admin/pano/tour_editor";

    /**
     * <p>初始化处理。
     */
    @RequiresPermissions("panoProj:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "init")
    public String init() {
        log.info("PanoProjController init.........");
        return init;
    }

    /**
     * <p>信息列表 (未删除)。
     */
    @RequiresPermissions("panoProj:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "list")
    public String list(PanoProj bean) {
        log.info("PanoProjController list.........");
        if (bean == null) {
            bean = new PanoProj();
        }
        bean.setType(0);
        bean.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
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
    @RequiresPermissions("panoProj:edit")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "edit/{id}")
    public String edit(PanoProj bean, @PathVariable("id") Long id) {
        log.info("PanoProjController edit.........");
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
                bean.setScenes(panoSceneService.findDataIsList(scene));
            }
        }
        if (bean == null || 0 == id) {
            bean = new PanoProj();
            bean.setCode(""+System.currentTimeMillis()+ (int)(Math.random()*1000000+100000));
            bean.setNewFlag(1);
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
    @RequiresPermissions("panoProj:del")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "del/{id}")
    @ALogOperation(type = "删除", desc = "全景项目")
    public String del(@PathVariable("id") Long id, RedirectAttributesModelMap modelMap) {
        log.info("PanoProjController del.........");
        Response result = new Response();
        try {
            PanoProj bean = new PanoProj();
            bean.setId(id);// id
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
    @RequiresPermissions(value = {"panoProj:add", "panoProj:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "全景项目信息")
    public String save(@Validated PanoProj bean, BindingResult bindingResult, RedirectAttributesModelMap modelMap) {
        log.info("PanoProjController save.........");
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
                    if ("1".equals(request.getParameter("makePanoFlag"))) {
                        List<PanoScene> scenes = new ArrayList<PanoScene>();
                        String[] scene_id_arr = request.getParameterValues("scene_id");
                        if (scene_id_arr != null && scene_id_arr.length > 0) {
                            for (int i = 0; i < scene_id_arr.length; i++) {
                                String scene_id = (scene_id_arr[i]);
                                PanoScene scene = new PanoScene();
                                scene.setId(scene_id);
                                scene.setOrderNo(i);
                                scene.setSceneSrc(request.getParameter(scene_id + "_scene_src"));
                                scene.setKeyword(request.getParameter(scene_id + "_scene_key"));
                                scene.setSceneTitle(request.getParameter(scene_id + "_scene_tit"));
                                scene.setCreateId(getUser().getId());
                                scenes.add(scene);
                            }
                        }
                        bean.setScenes(scenes);

                        bean.setMakePanoFlag(true);
                    } else {
                        bean.setMakePanoFlag(false);
                    }

                    if(bean.getIsComments()==null) bean.setIsComments(0);
                    if(bean.getIsPlanetoid()==null) bean.setIsPlanetoid(0);
                    if(bean.getIsFps()==null) bean.setIsFps(0);

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
            bean.setScenes(getScenesByjson(Long.parseLong(pid), scene_str));
            bean.setRadars(getRadarsByjson(Long.parseLong(pid), radars_str));
            panoProjService.saveXmlData(bean);

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

    private List<PanoScene> getScenesByjson(Long pid, String scene_str) {
        List<PanoScene> scenes = new ArrayList<PanoScene>();
        JSONObject dataJson = JSON.parseObject(scene_str);
        if (dataJson != null && dataJson.entrySet() != null && dataJson.entrySet().size() > 0) {
            for (Entry<String, Object> entiry : dataJson.entrySet()) {
                if (entiry.getValue() != null) {
                    JSONObject sceneJson = null;
                    try {
                        sceneJson = JSON.parseObject(entiry.getValue().toString());
                    } catch (Exception e) {

                    }
                    if (sceneJson != null && !sceneJson.isEmpty()) {
                        PanoScene scene = new PanoScene();
                        //项目id
                        scene.setProjId(pid);
                        //获取场景id
                        scene.setId(entiry.getKey());
                        //获取场景初始视角参数字符串
                        String viewStr = sceneJson.getString("view");
                        if (ValidatorUtil.notEmpty(viewStr)) {
                            JSONObject viewJson = JSON.parseObject(viewStr);
                            if (viewJson != null && !viewJson.isEmpty()) {
                                scene.setHlookat(viewJson.getString("hlookat"));
                                scene.setVlookat(viewJson.getString("vlookat"));
                            }
                        }
                        //获取场景跳转热点列表
                        String hotspotsStr = sceneJson.getString("hotspots");
                        if (ValidatorUtil.notEmpty(hotspotsStr) && !"[]".equals(hotspotsStr.replaceAll(" ", ""))) {
                            JSONArray spotsJsonArr = null;
                            try {
                                spotsJsonArr = JSONArray.parseArray(hotspotsStr);
                            } catch (Exception e) {
                            }
                            if (spotsJsonArr != null && !spotsJsonArr.isEmpty()) {
                                List<PanoSpots> spots = new ArrayList<PanoSpots>();
                                for (int i = 0; i < spotsJsonArr.size(); i++) {
                                    JSONObject spotsJson = spotsJsonArr.getJSONObject(i);
                                    if (spotsJson != null && !spotsJson.isEmpty()) {
                                        PanoSpots panoSpots = new PanoSpots();
//                                        panoSpots.setId(IdUtil.createUUID(32));
                                        panoSpots.setProjId(scene.getProjId());
                                        panoSpots.setSceneId(scene.getId());
                                        panoSpots.setHtype(0);
                                        panoSpots.setHname(spotsJson.getString("hname"));
                                        panoSpots.setAth(spotsJson.getString("ath"));
                                        panoSpots.setAtv(spotsJson.getString("atv"));
                                        panoSpots.setLinkedscene(spotsJson.getString("linkedscene"));
                                        panoSpots.setScale(spotsJson.getString("scale"));
                                        panoSpots.setDepth(spotsJson.getString("depth"));
                                        panoSpots.setRotate(spotsJson.getString("rotate"));
//                                        panoSpots.setUrl( spotsJson.getString( "url" ) );
                                        spots.add(panoSpots);
                                    }
                                }
                                scene.setHotSpots(spots);
                            }
                        }
                        //获取场景图片装饰列表
                        String picspotsStr = sceneJson.getString("picspots");
                        if (ValidatorUtil.notEmpty(picspotsStr) && !"[]".equals(picspotsStr.replaceAll(" ", ""))) {
                            JSONArray spotsJsonArr = null;
                            try {
                                spotsJsonArr = JSONArray.parseArray(picspotsStr);
                            } catch (Exception e) {
                            }
                            if (spotsJsonArr != null && !spotsJsonArr.isEmpty()) {
                                List<PanoSpots> spots = new ArrayList<PanoSpots>();
                                for (int i = 0; i < spotsJsonArr.size(); i++) {
                                    JSONObject spotsJson = spotsJsonArr.getJSONObject(i);
                                    if (spotsJson != null && !spotsJson.isEmpty()) {
                                        PanoSpots panoSpots = new PanoSpots();
//                                        panoSpots.setId(IdUtil.createUUID(32));
                                        panoSpots.setProjId(scene.getProjId());
                                        panoSpots.setSceneId(scene.getId());
                                        panoSpots.setHtype(1);
                                        panoSpots.setHname(spotsJson.getString("hname"));
                                        panoSpots.setAth(spotsJson.getString("ath"));
                                        panoSpots.setAtv(spotsJson.getString("atv"));
                                        panoSpots.setLinkedscene(spotsJson.getString("linkedscene"));
                                        panoSpots.setScale(spotsJson.getString("scale"));
                                        panoSpots.setDepth(spotsJson.getString("depth"));
                                        panoSpots.setRotate(spotsJson.getString("rotate"));
                                        panoSpots.setUrl(spotsJson.getString("url"));
                                        panoSpots.setIsOnclick(spotsJson.getInteger("isOnclick"));
                                        spots.add(panoSpots);
                                    }
                                }
                                scene.setImgSpots(spots);
                            }
                        }
                        scenes.add(scene);
                    }
                }
            }
        }
        return scenes;
    }

    private List<PanoMap> getRadarsByjson(Long pid, String radars_str) {
        List<PanoMap> radars = new ArrayList<PanoMap>();
        JSONObject radarsJson = JSON.parseObject(radars_str);
        if (radarsJson != null && radarsJson.entrySet() != null && radarsJson.entrySet().size() > 0) {
            for (Entry<String, Object> entiry : radarsJson.entrySet()) {
                if (entiry.getValue() != null) {
                    JSONObject mapJson = null;
                    try {
                        mapJson = JSON.parseObject(entiry.getValue().toString());
                    } catch (Exception e) {

                    }
                    if (mapJson != null && !mapJson.isEmpty()) {
                        PanoMap map = new PanoMap();
                        //项目id
                        map.setProjId(pid);
                        //获取场景id
                        map.setSceneId(entiry.getKey());
                        //--
                        map.setRotate(mapJson.getString("rotate"));
                        map.setX(mapJson.getString("x"));
                        map.setY(mapJson.getString("y"));
                        radars.add(map);
                    }
                }
            }
        }
        return radars;
    }

    @RequiresPermissions(value = {"panoProj:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/h/touredit/{id}")
    public String touredit(@PathVariable("id") Long id) {
        log.info("PanoProjController touredit.........");
        PanoProj bean = new PanoProj();
        bean.setId(id);
        bean.setType(0);
        PanoProj proj = panoProjService.findDataById(bean);
        PanoScene sceneDto = new PanoScene();
        sceneDto.setProjId(proj.getId());
        List<PanoScene> sceneList = (List<PanoScene>) panoSceneService.findDataIsList(sceneDto);
        if (sceneList != null && sceneList.size() > 0) {
            PanoSpots panoSpots = null;
            for (PanoScene scene : sceneList) {
                scene.setBreakdownImg(getBreakdownImg(proj.getId(), scene.getSceneSrc()));
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

    private String getBreakdownImg(Long projId, String sceneSrc) {
        sceneSrc = sceneSrc.substring(sceneSrc.lastIndexOf("/") + 1, sceneSrc.indexOf("."));
        return "/upload/image/n4/" + projId + "/vtour/panos/" + sceneSrc + ".tiles/";
    }
}