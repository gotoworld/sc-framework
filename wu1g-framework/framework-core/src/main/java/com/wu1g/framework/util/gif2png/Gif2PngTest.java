package com.wu1g.framework.util.gif2png;

import javax.swing.*;
import java.io.File;


public class Gif2PngTest extends JFrame {
    private static Gif2PngUtil mainM;
    private File srcFile;

    /**
     * test
     */
    public static void main(String[] args) {
        mainM = new Gif2PngUtil();
        String defaultUrl = "d:/Pictures/";//System.getProperty("user.dir");
        File srcF = new File(defaultUrl + "QQ图片20170116144437.gif");
        File desF = new File(defaultUrl + "");
        mainM.transGifToPng(srcF, desF);
    }
}

