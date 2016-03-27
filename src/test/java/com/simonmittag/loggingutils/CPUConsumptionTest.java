package com.simonmittag.loggingutils;

import junit.framework.TestCase;

/**
 * Test CPU consumption
 */
public class CPUConsumptionTest extends TestCase {
    protected int TESTS = 100000;

    public void testCPU() {

        CPUUtil.init();
        int i=0;
        //mini load tests
        for (i=0; i<TESTS; i++ ) {
            System.out.println(CPUUtil.stats());
        }
        assertTrue("CPU utilization needs to be greater 0.00 ", CPUUtil.stats().consumed >= 0.00f);
        assertTrue("CPU utilization needs to be smaller than 1.00", CPUUtil.stats().consumed <= 1.00f);
        CPUUtil.destroy();
    }
    
   
}
