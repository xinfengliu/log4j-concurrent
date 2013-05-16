log4j-concurrent
================

Improve log4j performance under mutli-threaded env (based on log4j-1.2.17).

Tested against the file appender and its descendants. The result shows over 30% improvement in latency or about 50% improvement in throughput under 200 threads that perform pure log writing to the same file on Sparc T4-2 server. In real workloads, the improvement is even bigger.

Usage: use JDK 1.5 or later; set bufferedIO to true and bufferSize to about 128K in log4j configuration.

**Warning**: please perform the strict tests before using it in your environment. I only tested file appenders.

Acknowledgements
================

Bartek Kowalewsk contributed a sample [improved Category.java](https://issues.apache.org/bugzilla/show_bug.cgi?id=51047&action=View) to Apache using read/write locks (since JDK 1.5). I took this version and made changes in other source files to improve the log4j performance.
