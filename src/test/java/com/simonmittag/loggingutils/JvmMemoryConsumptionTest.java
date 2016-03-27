package com.simonmittag.loggingutils;

import junit.framework.TestCase;

/**
 * Test the Jvm memory consumption
 */
public class JvmMemoryConsumptionTest extends TestCase {
    protected int TESTS = 100000;
    
    public void testMem() {
        int i=0;
        for (i=0; i<TESTS; i++ ) {
            System.out.println(JvmMemoryUtil.stats());
        }
        assertTrue("Jvm free memory needs to be greater than zero", JvmMemoryUtil.getFreeMb()>0);
        assertTrue("Jvm free memory needs to be smaller or equal than Jvm total memory", JvmMemoryUtil.getFreeMb()<=JvmMemoryUtil.getTotalMb());
        assertTrue("Jvm total memory needs to be smaller or equal Jvm max memory", JvmMemoryUtil.getTotalMb()<= JvmMemoryUtil.getMaxMb());
    }
}
