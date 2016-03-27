package com.simonmittag.loggingutils;

import com.jezhumble.javasysmon.MemoryStats;

/**
 * Physical memory wrapper object for print
 */
public class PhysicalMemory extends Memory {
    protected static final String FREE_SYS_MEM_MB = "freeSysMemMB=";
    protected static final String TOTAL_SYS_MEM_MB = ", totalSysMemMB=";
    protected static final String FREE_SYS_MEM_PERCENT = ", freeSysMemPercent=";

    public PhysicalMemory() {
        super();
    }

    public PhysicalMemory(MemoryStats stats) {
        super(stats);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if (totalMB > 0) {
            sb.append(FREE_SYS_MEM_MB);
            sb.append(freeMB);
            sb.append(TOTAL_SYS_MEM_MB);
            sb.append(totalMB);
            sb.append(FREE_SYS_MEM_PERCENT);
            sb.append(percent.format(100 * (float) freeMB / totalMB));
        }
        return sb.toString();
    }
}

