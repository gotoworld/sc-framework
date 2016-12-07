package com.wu1g.framework.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wu1g.framework.config.AppConfig;
import com.wu1g.framework.thread.ImageUtilThread;
import com.wu1g.framework.util.ImageUtil;
import com.wu1g.framework.util.PathCommonConstant;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.framework.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@WebServlet(urlPatterns="/jQueryFileUpload", description="文件上传")
@Slf4j
public class jQueryFileUpload extends HttpServlet implements  EnvironmentAware {
    private static RelaxedPropertyResolver appProperty;
    @Override
    public void setEnvironment(Environment env) {
        this.appProperty = new RelaxedPropertyResolver(env, AppConfig.keyPrefix);
    }
    private final long serialVersionUID = 1L;
    // 线程池 默认大小
    private static ExecutorService threadPool = null;
    private static String rootFolderUpload = null;
    private static String rootFolderDownload =  null;
    private static Integer imageN0Width =  null;
    private static Integer imageN0Height =  null;
    private static Integer imageN1Width =  null;
    private static Integer imageN1Height =  null;
    private static Integer imageN2Width = null;
    private static Integer imageN2Height =  null;
    private static Integer imageN3Width =  null;
    private static Integer imageN3Height =  null;
    //
    private static final SimpleDateFormat sdf = new SimpleDateFormat("/yyyyMM/");

    private void setAppProperty(){
        if(threadPool==null)
            this.threadPool = Executors.newScheduledThreadPool(Integer.parseInt(appProperty.getProperty("common.fileServer.image.executorServiceSize")));
        if(rootFolderUpload==null)
            this.rootFolderUpload = appProperty.getProperty("common.fileServer.upload");
        if(rootFolderDownload==null)
            this.rootFolderDownload = appProperty.getProperty("common.fileServer.download");
        if(imageN0Width==null)
            this.imageN0Width = Integer.parseInt(appProperty.getProperty("common.fileServer.image.n0.width"));
        if(imageN0Height==null)
            this.imageN0Height = Integer.parseInt(appProperty.getProperty("common.fileServer.image.n0.height"));
        if(imageN1Width==null)
            this.imageN1Width = Integer.parseInt(appProperty.getProperty("common.fileServer.image.n1.width"));
        if(imageN1Height==null)
            this.imageN1Height = Integer.parseInt(appProperty.getProperty("common.fileServer.image.n1.height"));
        if(imageN2Width==null)
            this.imageN2Width = Integer.parseInt(appProperty.getProperty("common.fileServer.image.n2.width"));
        if(imageN2Height==null)
            this.imageN2Height = Integer.parseInt(appProperty.getProperty("common.fileServer.image.n2.height"));
        if(imageN3Width==null)
            this.imageN3Width = Integer.parseInt(appProperty.getProperty("common.fileServer.image.n3.width"));
        if(imageN3Height==null)
            this.imageN3Height = Integer.parseInt(appProperty.getProperty("common.fileServer.image.n3.height"));
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        uploadify(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }

    public void uploadify(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        setAppProperty();
        // 路径变量
        String savePath = ""; // 文件保存路径
        String saveUrl = ""; // 文件保存目录URL(返前台)
        // 子文件夹
        String forDate = sdf.format(new Date());
        // n0到n4保存路径
        String savePathN0 = "";
        String savePathN1 = "";
        String savePathN2 = "";
        String savePathN3 = "";
        String savePathN4 = "";
        // 上传文件类型（图片image，动画flash，多媒体media，文件file）
        String dirName = req.getParameter("dir");
        if ("image".equals(dirName)) {
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
                // // 检查写权限
                // if (!saveDirFileN0.canWrite()) {
                // response.getWriter().write(getError("上传目录没有写权限"));
                // return;
                // }
                // if (!saveDirFileN1.canWrite()) {
                // response.getWriter().write(getError("上传目录没有写权限"));
                // return;
                // }
                // if (!saveDirFileN2.canWrite()) {
                // response.getWriter().write(getError("上传目录没有写权限"));
                // return;
                // }
                // if (!saveDirFileN3.canWrite()) {
                // response.getWriter().write(getError("上传目录没有写权限"));
                // return;
                // }
                // 原图将在n4保存
                savePath = savePathN4;
            }
            // 文件保存目录URL
            String isRichEditor = req.getParameter(PathCommonConstant.IS_RICH_EDITOR);
            if (!ValidatorUtil.isNullEmpty(isRichEditor) && isRichEditor.equals("1")) {
                // 在富文本编辑器内上传的返n3
                saveUrl = rootFolderDownload + "/" + dirName + "/" + "n3" + forDate;
            } else {
                // 在其他地方上传的返n1
                saveUrl = rootFolderDownload + "/" + dirName + "/" + "n1" + forDate;
            }
        } else {
            // 非图片的保存路径中没有Nx
            // 文件保存目录路径
            savePath = rootFolderUpload + dirName + forDate;

            // 文件保存目录URL
            saveUrl = rootFolderDownload + "/" + dirName + forDate;
        }

        // 创建文件夹
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        // // 检查目录写权限
        // if (!saveDirFile.canWrite()) {
        // resp.getWriter().write(getError("上传目录没有写权限"));
        // return;
        // }
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        List<FileItem> items = null;
        try {
            items = upload.parseRequest(req);
        } catch (FileUploadException e1) {
            e1.printStackTrace();
        }
        File f1 = new File(savePath);
        if (!f1.exists()) {
            f1.mkdirs();
        }
        f1 = null;

        if (items == null) {
            return;
        }
        Iterator<FileItem> it = items.iterator();
        String name = "";

        List<Map<String, Object>> ja = new ArrayList<Map<String, Object>>();

        while (it.hasNext()) {
            FileItem item = it.next();
            if (!item.isFormField()) {
                name = item.getName();
                if (name == null || name.trim().equals("")) {
                    continue;
                }
                // \s去除任何空空白符如： 空格符、制表符和进纸符等
                String fileName = name.replaceAll("\\s", "");

                // 检查扩展名
                String fileExt = WebUtil.getFileExt(name);
                // 创建新的文件名
                String newFileName = WebUtil.getTime("yyyyMMddHHmmss") + WebUtil.getRandomString(5) + "." + fileExt;

                Map<String, Object> json = new HashMap<String, Object>();

                try {
                    File uploadedFile = new File(savePath, newFileName);
                    item.write(uploadedFile);

                    // // 图加水印
                    // if ("image".equals(dirName) && "product".equals(key) && "show".equals(picType)) {
                    // ImageUtils.watermark(savePath, newFileName);
                    // }

                    // // 生成缩略图
                    // createThumbnails(dirName, key, savePath, newFileName,
                    // picType);

                    // 对于图片,存储n0,n1,n2,n3
                    if (PathCommonConstant.UPLOAD_CATAGORY_IMAGE.equals(dirName)) {
                        // 处理n0 其它的使用多线程处理
                        ImageUtil.resizeNx(savePath, savePathN0, newFileName, newFileName, imageN0Width, imageN0Height, true);
                        threadPool.execute(new ImageUtilThread(savePath, savePathN1, newFileName, newFileName, imageN1Width, imageN1Height, false));
                        threadPool.execute(new ImageUtilThread(savePath, savePathN2, newFileName, newFileName, imageN2Width, imageN2Height, false));
                        threadPool.execute(new ImageUtilThread(savePath, savePathN3, newFileName, newFileName, imageN3Width, imageN3Height, false));
                    }
                    json.put("name", fileName);
                    json.put("size", item.getSize());
                    if ("image".equals(dirName)) {
                        json.put("url", (rootFolderDownload + dirName + "/" + "n3" + forDate + newFileName));
                        json.put("thumbnailUrl", (rootFolderDownload + dirName + "/" + "n0" + forDate + newFileName));
                    } else {
                        json.put("url", (saveUrl + newFileName));
                    }
                    json.put("deleteUrl", (saveUrl + newFileName));
                    json.put("deleteType", "DELETE");
                } catch (Exception e) {
                    log.error(PathCommonConstant.LOG_ERROR_TITLE, e);
                    /*
                     * 注：插件需要服务器端返回JSON格式的字符串，且必须以下面的格式来返回，一个字段都不能少 如果上传失败，那么则须用特定格式返回信息： "name": "picture1.jpg", "size": 902604, "error":
					 * "Filetype not allowed" 另外，files必须为一个JSON数组，哪怕上传的是一个文件
					 */
                    json = new HashMap<String, Object>();
                    json.put("name", fileName);
                    json.put("size", item.getSize());
                    json.put("error", "文件上传失败!" + e.getMessage());
                }
                // System.out.println(savePathN1 + newFileName);//返回相对根路径：/upload/songs/2010/06/05/ring.mp3
                // ------------------------------
                ja.add(json);
            }
        }
        Map<String, Object> js = new HashMap<String, Object>();
        js.put("files", ja);
        resp.getWriter().print((new ObjectMapper()).writeValueAsString(js));
    }
    // //test
    // public void main(String[] args) throws Exception {
    // Map<String,Object> js=new HashMap<String,Object>();
    //
    // List<Map<String,Object>> ja = new ArrayList<Map<String,Object>>();
    //
    // for(int i=0;i<5;i++){
    // Map<String,Object> json = new HashMap<String,Object>();
    // json.put("name", "===="+i);
    // json.put("size", 222222222);
    // if(i==2){
    // json.put("error", "文件上传失败!");
    // }else{
    // }
    // ja.add(json);
    // }
    // js.put("files", ja);
    //
    // System.out.println( (new ObjectMapper()).writeValueAsString(js) );
    // }
}