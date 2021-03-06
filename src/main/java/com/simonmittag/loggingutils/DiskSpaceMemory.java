package com.simonmittag.loggingutils;

/**
 * Diskspace wrapper object for print
 */
public class DiskSpaceMemory extends Memory {

    /**
     * We print key=value pairs
     */
    protected static final String FREE_DISK_SPACE_MB = "freeDiskSpaceMB=";
    protected static final String TOTAL_DISK_SPACE_MB = ", totalDiskSpaceMB=";
    protected static final String FREE_DISK_SPACE_PERCENT = ", freeDiskSpacePercent=";

    /**
     * Create instance with free/total megabytes
     * @param freeMB free megabytes diskspace
     * @param totalMB total megabytes diskspace
     */
    public DiskSpaceMemory(long freeMB, long totalMB) {
        super(freeMB, totalMB);
    }

    /**
     * Format for stdout
     * @return a printable String
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(FREE_DISK_SPACE_MB);
        sb.append(this.freeMB);
        sb.append(TOTAL_DISK_SPACE_MB);
        sb.append(this.totalMB);
        sb.append(FREE_DISK_SPACE_PERCENT);
        sb.append(percent.format((this.freeMB / (float) this.totalMB) * 100));
        return sb.toString();
    }
}

