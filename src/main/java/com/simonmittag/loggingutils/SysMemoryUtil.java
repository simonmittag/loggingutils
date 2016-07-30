package com.simonmittag.loggingutils;

import com.jezhumble.javasysmon.JavaSysMon;

/**
 * Wrapper object for Unix system memory, uses embedded Javasysmon
 */
public class SysMemoryUtil {

    /**
     * Set this to false to stop the updater
     */
    static boolean run = true;

    /**
     * Updater Thread
     */
    static Thread updaterThread;

    /**
     * Value for physical RAM
     */
    static Memory physical;

    /**
     * Value for OS swap memory
     */
    static Memory swap;

    /**
     * Sleep for this period
     */
    static final long MEM_WAIT = 1000;

    /**
     * Our background updater. Memory stats are update once every MEM_WAIT interval
     */
    static Runnable updater = new Runnable() {
        public void run() {
            while (run) {
                try {
                    Thread.sleep(MEM_WAIT);
                } catch (InterruptedException e) {
                    //do nothing
                }
                SysMemoryUtil.physical = physical();
                SysMemoryUtil.swap = swap();
            }
        }
    };

    /**
     * Init the thread
     */
    public static void init() {
        physical = physical();
        swap = swap();
        updaterThread = new Thread(updater);
        updaterThread.start();
    }

    /**
     * Destroy the thread
     */
    public static void destroy() {
        run = false;
        updaterThread = null;
    }

    /**
     * Returns Memory[] for printing physical and swap memory stats
     *
     * @return Memory[] stats
     */
    public static Memory[] stats() {
        return new Memory[]{physicalStats(), swapStats()};
    }

    /**
     * Returns Memory object for printing physical memory stats.
     *
     * @return Memory stats
     */
    public static Memory physicalStats() {
        return physical;
    }

    /**
     * Returns Memory object for printing swap memory stats
     *
     * @return Memory swap stats
     */
    public static Memory swapStats() {
        return swap;
    }

    /**
     * Internal JavaSysMon wrapper for physical memory
     *
     * @return Memory physical memory stats
     */
    private static Memory physical() {
        try {
            JavaSysMon mon = new JavaSysMon();
            return new PhysicalMemory(mon.physical());
        } catch (Exception e) {
            return new PhysicalMemory();
        }
    }

    /**
     * Internal JavaSysMon wrapper for swap memory
     *
     * @return Memory swap memory stats
     */
    private static Memory swap() {
        try {
            JavaSysMon mon = new JavaSysMon();
            return new SwapMemory(mon.swap());
        } catch (Exception e) {
            return new SwapMemory();
        }
    }
}
