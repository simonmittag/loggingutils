package com.simonmittag.loggingutils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Daemon for logging utils, runs in own thread.
 */
public class LoggingDaemon implements Runnable {
    protected static int SLEEP = 1000 * 60;
    protected Log log = LogFactory.getLog(this.getClass());
    public static boolean run = true;

    public void run() {
        
        try {
            LoggingUtils.init();
            log.info(this.getClass().getName() + " daemon is alive");
            while (run) {
                log.info(LoggingUtils.printStats());
                try {
                    Thread.sleep(SLEEP);
                } catch (InterruptedException e) {
                    //do nothing
                }
            }
        } catch (Exception e) {
            System.out.println("LoggingDaemon aborted uncontrolled, cause: " + e.getMessage());
            e.printStackTrace();
        } finally {
            LoggingUtils.destroy();
            log.info(this.getClass().getName() + " daemon is dying");
        }
    }

    public static void on() {
        LoggingDaemon.run = true;
    }

    public static void off() {
        LoggingDaemon.run = false;
    }
}
