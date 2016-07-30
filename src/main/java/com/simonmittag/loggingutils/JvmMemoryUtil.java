package com.simonmittag.loggingutils;

/**
 * Java Memory Util for logging
 */
public class JvmMemoryUtil {

    /**
     * Exec runtime env
     */
    protected static Runtime runtime = Runtime.getRuntime();

    /**
     * Free MB Jvm memory
     * @return Free MB Jvm memory
     */
    public static float getFreeMb() {
        return (runtime.freeMemory() / 1024f / 1024f);
    }

    /**
     * Total MB Jvm memory
     * @return Total MB Jvm memory
     */
    public static float getTotalMb() {
        return (runtime.totalMemory() / 1024f / 1024f);
    }

    /**
     * Max MB Jvm memory
     * @return Max MB Jvm memory
     */
    public static float getMaxMb() {
        return (runtime.maxMemory() / 1024f / 1024f);
    }

    /**
     * Use this value object for printing and log statements.
     * @return A wrapper obj with all Jvm stats
     */
    public static JvmMemory stats() {
        JvmMemory m = new JvmMemory();
        m.freeMb = getFreeMb();
        m.totalMb = getTotalMb();
        m.maxMb = getMaxMb();
        return m;
    }
}
