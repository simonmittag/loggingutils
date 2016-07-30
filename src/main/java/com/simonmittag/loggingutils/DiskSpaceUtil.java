package com.simonmittag.loggingutils;

import java.io.File;

/**
 * DiskSpaceUtil, prints currently free disk space.
 */
public class DiskSpaceUtil {

    /**
     * Root file system
     */
    protected static File disk;

    /**
     * Work on the default file root
     */
    static {
        disk = File.listRoots()[0];
    }

    /**
     * Returns a DiskSpaceMemory object you can use to print current free space.
     * @return DiskSpaceMemory
     */
    public static DiskSpaceMemory stats() {
        return new DiskSpaceMemory((long) (disk.getFreeSpace() / 1024f / 1024f), (long) (disk.getTotalSpace() / 1024f / 1024f));
    }
}
