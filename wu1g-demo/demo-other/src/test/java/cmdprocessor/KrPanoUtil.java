package cmdprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class KrPanoUtil {
    private static Logger log = LoggerFactory.getLogger(KrPanoUtil.class);
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(25, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

    private static String shellCommand = null;

    private static String  getShellCommand() {
        try {
            if(shellCommand!=null){
                return shellCommand;
            }
            shellCommand = "D:/home/krpano/win/krpanotools64.exe makepano -config=templates/vtour-multires.config  -askforxmloverwrite=false -xml=false -html=false -panotype=sphere -tilesize=256 -adjustlevelsizes=true -adjustlevelsizesformipmapping=true -progressiveloading=auto -preview=true -cspreview=true -quiet=false -maxcubesize=512";
        } catch (Exception e) {
            System.out.println("参数路径初始化失败!"+ e.getMessage());
        }
        return shellCommand;
    }

    public static void runShell(final String fileUrl) {
        final String finalShellCommand = getShellCommand();
        executor.execute(new Thread(() -> {
            try {
                long stime = System.currentTimeMillis();
                System.out.println(finalShellCommand+" "+fileUrl);
                final Process p = Runtime.getRuntime().exec(finalShellCommand + " " + fileUrl);
                    // WATCHDOG
                    Watchdog wd = null;
                    final Long timeout = 1 * 60 * 1000L;// 20分钟
                    System.out.println(String.format("timeout is '%s' -> start watchdog", timeout));
                    wd = new Watchdog(timeout);
                    wd.addListener(() -> {
                        System.out.println("TIMEOUT -> kill process");
                        p.destroy();
                    });
//                    if (log.isDebugEnabled()) {
                        // CREATE STREAM PUMPS
                        final StreamPump out = new StreamPump("IN", p.getInputStream(), System.out, wd);
                        final StreamPump err = new StreamPump("ERR", p.getErrorStream(), System.out, wd);

                        // WAIT FOR TERMINATION
                        p.waitFor();
                        System.out.println("Process finished");
                        out.join();
                        System.out.println("Outthread finished");
                        err.join();
                        System.out.println("Errthread finished");
//                    }
                System.out.println(fileUrl + "============end=============用时:" + (System.currentTimeMillis() - stime));
            } catch (Exception e) {
                System.out.println("全景图生成异常!" + fileUrl+e.getMessage());
            }
        }));
    }
    public static void main(String[] args) throws Exception {
		runShell( "D:/home/krpano/3.JPG" );
    }
}
