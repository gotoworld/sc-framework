package com.hsd.framework.util;

import com.hsd.framework.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang.StringUtils;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class ImageUtil {
    // 水印图片
    public static final String WATERMARK = "watermark.png";
    // 中图key
    public static final String THUMBNAILS_MIDDLE = "middle";
    public static final SimpleDateFormat sdf = new SimpleDateFormat("/yyyyMM/");
    private static final String rootFolderUpload = AppConfig.getProperty("common2.fileServer.rootFolder.upload");
    private static final String rootFolderDownload = AppConfig.getProperty("common2.fileServer.rootFolder.download");
    private static final int imageN0Width = Integer.parseInt(AppConfig.getProperty("common.fileServer.image.n0.width"));
    private static final int imageN0Height = Integer.parseInt(AppConfig.getProperty("common.fileServer.image.n0.height"));
    private static final int imageN1Width = Integer.parseInt(AppConfig.getProperty("common.fileServer.image.n1.width"));
    private static final int imageN1Height = Integer.parseInt(AppConfig.getProperty("common.fileServer.image.n1.height"));
    private static final int imageN2Width = Integer.parseInt(AppConfig.getProperty("common.fileServer.image.n2.width"));
    private static final int imageN2Height = Integer.parseInt(AppConfig.getProperty("common.fileServer.image.n2.height"));
    private static final int imageN3Width = Integer.parseInt(AppConfig.getProperty("common.fileServer.image.n3.width"));
    private static final int imageN3Height = Integer.parseInt(AppConfig.getProperty("common.fileServer.image.n3.height"));

    /**
     * 设置图片的大小
     *
     * @param savePath 存放目录
     * @param picFrom  源图地址
     * @param picTo    新图保存地址
     * @param width    新图宽度
     * @param height   新图高度
     * @return
     */
    public static void resize(String savePath, String picFrom, String picTo, int width, int height) throws IOException {
        Thumbnails.of(new File(savePath, picFrom)).forceSize(width, height).toFile(new File(savePath, picTo));
    }

    /**
     * 转换Nx图片
     *
     * @param srcPath 源图目录
     * @param optPath 新图保存目录
     * @param picFrom 源图地址
     * @param picTo   新图保存地址
     * @param width   新图宽度
     * @param height  新图高度
     * @return
     */
    public static void resizeNx(String srcPath, String optPath, String picFrom, String picTo, Integer width, Integer height, boolean forceSize)
            throws IOException {
        File srcImg = new File(srcPath, picFrom);
        File destImg = new File(optPath, picTo);
        BufferedImage bi = null;

        try {
            bi = ImageIO.read(srcImg);
        } catch (IOException e) {
            // 取分辨率出问题则忽略转换记日志
            log.info("ImageIO读取原图出错:" + srcImg.getAbsolutePath());

            return;
        }

        if (forceSize) {
            // forceSize=true时强制转为指定尺寸,不保持原图比例
            Thumbnails.of(srcImg).forceSize(width, height).toFile(destImg);
        } else {
            if ((bi.getWidth() < width) && (bi.getHeight() < height)) {
                // 原图宽高都小于转换目标,则直接复制
                copyFile(srcImg, destImg);
            } else {
                // 否则进行图象转换
                Thumbnails.of(srcImg).size(width, height).toFile(destImg);
            }
        }
    }

    /**
     * 给图片加水印
     *
     * @param savePath 存放目录
     * @return
     */
    public static void watermark(String savePath, String pic) throws IOException {
//        Thumbnails.of(new File(savePath, pic)).allowOverwrite(true).scale(1.0f)
//                .watermark(Positions.CENTER, ImageIO.read(ResourcesUtil.getResourceAsFile(WATERMARK)), 0.5f).asFiles(Rename.NO_CHANGE);
    }

    /**
     * 复制单个文件
     *
     * @param srcFile 待复制的文件
     * @return 如果复制成功返回true，否则返回false
     */
    public static boolean copyFile(File srcFile, File destFile) {
        // 复制文件
        int byteread = 0; // 读取的字节数
        InputStream in = null;
        OutputStream out = null;

        try {
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(destFile);

            byte[] buffer = new byte[1024 * 32];

            while ((byteread = in.read(buffer)) != -1) {
                out.write(buffer, 0, byteread);
            }

            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }

                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据图片byte数组转换为本地系统的图片
     *
     * @param images
     * @return 图片路径
     * @throws Exception
     */
    public static String parseImage(String images) throws Exception {
        String imagePath = "";

        if (StringUtils.isNotBlank(images)) {
            int index = images.indexOf(",");

            if (index > 0) {
                images = images.substring(index + 1);
            } else {
                return "";
            }

            try {
                // 上传的是原图n4
                String forDate = sdf.format(new Date());
                String path = rootFolderDownload + "image/n4" + forDate;
                String picture_path = rootFolderUpload + "image/n4" + forDate;

                // 创建新的文件名
                String newFileName = WebUtil.getTime("yyyyMMddHHmmss") + WebUtil.getRandomString(5) + ".jpg";

                File outputFile = new File(picture_path);

                if (!outputFile.exists()) {
                    outputFile.mkdirs();
                }

                String n4Url = (picture_path + newFileName);
                File newFile = new File(n4Url);

                if (!newFile.exists()) {
                    newFile.createNewFile();
                }

                FileImageOutputStream fios = new FileImageOutputStream(newFile);
                fios.write(Base64Util.decode(images));
                imageResize(picture_path, newFileName);

                imagePath = path + newFileName;

                if ((imagePath != null) && !imagePath.equals("")) {
                    imagePath = imagePath.replace("n4", "n1");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return imagePath;
    }

    public static String imageResize(String savePath, String newFileName) throws IOException {
        String dirName = "image";
//		String saveUrl = ""; // 文件保存目录URL(返前台)
        // n0到n4保存路径

        String savePathN0 = "";
        String savePathN1 = "";
        String savePathN2 = "";
        String savePathN3 = "";

        // 子文件夹
        String forDate = sdf.format(new Date());
        // 文件保存目录路径
        savePathN0 = rootFolderUpload + dirName + PathCommonConstant.PATH_SEPARATOR + "n0" + forDate;
        savePathN1 = rootFolderUpload + dirName + PathCommonConstant.PATH_SEPARATOR + "n1" + forDate;
        savePathN2 = rootFolderUpload + dirName + PathCommonConstant.PATH_SEPARATOR + "n2" + forDate;
        savePathN3 = rootFolderUpload + dirName + PathCommonConstant.PATH_SEPARATOR + "n3" + forDate;

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
            return "上传目录没有写权限";
        }

        if (!saveDirFileN1.canWrite()) {
            return "上传目录没有写权限";
        }

        if (!saveDirFileN2.canWrite()) {
            return "上传目录没有写权限";
        }

        if (!saveDirFileN3.canWrite()) {
            return "上传目录没有写权限";
        }

        ImageUtil.resizeNx(savePath, savePathN0, newFileName, newFileName, imageN0Width, imageN0Height, true);
        ImageUtil.resizeNx(savePath, savePathN1, newFileName, newFileName, imageN1Width, imageN1Height, false);
        ImageUtil.resizeNx(savePath, savePathN2, newFileName, newFileName, imageN2Width, imageN2Height, false);
        ImageUtil.resizeNx(savePath, savePathN3, newFileName, newFileName, imageN3Width, imageN3Height, false);

        return "";
    }

    public static void main(String[] args) throws Exception {
        ImageUtil.parseImage("data:image/jpeg;base64,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");
    }
}
