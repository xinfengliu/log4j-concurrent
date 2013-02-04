log4j-concurrent
================

Improve log4j performance under mutli-threaded env (based on log4j-1.2.17).

Tested for file appender and its descendants. The result shows it has over 30% improvement under 200 threads that perform pure log writing to the same file on Sparc T4 server. In read workloads, the improvement is even bigger.

Acknowledgement
===============

Bartek Kowalewsk contributed the [improved Category.java](https://issues.apache.org/bugzilla/show_bug.cgi?id=51047&action=View) using read/write locks (since JDK 1.5).
