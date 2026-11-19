
# Java Multithreading – Interview Preparation

## 1. Multithreading Fundamentals

### Q1. What is Multithreading in Java?

Multithreading is a Java feature that allows multiple threads to execute concurrently within a single process.

A thread is the smallest unit of execution within a process.

Advantages:
- Improves application responsiveness.
- Enables concurrent task execution.
- Better CPU utilization.
- Useful for asynchronous processing.
- Helps build scalable applications.

Example:

    public class Main {
        public static void main(String[] args) {

            Thread t1 = new Thread(() ->
                System.out.println("Thread 1")
            );

            Thread t2 = new Thread(() ->
                System.out.println("Thread 2")
            );

            t1.start();
            t2.start();
        }
    }

Note: The execution order is not guaranteed.

### Q2. What is the difference between Process and Thread?

| Process | Thread |
|---|---|
| Independent executing program. | Smallest execution unit within a process. |
| Has its own memory space. | Shares process memory with other threads. |
| Communication is comparatively expensive. | Communication is easier through shared memory. |
| Context switching is generally more expensive. | Context switching is generally lighter. |
| More isolated. | Requires synchronization for shared mutable data. |

Example:

- Process: Running a browser.
- Threads: Browser rendering, network, and background tasks.

### Q3. What are the advantages of Multithreading?

1. Better CPU utilization.
2. Improved application performance for suitable workloads.
3. Better responsiveness.
4. Concurrent execution.
5. Efficient background processing.
6. Improved throughput for I/O-bound applications.

### Q4. What is the difference between Concurrency and Parallelism?

Concurrency:
Multiple tasks make progress during overlapping time periods.

Parallelism:
Multiple tasks execute simultaneously on multiple CPU cores.

Example:

Concurrency:
One CPU core switches between multiple tasks.

Parallelism:
Two CPU cores execute two tasks simultaneously.

### Q5. What is a Thread in Java?

A thread is an independent path of execution within a Java application.

Java supports thread creation using:

1. Extending Thread class.
2. Implementing Runnable interface.
3. Implementing Callable interface.
4. Using ExecutorService.
5. Using CompletableFuture.
6. Using Virtual Threads (Java 21+).

---

## 2. Creating Threads

### Q6. How many ways can we create a thread in Java?

Common approaches:

1. Extending Thread.
2. Implementing Runnable.
3. Implementing Callable with ExecutorService.

Modern applications generally prefer Runnable, Callable, and ExecutorService over directly extending Thread.

### Q7. What is the difference between Thread and Runnable?

| Thread | Runnable |
|---|---|
| Class. | Functional interface. |
| Represents a thread of execution. | Represents a task. |
| Cannot extend another class. | Class can extend another class. |
| Combines task and thread management. | Separates task from execution mechanism. |

Example using Thread:

    class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("Thread running");
        }
    }

    public class Main {
        public static void main(String[] args) {
            MyThread thread = new MyThread();
            thread.start();
        }
    }

Example using Runnable:

    class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println("Runnable running");
        }
    }

    public class Main {
        public static void main(String[] args) {
            Thread thread = new Thread(new MyTask());
            thread.start();
        }
    }

### Q8. What is the difference between start() and run()?

| start() | run() |
|---|---|
| Starts a new thread. | Executes like a normal method call. |
| JVM schedules the new thread. | No new thread is created by calling it directly. |
| Calls run() internally. | Contains the task logic. |
| Can be called only once on a Thread instance. | Can be called multiple times as a normal method. |

Example:

    public class Main {
        public static void main(String[] args) {

            Thread t1 = new Thread(() ->
                System.out.println(
                    Thread.currentThread().getName()
                )
            );

            t1.run();
            t1.start();
        }
    }

Output:

    main
    Thread-0

### Q9. What happens if we call start() twice?

Java throws IllegalThreadStateException.

Example:

    Thread t1 = new Thread(() ->
        System.out.println("Running")
    );

    t1.start();
    t1.start(); // IllegalThreadStateException

A Thread instance cannot be restarted after termination.

### Q10. What is Runnable?

Runnable is a functional interface representing a task that returns no result.

    @FunctionalInterface
    public interface Runnable {
        void run();
    }

Example:

    Runnable task = () ->
        System.out.println("Task executed");

    new Thread(task).start();

### Q11. What is Callable?

Callable is an interface that represents a task that can return a result and throw checked exceptions.

    public interface Callable<V> {
        V call() throws Exception;
    }

Example:

    Callable<Integer> task = () -> {
        return 10 + 20;
    };

    ExecutorService executor =
        Executors.newSingleThreadExecutor();

    Future<Integer> future = executor.submit(task);

    try {
        System.out.println(future.get());
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        executor.shutdown();
    }

### Q12. Difference between Runnable and Callable.

| Runnable | Callable |
|---|---|
| Introduced before Callable. | Introduced in Java 5. |
| run() method. | call() method. |
| Does not return a result. | Returns a result. |
| Cannot declare checked exceptions. | Can throw checked exceptions. |
| Used with Thread and ExecutorService. | Commonly used with ExecutorService. |

---

## 3. Thread Lifecycle and Methods

### Q13. Explain the Thread Lifecycle in Java.

Java defines six thread states:

1. NEW
2. RUNNABLE
3. BLOCKED
4. WAITING
5. TIMED_WAITING
6. TERMINATED

Explanation:

NEW:
Thread object created but not started.

RUNNABLE:
Thread is eligible to run or is running.

BLOCKED:
Waiting to acquire an intrinsic monitor lock.

WAITING:
Waiting indefinitely for another thread's action.

TIMED_WAITING:
Waiting for a specified duration.

TERMINATED:
Thread execution completed.

Example:

    Thread t1 = new Thread(() ->
        System.out.println("Running")
    );

    System.out.println(t1.getState()); // NEW

    t1.start();

    System.out.println(t1.getState());

Note: The exact state after start() depends on scheduling.

### Q14. What is sleep()?

sleep() pauses the currently executing thread for a specified duration.

Example:

    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }

Important:
- sleep() is a static method of Thread.
- It does not release intrinsic locks held by the thread.
- It can throw InterruptedException.

### Q15. What is yield()?

yield() is a static method that hints to the scheduler that the current thread is willing to let other threads execute.

Example:

    Thread.yield();

Important:
- It is only a scheduling hint.
- The scheduler may ignore it.
- It does not guarantee another thread will run.

### Q16. What is join()?

join() makes the current thread wait until another thread terminates.

Example:

    public class Main {
        public static void main(String[] args)
                throws InterruptedException {

            Thread t1 = new Thread(() ->
                System.out.println("Task completed")
            );

            t1.start();
            t1.join();

            System.out.println("Main completed");
        }
    }

Output:

    Task completed
    Main completed

### Q17. Difference between sleep() and wait().

| sleep() | wait() |
|---|---|
| Method of Thread. | Method of Object. |
| Pauses the current thread. | Causes the current thread to wait on an object's monitor. |
| Does not release intrinsic locks. | Releases the monitor lock of that object. |
| Can be called without synchronization. | Must own the object's monitor. |
| Wakes after timeout or interruption. | Wakes after notification, interruption, or timeout. |

### Q18. What is interrupt()?

interrupt() is used to request interruption of a thread.

It does not forcibly terminate the thread.

Example:

    public class Main {

        public static void main(String[] args)
                throws InterruptedException {

            Thread t1 = new Thread(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted");
                    Thread.currentThread().interrupt();
                }
            });

            t1.start();

            t1.interrupt();
        }
    }

Important:
- Blocking methods such as sleep(), wait(), and join() respond to interruption by throwing InterruptedException.
- For a thread not currently blocked, interrupt status is set.
- InterruptedException clears the interrupt status when thrown.

### Q19. What is the difference between isInterrupted() and interrupted()?

| isInterrupted() | interrupted() |
|---|---|
| Instance method. | Static method. |
| Checks a particular thread's interrupt status. | Checks current thread's interrupt status. |
| Does not clear the status. | Clears the status when it returns true. |

Example:

    Thread.currentThread().isInterrupted();

    Thread.interrupted();

### Q20. What is a Daemon Thread?

A daemon thread is a background thread that does not prevent the JVM from exiting when all non-daemon threads have terminated.

Example:

    Thread t1 = new Thread(() ->
        System.out.println("Background task")
    );

    t1.setDaemon(true);
    t1.start();

Examples:
- JVM garbage collector threads.
- Background service threads.

Important:
setDaemon() must be called before start().

---

## 4. Synchronization

### Q21. What is Synchronization in Java?

Synchronization is a mechanism used to control access to shared resources by multiple threads.

It prevents race conditions and ensures mutual exclusion for synchronized sections.

Example:

    class Counter {

        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

### Q22. What is a Race Condition?

A race condition occurs when multiple threads access and modify shared data concurrently, and the result depends on execution timing.

Example:

    class Counter {

        int count = 0;

        void increment() {
            count++;
        }
    }

count++ is not an atomic operation.

It involves:
1. Read current value.
2. Increment value.
3. Write updated value.

Two threads can interfere with each other.

Solution:
- synchronized.
- Lock API.
- AtomicInteger.
- Other appropriate concurrency controls.

### Q23. What is the synchronized keyword?

synchronized provides intrinsic locking and mutual exclusion.

It can be used with:
1. Instance methods.
2. Static methods.
3. Code blocks.

### Q24. Difference between synchronized method and synchronized block.

| Synchronized Method | Synchronized Block |
|---|---|
| Locks the entire method. | Locks only the specified block. |
| Less granular. | More granular. |
| Uses this for instance methods. | Can use a specified lock object. |
| Class monitor for static methods. | Can use a Class object as the lock. |

Example:

    class Counter {

        private int count = 0;
        private final Object lock = new Object();

        public void increment() {

            synchronized (lock) {
                count++;
            }
        }
    }

### Q25. What is an Object Lock?

Every Java object can act as an intrinsic monitor lock.

A thread must acquire the object's monitor before entering a synchronized block protected by that object.

Example:

    synchronized (this) {
        // Critical section
    }

### Q26. What is a Class-level Lock?

A class-level lock is the monitor associated with the Class object.

It is commonly used by static synchronized methods.

Example:

    class Counter {

        static synchronized void increment() {
            // Class-level lock
        }
    }

Equivalent concept:

    synchronized (Counter.class) {
        // Critical section
    }

### Q27. Can two threads execute two synchronized methods simultaneously?

It depends on the lock object.

For two synchronized instance methods on the same object:
- Only one thread can hold that object's monitor at a time.

For synchronized instance methods on different objects:
- They can execute simultaneously.

For two static synchronized methods of the same class:
- They use the same class monitor.

### Q28. What is Reentrant Synchronization?

Java intrinsic locks are reentrant.

A thread that already owns a monitor can acquire that same monitor again.

Example:

    class Test {

        synchronized void method1() {
            method2();
        }

        synchronized void method2() {
            System.out.println("Reentrant lock");
        }
    }

### Q29. What is a Volatile variable?

volatile ensures visibility of writes to a variable across threads and provides ordering guarantees.

Example:

    class SharedData {
        volatile boolean running = true;
    }

Important:
volatile does not make compound operations like count++ atomic.

### Q30. Difference between synchronized and volatile.

| synchronized | volatile |
|---|---|
| Provides mutual exclusion. | Does not provide mutual exclusion. |
| Provides visibility guarantees. | Provides visibility guarantees. |
| Supports atomic execution of protected critical sections. | Does not make compound operations atomic. |
| Uses intrinsic monitor locks. | Uses memory visibility and ordering guarantees. |
| Suitable for compound shared operations. | Suitable for simple state visibility. |

---

## 5. Inter-Thread Communication

### Q31. What is Inter-Thread Communication?

It is a mechanism through which threads coordinate their execution.

Java provides:
- wait()
- notify()
- notifyAll()

These methods belong to Object.

### Q32. What is wait()?

wait() causes the current thread to release the monitor of the object and wait until notification, interruption, or timeout.

It must be called while owning the object's monitor.

Example:

    synchronized (lock) {
        lock.wait();
    }

### Q33. What is notify()?

notify() wakes one thread waiting on the object's monitor.

The awakened thread cannot continue until it reacquires the monitor.

### Q34. What is notifyAll()?

notifyAll() wakes all threads waiting on the object's monitor.

They compete to reacquire the lock.

### Q35. Difference between notify() and notifyAll().

| notify() | notifyAll() |
|---|---|
| Wakes one waiting thread. | Wakes all waiting threads. |
| Which thread wakes is not guaranteed. | All waiters become eligible to compete for the lock. |
| May be appropriate when any waiter can proceed. | Safer when different waiters have different conditions. |

### Q36. Why should wait() be called inside a while loop instead of an if block?

Because of spurious wakeups and because the condition may no longer be true when a thread reacquires the lock.

Correct approach:

    synchronized (lock) {
        while (!condition) {
            lock.wait();
        }

        // Process when condition is true
    }

### Q37. What is the Producer-Consumer Problem?

It is a classic concurrency problem involving:
- Producer: Produces data.
- Consumer: Consumes data.
- Shared buffer: Stores data.

Java can solve this using:
- wait() and notifyAll().
- BlockingQueue.
- Lock and Condition.

Modern preferred approach:

    BlockingQueue<Integer> queue =
        new ArrayBlockingQueue<>(10);

Producer:

    queue.put(100);

Consumer:

    Integer value = queue.take();

BlockingQueue handles synchronization internally.

---

## 6. Deadlock and Other Concurrency Problems

### Q38. What is Deadlock?

Deadlock occurs when two or more threads wait indefinitely for locks held by one another.

Example:

    class Main {

        static final Object lock1 = new Object();
        static final Object lock2 = new Object();

        public static void main(String[] args) {

            Thread t1 = new Thread(() -> {
                synchronized (lock1) {
                    synchronized (lock2) {
                        System.out.println("Thread 1");
                    }
                }
            });

            Thread t2 = new Thread(() -> {
                synchronized (lock2) {
                    synchronized (lock1) {
                        System.out.println("Thread 2");
                    }
                }
            });

            t1.start();
            t2.start();
        }
    }

Both threads may wait indefinitely.

### Q39. How do you prevent Deadlock?

1. Acquire locks in a consistent order.
2. Avoid unnecessary nested locks.
3. Use tryLock() with timeouts.
4. Keep synchronized blocks small.
5. Avoid calling external methods while holding locks.

### Q40. What are the four necessary conditions for Deadlock?

1. Mutual Exclusion.
2. Hold and Wait.
3. No Preemption.
4. Circular Wait.

Breaking at least one of these conditions can prevent deadlock.

### Q41. What is Starvation?

Starvation occurs when a thread cannot obtain sufficient CPU time or required resources because other threads continually get preference.

Example:
A low-priority thread may rarely execute if high-priority threads continuously consume CPU time.

### Q42. What is Livelock?

Livelock occurs when threads remain active and continuously respond to each other's actions but make no meaningful progress.

Difference:
- Deadlock: Threads are blocked.
- Livelock: Threads are active but cannot progress.

### Q43. What is Thread Safety?

Thread safety means a component behaves correctly when accessed concurrently by multiple threads.

Ways to achieve thread safety:
- Synchronization.
- Immutable objects.
- Atomic variables.
- Concurrent collections.
- Thread confinement.

### Q44. What is an Atomic Operation?

An atomic operation is an operation that appears indivisible to other threads.

Example:

    AtomicInteger count = new AtomicInteger(0);

    count.incrementAndGet();

This avoids the race condition associated with an unsynchronized count++.

---

## 7. Java Concurrency Utilities

### Q45. What is ExecutorService?

ExecutorService is a framework for managing and executing asynchronous tasks using thread pools.

Advantages:
- Thread reuse.
- Better resource management.
- Task scheduling and execution.
- Graceful shutdown.
- Future-based result handling.

Example:

    ExecutorService executor =
        Executors.newFixedThreadPool(3);

    executor.submit(() ->
        System.out.println("Task executed")
    );

    executor.shutdown();

### Q46. What is a Thread Pool?

A thread pool is a collection of reusable worker threads that execute submitted tasks.

Advantages:
- Avoids creating a new thread for every task.
- Reduces thread creation overhead.
- Controls concurrency.
- Improves resource management.

### Q47. What are the different types of Thread Pools?

Common ExecutorService factory methods:

1. newFixedThreadPool()
2. newSingleThreadExecutor()
3. newCachedThreadPool()
4. newScheduledThreadPool()
5. newWorkStealingPool()

Example:

    ExecutorService executor =
        Executors.newFixedThreadPool(5);

Note:
For production systems, ThreadPoolExecutor with explicit configuration is often preferable to blindly using factory defaults.

### Q48. What is ThreadPoolExecutor?

ThreadPoolExecutor is a configurable implementation of ExecutorService.

Important parameters:

- corePoolSize
- maximumPoolSize
- keepAliveTime
- TimeUnit
- BlockingQueue
- ThreadFactory
- RejectedExecutionHandler

Example:

    ThreadPoolExecutor executor =
        new ThreadPoolExecutor(
            2,
            5,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100)
        );

### Q49. What happens when the Thread Pool is full?

For ThreadPoolExecutor:
1. New tasks are assigned to workers until corePoolSize is reached.
2. Additional tasks are queued.
3. If the queue is full, new workers are created up to maximumPoolSize.
4. If both maximum threads and queue capacity are exhausted, the rejection policy is invoked.

### Q50. What are RejectedExecutionHandler policies?

Java provides:

1. AbortPolicy: Throws RejectedExecutionException.
2. CallerRunsPolicy: Executes the task in the submitting thread.
3. DiscardPolicy: Silently discards the task.
4. DiscardOldestPolicy: Discards the oldest queued task and retries submission.

### Q51. Difference between execute() and submit().

| execute() | submit() |
|---|---|
| Defined in Executor. | Defined in ExecutorService. |
| Accepts Runnable. | Accepts Runnable or Callable. |
| Returns void. | Returns Future. |
| No Future for task result. | Supports result and exception retrieval through Future. |

### Q52. What is Future?

Future represents the result of an asynchronous computation.

Important methods:
- get()
- get(timeout, unit)
- isDone()
- isCancelled()
- cancel()

Example:

    Future<Integer> future =
        executor.submit(() -> 100);

    Integer result = future.get();

Note:
get() blocks until the result becomes available.

### Q53. What is CompletableFuture?

CompletableFuture is an advanced asynchronous programming API introduced in Java 8.

It supports:
- Asynchronous execution.
- Chaining operations.
- Combining multiple tasks.
- Exception handling.
- Non-blocking composition.

Example:

    CompletableFuture<Integer> future =
        CompletableFuture.supplyAsync(() -> 10)
            .thenApply(value -> value * 2)
            .thenApply(value -> value + 5);

    System.out.println(future.join());

Output:

    25

### Q54. Difference between Future and CompletableFuture.

| Future | CompletableFuture |
|---|---|
| Introduced in Java 5. | Introduced in Java 8. |
| Basic asynchronous result handling. | Advanced asynchronous composition. |
| get() commonly used for results. | Supports thenApply(), thenCompose(), etc. |
| Limited exception handling. | Supports exceptionally(), handle(), etc. |
| Limited composition. | Supports combining multiple tasks. |

### Q55. Difference between thenApply(), thenCompose(), and thenCombine().

thenApply():
Transforms the result of a completed stage.

    future.thenApply(value -> value * 2);

thenCompose():
Chains another asynchronous operation and flattens the resulting CompletableFuture.

    future.thenCompose(value ->
        fetchUserAsync(value)
    );

thenCombine():
Combines results from two independent CompletableFutures.

    future1.thenCombine(future2, (a, b) -> a + b);

### Q56. Difference between join() and get().

| join() | get() |
|---|---|
| Method of CompletableFuture. | Method of Future. |
| Throws unchecked CompletionException for exceptional completion. | Throws checked ExecutionException for exceptional completion. |
| Does not declare InterruptedException. | Can throw InterruptedException. |
| Commonly used for CompletableFuture. | Commonly used for Future. |

### Q57. What is ForkJoinPool?

ForkJoinPool is designed for parallel execution of tasks that can be split into smaller subtasks and combined.

It uses work-stealing.

Example:

    ForkJoinPool pool = new ForkJoinPool();

    int result = pool.invoke(
        new RecursiveTask<Integer>() {
            @Override
            protected Integer compute() {
                return 10 + 20;
            }
        }
    );

### Q58. What is Work Stealing?

Work stealing is a scheduling technique where an idle worker thread takes tasks from another busy worker's queue.

It improves utilization in parallel task processing.

### Q59. What is CountDownLatch?

CountDownLatch allows one or more threads to wait until a specified number of operations complete.

Example:

    CountDownLatch latch = new CountDownLatch(2);

    Runnable task = () -> {
        System.out.println("Task completed");
        latch.countDown();
    };

    new Thread(task).start();
    new Thread(task).start();

    latch.await();

    System.out.println("All tasks completed");

### Q60. What is CyclicBarrier?

CyclicBarrier allows a group of threads to wait until all participating threads reach a common barrier.

Unlike CountDownLatch, it can be reused after the barrier is released.

### Q61. Difference between CountDownLatch and CyclicBarrier.

| CountDownLatch | CyclicBarrier |
|---|---|
| Cannot be reset. | Can be reused. |
| Count decreases using countDown(). | Threads call await(). |
| One or more threads can wait for completion. | Participating threads wait for one another. |
| Useful for one-time coordination. | Useful for repeated synchronization phases. |

### Q62. What is Semaphore?

Semaphore controls access to a shared resource using permits.

Example:

    Semaphore semaphore = new Semaphore(3);

    semaphore.acquire();

    try {
        // Access limited resource
    } finally {
        semaphore.release();
    }

Use case:
Limit concurrent requests to an external service.

### Q63. What is ReadWriteLock?

ReadWriteLock allows:
- Multiple threads to read simultaneously.
- Only one thread to write at a time.
- No concurrent readers while a writer holds the write lock.

Example:

    ReadWriteLock lock = new ReentrantReadWriteLock();

    lock.readLock().lock();

    try {
        // Read data
    } finally {
        lock.readLock().unlock();
    }

### Q64. What is ReentrantLock?

ReentrantLock is an explicit locking mechanism from java.util.concurrent.locks.

Features:
- tryLock()
- lockInterruptibly()
- Timed lock acquisition.
- Fairness option.
- Multiple Condition objects.

Example:

    ReentrantLock lock = new ReentrantLock();

    lock.lock();

    try {
        System.out.println("Critical section");
    } finally {
        lock.unlock();
    }

### Q65. Difference between synchronized and ReentrantLock.

| synchronized | ReentrantLock |
|---|---|
| Built-in language feature. | Explicit Lock API. |
| Lock released automatically when monitor exits. | Must explicitly unlock. |
| No timed lock acquisition. | Supports tryLock with timeout. |
| No configurable fairness. | Supports optional fairness. |
| Uses wait/notify for monitor coordination. | Supports multiple Condition objects. |

---

## 8. Concurrent Collections

### Q66. What are Concurrent Collections?

Concurrent collections are thread-safe data structures designed for concurrent access.

Examples:

- ConcurrentHashMap
- CopyOnWriteArrayList
- BlockingQueue
- ConcurrentLinkedQueue
- ConcurrentSkipListMap

### Q67. Difference between HashMap and ConcurrentHashMap.

| HashMap | ConcurrentHashMap |
|---|---|
| Not thread-safe. | Supports concurrent access safely. |
| Allows one null key and multiple null values. | Does not allow null keys or values. |
| Requires external synchronization for shared concurrent modification. | Provides built-in concurrency controls. |
| Fail-fast iterators. | Weakly consistent iterators. |

### Q68. How does ConcurrentHashMap work?

ConcurrentHashMap supports concurrent reads and updates using internal synchronization and atomic operations.

Modern Java implementations use:
- CAS operations.
- Synchronization on individual bins when needed.
- Resizing mechanisms.

It does not lock the entire map for every operation.

### Q69. What is CopyOnWriteArrayList?

CopyOnWriteArrayList is a thread-safe list that creates a new copy of its underlying array for every mutation.

Advantages:
- Thread-safe iteration.
- Readers do not require explicit synchronization.

Disadvantages:
- Expensive write operations.
- More memory consumption.

Use case:
Read-heavy applications with infrequent updates.

### Q70. What is BlockingQueue?

BlockingQueue is a thread-safe queue that supports blocking operations.

Important methods:

- put()
- take()
- offer()
- poll()

Example:

    BlockingQueue<Integer> queue =
        new LinkedBlockingQueue<>(10);

    queue.put(100);

    Integer value = queue.take();

### Q71. Difference between ArrayList and CopyOnWriteArrayList.

| ArrayList | CopyOnWriteArrayList |
|---|---|
| Not thread-safe. | Thread-safe. |
| Concurrent modification can cause issues. | Iterators use snapshots. |
| Efficient for normal modifications. | Expensive modifications. |
| Suitable for single-threaded or externally synchronized access. | Suitable for read-heavy concurrent workloads. |

---

## 9. Java Memory Model and Advanced Concepts

### Q72. What is Java Memory Model (JMM)?

Java Memory Model defines how threads interact through memory and what visibility and ordering guarantees exist between threads.

It defines:
- Visibility.
- Atomicity.
- Ordering.
- Happens-before relationships.

### Q73. What is the happens-before relationship?

Happens-before defines an ordering guarantee between operations.

If action A happens-before action B, the effects of A are visible to B.

Examples:
- Unlocking a monitor happens-before a subsequent lock on that same monitor.
- A volatile write happens-before subsequent reads of that variable.
- Thread.start() happens-before actions in the started thread.
- Actions in a thread happen-before another thread successfully returns from join() on it.

### Q74. What is ThreadLocal?

ThreadLocal provides a separate variable instance for each thread.

Example:

    ThreadLocal<Integer> threadLocal =
        ThreadLocal.withInitial(() -> 0);

    threadLocal.set(100);

    System.out.println(threadLocal.get());

Use cases:
- Request context.
- Per-thread transaction context.
- User context.

Important:
In thread pools, call remove() when the value is no longer needed to avoid stale values being retained by reused worker threads.

### Q75. What is the difference between Stack and Heap memory in multithreading?

Stack:
- Each thread has its own JVM stack.
- Stores method frames and local variables.
- Local primitive variables are thread-confined.

Heap:
- Shared across threads.
- Stores objects and arrays.
- Shared mutable objects require appropriate concurrency controls.

### Q76. What is CAS (Compare-And-Swap)?

CAS is an atomic operation that compares a memory location with an expected value and updates it only if the current value matches.

Used in:
- AtomicInteger.
- AtomicLong.
- Concurrent collections.
- Lock-free algorithms.

Example:

    AtomicInteger count = new AtomicInteger(0);

    count.compareAndSet(0, 10);

### Q77. What is Lock-Free Programming?

Lock-free programming uses atomic operations to coordinate threads without traditional blocking locks.

Advantages:
- Avoids certain lock-related blocking problems.
- Can provide progress guarantees under contention.

Disadvantages:
- More complex implementation.
- Can suffer from retry overhead.
- Requires careful memory ordering.

### Q78. What is the volatile double-checked locking problem?

Incorrect double-checked locking can expose a partially constructed object due to instruction reordering and visibility problems.

Correct implementation:

    class Singleton {

        private static volatile Singleton instance;

        private Singleton() {}

        public static Singleton getInstance() {

            if (instance == null) {

                synchronized (Singleton.class) {

                    if (instance == null) {
                        instance = new Singleton();
                    }
                }
            }

            return instance;
        }
    }

volatile ensures the required visibility and ordering guarantees.

---

## 10. Virtual Threads – Java 21+

### Q79. What are Virtual Threads?

Virtual threads are lightweight threads introduced as a final feature in Java 21.

They are managed by the Java runtime rather than directly mapping every virtual thread to a dedicated operating-system thread.

Advantages:
- Supports high numbers of concurrent tasks.
- Particularly useful for blocking I/O workloads.
- Reduces the overhead of platform-thread-per-task designs.

Example:

    Thread.startVirtualThread(() -> {
        System.out.println("Virtual thread");
    });

### Q80. Difference between Platform Threads and Virtual Threads.

| Platform Threads | Virtual Threads |
|---|---|
| Typically mapped one-to-one to OS threads. | Managed by JVM and scheduled on carrier threads. |
| Higher resource cost per thread. | Much lighter per thread. |
| Suitable for CPU-bound and I/O-bound work. | Particularly suitable for I/O-bound workloads. |
| Limited by OS thread resources. | Supports very large numbers of concurrent threads. |

### Q81. How do you create Virtual Threads?

Using Thread:

    Thread.startVirtualThread(() -> {
        System.out.println("Task");
    });

Using ExecutorService:

    try (ExecutorService executor =
            Executors.newVirtualThreadPerTaskExecutor()) {

        executor.submit(() ->
            System.out.println("Virtual thread task")
        );
    }

### Q82. Are Virtual Threads faster than Platform Threads?

Not necessarily.

Virtual threads improve scalability for workloads involving many concurrent blocking operations.

They do not make CPU-bound calculations execute faster.

### Q83. Should we use Virtual Threads for CPU-intensive tasks?

No special performance advantage is expected.

CPU-intensive tasks should generally use a bounded number of platform threads aligned with available processor capacity.

### Q84. What is Thread Pinning?

Thread pinning occurs when a virtual thread cannot unmount from its carrier thread during certain operations.

In Java 21, blocking inside synchronized regions can pin virtual threads in relevant circumstances.

JDK 24 improved this behavior by removing most monitor-related pinning scenarios.

---

## 11. Real-Time Scenario-Based Questions

### Q85. How would you process 10,000 records concurrently?

Answer:

1. Divide records into manageable batches.
2. Use a bounded ExecutorService.
3. Configure an appropriate thread pool.
4. Submit tasks.
5. Handle failures individually.
6. Collect results using Future or CompletableFuture.
7. Apply backpressure.
8. Monitor execution time and resource usage.

Avoid creating 10,000 platform threads directly.

### Q86. How do you prevent multiple threads from updating the same database record simultaneously?

Possible solutions:

- Optimistic locking using @Version.
- Pessimistic locking.
- Database transactions.
- Atomic database updates.
- Unique constraints where applicable.

Example:

    @Entity
    public class Account {

        @Id
        private Long id;

        @Version
        private Long version;
    }

### Q87. How do you handle concurrent API requests in Spring Boot?

Answer:

- Spring MVC typically handles requests using a servlet thread pool.
- Keep singleton service beans stateless where possible.
- Avoid unsafe shared mutable instance variables.
- Use thread-safe collections where required.
- Use database transaction management.
- Configure connection pools and executor pools appropriately.

### Q88. How do you prevent duplicate processing of the same request?

Possible approaches:

- Idempotency keys.
- Database unique constraints.
- Atomic status transitions.
- Distributed locks when justified.
- Message deduplication.

### Q89. How do you improve thread pool performance?

1. Select an appropriate pool size.
2. Use bounded queues.
3. Avoid unnecessary synchronization.
4. Avoid blocking operations in CPU-heavy pools.
5. Monitor queue size and task execution time.
6. Configure rejection policies.
7. Avoid creating excessive threads.

### Q90. How do you debug a deadlock in production?

Answer:

1. Capture a thread dump.
2. Use jstack or JVM monitoring tools.
3. Identify BLOCKED threads.
4. Inspect lock ownership.
5. Identify circular lock dependencies.
6. Correct lock ordering or synchronization logic.
7. Monitor after deployment.

---

## 12. Output-Based Interview Questions

### Q91. What is the output?

    Thread t1 = new Thread(() ->
        System.out.println("A")
    );

    t1.run();
    System.out.println("B");

Answer:

    A
    B

Reason:
run() is called directly on the main thread.

### Q92. What happens?

    Thread t1 = new Thread(() ->
        System.out.println("A")
    );

    t1.start();
    t1.start();

Answer:

IllegalThreadStateException.

Reason:
A Thread instance cannot be started twice.

### Q93. What is the output?

    class Counter {
        int count = 0;

        void increment() {
            count++;
        }
    }

    Counter counter = new Counter();

    Thread t1 = new Thread(() -> counter.increment());
    Thread t2 = new Thread(() -> counter.increment());

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    System.out.println(counter.count);

Answer:

    2

Reason:
Both threads increment once, and the main thread waits for both to complete.

### Q94. What is the possible output?

    Thread t1 = new Thread(() ->
        System.out.println("Thread 1")
    );

    Thread t2 = new Thread(() ->
        System.out.println("Thread 2")
    );

    t1.start();
    t2.start();

Answer:

Either:

    Thread 1
    Thread 2

Or:

    Thread 2
    Thread 1

Reason:
Execution order is not guaranteed.

### Q95. What happens if we use sleep() inside synchronized?

The thread sleeps while retaining the intrinsic monitor lock.

Other threads requiring the same lock remain blocked until the sleeping thread exits the synchronized region.

### Q96. What happens if we call wait() without synchronization?

IllegalMonitorStateException is thrown.

Reason:
The current thread must own the object's monitor before calling wait().

### Q97. What happens if we call notify() without synchronization?

IllegalMonitorStateException is thrown.

Reason:
The thread must own the object's monitor before calling notify().

---

## 13. Important Coding Interview Questions

### Q98. Implement a thread-safe Counter.

    class Counter {

        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        public synchronized int getCount() {
            return count;
        }
    }

### Q99. Implement a Counter using AtomicInteger.

    class Counter {

        private final AtomicInteger count =
            new AtomicInteger(0);

        public void increment() {
            count.incrementAndGet();
        }

        public int getCount() {
            return count.get();
        }
    }

### Q100. Implement Producer-Consumer using BlockingQueue.

    import java.util.concurrent.*;

    public class Main {

        public static void main(String[] args) {

            BlockingQueue<Integer> queue =
                new ArrayBlockingQueue<>(5);

            Runnable producer = () -> {
                try {
                    for (int i = 1; i <= 5; i++) {
                        queue.put(i);
                        System.out.println("Produced: " + i);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            };

            Runnable consumer = () -> {
                try {
                    for (int i = 1; i <= 5; i++) {
                        int value = queue.take();
                        System.out.println("Consumed: " + value);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            };

            new Thread(producer).start();
            new Thread(consumer).start();
        }
    }

### Q101. Print Odd and Even Numbers using Two Threads.

    class OddEven {

        private int number = 1;
        private final int limit = 10;

        public synchronized void printOdd() {

            while (number <= limit) {

                while (number % 2 == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                if (number <= limit) {
                    System.out.println("Odd: " + number++);
                    notifyAll();
                }
            }
        }

        public synchronized void printEven() {

            while (number <= limit) {

                while (number % 2 != 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                if (number <= limit) {
                    System.out.println("Even: " + number++);
                    notifyAll();
                }
            }
        }
    }

### Q102. Implement a Singleton using Bill Pugh approach.

    class Singleton {

        private Singleton() {}

        private static class Holder {
            private static final Singleton INSTANCE =
                new Singleton();
        }

        public static Singleton getInstance() {
            return Holder.INSTANCE;
        }
    }

Advantages:
- Lazy initialization.
- Thread-safe initialization.
- No explicit synchronization required.

### Q103. Execute Multiple Tasks using CompletableFuture.

    CompletableFuture<Integer> task1 =
        CompletableFuture.supplyAsync(() -> 10);

    CompletableFuture<Integer> task2 =
        CompletableFuture.supplyAsync(() -> 20);

    CompletableFuture<Integer> result =
        task1.thenCombine(task2, Integer::sum);

    System.out.println(result.join());

Output:

    30

---

## 14. Important Questions for 5+ Years Experience

Prioritize these questions:

1. Process vs Thread.
2. Concurrency vs Parallelism.
3. Thread vs Runnable.
4. Runnable vs Callable.
5. start() vs run().
6. Thread lifecycle.
7. sleep() vs wait().
8. wait() vs notify() vs notifyAll().
9. interrupt() and interrupt status.
10. synchronized method vs synchronized block.
11. Object lock vs Class lock.
12. volatile vs synchronized.
13. Race condition.
14. Deadlock and prevention.
15. Starvation vs Livelock.
16. Thread safety.
17. ExecutorService.
18. ThreadPoolExecutor.
19. Thread pool sizing.
20. execute() vs submit().
21. Future vs CompletableFuture.
22. thenApply() vs thenCompose().
23. CountDownLatch vs CyclicBarrier.
24. Semaphore.
25. ReentrantLock.
26. ConcurrentHashMap internals.
27. BlockingQueue.
28. ThreadLocal.
29. Java Memory Model.
30. Happens-before relationship.
31. CAS.
32. Virtual Threads.
33. Producer-Consumer implementation.
34. Odd-Even printing using two threads.
35. Handling concurrent database updates.
36. Handling concurrent REST API requests.
37. Processing thousands of records efficiently.
38. Debugging production deadlocks.

---

## 15. Multithreading Interview Preparation Checklist

### Fundamentals
- [ ] Process vs Thread
- [ ] Concurrency vs Parallelism
- [ ] Thread lifecycle
- [ ] Thread creation
- [ ] Thread vs Runnable
- [ ] Runnable vs Callable
- [ ] start() vs run()

### Thread Methods
- [ ] sleep()
- [ ] wait()
- [ ] notify()
- [ ] notifyAll()
- [ ] join()
- [ ] yield()
- [ ] interrupt()
- [ ] Daemon threads

### Synchronization
- [ ] synchronized
- [ ] Object lock
- [ ] Class lock
- [ ] Reentrant synchronization
- [ ] volatile
- [ ] AtomicInteger
- [ ] Race condition
- [ ] Thread safety

### Concurrency Problems
- [ ] Deadlock
- [ ] Starvation
- [ ] Livelock
- [ ] Producer-Consumer
- [ ] Thread coordination

### Executor Framework
- [ ] Executor
- [ ] ExecutorService
- [ ] ThreadPoolExecutor
- [ ] Thread pool
- [ ] Future
- [ ] Callable
- [ ] CompletableFuture
- [ ] ForkJoinPool

### Advanced Concurrency
- [ ] CountDownLatch
- [ ] CyclicBarrier
- [ ] Semaphore
- [ ] ReentrantLock
- [ ] ReadWriteLock
- [ ] ConcurrentHashMap
- [ ] BlockingQueue
- [ ] ThreadLocal
- [ ] CAS
- [ ] Java Memory Model
- [ ] Happens-before

### Modern Java
- [ ] Virtual Threads
- [ ] Platform vs Virtual Threads
- [ ] Virtual Thread use cases
- [ ] Thread pinning

### Coding Practice
- [ ] Thread-safe Counter
- [ ] Producer-Consumer
- [ ] Odd-Even printing
- [ ] Print numbers using multiple threads
- [ ] Singleton implementation
- [ ] CompletableFuture chaining
- [ ] Deadlock demonstration and prevention
- [ ] ExecutorService task execution
