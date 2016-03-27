package com.simonmittag.loggingutils;

/**
 * Logging utils is a convenience object for logging all system stats
 */
public class LoggingUtils {
    protected static final String COMMA = ", ";
    protected static int attempts = 1;
    protected static int sleep = 1 * 1000;

    public static String printStats() {
        StringBuffer sb = new StringBuffer();
        CPUPercentage pc = CPUUtil.stats();
        if (pc != null) {
            sb.append(pc);
            sb.append(COMMA);
        }
        sb.append(DiskSpaceUtil.stats());
        sb.append(COMMA);
        sb.append(JvmMemoryUtil.stats());
        Memory[] sm = SysMemoryUtil.stats();
        if (sm != null && sm.length > 0) {
            boolean n = false;
            for (Memory m : sm) {
                if (m != null) n = true;
                break;
            }
            if (n) sb.append(COMMA);
        }
        for (Memory memory : sm) {
            if (memory != null) {
                sb.append(memory);
                sb.append(COMMA);
            }
        }
        sb.append(TomcatThreadUtil.stats());
        //clean
        String s = sb.toString();
        if (s.endsWith(COMMA)) {
            s = s.substring(0, s.length() - 2);
        }
        return s;
    }

    public static void init() {
        SysMemoryUtil.init();
        CPUUtil.init();
    }

    public static void destroy() {
        SysMemoryUtil.destroy();
        CPUUtil.destroy();
    }

    public static void main(String[] args) {
        eval(args);

        LoggingUtils.init();
        for (int i = 0; i < attempts; i++) {
            try {
                System.out.println("n" + i + ": " + LoggingUtils.printStats());
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                //do nothing
            }
        }
        LoggingUtils.destroy();

    }

    private static void eval(String[] args) {
        try {
            if (args != null & args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    if ("-n".equals(args[i])) {
                        attempts = Integer.parseInt(args[i + 1].trim());
                    }
                    if ("-t".equals(args[i])) {
                        sleep = Integer.parseInt(args[i + 1].trim()) * 1000;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Usage:  LoggingUtils [-n] repeat [-t] pause");
            System.out.println("        [-n] repeating attempts to log");
            System.out.println("        [-t] pause in seconds between attempts");
        }
    }
}
