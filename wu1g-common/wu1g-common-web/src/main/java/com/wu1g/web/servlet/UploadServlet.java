package com.wu1g.web.servlet;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wu1g.dao.sys.ISysMaterialDao;
import com.wu1g.framework.config.AppConfig;
import com.wu1g.framework.util.*;
import com.wu1g.vo.org.OrgUser;
import com.wu1g.vo.sys.SysMaterial;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Servlet implementation class upload
 */
@AutoConfigureBefore(AppConfig.class)
@WebServlet(urlPatterns = "/fileUpload", description = "文件上传")
@Slf4j
public class UploadServlet extends HttpServlet{
    private final long serialVersionUID = 1L;
    @Autowired
    private ISysMaterialDao materialDao;
    // 线程池 默认大小
//    private static ExecutorService threadPool = null;
    private static String rootFolderUpload = null;
    private static String rootFolderDownload = null;
    private static Integer imageN0Width = null;
    private static Integer imageN0Height = null;
    private static Integer imageN1Width = null;
    private static Integer imageN1Height = null;
    private static Integer imageN2Width = null;
    private static Integer imageN2Height = null;
    private static Integer imageN3Width = null;
    private static Integer imageN3Height = null;
    //
    private static final SimpleDateFormat sdf = new SimpleDateFormat("/yyyyMM/");

    private void setAppConfig() {
//        if(threadPool==null)
//        this.threadPool = Executors.newScheduledThreadPool(Integer.parseInt(AppConfig.getProperty("common.fileServer.image.executorServiceSize")));
        if (rootFolderUpload == null) {
            this.rootFolderUpload = AppConfig.getProperty("common.fileServer.upload");
        }
        if (rootFolderDownload == null) {
            this.rootFolderDownload = AppConfig.getProperty("common.fileServer.download");
        }
        if (imageN0Width == null) {
            this.imageN0Width = Integer.parseInt(AppConfig.getProperty("common.fileServer.image.n0.width"));
        }
        if (imageN0Height == null) {
            this.imageN0Height = Integer.parseInt(AppConfig.getProperty("common.fileServer.image.n0.height"));
        }
        if (imageN1Width == null) {
            this.imageN1Width = Integer.parseInt(AppConfig.getProperty("common.fileServer.image.n1.width"));
        }
        if (imageN1Height == null) {
            this.imageN1Height = Integer.parseInt(AppConfig.getProperty("common.fileServer.image.n1.height"));
        }
        if (imageN2Width == null) {
            this.imageN2Width = Integer.parseInt(AppConfig.getProperty("common.fileServer.image.n2.width"));
        }
        if (imageN2Height == null) {
            this.imageN2Height = Integer.parseInt(AppConfig.getProperty("common.fileServer.image.n2.height"));
        }
        if (imageN3Width == null) {
            this.imageN3Width = Integer.parseInt(AppConfig.getProperty("common.fileServer.image.n3.width"));
        }
        if (imageN3Height == null) {
            this.imageN3Height = Integer.parseInt(AppConfig.getProperty("common.fileServer.image.n3.height"));
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("UploadServlet doGet...");
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("UploadServlet init");
        response.reset();
        response.setContentType("text/plain;charset=" + PathCommonConstant.DEFAULT_ENCODE);
        setAppConfig();
        //--项目id
        String projId = request.getParameter("projId");

        // 上传文件类型（图片image，动画flash，多媒体media，文件file）
        String dirName = request.getParameter("dir");
        if (ValidatorUtil.isNullEmpty(dirName)) {
            dirName = "file";
        }
        // 是否选择了文件
        if (!ServletFileUpload.isMultipartContent(request)) {
            response.getWriter().write(getError("请选择文件"));
            return;
        }
        // 路径变量
        String savePath = ""; // 文件保存路径
        String saveUrl = ""; // 文件保存目录URL(返前台)
        // n0到n4保存路径
        String savePathN0 = "";
        String savePathN1 = "";
        String savePathN2 = "";
        String savePathN3 = "";
        String savePathN4 = "";
        // 子文件夹
        String forDate = sdf.format(new Date());
        if (ValidatorUtil.notEmpty(projId)) {
            forDate = "/" + projId + "/";
        }
        // 根据是否是图片处理保存路径
        if (PathCommonConstant.UPLOAD_CATAGORY_IMAGE.equals(dirName)) {
            // 文件保存目录路径
            savePathN0 = rootFolderUpload + dirName + "/" + "n0" + forDate;
            savePathN1 = rootFolderUpload + dirName + "/" + "n1" + forDate;
            savePathN2 = rootFolderUpload + dirName + "/" + "n2" + forDate;
            savePathN3 = rootFolderUpload + dirName + "/" + "n3" + forDate;
            savePathN4 = rootFolderUpload + dirName + "/" + "n4" + forDate;
            // 创建文件夹
            File saveDirFileN0 = new File(savePathN0);
            if (!saveDirFileN0.exists()) {
                saveDirFileN0.mkdirs();
            }
            File saveDirFileN1 = new File(savePathN1);
            if (!saveDirFileN1.exists()) {
                saveDirFileN1.mkdirs();
            }
            File saveDirFileN2 = new File(savePathN2);
            if (!saveDirFileN2.exists()) {
                saveDirFileN2.mkdirs();
            }
            File saveDirFileN3 = new File(savePathN3);
            if (!saveDirFileN3.exists()) {
                saveDirFileN3.mkdirs();
            }
            // 检查写权限
            if (!saveDirFileN0.canWrite()) {
                response.getWriter().write(getError("上传目录没有写权限"));
                return;
            }
            if (!saveDirFileN1.canWrite()) {
                response.getWriter().write(getError("上传目录没有写权限"));
                return;
            }
            if (!saveDirFileN2.canWrite()) {
                response.getWriter().write(getError("上传目录没有写权限"));
                return;
            }
            if (!saveDirFileN3.canWrite()) {
                response.getWriter().write(getError("上传目录没有写权限"));
                return;
            }
            // 原图将在n4保存
            savePath = savePathN4;
            // 文件保存目录URL
            String isRichEditor = request.getParameter(PathCommonConstant.IS_RICH_EDITOR);
            if (!ValidatorUtil.isNullEmpty(isRichEditor) && isRichEditor.equals("1")) {
                // 在富文本编辑器内上传的返n3
                saveUrl = rootFolderDownload + dirName + "/" + "n3" + forDate;
            } else {
                // 在其他地方上传的返n1
                saveUrl = rootFolderDownload + dirName + "/" + "n1" + forDate;
            }
        } else {
            // 非图片的保存路径中没有Nx
            // 文件保存目录路径
            savePath = rootFolderUpload + dirName + forDate;

            // 文件保存目录URL
            saveUrl = rootFolderDownload + dirName + forDate;
        }

        // 创建文件夹
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        // 检查目录写权限
        if (!saveDirFile.canWrite()) {
            response.getWriter().write(getError("上传目录没有写权限"));
            return;
        }
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        List<FileItem> items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e1) {
            e1.printStackTrace();
        }

        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("code", 0);

        Iterator<?> itr = items.iterator();
        while (itr.hasNext()) {
            FileItem item = (FileItem) itr.next();
            // String fileName = item.getName();
            // long fileSize = item.getSize();
            // 普通文件表单，没有FORM
            if (!item.isFormField()) {

                // 上传文件的名称
                String name = item.getName();
                if (ValidatorUtil.isNullEmpty(name)) {
                    response.getWriter().write(getError("上传文件没有文件名"));
                    return;
                }

                // 检查文件大小
                if (!checkFileSize(item.getSize(), dirName)) {
                    response.getWriter().write(getError("上传文件大小超过限制"));
                    return;
                }

                // 检查扩展名
                String fileExt = WebUtil.getFileExt(name);
                if (!checkExt(dirName, fileExt)) {
                    response.getWriter().write(getError("上传文件扩展名不正确"));
                    return;
                }

                // 创建新的文件名
                String newFileName = WebUtil.getTime("yyyyMMddHHmmss") + WebUtil.getRandomString(5) + "." + fileExt;

                try {
                    File uploadedFile = new File(savePath, newFileName);
                    item.write(uploadedFile);

                    // // 图加水印
                    // if ("image".equals(dirName) && "product".equals(key)
                    // && "show".equals(picType)) {
                    // ImageUtil.watermark(savePath, newFileName);
                    // }

                    // // 生成缩略图
                    // createThumbnails(dirName, key, savePath, newFileName,
                    // picType);

                    // 对于图片,存储n0,n1,n2,n3
                    if (PathCommonConstant.UPLOAD_CATAGORY_IMAGE.equals(dirName)) {
                        if(item.getSize()<6*1024*1024){//图片小于n M生成3张小图
                            ImageUtil.resizeNx(savePath, savePathN1, newFileName, newFileName, imageN1Width, imageN1Height, false);
                            if (!ValidatorUtil.notEmpty(projId)) {
                                ImageUtil.resizeNx(savePath, savePathN0, newFileName, newFileName, imageN0Width, imageN0Height, true);
                                ImageUtil.resizeNx(savePath, savePathN2, newFileName, newFileName, imageN2Width, imageN2Height, false);
                                ImageUtil.resizeNx(savePath, savePathN3, newFileName, newFileName, imageN3Width, imageN3Height, false);

                            }
                        }else{
                            obj.put("defaultBigPicUrl", (request.getContextPath()+"/img/default_big_pic.png"));
                        }
                    }

                } catch (Exception e) {
                    response.getWriter().write(getError("上传文件失败"));
                    log.error(PathCommonConstant.LOG_ERROR_TITLE, e);
                    return;
                }

                obj.put("error", 0);
                obj.put("url", saveUrl + newFileName);

                obj.put("fileUrl", (saveUrl + newFileName));
                obj.put("fileName", name);

                try {
                    Integer materialType=3;
                    switch (dirName){
                        case "image": if(ValidatorUtil.notEmpty(projId)) materialType=4; else materialType=0; break;
                        case "audio": materialType=1; break;
                        case "media": if(ValidatorUtil.notEmpty(projId)) materialType=5; else materialType=2; break;
                        default:
                            if("bmp gif jfif jpe jpeg jpg png ico".contains((""+fileExt).toLowerCase())){
                                if(ValidatorUtil.notEmpty(projId)) materialType=4; else materialType=0;
                            }else if("flac ape wav mp3 aac ogg wma".contains((""+fileExt).toLowerCase())){
                                materialType=1;
                            }else if("mp4".contains((""+fileExt).toLowerCase())){
                                if(ValidatorUtil.notEmpty(projId)) materialType=5; else materialType=2;
                            }else{
                                materialType=3;
                            }
                            break;
                    }
                    SysMaterial material=new SysMaterial();
                    material.setType(materialType);//0图片1音乐2视频3文件
                    material.setName(name);//资源名称
                    material.setLogoUrl(obj.get("defaultBigPicUrl")==null?(saveUrl + newFileName):""+obj.get("defaultBigPicUrl"));
                    material.setMaterialUrl(saveUrl + newFileName);
                    material.setMemo("来源:"+request.getHeader("referer"));
                    OrgUser user = (OrgUser) SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.SESSION_KEY_USER);
                    if(user!=null){
                        material.setCreateId(user.getId());
                    }
                    materialDao.insert(material);
                } catch (Throwable e) {
                   log.error("素材记录异常!",e);
                }

                response.getWriter().write(JSON.toJSONString(obj));
            }
        }
    }

    /**
     * 检查文件大小
     *
     * @param size 上传文件的大小
     * @param type 上传文件的类型
     * @return
     */
    private boolean checkFileSize(long size, String type) {
        boolean flag = true;
        if (PathCommonConstant.UPLOAD_CATAGORY_IMAGE.equals(type)) {
            if (size/1024/1024 > PathCommonConstant.UPLOAD_PIC_MAX_SIZE) {
                flag = false;
            }
        } else if (PathCommonConstant.UPLOAD_CATAGORY_FLASH.equals(type) || PathCommonConstant.UPLOAD_CATAGORY_MEDIA.equals(type)) {
            if (size/1024/1024 > PathCommonConstant.UPLOAD_VIDEO_MAX_SIZE) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * <p>
     * 返回JSON消息
     * </p>
     *
     * @return JSON字符串
     * @throws JsonProcessingException
     */
    private String getError(String message) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("code", 1);
        obj.put("error", 1);
        obj.put("message", message);
        return JSON.toJSONString(obj);
    }

    /**
     * <p>
     * 检查文件扩展名是否合法
     * </p>
     *
     * @param type 上传类型
     * @param ext  文件扩展名
     * @return true:合法 false:不合法
     */
    private boolean checkExt(String type, String ext) {
        if (PathCommonConstant.UPLOAD_CATAGORY_IMAGE.equals(type)) {
            // 图片
            return PathCommonConstant.UPLOAD_TYPE_IMAGE.contains(ext);
        } else if (PathCommonConstant.UPLOAD_CATAGORY_FLASH.equals(type) || PathCommonConstant.UPLOAD_CATAGORY_MEDIA.equals(type)) {
            // 动画或者多媒体
            return PathCommonConstant.UPLOAD_TYPE_MEDIA.contains(ext);
        } else if (PathCommonConstant.UPLOAD_CATAGORY_FILE.equals(type)) {
            // 普通文件
            return true;
        }
        return false;
    }
}
