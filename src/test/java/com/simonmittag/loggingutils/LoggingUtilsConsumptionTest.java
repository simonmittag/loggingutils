package com.simonmittag.loggingutils;

import junit.framework.TestCase;

/**
 * Test the logging utility
 */
public class LoggingUtilsConsumptionTest extends TestCase {
    protected int TESTS = 100000;

    public void testLoggingUtils() {

        LoggingUtils.init();
        
        for(int i=0;i<TESTS;i++) {
            System.out.println(LoggingUtils.printStats());
        }
        assertNotNull("needs to log something meaningful, can't be null", LoggingUtils.printStats());
        
        LoggingUtils.destroy();
    }
}
