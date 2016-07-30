package com.simonmittag.loggingutils;

/**
 * TomcatThreads reads the Tomcat server threadpool so it can report how busy we are.
 * @author Simon Mittag
 */
public class TomcatThreads {

    /**
     * We print these key=value pairs
     */
    public static final String BUSY_THREADS = "busyThreads=";
    public static final String CURRENT_THREADS = ", currentThreads=";
    public static final String MAX_THREADS = ", maxThreads=";

    /**
     * maximum number of threads allowed in the HTTP server connection pool
     */
    protected int max;

    /**
     * current number of threads in the HTTP server connection poool
     */
    protected int current;

    /**
     * busy threads currently serving requests in the HTTP server connection pool
     */
    protected long busy;

    /**
     * Create printable version for stdout with all stats
     * @return The formatted, printable version
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if (max > 0) {
            //log only if tc threads were found
            sb.append(BUSY_THREADS);
            sb.append(busy);
            sb.append(CURRENT_THREADS);
            sb.append(current);
            sb.append(MAX_THREADS);
            sb.append(max);
        }
        return sb.toString();
    }
}
