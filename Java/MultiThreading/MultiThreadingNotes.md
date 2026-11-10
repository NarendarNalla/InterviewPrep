# Level 0 — Multithreading Fundamentals Cheat Sheet

| Topic                              | Quick Revision Notes                                                                                                                 |
| ---------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------ |
| Process                            | A program in execution. Has its own memory space and resources.                                                                      |
| Thread                             | Smallest unit of execution scheduled within a process. Threads share heap memory but have separate stacks.                           |
| Process vs. Thread                 | Processes have separate address spaces; threads within a process share memory and resources.                                         |
| Multithreading                     | Executing multiple threads concurrently within a process. Improves responsiveness and can improve throughput.                        |
| Multitasking                       | Managing multiple tasks during the same period. Can involve multiple processes or threads.                                           |
| Multithreading vs. Multiprocessing | Multithreading uses multiple threads, usually sharing memory; multiprocessing uses separate processes with greater isolation.        |
| Benefits                           | Responsiveness, better resource utilization, overlapping I/O, and potential parallel execution.                                      |
| Limitations                        | Race conditions, deadlocks, synchronization overhead, and complex debugging.                                                         |
| Concurrency                        | Multiple tasks make progress over overlapping periods, even on one CPU core.                                                         |
| Parallelism                        | Multiple tasks execute at the same instant, typically on different CPU cores.                                                        |
| CPU-bound                          | Tasks limited mainly by computation, such as sorting large arrays.                                                                   |
| I/O-bound                          | Tasks spend much time waiting for I/O, such as database queries or HTTP requests.                                                    |
| Single-threaded                    | Application work runs through one thread; blocking work can delay subsequent tasks.                                                  |
| Multithreaded                      | Multiple threads perform work concurrently; coordination may be needed for shared data.                                              |
| Thread Safety                      | Code behaves correctly under concurrent access. Use synchronization, atomic variables, or other concurrency tools where appropriate. |

## Important Java Interview Points

- `start()` creates a new thread of execution; calling `run()` directly does not.
- `count++` is not atomic and can cause a race condition when shared between threads.
- Multithreading does not always improve performance; the result depends on the workload and available resources.