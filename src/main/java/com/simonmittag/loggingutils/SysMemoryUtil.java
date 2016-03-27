package com.simonmittag.loggingutils;

import com.jezhumble.javasysmon.JavaSysMon;

/**
 * Wrapper object for Unix system memory, uses embedded Javasysmon
 */
public class SysMemoryUtil {
    static boolean run = true;
    static Thread updaterThread;
    static Memory physical;
    static Memory swap;
    static final long CPU_WAIT = 1000;

    static Runnable updater = new Runnable() {
        public void run() {
            while (run) {
                try {
                    Thread.sleep(CPU_WAIT);
                } catch (InterruptedException e) {
                    //do nothing
                }
                SysMemoryUtil.physical = physical();
                SysMemoryUtil.swap = swap();
            }
        }
    };

    /**
     * Physical memory stats are update once every CPU_WAIT interval
     */
    public static void init() {
        physical = physical();
        swap = swap();
        updaterThread = new Thread(updater);
        updaterThread.start();
    }

    public static void destroy() {
        run = false;
        updaterThread = null;
    }

    /**
     * Returns Memory[] for printing physical and swap memory stats
     *
     * @return Memory[]
     */
    public static Memory[] stats() {
        return new Memory[]{physicalStats(), swapStats()};
    }

    /**
     * Returns Memory object for printing physical memory stats.
     */
    public static Memory physicalStats() {
        return physical;
    }

    /**
     * Returns Memory object for printing swap memory stats
     *
     * @return Memory
     */
    public static Memory swapStats() {
        return swap;
    }

    /**
     * Internal JavaSysMon wrapper for physical memory
     *
     * @return Memory
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
     * @return Memory
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
