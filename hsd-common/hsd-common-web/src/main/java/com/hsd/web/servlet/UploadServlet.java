package com.hsd.web.servlet;

import com.hsd.framework.annotation.NoAuthorize;
import com.hsd.framework.config.AppConfig;
import com.hsd.framework.util.PathCommonConstant;
import com.hsd.framework.util.ValidatorUtil;
import com.hsd.framework.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Servlet implementation class upload
 */
@AutoConfigureBefore(AppConfig.class)
//@WebServlet(urlPatterns = "/file/fileUpload", description = "文件上传")
@NoAuthorize
@Slf4j
@RestController
public class UploadServlet {
    private final long serialVersionUID = 1L;
    //    @Autowired
//    private ISysMaterialService materialService;
    // 线程池 默认大小
//    private static ExecutorService threadPool = null;
    private static String rootFolderUpload = null;
    private static String rootFolderDownload = null;
    private static Integer imageN1Width = null;
    private static Integer imageN1Height = null;
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
        if (imageN1Width == null) {
            try {
                this.imageN1Width = Integer.parseInt(AppConfig.getProperty("common.fileServer.image.n1.width"));
            } catch (Exception e) {
                this.imageN1Width = 200;
            }
        }
        if (imageN1Height == null) {
            try {
                this.imageN1Height = Integer.parseInt(AppConfig.getProperty("common.fileServer.image.n1.height"));
            } catch (Exception e) {
                this.imageN1Height = 200;
            }
        }
    }

    @RequestMapping(value = "/file/fileUpload")
    @ResponseBody
    protected Object fileUpload(HttpServletRequest request) throws Exception {
        log.info("UploadServlet fileUpload");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        // 是否选择了文件
        if (fileMap == null || fileMap.size() == 0) {
            return (getError("请选择文件"));
        }
        setAppConfig();
        // 上传文件类型（图片image，动画flash，多媒体media，文件file）
        String dirName = request.getParameter("dir");
        if (ValidatorUtil.isNullEmpty(dirName)) {
            dirName = "file";
        }
        // 路径变量
        String savePath = rootFolderUpload + dirName, remoteUrl = rootFolderDownload + dirName;
        // 子文件夹
        String forDate = sdf.format(new Date());
        // 文件保存目录路径
        savePath += forDate;
        // 文件保存目录URL
        remoteUrl += forDate;
        // 创建文件夹
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        // 检查写权限
        if (!saveDirFile.canWrite()) {
            return (getError("上传目录没有写权限"));
        }
        Map<String, Object> obj = new HashMap<>();
        obj.put("code", 0);

        for (MultipartFile multipartFile : fileMap.values()) {
            String fileName = multipartFile.getOriginalFilename();
            // long fileSize = item.getSize();
            // 上传文件的名称
            if (ValidatorUtil.isNullEmpty(fileName)) {
                return (getError("上传文件没有文件名"));
            }

            // 检查文件大小
            if (!checkFileSize(multipartFile.getSize(), dirName)) {
                return (getError("上传文件大小超过限制"));
            }

            // 检查扩展名
            String fileExt = WebUtil.getFileExt(fileName);
            if (!checkExt(dirName, fileExt)) {
                return (getError("上传文件扩展名不正确"));
            }

            // 创建新的文件名
            String newFileName = WebUtil.getTime("yyyyMMddHHmmss") + WebUtil.getRandomString(5) + "." + fileExt;

            try {
                multipartFile.transferTo(new File(savePath, newFileName));
            } catch (Throwable e) {
                log.error(PathCommonConstant.LOG_ERROR_TITLE, e);
                return (getError("上传文件失败"));
            }

            obj.put("error", 0);
            obj.put("url", remoteUrl + newFileName);
            obj.put("fileUrl", (remoteUrl + newFileName));
            obj.put("fileName", fileName);

        }
        return (obj);
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
            if (size / 1024 / 1024 > PathCommonConstant.UPLOAD_PIC_MAX_SIZE) {
                flag = false;
            }
        } else if (PathCommonConstant.UPLOAD_CATAGORY_FLASH.equals(type) || PathCommonConstant.UPLOAD_CATAGORY_MEDIA.equals(type)) {
            if (size / 1024 / 1024 > PathCommonConstant.UPLOAD_VIDEO_MAX_SIZE) {
                flag = false;
            }
        } else if (PathCommonConstant.UPLOAD_CATAGORY_AUDIO.equals(type)) {
            if (size / 1024 / 1024 > PathCommonConstant.UPLOAD_VIDEO_MAX_SIZE) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * <p>
     * 返回JSON消息
     */
    private Object getError(String message) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("status", 1);
        obj.put("code", 1);
        obj.put("error", 1);
        obj.put("message", message);
        return obj;
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
        } else if (PathCommonConstant.UPLOAD_CATAGORY_AUDIO.equals(type)) {
            return true;
        }
        return false;
    }
}
