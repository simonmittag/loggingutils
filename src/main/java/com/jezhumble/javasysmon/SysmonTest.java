package com.jezhumble.javasysmon;

/**
 * Created by IntelliJ IDEA.
 * User: simonmittag
 * Date: 15/02/12
 * Time: 1:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class SysmonTest {

    public static void main(String[] args) throws InterruptedException {


//        MemoryStats memstat = mon.physical();
//        System.out.println(memstat.toString());

//        while (true) {
//            JavaSysMon mon = new JavaSysMon();
//            CpuTimes times = mon.cpuTimes();
//
//            System.out.println("CPU user millis: " + times.getUserMillis());
//            System.out.println("CPU user pc: " + (float) times.getUserMillis() / totalMillis(times));
//            System.out.println("CPU system millis: " + times.getSystemMillis());
//            System.out.println("CPU system pc: " + (float) times.getSystemMillis() / totalMillis(times));
//            System.out.println("CPU idle millis: " + times.getIdleMillis());
//            System.out.println("CPU idle pc: " + (float) times.getIdleMillis() / totalMillis(times));
//            System.out.println("CPU usage total millis: " + (long) totalMillis(times));
//
//            System.out.println("CPU usage pc: " + (float) (times.getSystemMillis() + times.getUserMillis()) / totalMillis(times));
//            mon=null;
//            times=null;
//        }

        while (true) {
            JavaSysMon mon = new JavaSysMon();
            CpuTimes before = mon.cpuTimes();
            Thread.sleep(1000);
            CpuTimes after = mon.cpuTimes();
            System.out.println("CPU usage: " + after.getCpuUsage(before));
        }
    }

    private static float totalMillis(CpuTimes times) {
        return (float) (times.getSystemMillis() + times.getUserMillis() + times.getIdleMillis());
    }
}
