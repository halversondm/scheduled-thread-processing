# Scheduled Thread Processing

Attempting to use different techniques to track running threads in a scheduled process on a Spring Scheduler.

`master` has the base code

`feature/count-down-latch` is using a `CountDownLatch` in order to notify the initiating thread that the child 'process' has completed.

`feature/thread-monitor` is using a `Future` in order to notify the initiating thread that the child 'thread' has completed.