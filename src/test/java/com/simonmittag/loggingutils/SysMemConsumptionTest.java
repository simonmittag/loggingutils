package com.simonmittag.loggingutils;

import junit.framework.TestCase;

/**
 * Test the system memory
 */
public class SysMemConsumptionTest extends TestCase {
    protected int TESTS = 10000;
    
    public void testSysMem() {
        SysMemoryUtil.init();

        int i=0;
        for (i=0; i<TESTS; i++ ) {
            System.out.println(SysMemoryUtil.physicalStats() + ", " + SysMemoryUtil.swapStats());
        }
        assertTrue("free swap memory should be greater than zero", SysMemoryUtil.swapStats().freeMB>0);
        assertTrue("free swap memory should be smaller or equal than total swap memory", SysMemoryUtil.swapStats().freeMB<=SysMemoryUtil.swapStats().totalMB);
        assertTrue("free physical memory should be greater than zero", SysMemoryUtil.physicalStats().freeMB>0);
        assertTrue("free physical memory should be smaller or equal than total physical memory", SysMemoryUtil.physicalStats().freeMB<=SysMemoryUtil.physicalStats().totalMB);

        SysMemoryUtil.destroy();
    }
}
