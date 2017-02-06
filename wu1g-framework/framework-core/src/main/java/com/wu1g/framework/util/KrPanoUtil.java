package com.wu1g.framework.util;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/2/6.
 */
public class KrPanoUtil {
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

    public void getFile(String path, StringBuffer sb, String imgName) {
        java.io.File dir = new File(path);
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
                    getFile(dir1[i].getPath() + "/1", sb, imgName);
                } else {
                    Dimension dimension = getImageDim(dir1[i].getPath());
                    tiledimagewidth += dimension.getWidth();
                    tiledimageheight += dimension.getHeight();
                }
            }
            if (tiledimagewidth == 0 || tiledimageheight == 0) {
                return;
            }
            sb.append("\t\t\t<level tiledimagewidth=\"" + tiledimagewidth + "\" tiledimageheight=\"" + tiledimageheight + "\">\n" +
                    "\t\t\t\t<cube url=\"panos/" + imgName + ".tiles/%s/" + dir.getParentFile().getName() + "/%v/" + dir.getParentFile().getName() + "_%s_%v_%h.jpg\" />\n" +
                    "\t\t\t</level>");
            sb.append("\n");
        }
    }

    public StringBuffer getSceneImage(String path) {
        int tilesize = 256;
        path = IOUtil.escapeRemoteToLocal(path);
        Dimension dimension= getImageDim(path+"/b/l1/1/l1_b_1_1.jpg");
        tilesize=((Double)dimension.getWidth()).intValue();
        System.out.println(path);
        StringBuffer sb = new StringBuffer("\t\t<image type=\"CUBE\" multires=\"true\" tilesize=\""+tilesize+"\" if=\"!webvr.isenabled\">\n");
        getFile(path+"/b", sb, (path.substring(path.indexOf("/panos/") + 7, path.indexOf(".tiles/"))));
        sb.append("\t\t</image>");
        return sb;
    }

    public static void main(String[] args) {
        KrPanoUtil krPanoUtil = new KrPanoUtil();
        String path = "/upload/vtour/panos/111.tiles/";
        System.out.println(krPanoUtil.getSceneImage(path));

    }
}
