package com.wu1g.pano.utils.cmdprocessor;

import java.util.LinkedList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Slf4j
public class Watchdog extends Thread {

  private final long timeout;
  private long origin = Long.MAX_VALUE;
  private final List<WatchdogListener> listeners = new LinkedList<>();

  public Watchdog(long timeout) {
    this.timeout = timeout;
    start();
  }

  public void addListener(WatchdogListener l) {
    listeners.add(l);
  }

  protected void sendTimeout() {
    for (final WatchdogListener l : listeners) {
      l.timeout();
    }
  }

  protected long now() {
    return System.currentTimeMillis();
  }

  protected synchronized void check() {
    if ((origin != Long.MAX_VALUE) && ((origin + timeout) < now())) {
      sendTimeout();
      origin = Long.MAX_VALUE;
    }
  }

  public synchronized void reset() {
    origin = now();
  }

  @Override
  public void run() {
    try {
      for (; !isInterrupted();) {
        Thread.sleep(1000);
        check();
      }
    } catch (final InterruptedException e) {
      log.info("Watchdog has been interrupted");
    }
  }

  /**
   * just for testing
   * 
   * @param args
   * @throws InterruptedException
   */
  public static void main(String[] args) throws InterruptedException {
    final Watchdog wd = new Watchdog(1000);
    wd.addListener(new WatchdogListener() {
      @Override
      public void timeout() {
        log.debug("timeout");
        System.exit(0);
      }
    });

    for (int i = 0; i < 10; ++i) {
      wd.reset();
      Thread.sleep(500);
      log.debug("reset");
    }

    log.debug("wait");
  }

}
