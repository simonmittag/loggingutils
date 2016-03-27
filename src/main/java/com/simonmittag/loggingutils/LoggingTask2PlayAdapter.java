package com.simonmittag.loggingutils;

import play.Logger;

/**
 * Daemon for logging utils, runs in own thread.
 */
public class LoggingTask2PlayAdapter implements Runnable {

    public void run() {
        try {
            LoggingUtils.init();
            Logger.info(LoggingUtils.printStats());
        } catch (Exception e) {
            // die quietly inside thread
        } finally {
            LoggingUtils.destroy();
        }
    }
}
