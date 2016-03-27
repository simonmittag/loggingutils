package com.simonmittag.loggingutils;

/**
 * Java Memory Util for logging
 */
public class JvmMemoryUtil {

    protected static Runtime runtime = Runtime.getRuntime();

    public static float getFreeMb() {
        return (float) (runtime.freeMemory() / 1024f / 1024f);
    }

    public static float getTotalMb() {
        return (float) (runtime.totalMemory() / 1024f / 1024f);
    }

    public static float getMaxMb() {
        return (float) (runtime.maxMemory() / 1024f / 1024f);
    }

    /**
     * Use this value object for printing and log statements.
     *
     * @return
     */
    public static JvmMemory stats() {
        JvmMemory m = new JvmMemory();
        m.freeMb = getFreeMb();
        m.totalMb = getTotalMb();
        m.maxMb = getMaxMb();
        return m;
    }
}
