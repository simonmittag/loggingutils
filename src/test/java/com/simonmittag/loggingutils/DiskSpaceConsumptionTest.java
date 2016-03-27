package com.simonmittag.loggingutils;

import junit.framework.TestCase;

/**
 * Test disk space consumption
 */
public class DiskSpaceConsumptionTest extends TestCase {
    public void testDiskSpace() {
        System.out.println(DiskSpaceUtil.stats());
        assertTrue("free disk space needs to be greater than zero megabyte", DiskSpaceUtil.stats().freeMB>0);
        assertTrue("total disk space needs to be greater than zero megabyte", DiskSpaceUtil.stats().totalMB>0);
        assertTrue("free disk space needs to be smaller or equal total disk space", DiskSpaceUtil.stats().freeMB <= DiskSpaceUtil.stats().totalMB);
    }
}
