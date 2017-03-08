package com.wu1g.service.utils.cmdprocessor;

import com.wu1g.framework.config.AppConfig;
import com.wu1g.framework.util.BeetlUtils;
import com.wu1g.vo.pano.PanoProj;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class KrpanoUtil {
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(1, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

    private static String shellCommand = null;

    public static void makeTourXml(Map context,String saveDir,PanoProj bean){
//			创建文件夹
        File saveDirFile = new File(saveDir);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        BeetlUtils.renderToFile("/btl/tour.xml.btl", context, saveDir + bean.getId() + ".xml");
//        BeetlUtils.renderToFile("/btl/tour.html.btl", context, saveDir + bean.getId() + ".html");
        BeetlUtils.renderToFile("/btl/tour_editor.xml.btl", context, saveDir + bean.getId() + ".editor.xml");
//        BeetlUtils.renderToFile("/btl/tour_editor.html.btl", context, saveDir + bean.getId() + ".editor.html");
    }

    private static String getShellCommand() {
        try {
            if (shellCommand != null) {
                return shellCommand;
            }
            String os = System.getProperty("os.name");
            if (os.toLowerCase().startsWith("win")) {
                shellCommand = AppConfig.getProperty("krpano.win") + " " + AppConfig.getProperty("krpano.config");
            } else if (os.toLowerCase().startsWith("mac")){
                shellCommand = AppConfig.getProperty("krpano.mac") + " " + AppConfig.getProperty("krpano.config");
            }else {
                shellCommand = AppConfig.getProperty("krpano.linux") + " " + AppConfig.getProperty("krpano.config");
            }
        } catch (Exception e) {
            log.error("参数路径初始化失败!", e);
        }
        return shellCommand;
    }

    public static void runShell(final String fileUrl,Map context,String saveDir,PanoProj bean) {
        final String finalShellCommand = getShellCommand();
        executor.execute(new Thread(() -> {
            try {
                long stime = System.currentTimeMillis();
                log.info(finalShellCommand + " " + fileUrl);
                final Process p = Runtime.getRuntime().exec(finalShellCommand + " " + fileUrl);
                // WATCHDOG
                Watchdog wd = null;
                final Long timeout = 20 * 60 * 1000L;// N分钟
                log.info(String.format("timeout is '%s' -> start watchdog", timeout));
                wd = new Watchdog(timeout);
                wd.addListener(() -> {
                    log.error("TIMEOUT -> kill process");
                    p.destroy();
                });
                // CREATE STREAM PUMPS
                final StreamPump out = new StreamPump("IN", p.getInputStream(), System.out, wd);
                final StreamPump err = new StreamPump("ERR", p.getErrorStream(), System.out, wd);

                // WAIT FOR TERMINATION
                p.waitFor();
                log.debug("Process finished");
                out.join();
                log.debug("Outthread finished");
                err.join();
                log.debug("Errthread finished");
                log.info(fileUrl + "============end=============用时:" + (System.currentTimeMillis() - stime));

                //重新计算全景场景图,且刷新xml
                KrpanoUtil.makeTourXml(context,saveDir,bean);
            } catch (Exception e) {
                log.error("全景图生成异常!" + fileUrl, e);
            }
        }));
    }
//
//    public static void main(String[] args) throws Exception {
////		runShell( "D:/home/krpano/3.JPG" );
//        URL url = Thread.currentThread().getContextClassLoader().getResource("");
//        System.out.println(url.getPath());
//    }
}
