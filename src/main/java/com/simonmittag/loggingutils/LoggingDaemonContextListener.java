package com.simonmittag.loggingutils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Start the logging daemon by adding it to web.xml as a ServletContextListener
 * This is for Servlet containers such as Tomcat.
 */
public class LoggingDaemonContextListener implements ServletContextListener {

    /**
     * Default port for Tomcat
     */
    public static int TCP_PORT=8080;

    /**
     * Our background Thread
     */
    protected Thread daemon;

    /**
     * Logger instance
     */
    protected Log log = LogFactory.getLog(this.getClass());

    /**
     * This is to bootstrap the listener.
     * @param sce ServletContextEvent
     */
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ServletContext c = sce.getServletContext();
            if (c != null) {
                if (c.getInitParameter("tcp_port") != null) {
                    TCP_PORT = Integer.parseInt(c.getInitParameter("tcp_port"));
                    log.info("logging daemon set servlet container tcp_port to " + c.getInitParameter("tcp_port"));
                }
                if (c.getInitParameter("poll_interval_seconds") != null) {
                    LoggingDaemon.SLEEP = 1000 * Integer.parseInt(c.getInitParameter("poll_interval_seconds"));
                    log.info("logging daemon set poll interval to " + c.getInitParameter("poll_interval_seconds") + "s");
                }
            }
            LoggingDaemon.on();

            daemon = new Thread(new LoggingDaemon());
            daemon.setDaemon(true);
            daemon.setName("logging daemon");
            daemon.start();
            log.info(" logging daemon has started");
        } catch (Exception e) {
            log.error("logging daemon startup failed, cause: " + e.getMessage());
        }
    }

    /**
     * Shut down the listener
     * @param sce ServletContextEvent
     */
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            LoggingDaemon.off();
            daemon.join();
            daemon = null;
            log.info(" logging daemon has stopped");
        } catch (Exception e) {
            log.error("logging daemon stopping has failed, shutdown uncontrolled, cause: " + e.getMessage());
        }
    }
}
