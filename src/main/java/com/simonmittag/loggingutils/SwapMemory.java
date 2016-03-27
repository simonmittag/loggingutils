package com.simonmittag.loggingutils;

import com.jezhumble.javasysmon.MemoryStats;

/**
 * Swap Memory Wrapper object for print
 */
public class SwapMemory extends Memory {
    protected static final String FREE_SWAP_MEM_MB = "freeSwapMemMB=";
    protected static final String TOTAL_SWAP_MEM_MB = ", totalSwapMemMB=";
    protected static final String FREE_SWAP_MEM_PERCENT = ", freeSwapMemPercent=";

    public SwapMemory() {
        //do nothing
    }

    public SwapMemory(MemoryStats stats) {
        super(stats);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(FREE_SWAP_MEM_MB);
        sb.append(freeMB);
        sb.append(TOTAL_SWAP_MEM_MB);
        sb.append(totalMB);
        sb.append(FREE_SWAP_MEM_PERCENT);
        sb.append(percent.format(100 * (float) freeMB / totalMB));
        return sb.toString();
    }
}
