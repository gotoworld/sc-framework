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

package com.wu1g.pano.web.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.wu1g.framework.Response;
import com.wu1g.framework.annotation.ALogOperation;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.framework.web.controller.BaseController;
import com.wu1g.org.vo.OrgUser;
import com.wu1g.pano.api.IPanoCategoryService;
import com.wu1g.pano.api.IPanoMapService;
import com.wu1g.pano.api.IPanoProjService;
import com.wu1g.pano.api.IPanoSceneService;
import com.wu1g.pano.vo.PanoMap;
import com.wu1g.pano.vo.PanoProj;
import com.wu1g.pano.vo.PanoScene;
import com.wu1g.pano.vo.PanoSpots;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
public class PanoProjController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IPanoProjService panoProjService;
    @Autowired
    private IPanoCategoryService panoCategoryService;
    @Autowired
    private IPanoMapService panoMapService;
    @Autowired
    private IPanoSceneService panoSceneService;
    // 全景_项目
    private static final String acPrefix = "/h/pano/proj/";
    private static final String init = "admin/pano/pano_proj";
    private static final String edit = "admin/pano/pano_proj_edit";
    private static final String list = "admin/pano/pano_proj_list";
    private static final String success = "redirect:" + acPrefix + "init";
    private static final String touredit = "admin/pano/tour_editor";

    /**
     * <p>
     * 初始化处理。
     * <ol>
     * [功能概要]
     * <li>初始化处理。
     */
    @RequiresPermissions("pano02:init")
    @RequestMapping(value = acPrefix + "init")
    public String init() {
        log.info("PanoProjController init.........");
        return init;
    }

    /**
     * <p>
     * 信息列表 (未删除)。
     * <ol>
     * [功能概要]
     * <li>信息列表。
     */
    @RequiresPermissions("pano02:init")
    @RequestMapping(value = acPrefix + "list")
    public String list(PanoProj bean) {
        log.info("PanoProjController list.........");
        if (bean == null) {
            bean = new PanoProj();
        }
        bean.setType("0");
        bean.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
        // 信息列表
        PageInfo<?> page = new PageInfo<>(panoProjService.findDataIsPage(bean));
        request.setAttribute("beans", page.getList());
        // 分页对象-JSP标签使用-
        request.setAttribute(CommonConstant.PAGEROW_OBJECT_KEY, page);
        return list;
    }

    /**
     * <p>
     * 编辑。
     * <p>
     * <ol>
     * [功能概要]
     * <li>编辑。
     */
    @RequiresPermissions("pano02:edit")
    @RequestMapping(value = acPrefix + "edit/{id}")
    @ALogOperation(type="查询",desc = "进入编辑页面")
    public String edit(PanoProj bean, @PathVariable("id") String id) {
        log.info("PanoProjController edit.........");
        int pageNum = 1;
        if (bean != null && bean.getPageNum() != null) {
            pageNum = bean.getPageNum();
        }
        if ("add".equals(id)) {
            id = null;
            bean = null;
        }
        if (ValidatorUtil.notEmpty(id)) {
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
        if (bean == null) {
            bean = new PanoProj();
            bean.setId(IdUtil.createUUID(32));// id
        }
        bean.setPageNum(pageNum);
        bean.setToken(IdUtil.createUUID(32));
        request.setAttribute("bean", bean);

        //获取类目列表
        request.setAttribute("categoryBeans", panoCategoryService.findDataTree(null));

        return edit;
    }

    /**
     * <p>
     * 删除。
     * <ol>
     * [功能概要]
     * <li>逻辑删除。
     */
    @RequiresPermissions("pano02:del")
    @RequestMapping(value = acPrefix + "del/{id}")
    @ALogOperation(type="删除",desc = "删除全景项目")
    public String del(@PathVariable("id") String id, RedirectAttributesModelMap modelMap) {
        log.info("PanoProjController del.........");

        // String id=request.getParameter("id");//id
        PanoProj bean = new PanoProj();
        bean.setId(id);// id
        String msg = "1";
        try {
            msg = panoProjService.deleteDataById(bean);
        } catch (Exception e) {
            msg = e.getMessage();
        }
        modelMap.addFlashAttribute("msg", msg);
        return success;
    }

    /**
     * <p>
     * 信息保存
     * <ol>
     * [功能概要]
     * <li>新增。
     * <li>修改。
     */
    @RequiresPermissions(value = {"pano02:add", "pano02:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.POST}, value = acPrefix + "save")
    @ALogOperation(desc = "编辑全景项目")
    public String save(PanoProj bean, RedirectAttributesModelMap modelMap, BindingResult bindingResult) {
        log.info("PanoProjController save.........");
        Response result = new Response();
        if (bean != null) {
            try {
                if ("1".equals(request.getSession().getAttribute(acPrefix + "save." + bean.getToken()))) {
                    throw new RuntimeException("请不要重复提交!");
                } else {
                    request.getSession().setAttribute(acPrefix + "save." + bean.getToken(), "1");
                }
                if (bindingResult.hasErrors()) {
                    String errorMsg = "";
                    List<ObjectError> errorList = bindingResult.getAllErrors();
                    for (ObjectError error : errorList) {
                        errorMsg += (error.getDefaultMessage()) + ";";
                    }
                    result = Response.error(errorMsg);
                } else {
//                    OrgUser user = (OrgUser) request.getSession().getAttribute(CommonConstant.SESSION_KEY_USER);
//                    if (user != null) {
//                        bean.setCreateIp(getIpAddr());
//                        bean.setCreateId(user.getId());
//                        bean.setUpdateIp(getIpAddr());
//                        bean.setUpdateId(user.getId());
//                    }
                    if ("1".equals(request.getParameter("makePanoFlag"))) {
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
                                scene.setKeyword(request.getParameter(scene_id + "_scene_key"));
                                scene.setSceneTitle(request.getParameter(scene_id + "_scene_tit"));

//                                scene.setCreateIp(getIpAddr());
//                                scene.setCreateId(user.getId());
//                                scene.setUpdateIp(getIpAddr());
//                                scene.setUpdateId(user.getId());

                                scenes.add(scene);
                            }
                        }
                        bean.setScenes(scenes);

                        bean.setMakePanoFlag(true);
                    } else {
                        bean.setMakePanoFlag(false);
                    }
                    result.message = panoProjService.saveOrUpdateData(bean);
                    result.data = bean.getId();

                    bean.setStr(getBasePath());
                    //生成全景图
                    panoProjService.makePano(bean);
                }
            } catch (Exception e) {
                result = Response.error(e.getMessage());
            }
        } else {
            result = Response.error("信息保存失败!");
        }
        modelMap.addFlashAttribute("msg", result);
        return success;
    }

    @RequiresPermissions(value = {"pano02:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.POST}, value = acPrefix + "xmlsave")
    @ResponseBody
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
            bean.setId(pid);
            bean.setXmlData(scene_str);
            bean.setScenes(getScenesByjson(pid, scene_str));
            bean.setRadars(getRadarsByjson(pid, radars_str));
//            OrgUser user = (OrgUser) request.getSession().getAttribute(CommonConstant.SESSION_KEY_USER);
//            if (user != null) {
//                bean.setCreateIp(getIpAddr());
//                bean.setCreateId(user.getId());
//                bean.setUpdateIp(getIpAddr());
//                bean.setUpdateId(user.getId());
//            }
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

    private List<PanoScene> getScenesByjson(String pid, String scene_str) {
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
                                        panoSpots.setId(IdUtil.createUUID(32));
                                        panoSpots.setProjId(scene.getProjId());
                                        panoSpots.setSceneId(scene.getId());
                                        panoSpots.setHtype("0");
                                        panoSpots.setHname(spotsJson.getString("hname"));
                                        panoSpots.setAth(spotsJson.getString("ath"));
                                        panoSpots.setAtv(spotsJson.getString("atv"));
                                        panoSpots.setLinkedscene(spotsJson.getString("linkedscene"));
                                        panoSpots.setScale(spotsJson.getString("scale"));
                                        panoSpots.setDepth(spotsJson.getString("depth"));
                                        panoSpots.setRotate(spotsJson.getString("rotate"));
                                        //panoSpots.setUrl( spotsJson.getString( "url" ) );
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
                                        panoSpots.setId(IdUtil.createUUID(32));
                                        panoSpots.setProjId(scene.getProjId());
                                        panoSpots.setSceneId(scene.getId());
                                        panoSpots.setHtype("1");
                                        panoSpots.setHname(spotsJson.getString("hname"));
                                        panoSpots.setAth(spotsJson.getString("ath"));
                                        panoSpots.setAtv(spotsJson.getString("atv"));
                                        panoSpots.setLinkedscene(spotsJson.getString("linkedscene"));
                                        panoSpots.setScale(spotsJson.getString("scale"));
                                        panoSpots.setDepth(spotsJson.getString("depth"));
                                        panoSpots.setRotate(spotsJson.getString("rotate"));
                                        panoSpots.setUrl(spotsJson.getString("url"));
                                        panoSpots.setIsOnclick(spotsJson.getString("isOnclick"));
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

    private List<PanoMap> getRadarsByjson(String pid, String radars_str) {
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

    @RequiresPermissions(value = {"pano02:edit"}, logical = Logical.OR)
    @RequestMapping(value = "/h/touredit/{id}")
    public String touredit(@PathVariable("id") String id) {
        log.info("PanoProjController touredit.........");
        PanoProj bean = new PanoProj();
        bean.setId(id);
        bean.setType("0");
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

    private String getBreakdownImg(String projId, String sceneSrc) {
        sceneSrc = sceneSrc.substring(sceneSrc.lastIndexOf("/") + 1, sceneSrc.indexOf("."));
        return "/upload/image/n4/" + projId + "/vtour/panos/" + sceneSrc + ".tiles/";
    }
}