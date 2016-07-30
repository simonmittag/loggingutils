package com.simonmittag.loggingutils;

/**
 * Wrapper object to print Jvm memory in log statements
 */
public class JvmMemory {
    /**
     * We print these key=value pairs
     */
    protected static final String FREE_JVM_MEM_MB = "freeJvmMemMB=";
    protected static final String FREE_JVM_MEM_PERCENT = ", freeJvmMemPercent=";
    protected static final String TOTAL_JVM_MEM_MB = ", totalJvmMemMB=";
    protected static final String MAX_JVM_MEM_MB = ", maxJvmMemMB=";

    /**
     * free memory in megabytes
     */
    protected float freeMb;

    /**
     * total memory in megabytes
     */
    protected float totalMb;

    /**
     * max memory in megabytes
     */
    protected float maxMb;

    /**
     * Get percent free memory for Jvm
     * @return percentage free
     */
    public int percentFree() {
        return (int) ((freeMb / totalMb) * 100);
    }

    /**
     * Create formatted toString for use with stdout.
     * @return A formatted String with key=value pairs
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(FREE_JVM_MEM_MB);
        sb.append((int) freeMb);
        sb.append(FREE_JVM_MEM_PERCENT);
        sb.append(percentFree());
        sb.append(TOTAL_JVM_MEM_MB);
        sb.append((int) totalMb);
        sb.append(MAX_JVM_MEM_MB);
        sb.append((int) maxMb);
        return sb.toString();
    }
}

