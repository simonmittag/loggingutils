package com.simonmittag.loggingutils;

import com.jezhumble.javasysmon.CpuTimes;
import com.jezhumble.javasysmon.JavaSysMon;

/**
 * Returns CPU usage data. Wrapper class around embedded Javasysmon project.
 */
public class CPUUtil {
    static boolean run = true;
    static CPUPercentage cpu;
    static final long CPU_WAIT = 1000;
    static Thread updaterThread;
    static Runnable updater = new Runnable() {
        public void run() {
            while (run) {
                try {
                    Thread.sleep(CPU_WAIT);
                } catch (InterruptedException e) {
                    //do nothing
                }
                CPUUtil.cpu = utilizedPercentage();
            }
        }
    };

    /**
     * Physical memory stats are update once every CPU_WAIT interval
     */
    public static void init() {
        cpu = utilizedPercentage();
        updaterThread = new Thread(updater);
        updaterThread.start();
    }

    public static void destroy() {
        run = false;
        updaterThread = null;
    }

    /**
     * Call this and use CPUPercentage in your log statement
     *
     * @return
     */
    public static CPUPercentage stats() {
        return cpu;
    }

    /**
     * Create a sysmon and pull CPU usage for CPU_WAIT interval.
     *
     * @return
     */
    private static CPUPercentage utilizedPercentage() {
        try {
            JavaSysMon mon = new JavaSysMon();
            CpuTimes before = mon.cpuTimes();
            try {
                Thread.sleep((long) CPU_WAIT);
            } catch (InterruptedException e) {
                //do nothing
            }
            CpuTimes after = mon.cpuTimes();
            return new CPUPercentage(after.getCpuUsage(before));
        } catch (Exception e) {
            //this only happens when unloading context when the application is shut down. return 0 but don't print it.
            return new CPUPercentage(0);
        }
    }

}
