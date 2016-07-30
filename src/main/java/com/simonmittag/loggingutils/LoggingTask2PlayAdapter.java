package com.simonmittag.loggingutils;

import play.Logger;

/**
 * This is an adapter task for the Play framework Logger.
 * TODO: this could probably be done a little nicer without using play but a log wrapper.
 */
public class LoggingTask2PlayAdapter implements Runnable {

    /**
     * Run this thread to print stats (once)
     */
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
