Loggingutils
============

Loggingutils is a small and simple wrapper around JavaSysMon (https://github.com/jezhumble/javasysmon) by Jez Humble. It prints system stats to the standard logging system (via commons-logging) in regular intervals.

Building from source
--------------------

Use maven to build:

    mvn clean package

Import
------

Declare as maven dependency

    <dependency>
      <groupId>com.simonmittag</groupId>
      <artifactId>loggingutils</artifactId>
      <version>1.0</version>
    </dependency>

Using the library from Servlet container
----------------------------------------

Simply put the jar in your classpath, and use it like this by including in your web.xml:

    <listener>
        <listener-class>com.simonmittag.loggingutils.LoggingDaemonContextListener</listener-class>
    </listener>

Current support and limitations (from JavaSysMon, see above)
------------------------------------------------------------

* Currently supports Mac OS X, Linux, Windows, and Solaris
* Solaris binary is compiled on x86 on OpenSolaris, so it won't work on SPARC, and has not been tested on SunOS < 5.11
* Solaris CPU usage only correctly reports usage for first CPU.
* Supports Java 1.4 and above
* CPU speed on Linux only reports correct values for Intel CPUs

License
-------

Loggingutils uses the Apache license. It is based on JavaSysMon which is available under a NetBSD license: https://github.com/jezhumble/javasysmon/blob/master/LICENSE
