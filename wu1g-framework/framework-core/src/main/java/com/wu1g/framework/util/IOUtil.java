/*
 * IO工具 Class
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00     2011.11.16  Huyunlin           程序・发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2011 yearos System. - All Rights Reserved.
 *
 */
package com.wu1g.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * <p>IO工具类</p>
 *
 * @author Huyunlin
 */
@Slf4j
public class IOUtil extends IOUtils {
    /**
     * <p>读取文件</p>
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public static synchronized String readFileData(String fileName) throws Exception {

        // 检查文件名是否存在
        if (ValidatorUtil.isNullEmpty(fileName)) {
            log.error("没有文件名，读取失败");
            return null;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader((new FileInputStream(fileName)), "UTF-8"));

        try {

            StringBuilder data = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
            reader.close();
            return data.toString();
        } catch (Exception e) {
            if (reader != null) {
                reader.close();
            }
            log.error("读取文件失败", e);
            return null;
        }
    }

    /**
     * <p>将文件内容写入文件</p>
     *
     * @param savePath 文件全路径
     * @param data     写入的内容
     * @param fileName 文件名
     * @throws Exception
     */
    private synchronized void writeFile(String savePath, String data, String fileName) throws Exception {
        //创建文件夹
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        //检查目录写权限
        if (!saveDirFile.canWrite()) {
            log.error("目录没有写权限，写入文件失败");
            throw new Exception();
        }
        File file = new File(savePath + fileName);
        PrintWriter pfp = new PrintWriter(file, "utf-8");
        pfp.print(data);
        pfp.flush();
        pfp.close();
    }

    /**
     * <p>将字符串写入文件</p>
     *
     * @param content 写入的内容
     * @throws Exception
     */
    public static String writeHtml(String dirName, String content) {
        // 文件保存目录路径
        String savePath = ResourcesUtil.getData("UPLOAD_ROOT_FOLDER") +
                dirName + PathCommonConstant.PATH_SEPARATOR;
        // 文件保存目录URL
        String saveUrl = ResourcesUtil.getData("UPLOAD_ROOT_FOLDER_URL") +
                dirName + PathCommonConstant.PATH_SEPARATOR;

        //创建文件夹
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }

        //检查目录写权限
        if (!saveDirFile.canWrite()) {
            log.error("目录没有写权限，写入文件失败");
            return null;
        }

        String newFileName = null;
        File saveFile = null;

        // 创建新的文件名
        newFileName = WebUtil.getTime("yyyyMMddHHmmss")
                + WebUtil.getRandomString(5)
                + ".html";
        saveFile = new File(savePath, newFileName);


        BufferedOutputStream writer = null;

        try {
            writer = new BufferedOutputStream(new FileOutputStream(saveFile));

            IOUtil.write(content, writer, PathCommonConstant.DEFAULT_ENCODE);

            writer.flush();
            writer.close();

            return saveUrl + newFileName;
        } catch (Exception e) {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e1) {
                    log.error("写入文件失败");
                    log.error(PathCommonConstant.LOG_ERROR_TITLE, e);
                    return null;
                }
            }
            log.error("写入文件失败");
            log.error(PathCommonConstant.LOG_ERROR_TITLE, e);
            return null;
        }
    }

    /**
     * <p>将字符串读取文件</p>
     *
     * @throws Exception
     */
    public static String readHtml(String fileurl) throws Exception {

        // 检查文件名是否存在
        if (ValidatorUtil.isNullEmpty(fileurl)) {
            log.error("没有文件名，读取失败");
            return null;
        }
        String savePath = escapeRemoteToLocal(fileurl);
        //创建文件夹
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            log.error("文件路径：" + savePath);
            log.error("文件不存在，读取失败");
            return null;
        }

        //检查目录读权限
        if (!saveDirFile.canRead()) {
            log.error("目录没有读权限，读取失败");
            return null;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader((new FileInputStream(savePath)), PathCommonConstant.DEFAULT_ENCODE));
        try {

            StringBuilder html = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                html.append(line);
            }

            reader.close();

            //return WebUtils.htmlToChars(html.toString());
            return html.toString();
        } catch (Exception e) {
            if (reader != null) {
                reader.close();
            }
            log.error("读取文件失败");
            log.error(PathCommonConstant.LOG_ERROR_TITLE, e);
            return null;
        }
    }

    /**
     * <p>将文件内容写入文件</p>
     *
     * @param savePath 文件全路径
     * @param content  写入的内容
     * @throws Exception
     */
    public static boolean writeTextData(String savePath, String content) throws Exception {

        //创建文件夹
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }

        //检查目录写权限
        if (!saveDirFile.canWrite()) {
            log.error("目录没有写权限，写入文件失败");
            return false;
        }

        //BufferedWriter writer = new BufferedWriter(new FileWriter(new File(savePath, fileName)));
        BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(new File(savePath)));

        try {
            IOUtil.write(content, writer, PathCommonConstant.DEFAULT_ENCODE);
            //writer.write(content);

            writer.flush();
            writer.close();

            return true;
        } catch (Exception e) {
            if (writer != null) {
                writer.close();
            }
            log.error("写入文件失败", e);
            return false;
        }
    }

    /**
     * <p>删除文件</p>
     *
     * @param fileName 文件全路径
     * @return true:删除成功 false:删除失败
     * @throws Exception
     */
    public static boolean deleteFile(String fileName) {

        // 检查是否为空
        if (ValidatorUtil.isNullEmpty(fileName)) {
            log.error("没有文件名，删除文件失败");
            return false;
        }

        // 文件物理路径
        String savePath = ResourcesUtil.getData("FILE_ROOT_FOLDER") + fileName;

        //检查文件是否存在
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            log.error("文件不存在，删除文件失败");
            return false;
        } else {
            boolean result = saveDirFile.delete();

            if (!result) {
                log.error("删除文件时发生错误！");
                return false;
            } else {
                log.info("删除文件【" + savePath + "】成功！");
            }

            return result;
        }
    }

    /**
     * 将服务器相对地址转为物理地址
     *
     * @param url
     * @return
     */
    public static String escapeRemoteToLocal(String url) {
        String local_url = null;
        if (url != null) {
            if (!ValidatorUtil.isUrl(url)) {
                local_url = url.replaceAll(
                        ResourcesUtil.getData("UPLOAD_ROOT_FOLDER_URL"),
                        ResourcesUtil.getData("UPLOAD_ROOT_FOLDER")).replaceAll(
                        "//", "/");
            }
        }
        return local_url;
    }

    /**
     * 将物理地址转为服务器相对地址
     *
     * @param url
     * @return
     */
    public static String escapeLocalToRemote(String url) {
        String remote_url = null;
        if (url != null) {
            if (!ValidatorUtil.isUrl(url)) {
                remote_url = url.replaceAll(
                        ResourcesUtil.getData("UPLOAD_ROOT_FOLDER"),
                        ResourcesUtil.getData("UPLOAD_ROOT_FOLDER_URL"))
                        .replaceAll("//", "/");
            }
        }
        return remote_url;
    }
}
