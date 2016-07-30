package com.simonmittag.loggingutils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Daemon for logging utils, runs in own thread.
 */
public class LoggingDaemon implements Runnable {
    /**
     * Sleep for a minute
     */
    protected static int SLEEP = 1000 * 60;

    /**
     * Get Apache commons logging instance
     */
    protected Log log = LogFactory.getLog(this.getClass());

    /**
     * Set this to false if you want the daemon to stop
     */
    public static boolean run = true;

    /**
     * Logs every SLEEP period
     */
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
        } finally {
            LoggingUtils.destroy();
            log.info(this.getClass().getName() + " daemon is dying");
        }
    }

    /**
     * Turn the daemon on
     */
    public static void on() {
        LoggingDaemon.run = true;
    }

    /**
     * And turn it off
     */
    public static void off() {
        LoggingDaemon.run = false;
    }
}
