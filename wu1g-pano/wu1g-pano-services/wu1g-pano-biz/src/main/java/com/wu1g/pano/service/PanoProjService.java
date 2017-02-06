package com.wu1g.pano.service;

import com.github.pagehelper.PageHelper;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.framework.config.AppConfig;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.BeetlUtils;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.pano.api.IPanoProjService;
import com.wu1g.pano.dao.IPanoMapDao;
import com.wu1g.pano.dao.IPanoProjDao;
import com.wu1g.pano.dao.IPanoSceneDao;
import com.wu1g.pano.dao.IPanoSpotsDao;
import com.wu1g.pano.utils.cmdprocessor.KrPanoUtil;
import com.wu1g.pano.vo.PanoMap;
import com.wu1g.pano.vo.PanoProj;
import com.wu1g.pano.vo.PanoScene;
import com.wu1g.pano.vo.PanoSpots;
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

@Service
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
        String msg = "seccuss";
        if (bean != null) {
            try {
                //判断数据是否存在
                if (panoProjDao.isDataYN(bean) != 0) {
                    //数据存在
                    panoProjDao.updateByPrimaryKeySelective(bean);
                } else {
                    //新增
                    if (ValidatorUtil.isEmpty(bean.getId())) {
                        bean.setId(IdUtil.createUUID(32));
                    }
                    panoProjDao.insert(bean);
                }

                if (bean.getScenes() != null && bean.getScenes().size() > 0) {
                    //--清空当前项目下所有场景信息
                    PanoScene scene = new PanoScene();
                    scene.setProjId(bean.getId());
                    panoSceneDao.deleteByProjId(scene);
                    //--新增场景信息
                    panoSceneDao.insertBatch(bean.getScenes());
                }
                //清除无效热点信息
                panoSpotsDao.deletePanoSpots(bean);
            } catch (Exception e) {
                msg = "信息保存失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    @Override
    public String deleteData(PanoProj bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                panoProjDao.deleteByPrimaryKey(bean);
            } catch (Exception e) {
                msg = "信息删除失败,数据库处理错误!";
                log.error(msg, e);
            }
        }
        return msg;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {
            Exception.class, RuntimeException.class})
    public String deleteDataById(PanoProj bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                panoProjDao.deleteById(bean);
            } catch (Exception e) {
                msg = "信息删除失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    @Override
    public List<PanoProj> findDataIsPage(PanoProj bean) {
        List<PanoProj> beans = null;
        try {
            PageHelper.startPage((Integer) bean.getPageNum(), (Integer) bean.getPageSize());
            beans = (List<PanoProj>) panoProjDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    @Override
    public List<PanoProj> findDataIsList(PanoProj bean) {
        List<PanoProj> beans = null;
        try {
            beans = (List<PanoProj>) panoProjDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    @Override
    @RfAccount2Bean
    public PanoProj findDataById(PanoProj bean) {
        PanoProj bean1 = null;
        try {
            bean1 = (PanoProj) panoProjDao.selectByPrimaryKey(bean);
        } catch (Exception e) {
            log.error("信息详情查询失败,数据库错误!", e);
        }
        return bean1;
    }

    @Override
    public String recoveryDataById(PanoProj bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                panoProjDao.recoveryDataById(bean);
            } catch (Exception e) {
                msg = "信息恢复失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
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
            bean.setType("0");
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
                    scene.setBreakdownImg(getBreakdownImg(proj.getId(), scene.getSceneSrc()));

                    //--获取场景-跳转热点
                    panoSpots = new PanoSpots();
                    panoSpots.setHtype("0");
                    panoSpots.setProjId(scene.getProjId());
                    panoSpots.setSceneId(scene.getId());
                    scene.setHotSpots((List<PanoSpots>) panoSpotsDao.findDataIsList(panoSpots));
                    //--获取场景-装饰图片热点
                    panoSpots.setHtype("1");
                    scene.setImgSpots((List<PanoSpots>) panoSpotsDao.findDataIsList(panoSpots));
                }
            }
            if (bean.isMakePanoFlag() && ValidatorUtil.notEmpty(imgappend)) {
                KrPanoUtil.runShell(imgappend);//生成图片
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

//			创建文件夹
            File saveDirFile = new File(saveDir);
            if (!saveDirFile.exists()) {
                saveDirFile.mkdirs();
            }

            BeetlUtils.renderToFile("/btl/tour.xml.btl", context, saveDir + bean.getId() + ".xml");

            BeetlUtils.renderToFile("/btl/tour.html.btl", context, saveDir + bean.getId() + ".html");

            BeetlUtils.renderToFile("/btl/tour_editor.xml.btl", context, saveDir + bean.getId() + ".editor.xml");

            BeetlUtils.renderToFile("/btl/tour_editor.html.btl", context, saveDir + bean.getId() + ".editor.html");
        } catch (Exception e) {
            String msg = "全景图生成失败!" + bean.getId() + "," + bean.getName();
            log.error(msg, e);
            throw new RuntimeException(msg);
        }
    }

    private String getBreakdownImg(String projId, String sceneSrc) {
        sceneSrc = sceneSrc.substring(sceneSrc.lastIndexOf("/") + 1, sceneSrc.indexOf("."));
        return "/upload/image/n4/" + projId + "/vtour/panos/" + sceneSrc + ".tiles/";
    }

    /**
     * <p>保存xml信息
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveXmlData(PanoProj bean) throws Exception {
        String msg = "seccuss";
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
                            panoSceneDao.updateByPrimaryKeySelective(scene);
                            //--新增跳转热点
                            if (scene.getHotSpots() != null && scene.getHotSpots().size() > 0) {
                                panoSpotsList.addAll(scene.getHotSpots());
                            }
                            //--新增图片热点
                            if (scene.getImgSpots() != null && scene.getImgSpots().size() > 0) {
                                panoSpotsList.addAll(scene.getImgSpots());
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
                msg = "信息保存失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public String thumbsUpNum(PanoProj bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                //判断数据是否存在
                if (panoProjDao.isDataYN(bean) != 0) {
                    panoProjDao.thumbsUpNum(bean);
                }
            } catch (Exception e) {
                msg = "信息保存失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public String pvNum(PanoProj bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                //判断数据是否存在
                if (panoProjDao.isDataYN(bean) != 0) {
                    panoProjDao.pvNum(bean);
                }
            } catch (Exception e) {
                msg = "信息保存失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    /**
     * <p>生成视频文件。
     */
    public void makeVideo(PanoProj bean) {
        try {
            Map context = new HashMap();
            bean.setType("1");
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

            BeetlUtils.renderToFile("/btl/video.html.btl", context, saveDir + bean.getId() + ".html");
        } catch (Exception e) {
            String msg = "全景视频文件生成失败!" + bean.getId() + "," + bean.getName();
            log.error(msg, e);
            throw new RuntimeException(msg);
        }
    }

    public static void main(String[] args) {
        String url = "/upload/image/n1/fd0f69dd6dc544c3a46959714783f7e7/20161003171213sr79b.jpg";
        url = url.substring(url.lastIndexOf("/") + 1, url.indexOf("."));
        System.out.println(url);
    }
}