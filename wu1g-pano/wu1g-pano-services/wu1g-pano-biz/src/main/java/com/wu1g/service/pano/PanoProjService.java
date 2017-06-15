package com.wu1g.service.pano;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.wu1g.api.pano.IPanoProjService;
import com.wu1g.dao.pano.IPanoMapDao;
import com.wu1g.dao.pano.IPanoProjDao;
import com.wu1g.dao.pano.IPanoSceneDao;
import com.wu1g.dao.pano.IPanoSpotsDao;
import com.wu1g.framework.annotation.FeignService;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.framework.config.AppConfig;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.BeetlUtils;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.service.utils.cmdprocessor.KrpanoUtil;
import com.wu1g.vo.pano.PanoMap;
import com.wu1g.vo.pano.PanoProj;
import com.wu1g.vo.pano.PanoScene;
import com.wu1g.vo.pano.PanoSpots;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FeignService
@Slf4j
public class PanoProjService extends BaseService implements IPanoProjService {
    @Autowired
    private IPanoProjDao panoProjDao;
    @Autowired
    private IPanoSceneDao panoSceneDao;
    @Autowired
    private IPanoSpotsDao panoSpotsDao;
    @Autowired
    private IPanoMapDao panoMapDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(PanoProj bean) throws Exception {
        String result = "seccuss";
        if (bean != null) {
            try {
                //判断数据是否存在
                if (panoProjDao.isDataYN(bean) != 0) {
                    //数据存在
                    panoProjDao.update(bean);
                } else {
                    //新增
                    panoProjDao.insert(bean);
                }
                if (bean.getScenes() != null && bean.getScenes().size() > 0) {
                    //--清空当前项目下所有场景信息
                    PanoScene scene = new PanoScene();
                    scene.setProjId(bean.getId());
                    panoSceneDao.deleteByProjId(scene);

                    bean.getScenes().forEach(ascene->{
                        ascene.setProjId(bean.getId());
                    });

                    //--新增场景信息
                    panoSceneDao.insertBatch(bean.getScenes());
                }
                if(0==bean.getType()){
                    //清除无效热点信息
                    panoSpotsDao.deletePanoSpots(bean);
                    //生成全景图
                    makePano(bean);
                }
            } catch (Exception e) {
                result = "信息保存失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    @Override
    public String deleteData(PanoProj bean) throws Exception {
        String result = "seccuss";
        if (bean != null) {
            try {
                panoProjDao.deleteByPrimaryKey(bean);
            } catch (Exception e) {
                result = "信息删除失败!";
                log.error(result, e);
            }
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {
            Exception.class, RuntimeException.class})
    public String deleteDataById(PanoProj bean) throws Exception {
        String result = "seccuss";
        if (bean != null) {
            try {
                panoProjDao.deleteById(bean);
            } catch (Exception e) {
                result = "信息删除失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    @Override
    public List<PanoProj> findDataIsPage(PanoProj bean) {
        List<PanoProj> results = null;
        try {
            PageHelper.startPage(PN(bean.getPageNum()), PS(bean.getPageSize()));
            results = (List<PanoProj>) panoProjDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return results;
    }

    @Override
    public List<PanoProj> findDataIsList(PanoProj bean) {
        List<PanoProj> results = null;
        try {
            results = (List<PanoProj>) panoProjDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return results;
    }

    @Override
    @RfAccount2Bean
    public PanoProj findDataById(PanoProj bean) {
        PanoProj result = null;
        try {
            result = (PanoProj) panoProjDao.selectByPrimaryKey(bean);
        } catch (Exception e) {
            log.error("信息详情查询失败!", e);
        }
        return result;
    }

    @Override
    public String recoveryDataById(PanoProj bean) throws Exception {
        String result = "seccuss";
        if (bean != null) {
            try {
                panoProjDao.recoveryDataById(bean);
            } catch (Exception e) {
                result = "信息恢复失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    /**
     * <p>生成全景图。
     */
    public void makePano(PanoProj bean) {
        try {
            /*
             * 1.获取项目信息
			 * 2.获取项目图片列表
			 * 3.传入图片列表调用shell生成全景切图
			 * 4.传入项目信息生成xml与html
			 */
            Map context = new HashMap();
            bean.setType(0);
            PanoProj proj = findDataById(bean);
            if (proj == null) {
                return;
            }
            PanoScene sceneDto = new PanoScene();
            sceneDto.setProjId(proj.getId());
            List<PanoScene> sceneList = (List<PanoScene>) panoSceneDao.findDataIsList(sceneDto);
            String imgappend = "";
            if (sceneList != null && sceneList.size() > 0) {
                PanoSpots panoSpots = null;
                for (PanoScene scene : sceneList) {
                    imgappend += AppConfig.getProperty("common.fileServer.upload") + scene.getSceneSrc().replace(AppConfig.getProperty("common.fileServer.download"), "").replace("/n1/", "/n4/") + " ";
                    scene.setBreakdownImg(getBreakdownImg(proj.getCode(), scene.getSceneSrc()));

                    //--获取场景-所有热点
                    panoSpots = new PanoSpots();
                    panoSpots.setProjId(scene.getProjId());
                    panoSpots.setSceneId(scene.getId());
                    scene.setHotSpots((List<PanoSpots>) panoSpotsDao.findDataIsList(panoSpots));
                }
            }
            //--获取导览图坐标信息
            if (ValidatorUtil.notEmpty(proj.getMapSrc())) {
                PanoMap panoMap = new PanoMap();
                panoMap.setProjId(proj.getId());
                proj.setRadars((List<PanoMap>) panoMapDao.findDataIsList(panoMap));
            }
            proj.setScenes(sceneList);
            context.put("proj", proj);
            context.put("basePath", bean.getStr());
            context.put("panoPath", bean.getStr() + "/plugins/krpano/");
            String saveDir = AppConfig.getProperty("common.fileServer.upload") + "pano/";
            if (bean.isMakePanoFlag() && ValidatorUtil.notEmpty(imgappend)) {
                KrpanoUtil.runShell(imgappend,context,saveDir,bean);//生成图片
            }
            KrpanoUtil.makeTourXml(context,saveDir,bean);
        } catch (Exception e) {
            String result = "全景图生成失败!" + bean.getId() + "," + bean.getName();
            log.error(result, e);
            throw new RuntimeException(result);
        }
    }

    private String getBreakdownImg(String projCode, String sceneSrc) {
        sceneSrc = sceneSrc.substring(sceneSrc.lastIndexOf("/") + 1, sceneSrc.indexOf("."));
        return "/upload/image/n4/" + projCode + "/vtour/panos/" + sceneSrc + ".tiles/";
    }

    /**
     * <p>保存xml信息
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveXmlData(PanoProj bean) throws Exception {
        String result = "seccuss";
        if (bean != null) {
            try {
                //判断数据是否存在
                if (panoProjDao.isDataYN(bean) != 0) {
                    //更新xmldata
                    panoProjDao.updateXmlData(bean);

                    if (bean.getScenes() != null && bean.getScenes().size() > 0) {
                        //--清空场景下-热点信息
                        PanoSpots panoSpots = new PanoSpots();
                        panoSpots.setProjId(bean.getId());
                        panoSpotsDao.deleteByProjId(panoSpots);

                        List<PanoSpots> panoSpotsList = new ArrayList<PanoSpots>();

                        for (PanoScene scene : bean.getScenes()) {
                            //--修改场景初始角度
                            panoSceneDao.update(scene);
                            //--新增热点-场景切换
                            if (scene.getHotSpots() != null && scene.getHotSpots().size() > 0) {
                                panoSpotsList.addAll(scene.getHotSpots());
                            }
                            //--新增热点-装饰图/单图片展示
                            if (scene.getImgSpots() != null && scene.getImgSpots().size() > 0) {
                                scene.getImgSpots().forEach(imgSpots -> {
                                    imgSpots.getUrl().replaceAll("/n1/", "/n3/");
                                });
                                panoSpotsList.addAll(scene.getImgSpots());
                            }
                            //--新增热点-外部链接
                            if (scene.getLinkSpots() != null && scene.getLinkSpots().size() > 0) {
                                panoSpotsList.addAll(scene.getLinkSpots());
                            }
                            //--新增热点-弹窗/图文介绍
                            if (scene.getRichSpots() != null && scene.getRichSpots().size() > 0) {
                                panoSpotsList.addAll(scene.getRichSpots());
                            }
                            //--新增热点-语音/讲解
                            if (scene.getSoundSpots() != null && scene.getSoundSpots().size() > 0) {
                                panoSpotsList.addAll(scene.getSoundSpots());
                            }
                            //--新增热点-视频/讲解
                            if (scene.getVideoSpots() != null && scene.getVideoSpots().size() > 0) {
                                panoSpotsList.addAll(scene.getVideoSpots());
                            }
                        }
                        //--写入数据库
                        if (panoSpotsList.size() > 0) {
                            panoSpotsDao.insertBatch(panoSpotsList);
                        }
                    }

                    if (bean.getRadars() != null && bean.getRadars().size() > 0) {
                        //--清空场景导览图设置信息
                        PanoMap panoMap = new PanoMap();
                        panoMap.setProjId(bean.getId());
                        panoMapDao.deleteByProjId(panoMap);
                        //--写入数据库
                        panoMapDao.insertBatch(bean.getRadars());
                    }
                }
            } catch (Exception e) {
                result = "信息保存失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    public String thumbsUpNum(PanoProj bean) throws Exception {
        String result = "seccuss";
        if (bean != null) {
            try {
                //判断数据是否存在
                if (panoProjDao.isDataYN(bean) != 0) {
                    panoProjDao.thumbsUpNum(bean);
                }
            } catch (Exception e) {
                result = "信息保存失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    public String pvNum(PanoProj bean) throws Exception {
        String result = "seccuss";
        if (bean != null) {
            try {
                //判断数据是否存在
                if (panoProjDao.isDataYN(bean) != 0) {
                    panoProjDao.pvNum(bean);
                }
            } catch (Exception e) {
                result = "信息保存失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    /**
     * <p>生成视频文件。
     */
    public void makeVideo(PanoProj bean) {
        try {
            Map context = new HashMap();
            bean.setType(1);
            PanoProj proj = findDataById(bean);
            if (proj == null) {
                return;
            }
            PanoScene sceneDto = new PanoScene();
            sceneDto.setProjId(proj.getId());
            List<PanoScene> sceneList = (List<PanoScene>) panoSceneDao.findDataIsList(sceneDto);
            proj.setScenes(sceneList);
            context.put("proj", proj);
            context.put("basePath", bean.getStr());
            context.put("panoPath", bean.getStr() + "/plugins/krpano/");

            String saveDir = AppConfig.getProperty("common.fileServer.upload") + "pano/";

//			创建文件夹
            File saveDirFile = new File(saveDir);
            if (!saveDirFile.exists()) {
                saveDirFile.mkdirs();
            }

            BeetlUtils.renderToFile("/btl/video.xml.btl", context, saveDir + bean.getId() + ".xml");

//            BeetlUtils.renderToFile("/btl/video.html.btl", context, saveDir + bean.getId() + ".html");
        } catch (Exception e) {
            String result = "全景视频文件生成失败!" + bean.getId() + "," + bean.getName();
            log.error(result, e);
            throw new RuntimeException(result);
        }
    }

    public List<PanoScene> getScenesByjson(Long pid, String scene_str) {
        List<PanoScene> scenes = new ArrayList<PanoScene>();
        JSONObject dataJson = JSON.parseObject(scene_str);
        if (dataJson != null && dataJson.entrySet() != null && dataJson.entrySet().size() > 0) {
            for (Map.Entry<String, Object> entiry : dataJson.entrySet()) {
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
                        //热点-0场景切换
                        scene.setHotSpots(getSpotsObject(0,sceneJson.getString("hotspots"),scene));
                        //热点-1装饰图片
                        scene.setImgSpots(getSpotsObject(1,sceneJson.getString("picspots"),scene));
                        //热点-2外部链接
                        scene.setLinkSpots(getSpotsObject(2,sceneJson.getString("linkSpots"),scene));
                        //热点-3图文介绍 | 内部弹窗页
                        scene.setRichSpots(getSpotsObject(3,sceneJson.getString("richSpots"),scene));
                        //热点-4语音热点
                        scene.setSoundSpots(getSpotsObject(4,sceneJson.getString("soundSpots"),scene));
                        //热点-5视频热点
                        scene.setVideoSpots(getSpotsObject(5,sceneJson.getString("videoSpots"),scene));

                        scenes.add(scene);
                    }
                }
            }
        }
        return scenes;
    }
    public List<PanoSpots>  getSpotsObject(Integer htype, String spotsStr, PanoScene scene ){
        List<PanoSpots> spots=null;
        if (ValidatorUtil.notEmpty(spotsStr) && !"[]".equals(spotsStr.replaceAll(" ", ""))) {
            JSONArray spotsJsonArr = null;
            try {
                spotsJsonArr = JSONArray.parseArray(spotsStr);
            } catch (Exception e) {
            }
            if (spotsJsonArr != null && !spotsJsonArr.isEmpty()) {
                spots = new ArrayList<>();
                for (int i = 0; i < spotsJsonArr.size(); i++) {
                    JSONObject spotsJson = spotsJsonArr.getJSONObject(i);
                    if (spotsJson != null && !spotsJson.isEmpty()) {
                        PanoSpots panoSpots = new PanoSpots();
//                      panoSpots.setId(IdUtil.createUUID(32));
                        panoSpots.setProjId(scene.getProjId());
                        panoSpots.setSceneId(scene.getId());
                        panoSpots.setHtype(htype);
                        panoSpots.setHname(spotsJson.getString("hname"));
                        panoSpots.setAth(spotsJson.getString("ath"));
                        panoSpots.setAtv(spotsJson.getString("atv"));
                        panoSpots.setLinkedscene(spotsJson.getString("linkedscene"));
                        panoSpots.setScale(spotsJson.getString("scale"));
                        panoSpots.setDepth(spotsJson.getString("depth"));
                        panoSpots.setRotate(spotsJson.getString("rotate"));
                        panoSpots.setUrl(spotsJson.getString("url"));
                        panoSpots.setIsOnclick(spotsJson.getInteger("isOnclick"));
                        panoSpots.setHotspotImg(spotsJson.getString("hotspotImg"));
                        panoSpots.setTitle(spotsJson.getString("title"));
                        panoSpots.setDetailInfo(spotsJson.getString("detailInfo"));
                        spots.add(panoSpots);
                    }
                }
            }
        }
        return spots;
    }
    public List<PanoMap> getRadarsByjson(Long pid, String radars_str) {
        List<PanoMap> radars = new ArrayList<PanoMap>();
        JSONObject radarsJson = JSON.parseObject(radars_str);
        if (radarsJson != null && radarsJson.entrySet() != null && radarsJson.entrySet().size() > 0) {
            for (Map.Entry<String, Object> entiry : radarsJson.entrySet()) {
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

    public static void main(String[] args) {
//        String url = "/upload/image/n1/fd0f69dd6dc544c3a46959714783f7e7/20161003171213sr79b.jpg";
//        url = url.substring(url.lastIndexOf("/") + 1, url.indexOf("."));
//        System.out.println(url);
        System.out.println(2000 * 1024 * 1024);
    }
}