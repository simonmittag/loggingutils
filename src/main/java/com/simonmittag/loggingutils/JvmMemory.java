package com.simonmittag.loggingutils;

/**
 * Wrapper object to print Jvm memory in log statements
 */
public class JvmMemory {
    protected static final String FREE_JVM_MEM_MB = "freeJvmMemMB=";
    protected static final String FREE_JVM_MEM_PERCENT = ", freeJvmMemPercent=";
    protected static final String TOTAL_JVM_MEM_MB = ", totalJvmMemMB=";
    protected static final String MAX_JVM_MEM_MB = ", maxJvmMemMB=";
    protected float freeMb;
    protected float totalMb;
    protected float maxMb;

    public static int lowMemoryPercentage = 25;

    public float getFreeMb() {
        return freeMb;
    }

    public void setFreeMb(float freeMb) {
        this.freeMb = freeMb;
    }

    public float getTotalMb() {
        return totalMb;
    }

    public void setTotalMb(float totalMb) {
        this.totalMb = totalMb;
    }

    public float getMaxMb() {
        return maxMb;
    }

    public void setMaxMb(float maxMb) {
        this.maxMb = maxMb;
    }

    public int percentFree() {
        return (int) ((freeMb / totalMb) * 100);
    }

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

