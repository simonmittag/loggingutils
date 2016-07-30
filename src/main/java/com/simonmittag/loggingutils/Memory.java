package com.simonmittag.loggingutils;

import com.jezhumble.javasysmon.MemoryStats;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Memory supertype, do not instantiate me, use PhysicalMemory, SwapMemory or DiskSpaceMemory instead.
 */
public abstract class Memory {

    /**
     * Format as two digits
     */
    NumberFormat percent;

    /**
     * Free MB memory
     */
    long freeMB;

    /**
     * Total MB memory
     */
    long totalMB;

    /**
     * Do not create empty Memory objects
     */
    protected Memory() {
        this.percent = new DecimalFormat();
        percent.setGroupingUsed(false);
        percent.setMaximumFractionDigits(0);
        percent.setMinimumFractionDigits(0);
        percent.setMinimumIntegerDigits(2);
        percent.setMaximumIntegerDigits(2);
    }

    /**
     * Build a memory object with values instead.
     *
     * @param freeMB  free memory in megabytes.
     * @param totalMB total memory in megabytes
     */
    public Memory(long freeMB, long totalMB) {
        this();
        this.freeMB = freeMB;
        this.totalMB = totalMB;
    }

    /**
     * Build a memory object from Sysmon MemoryStats
     *
     * @param stats the memory stats
     */
    public Memory(MemoryStats stats) {
        this();
        this.freeMB = (int) (stats.getFreeBytes() / 1024f / 1024f);
        this.totalMB = (int) (stats.getTotalBytes() / 1024f / 1024f);
    }
}