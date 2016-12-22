package cmdprocessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StreamPump extends Thread {

    private static Logger log = LoggerFactory.getLogger(StreamPump.class);

    private final OutputStream mOs;
    private final InputStream mIs;
    private final String mName;
    private final Watchdog watchdog;

    public StreamPump(String name, InputStream is, OutputStream os) {
        this(name, is, os, null);
    }

    public StreamPump(String name, InputStream is, OutputStream os, Watchdog wd) {
        mName = name;
        mIs = is;
        mOs = os;
        watchdog = wd;
        start();
    }

    @Override
    public void run() {
        try {
            int i;
            while ((i = mIs.read()) != -1) {
                mOs.write(i);
                if (watchdog != null) {
                    watchdog.reset();
                }
            }
            // final int l;
            // final byte[] buffer = new byte[1024];
            // while ((l = mIs.read(buffer)) > 0) {
            // mOs.write((mName + "{").getBytes());
            // mOs.write(buffer, 0, l);
            // mOs.write(("}").getBytes());
            // mOs.flush();
            // if (watchdog != null)
            // watchdog.reset();
            // }
        } catch (final IOException e) {
            System.out.println(String.format("StreamPump '%s' failed ", mName) + e.getMessage());
            e.printStackTrace();
        }
        System.out.println(String.format("StreamPump '%s' finieshed ", mName));
    }
}
