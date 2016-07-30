package com.simonmittag.loggingutils;

import com.jezhumble.javasysmon.MemoryStats;

/**
 * Swap Memory Wrapper object for print
 */
public class SwapMemory extends Memory {

    /**
     * Our key=value pairs
     */
    protected static final String FREE_SWAP_MEM_MB = "freeSwapMemMB=";
    protected static final String TOTAL_SWAP_MEM_MB = ", totalSwapMemMB=";
    protected static final String FREE_SWAP_MEM_PERCENT = ", freeSwapMemPercent=";


    /**
     * Create instance
     */
    public SwapMemory() {
        //do nothing
    }

    /**
     * Create instance with memory stats
     * @param stats memoryStats
     */
    public SwapMemory(MemoryStats stats) {
        super(stats);
    }

    /**
     * Create printable String with all stats
     * @return A printable String for use with stdout
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(FREE_SWAP_MEM_MB);
        sb.append(freeMB);
        sb.append(TOTAL_SWAP_MEM_MB);
        sb.append(totalMB);
        sb.append(FREE_SWAP_MEM_PERCENT);
        try {
            sb.append(percent.format(100 * (float) freeMB / totalMB));
        } catch (Exception e) {
            sb.append("0");
        }
        return sb.toString();
    }
}
