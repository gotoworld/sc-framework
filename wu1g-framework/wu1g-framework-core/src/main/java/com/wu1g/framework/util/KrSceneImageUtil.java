package com.wu1g.framework.util;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/2/6.
 */
@Slf4j
public class KrSceneImageUtil {
    /**
     * 获取图片的分辨率
     */
    public Dimension getImageDim(String path) {
        Dimension result = null;
        String suffix = getFileSuffix(path);
        //解码具有给定后缀的文件
        Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(suffix);
        if (iter.hasNext()) {
            ImageReader reader = iter.next();
            try {
                ImageInputStream stream = new FileImageInputStream(new File(path));
                reader.setInput(stream);
                int width = reader.getWidth(reader.getMinIndex());
                int height = reader.getHeight(reader.getMinIndex());
                result = new Dimension(width, height);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                reader.dispose();
            }
        }
        return result;
    }

    /**
     * 获得图片的后缀名
     */
    private String getFileSuffix(final String path) {
        String result = null;
        if (path != null) {
            result = "";
            if (path.lastIndexOf('.') != -1) {
                result = path.substring(path.lastIndexOf('.'));
                if (result.startsWith(".")) {
                    result = result.substring(1);
                }
            }
        }
        return result;
    }

    public void getFile(String dirPrefix,String projDir,String path, StringBuffer sb) {
        File dir = new File(path);
        if (dir.isDirectory()) {
            File[] dir1 = dir.listFiles();
            if (dir1 == null || dir1.length == 0) {
                return;
            }
            if (sb == null) {
                sb = new StringBuffer("");
            }
            int tiledimagewidth = 0;
            int tiledimageheight = 0;
            for (int i = 0; i < dir1.length; i++) {
                if (dir1[i].isDirectory()) {
                    String fileName=dir1[i].listFiles()[0].getName();
                    if(fileName.length()>1){
                        dirPrefix=fileName.substring(0,fileName.length()-1);
                    }
                    getFile(dirPrefix,projDir,dir1[i].getPath() + "/"+fileName, sb);
                } else {
                    Dimension dimension = getImageDim(dir1[i].getPath());
                    tiledimagewidth += dimension.getWidth();
                    tiledimageheight += dimension.getHeight();
                }
            }
            if (tiledimagewidth == 0 || tiledimageheight == 0) {
                return;
            }
            sb.append("\t\t\t<level tiledimagewidth=\"" + tiledimagewidth + "\" tiledimageheight=\"" + tiledimagewidth + "\">\n" +
                    "\t\t\t\t<cube url=\""+projDir+"%s/" + dir.getParentFile().getName() + "/%"+dirPrefix+"v/" + dir.getParentFile().getName() + "_%s_%"+dirPrefix+"v_%"+dirPrefix+"h.jpg\" />\n" +
                    "\t\t\t</level>");
            sb.append("\n");
        }
    }
    private int getTitleSize(File dir){
        Dimension dimension = getImageDim(dir.getPath());
        int width=((Double) dimension.getWidth()).intValue();
        int height=((Double) dimension.getHeight()).intValue();
        return width>height?width:height;
    }
    public StringBuffer getSceneImage(final String path) {
        try {
            int tilesize = 64;
            String newPath = IOUtil.escapeRemoteToLocal(path);
            try {
                //  xx.tiles/b/l1/1/l1_b_1_1.jpg
                //  xx.tiles/b/l1/01/l1_b_01_01.jpg
                File dir = new File(newPath+"/b/l1/1/l1_b_1_1.jpg");
                if (dir.exists() && dir.isFile()) {
                    tilesize = getTitleSize(dir);
                }else{
                    tilesize = getTitleSize( new File(newPath+"/b/l1/01/l1_b_01_01.jpg"));
                }
            } catch (Exception e) {
                tilesize=256;
                log.error("获取全景切图分辨率异常!",e);
            }
//        System.out.println(path);
            StringBuffer sb = new StringBuffer("\t\t<image type=\"CUBE\" multires=\"true\" tilesize=\"" + tilesize + "\" if=\"!webvr.isenabled\">\n");
            getFile("",path,newPath + "/b", sb);
            sb.append("\t\t</image>");
            return sb;
        } catch (Exception e) {
            return new StringBuffer(
                    "            <image type=\"CUBE\" multires=\"true\" if=\"!webvr.isenabled\">\n" +
                    "                <level>\n" +
                    "                    <cube url=\"" + path + "%s/l1/%v/l1_%s_%v_%h.jpg\" />\n" +
                    "                </level>\n" +
                    "            </image>"
            );
        }
    }
    public static String getBreakdownImg(String projCode, String sceneSrc) {
        sceneSrc = sceneSrc.substring(sceneSrc.lastIndexOf("/") + 1, sceneSrc.indexOf("."));
        return "/upload/image/n4/" + projCode + "/vtour/panos/" + sceneSrc + ".tiles/";
    }
}
