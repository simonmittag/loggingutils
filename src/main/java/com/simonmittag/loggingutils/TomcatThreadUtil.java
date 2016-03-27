package com.simonmittag.loggingutils;

import javax.management.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Simon Mittag
 */
public class TomcatThreadUtil {

    private static final String TYPE_THREAD_POOL = "*:type=ThreadPool,*";

    public static TomcatThreads stats() {
        TomcatThreads tomcatThreads = new TomcatThreads();
        tomcatThreads.current = 0;
        tomcatThreads.max = 0;
        tomcatThreads.busy = 0;

        try {
            //find tomcat's mbean server
            ArrayList list = MBeanServerFactory.findMBeanServer(null);
            MBeanServer mbeanServer = (MBeanServer) list.get(0);

            tomcatThreads.max = (Integer) findThreadPoolAttribute(mbeanServer, "maxThreads");
            tomcatThreads.current = (Integer) findThreadPoolAttribute(mbeanServer, "currentThreadCount");
            tomcatThreads.busy = (Integer) findThreadPoolAttribute(mbeanServer, "currentThreadsBusy");

        } catch (Exception e) {
            //do not complain if stats are absent, simply don't print them.
        }

        return tomcatThreads;
    }

    private static Object findThreadPoolAttribute(MBeanServer mbeanServer, String attribute) throws Exception {
        Object rval = null;
        try {
            Set<ObjectName> mbeans = mbeanServer.queryNames(new ObjectName(TYPE_THREAD_POOL), null);
            for(ObjectName mbean : mbeans) {
                if(mbean.getCanonicalName().contains(LoggingDaemonContextListener.TCP_PORT+"")) {
                    rval = (mbeanServer.getAttribute(mbean, attribute));
                }
            }
        } catch (Exception e) {
            rval=0;
        }
        return rval;
    }

}
