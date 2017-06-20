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
public class k1 {
    /**
     * 获取图片的分辨率
     */
    public static Dimension getImageDim(String path) {
        Dimension result = null;
        String suffix = getFileSuffix(path);
        //解码具有给定后缀的文件
        Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(suffix);
//        System.out.println(ImageIO.getImageReadersBySuffix(suffix));
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
//        System.out.println("getImageDim:" + result);
        return result;
    }

    /**
     * 获得图片的后缀名
     */
    private static String getFileSuffix(final String path) {
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
//        System.out.println("getFileSuffix:" + result);
        return result;
    }

    public static void getFile(String path, StringBuffer sb) {
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
                    getFile(dir1[i].getPath() + "/1", sb);
                } else {
//                    System.out.println(dir1[i].getPath());
                    Dimension dimension = getImageDim(dir1[i].getPath());
                    tiledimagewidth += dimension.getWidth();
                    tiledimageheight += dimension.getHeight();
                }
            }
            if (tiledimagewidth == 0 || tiledimageheight == 0) {
                return;
            }
//            System.out.println(dir.getParentFile().getName());
            sb.append("<level tiledimagewidth=\"" + tiledimagewidth + "\" tiledimageheight=\"" + tiledimageheight + "\">\n" +
                    "\t\t<cube url=\"panos/3.tiles/%s/" + dir.getParentFile().getName() + "/%v/" + dir.getParentFile().getName() + "_%s_%v_%h.jpg\" />\n" +
                    "</level>");
            sb.append("\n");
//            System.out.println(sb);
        }
    }

    public static void main(String[] args) {
        String path = "D:/home/krpano/vtour0/panos/3.tiles/b";
        StringBuffer sb = new StringBuffer("");
        getFile(path, sb);
        System.out.println(sb);
    }
}
