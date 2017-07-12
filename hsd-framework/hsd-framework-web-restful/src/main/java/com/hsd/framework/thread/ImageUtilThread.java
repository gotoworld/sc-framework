package com.hsd.framework.thread;


import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;

@Slf4j
public class ImageUtilThread implements Runnable {
    // 水印图片
    public static final String WATERMARK = "watermark.png";
    public static final SimpleDateFormat sdf = new SimpleDateFormat("/yyyyMM/");

    /**
     * 源图目录
     */
    private String srcPath;

    /**
     * 新图保存目录
     */
    private String optPath;

    /**
     * 新图保存地址
     */
    private String picFrom;

    /**
     * 源图地址
     */
    private String picTo;

    /**
     * 新图宽度
     */
    private int width;

    /**
     * 新图高度
     */
    private int height;

    /**
     * =true时强制转为指定尺寸,不保持原图比例
     */
    private boolean forceSize;

    /**
     * 构造
     *
     * @param _srcPath 源图目录
     * @param _optPath 新图保存目录
     * @param _picFrom 源图地址
     * @param _picTo   新图保存地址
     * @param _width   新图宽度
     * @param _height  新图高度
     */
    public ImageUtilThread(String _srcPath, String _optPath, String _picFrom, String _picTo, int _width, int _height, boolean _forceSize) {
        this.srcPath = _srcPath;
        this.optPath = _optPath;
        this.picFrom = _picFrom;
        this.picTo = _picTo;
        this.width = _width;
        this.height = _height;
        this.forceSize = _forceSize;
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
    public void resizeNx(String srcPath, String optPath, String picFrom, String picTo, int width, int height, boolean forceSize) throws IOException {
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
     * @param pic      源图地址
     * @return
     */
    public void watermark(String savePath, String pic) throws IOException {
        Thumbnails.of(new File(savePath, pic)).allowOverwrite(true).scale(1.0f)
                .watermark(Positions.CENTER, ImageIO.read(ResourcesUtil.getResourceAsFile(WATERMARK)), 0.5f).asFiles(Rename.NO_CHANGE);
    }

    /**
     * 复制单个文件
     *
     * @param srcFile  待复制的文件
     * @param destFile 目标文件
     * @return 如果复制成功返回true，否则返回false
     */
    public boolean copyFile(File srcFile, File destFile) {
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

    @Override
    public void run() {
        try {
            resizeNx(srcPath, optPath, picFrom, picTo, width, height, forceSize);
        } catch (Exception e) {
            log.error("图片比例处理异常!", e);
        }
    }
}
