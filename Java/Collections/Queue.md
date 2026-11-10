<a id="top"></a>

# 📚 Java Collections Framework — Queue, Deque & PriorityQueue

This guide follows the same interview-preparation format as the List guide: **concepts → internal implementation → time complexity → comparisons → methods → examples → interview questions**.

The most important topics are `Queue`, `Deque`, `ArrayDeque`, `PriorityQueue`, `Comparable`, `Comparator`, and `BlockingQueue`.

---

## 🧭 Table of Contents

*Click any item to jump to that section. Every section ends with a "Back to top" link.*

| # | Section | Type |
|---|---|---|
| 1 | [Queue interface — overview](#s1) | 🔵 Concept |
| 2 | [Queue methods — must know](#s2) | ⭐ Core |
| 3 | [ArrayDeque — internal implementation, usage and complexity](#s3) | ⭐ Core |
| 4 | [LinkedList as a Queue and Deque](#s4) | 🔵 Concept |
| 5 | [ArrayDeque vs LinkedList](#s5) | 🟣 Interview |
| 6 | [Deque — Double-Ended Queue](#s6) | ⭐ Core |
| 7 | [ArrayDeque as a Stack](#s7) | 🟢 Practical |
| 8 | [PriorityQueue — internal implementation and usage](#s8) | ⭐ Core |
| 9 | [Comparable vs Comparator with PriorityQueue](#s9) | ⭐ Core |
| 10 | [ArrayBlockingQueue and LinkedBlockingQueue](#s10) | 🔵 Concept |
| 11 | [BlockingQueue methods — blocking vs non-blocking](#s11) | ⭐ Core |
| 12 | [BlockingDeque — blocking double-ended queue](#s12) | 🔵 Concept |
| 13 | [Queue vs Deque vs BlockingQueue vs BlockingDeque](#s13) | 🟣 Interview |
| 14 | [Important Queue interview questions](#s14) | 🟣 Interview |
| 15 | [Queue interview revision cheat sheet](#s15) | 📝 Revision |
| 16 | [Recommended learning and practice order](#s16) | 📝 Revision |

### 🔖 Quick jumps

[⚡ Cheat sheet](#s15) · [❓ Interview questions](#s14) · [⚖️ ArrayDeque vs LinkedList](#s5) · [⚖️ All four queue types](#s13) · [🧮 Comparable vs Comparator](#s9) · [🔄 Producer-consumer](#producer-consumer)

---

<a id="s1"></a>

## 1. Queue interface — overview

A `Queue` is a collection designed to hold elements **before they are processed**. Many queues follow **FIFO** (First In, First Out): the first element inserted is the first removed.

For example, a line of people waiting at a ticket counter.

### Java Queue hierarchy

```text
Iterable
   |
Collection
   |
  Queue (interface)
   |
   +-------------------+------------------+
   |                   |                  |
ArrayDeque         LinkedList         PriorityQueue
   |                   |
   +--- implements Deque
              |
            Queue
```

The related subinterfaces are:

```text
Queue
  |
  +-- Deque
  |     +-- ArrayDeque
  |     +-- LinkedList
  |
  +-- BlockingQueue
  |     +-- ArrayBlockingQueue
  |     +-- LinkedBlockingQueue
  |
  +-- BlockingDeque
        +-- LinkedBlockingDeque
```

- `LinkedList` implements both `List` and `Deque`.
- `ArrayDeque` implements `Deque`.
- `PriorityQueue` implements `Queue` but **not** `Deque`.

> [!IMPORTANT]
> **🟣 Interview point:** `BlockingDeque` extends `BlockingQueue`, which extends `Queue`. It is not a separate branch unrelated to `BlockingQueue`.

[⬆️ Back to top](#top)

---

<a id="s2"></a>

## 2. Queue methods — must know

The `Queue` interface provides methods for inserting, inspecting, and removing elements.

| Operation | 🔴 Throws exception | 🟢 Returns special value |
|---|---|---|
| **Insert** | `add(e)` | `offer(e)` |
| **Remove head** | `remove()` | `poll()` |
| **Inspect head** | `element()` | `peek()` |

### What is the difference?

- `add(e)`: inserts an element; may throw an exception if insertion fails.
- `offer(e)`: attempts insertion; returns `false` if it cannot insert.
- `remove()`: removes and returns the head; **throws** if empty.
- `poll()`: removes and returns the head; returns **`null`** if empty.
- `element()`: reads the head without removing it; **throws** if empty.
- `peek()`: reads the head without removing it; returns **`null`** if empty.

> These differences are especially important for **bounded queues**, where insertion can fail because the queue is full.

### Example

```java
import java.util.*;

public class QueueDemo {
    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(10);
        queue.offer(20);
        queue.offer(30);

        System.out.println(queue);       // [10, 20, 30]
        System.out.println(queue.peek()); // 10

        System.out.println(queue.poll()); // 10
        System.out.println(queue.poll()); // 20

        System.out.println(queue); // [30]
    }
}
```

> [!NOTE]
> **Remember:** A normal FIFO queue processes elements in insertion order. A `PriorityQueue` is different because it orders elements according to **priority**.

[⬆️ Back to top](#top)

---

<a id="s3"></a>

## 3. ArrayDeque — internal implementation, usage and complexity

`ArrayDeque` is a **resizable-array** implementation of the `Deque` interface. It supports insertion and removal at **both ends**.

It can be used as:

- A normal FIFO queue.
- A LIFO stack.
- A double-ended queue.

### How does it work internally?

Unlike `LinkedList`, `ArrayDeque` uses an array-based structure managed as a **circular array**. The front and rear positions can move around the array, allowing elements to be inserted or removed at either end without shifting every element on each operation.

Conceptually:

```text
        Front                     Rear
          |                         |
          v                         v
      [ 10 ][ 20 ][ 30 ][    ][    ]
```

As elements are removed from the front, the implementation can advance its front position rather than shifting all the remaining elements.

### ⏱️ Time complexity

| Operation | Typical complexity |
|---|---|
| `addFirst(e)` / `addLast(e)` | **Amortized O(1)** |
| `removeFirst()` / `removeLast()` | **O(1)** |
| `peekFirst()` / `peekLast()` | **O(1)** |
| Search using `contains()` | **O(n)** |
| Resize when capacity is exhausted | **O(n)** |

### Important characteristics

- ❌ Does **not** allow `null` elements.
- ❌ Is **not** thread-safe by default.
- ✅ Is generally efficient for stack and queue operations.
- ✅ Usually uses less memory per element than a linked list.
- ✅ Grows automatically when more capacity is needed.

### Example

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeDemo {
    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();

        deque.addFirst(20);
        deque.addFirst(10);
        deque.addLast(30);

        System.out.println(deque);
        // [10, 20, 30]

        System.out.println(deque.removeFirst()); // 10
        System.out.println(deque.removeLast());  // 30
        System.out.println(deque.peekFirst());   // 20
    }
}
```

> [!IMPORTANT]
> **🟣 Interview answer:** `ArrayDeque` is often the preferred general-purpose implementation for stack and queue operations because it supports both ends efficiently and avoids the per-node overhead of `LinkedList`.

[⬆️ Back to top](#top)

---

<a id="s4"></a>

## 4. LinkedList as a Queue and Deque

`LinkedList` implements both `List` and `Deque`, so it can be used as a queue, stack, or double-ended queue.

Internally, it uses a **doubly linked list**. Each node stores the element and references to its previous and next nodes.

### Example as a normal Queue

```java
import java.util.LinkedList;
import java.util.Queue;

public class LinkedListQueueDemo {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(10);
        queue.offer(20);
        queue.offer(30);

        System.out.println(queue);       // [10, 20, 30]
        System.out.println(queue.poll()); // 10
        System.out.println(queue.peek()); // 20
    }
}
```

### Example as a Deque

```java
import java.util.Deque;
import java.util.LinkedList;

public class LinkedListDequeDemo {
    public static void main(String[] args) {
        Deque<Integer> deque = new LinkedList<>();

        deque.offerFirst(20);
        deque.offerFirst(10);
        deque.offerLast(30);

        System.out.println(deque);
        // [10, 20, 30]

        deque.pollFirst();
        deque.pollLast();

        System.out.println(deque);
        // [20]
    }
}
```

### ✅ When would you use `LinkedList`?

- When you need a linked-list implementation for both list and deque operations.
- When you need efficient insertion or removal at the ends.
- When accepting `null` elements is a requirement and the chosen API permits them.

> [!TIP]
> However, don't choose `LinkedList` just because it has O(1) insertion and removal at the ends. For most queue and deque workloads, **`ArrayDeque` is usually the better starting point**.

[⬆️ Back to top](#top)

---

<a id="s5"></a>

## 5. ArrayDeque vs LinkedList

This is a common Java interview question.

| Feature | `ArrayDeque` | `LinkedList` |
|---|---|---|
| **Internal structure** | Resizable circular array | Doubly linked nodes |
| **Implements** | `Deque` | `List`, `Deque` |
| **Null elements** | ❌ Not allowed | ✅ Allowed |
| **Add/remove at ends** | Efficient | Efficient |
| **Random access by index** | Not supported through `Deque` API | Supported through `List` API, but O(n) |
| **Memory overhead** | Usually lower | Higher due to node references |
| **Cache locality** | Generally better | Generally worse |
| **Thread-safe by default** | No | No |
| **Best general use** | Stack, queue, deque | Linked list or specialized deque/list use cases |

### ❓ Interview question: Why is `ArrayDeque` usually faster?

`ArrayDeque` stores elements in an array-backed structure. This reduces per-element object overhead and usually improves cache locality.

`LinkedList` stores each element in a separate node with references to neighboring nodes. Traversing those nodes often involves less cache-friendly memory access.

> [!WARNING]
> **Important:** This is a general performance expectation, not an absolute guarantee for every workload.

### ❓ Interview question: Why does `LinkedList` support `null` but `ArrayDeque` does not?

`LinkedList` can store `null` as an ordinary element. `ArrayDeque` rejects `null` because its implementation uses `null` to represent unused array positions, and its queue operations use `null` to signal an empty result.

This also explains why `poll()` and `peek()` returning `null` are unambiguous for `ArrayDeque`: `null` cannot be an actual stored element.

[⬆️ Back to top](#top)

---

<a id="s6"></a>

## 6. Deque — Double-Ended Queue

`Deque` is pronounced **"deck"**. It stands for **D**ouble-**E**nded **Que**ue.

A deque allows insertion and removal from **both the front and the rear**.

```text
             Front                 Rear
               |                     |
               v                     v
           +------+------+------+
           |  10  |  20  |  30  |
           +------+------+------+
               <---- both ends ---->
```

Unlike a normal FIFO queue, a deque can also behave like a **stack**.

### Important Deque methods

| Front operation | Rear operation | Purpose |
|---|---|---|
| `addFirst(e)` | `addLast(e)` | Insert; **throws** if insertion fails |
| `offerFirst(e)` | `offerLast(e)` | Insert; **returns** success status |
| `removeFirst()` | `removeLast()` | Remove and return; **throws** if empty |
| `pollFirst()` | `pollLast()` | Remove and return; returns **`null`** if empty |
| `getFirst()` | `getLast()` | Read; **throws** if empty |
| `peekFirst()` | `peekLast()` | Read; returns **`null`** if empty |

Other useful methods include `push(e)`, `pop()`, `peek()`, `contains(o)`, `size()`, and `isEmpty()`.

### Queue methods vs Deque methods

For a deque, the ordinary `Queue` operations use the **front** as the head:

| Queue method | Corresponding deque operation |
|---|---|
| `offer(e)` | `offerLast(e)` |
| `poll()` | `pollFirst()` |
| `peek()` | `peekFirst()` |

### Example

```java
Deque<String> deque = new ArrayDeque<>();

deque.offerFirst("B");
deque.offerFirst("A");
deque.offerLast("C");

System.out.println(deque); // [A, B, C]

System.out.println(deque.pollFirst()); // A
System.out.println(deque.pollLast());  // C
System.out.println(deque);             // [B]
```

### ✅ When should you use `Deque`?

- Implementing a stack.
- Implementing a FIFO queue.
- Processing elements at both ends.
- Sliding-window algorithms and other DSA problems that require adding and removing elements from both ends.

[⬆️ Back to top](#top)

---

<a id="s7"></a>

## 7. ArrayDeque as a Stack

A stack follows **LIFO** (Last In, First Out).

The last element pushed is the first element popped.

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class StackUsingDeque {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println(stack);      // [30, 20, 10]
        System.out.println(stack.peek()); // 30

        System.out.println(stack.pop()); // 30
        System.out.println(stack.pop()); // 20
        System.out.println(stack.pop()); // 10

        System.out.println(stack.isEmpty()); // true
    }
}
```

### How do the methods map to the deque?

| Stack operation | Deque method |
|---|---|
| `push(e)` | `addFirst(e)` |
| `pop()` | `removeFirst()` |
| `peek()` | `getFirst()` / `peekFirst()` |

The `Deque` interface already defines `push()`, `pop()`, and `peek()` with stack semantics, so you can use those directly.

### ❓ Why use `Deque` instead of `Stack`?

The legacy `Stack` class extends `Vector`, inheriting its synchronization behavior and its older class design. `ArrayDeque` is generally more efficient for single-threaded stack operations and has a cleaner API for this purpose.

> [!WARNING]
> **🟣 Interview point:** `ArrayDeque` is **not synchronized**. If multiple threads need to share a stack, select a suitable concurrency mechanism or collection instead of assuming `ArrayDeque` is thread-safe.

[⬆️ Back to top](#top)

---

<a id="s8"></a>

## 8. PriorityQueue — internal implementation and usage

A `PriorityQueue` processes elements according to their **priority** rather than their insertion order.

By default, Java's `PriorityQueue` places the **smallest** element at the head when elements use natural ordering.

### Internal implementation

`PriorityQueue` uses a **binary heap**, typically represented using an array.

For a **min-heap**:

- The parent is less than or equal to its children.
- The root contains the smallest element.
- The entire array is **not necessarily sorted**.

Conceptual example:

```text
             10
            /  \
           20   30
          / \
         40  50
```

The root is the minimum. The remaining elements satisfy the heap property, but they are not in globally sorted order.

### ⏱️ Time complexity

| Operation | Complexity |
|---|---|
| `peek()` | **O(1)** |
| `offer(e)` / `add(e)` | **O(log n)** |
| `poll()` / `remove()` head | **O(log n)** |
| `contains(e)` | **O(n)** |
| `remove(object)` | **O(n)** |

### Example: Min-priority queue

```java
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueDemo {
    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue<>();

        queue.offer(40);
        queue.offer(10);
        queue.offer(30);
        queue.offer(20);

        System.out.println(queue.peek()); // 10

        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + " ");
        }
        // 10 20 30 40
    }
}
```

> [!CAUTION]
> **🪤 Important interview trap:** Printing a `PriorityQueue` does **not** guarantee that all elements appear in sorted order. Repeatedly removing the head retrieves them in priority order.

### ✅ When should you use `PriorityQueue`?

- Finding the smallest or largest element repeatedly.
- Implementing Dijkstra's shortest-path algorithm.
- Finding the K largest or K smallest elements.
- Scheduling tasks by priority.
- Merging sorted collections efficiently.

[⬆️ Back to top](#top)

---

<a id="s9"></a>

## 9. Comparable vs Comparator with PriorityQueue

This is one of the **most important** interview topics for `PriorityQueue`.

Both `Comparable` and `Comparator` define ordering, but they differ in **where** that ordering is specified.

| Feature | `Comparable` | `Comparator` |
|---|---|---|
| **Package** | `java.lang` | `java.util` |
| **Main method** | `compareTo()` | `compare()` |
| **Defines** | Natural ordering | External/custom ordering |
| **Implemented by** | The class being ordered | A separate comparator or lambda |
| **Number of orderings** | Typically one natural ordering | Multiple custom orderings |
| **PriorityQueue usage** | `new PriorityQueue<>()` | `new PriorityQueue<>(comparator)` |

### 🅰️ Comparable — natural ordering

Suppose you have an `Employee` class and want to order employees by salary.

```java
class Employee implements Comparable<Employee> {
    String name;
    int salary;

    Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.salary, other.salary);
    }

    @Override
    public String toString() {
        return name + ":" + salary;
    }
}
```

Use it in a `PriorityQueue`:

```java
PriorityQueue<Employee> queue = new PriorityQueue<>();

queue.offer(new Employee("Ravi", 50000));
queue.offer(new Employee("Anu", 30000));
queue.offer(new Employee("Kiran", 40000));

while (!queue.isEmpty()) {
    System.out.println(queue.poll());
}
```

**Output:**

```text
Anu:30000
Kiran:40000
Ravi:50000
```

The `compareTo()` implementation establishes ascending salary order.

### 🅱️ Comparator — custom ordering

Suppose you want to order the same employees by salary in **descending** order, without changing the `Employee` class.

```java
PriorityQueue<Employee> queue =
        new PriorityQueue<>(
            (e1, e2) -> Integer.compare(e2.salary, e1.salary)
        );

queue.offer(new Employee("Ravi", 50000));
queue.offer(new Employee("Anu", 30000));
queue.offer(new Employee("Kiran", 40000));

while (!queue.isEmpty()) {
    System.out.println(queue.poll());
}
```

**Output:**

```text
Ravi:50000
Kiran:40000
Anu:30000
```

The comparator reverses the salary ordering.

### 🅲 Using built-in comparators

For integers:

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

PriorityQueue<Integer> maxHeap =
        new PriorityQueue<>(Comparator.reverseOrder());
```

For employees:

```java
PriorityQueue<Employee> bySalary =
        new PriorityQueue<>(
            Comparator.comparingInt(e -> e.salary)
        );
```

Descending salary:

```java
PriorityQueue<Employee> bySalaryDescending =
        new PriorityQueue<>(
            Comparator.comparingInt(
                (Employee e) -> e.salary
            ).reversed()
        );
```

### 🅳 How does `compare()` work?

For a comparator:

```java
(e1, e2) -> Integer.compare(e1.salary, e2.salary)
```

| Result | Meaning |
|---|---|
| **Negative** | `e1` comes **before** `e2` |
| **Zero** | They compare as equal in ordering |
| **Positive** | `e1` comes **after** `e2` |

> [!WARNING]
> Avoid implementing comparisons with subtraction, such as `e1.salary - e2.salary`, because **integer overflow** can produce incorrect ordering. Prefer `Integer.compare()` or the built-in comparator methods.

> [!IMPORTANT]
> **🟣 Interview answer:** `Comparable` defines a class's natural ordering; `Comparator` allows ordering to be specified externally. A `PriorityQueue` uses natural ordering by default or the `Comparator` supplied at construction.

[⬆️ Back to top](#top)

---

<a id="s10"></a>

## 10. ArrayBlockingQueue and LinkedBlockingQueue

`BlockingQueue` is a subinterface of `Queue` designed for **concurrent producer-consumer workflows**.

A blocking queue can **wait** when an operation cannot proceed immediately:

- A **producer** may wait until space becomes available.
- A **consumer** may wait until an element becomes available.

Two common implementations are:

- `ArrayBlockingQueue`
- `LinkedBlockingQueue`

### Comparison

| Feature | `ArrayBlockingQueue` | `LinkedBlockingQueue` |
|---|---|---|
| **Internal structure** | Array-backed | Linked nodes |
| **Capacity** | Fixed and bounded | Optionally bounded; otherwise limited by `Integer.MAX_VALUE` |
| **Thread-safe** | Yes | Yes |
| **`put()` on full queue** | Waits | Waits |
| **`take()` on empty queue** | Waits | Waits |
| **Memory usage** | Predictable backing array | Per-node allocation overhead |
| **Typical use** | Fixed-size work buffer | Producer-consumer workloads with configurable capacity |

### 🅰️ ArrayBlockingQueue

```java
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue =
                new ArrayBlockingQueue<>(3);

        queue.put(10);
        queue.put(20);
        queue.put(30);

        System.out.println(queue.take()); // 10
        System.out.println(queue.take()); // 20
    }
}
```

The queue can hold at most three elements. If it is full, a further `put()` waits until space becomes available.

### 🅱️ LinkedBlockingQueue

```java
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue =
                new LinkedBlockingQueue<>(2);

        queue.put("Task 1");
        queue.put("Task 2");

        System.out.println(queue.take()); // Task 1
        System.out.println(queue.take()); // Task 2
    }
}
```

> [!TIP]
> Although `LinkedBlockingQueue` can be created without an explicit capacity, setting a suitable bound is often safer for production systems because it prevents an unbounded backlog from consuming excessive memory.

[⬆️ Back to top](#top)

---

<a id="s11"></a>

## 11. BlockingQueue methods — blocking vs non-blocking

This is a key interview distinction.

| Operation | 🔴 Throws on failure | 🟢 Returns special value | 🟡 Waits |
|---|---|---|---|
| **Insert** | `add(e)` | `offer(e)` | `put(e)` |
| **Remove** | `remove()` | `poll()` | `take()` |
| **Timed insert** | — | `offer(e, time, unit)` | Waits up to the timeout |
| **Timed remove** | — | `poll(time, unit)` | Waits up to the timeout |

### What do `put()` and `take()` do?

**`put(e)`**

Inserts an element. If the bounded queue is full, the calling thread **waits** until space becomes available, unless interrupted.

**`take()`**

Removes and returns the head element. If the queue is empty, the calling thread **waits** until an element becomes available, unless interrupted.

Both methods declare `InterruptedException`, so callers must handle or propagate it.

<a id="producer-consumer"></a>

### Example: Producer-consumer coordination

```java
import java.util.concurrent.*;

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue =
                new ArrayBlockingQueue<>(2);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    queue.put(i);
                    System.out.println("Produced: " + i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    int value = queue.take();
                    System.out.println("Consumed: " + value);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}
```

The producer inserts five elements. The consumer removes five elements. The bounded queue automatically coordinates their progress when it becomes full or empty.

> [!NOTE]
> The exact interleaving of the output can vary because the threads run concurrently.

### ❓ Why is `BlockingQueue` thread-safe?

Its implementations provide internal synchronization and coordination so multiple threads can safely interact with the queue.

This avoids manually implementing the usual combination of:

1. Shared collection protection.
2. Checking whether the queue is empty or full.
3. Waiting for another thread to change the state.
4. Notifying waiting threads when that state changes.

> [!IMPORTANT]
> **🟣 Interview answer:** `BlockingQueue` is a thread-safe producer-consumer abstraction. `put()` waits for space and `take()` waits for data. It coordinates access without requiring application code to manually synchronize every queue operation.

[⬆️ Back to top](#top)

---

<a id="s12"></a>

## 12. BlockingDeque — blocking double-ended queue

`BlockingDeque` extends `BlockingQueue` and `Deque` through its interface hierarchy. It supports **blocking** insertion and removal at **both ends**.

The main standard implementation is `LinkedBlockingDeque`.

### Important methods

| Method | Behavior |
|---|---|
| `putFirst(e)` | Waits for space, then inserts at the front |
| `putLast(e)` | Waits for space, then inserts at the rear |
| `takeFirst()` | Waits for an element, then removes the front |
| `takeLast()` | Waits for an element, then removes the rear |
| `offerFirst(e)` | Attempts insertion at the front without waiting |
| `offerLast(e)` | Attempts insertion at the rear without waiting |
| `pollFirst()` | Removes the front or returns `null` |
| `pollLast()` | Removes the rear or returns `null` |

### Example

```java
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class BlockingDequeDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingDeque<Integer> deque =
                new LinkedBlockingDeque<>(3);

        deque.putFirst(20);
        deque.putFirst(10);
        deque.putLast(30);

        System.out.println(deque); // [10, 20, 30]

        System.out.println(deque.takeFirst()); // 10
        System.out.println(deque.takeLast());  // 30
    }
}
```

### ✅ When should you use `BlockingDeque`?

Use it when multiple threads need a thread-safe double-ended queue with blocking behavior, such as task processing where tasks may be added or consumed from either end.

[⬆️ Back to top](#top)

---

<a id="s13"></a>

## 13. Queue vs Deque vs BlockingQueue vs BlockingDeque

| Feature | `Queue` | `Deque` | `BlockingQueue` | `BlockingDeque` |
|---|---|---|---|---|
| **Basic purpose** | Queue processing | Operations at both ends | Thread-safe producer-consumer queue | Thread-safe blocking operations at both ends |
| **FIFO behavior** | Commonly | Supported | Commonly | Supported |
| **Stack operations** | Not directly | ✅ Yes | Not required by interface | ✅ Yes |
| **Blocking operations** | ❌ No | ❌ No | ✅ Yes | ✅ Yes |
| **Common implementation** | `PriorityQueue` | `ArrayDeque` | `ArrayBlockingQueue` | `LinkedBlockingDeque` |

> [!WARNING]
> Remember that `Queue` is an interface, **not a guarantee of FIFO ordering**. `PriorityQueue`, for example, orders elements by priority.

[⬆️ Back to top](#top)

---

<a id="s14"></a>

## 14. Important Queue interview questions

### Q1. What is the difference between `poll()` and `remove()`?

Both remove the queue head. `poll()` returns `null` when the queue is empty, whereas `remove()` throws `NoSuchElementException`.

### Q2. What is the difference between `peek()` and `element()`?

Both inspect the head without removing it. `peek()` returns `null` if empty, while `element()` throws `NoSuchElementException`.

### Q3. Is `PriorityQueue` sorted internally?

No. It maintains a **heap property**, not a fully sorted sequence. Repeatedly calling `poll()` retrieves elements in priority order.

### Q4. Does `PriorityQueue` allow `null`?

No. Inserting `null` throws `NullPointerException`.

### Q5. Does `PriorityQueue` allow duplicate elements?

Yes. Duplicate elements are allowed, provided they can be ordered by the configured ordering.

### Q6. Can we use a custom class in `PriorityQueue`?

Yes. The class must implement `Comparable`, or you must supply a suitable `Comparator` when creating the queue. Otherwise, inserting multiple non-comparable elements can cause `ClassCastException`.

### Q7. Why is `ArrayDeque` preferred over `Stack`?

`ArrayDeque` provides efficient stack operations without inheriting the legacy `Vector` synchronization design. It is generally preferable for single-threaded stack usage.

### Q8. Is `ArrayDeque` thread-safe?

No. It is not synchronized by default. Use an appropriate concurrent collection or synchronization strategy for shared multi-threaded access.

### Q9. What happens when `put()` is called on a full `BlockingQueue`?

The calling thread waits until space becomes available, unless interrupted. A timed `offer()` is useful when you want to limit how long the thread waits.

### Q10. What happens when `take()` is called on an empty `BlockingQueue`?

The calling thread waits until an element becomes available, unless interrupted.

### Q11. Does `BlockingQueue` accept `null`?

No. Its implementations prohibit `null` elements. This allows `null` to represent the absence of a result in operations such as `poll()`.

### Q12. What is the difference between `Comparable` and `Comparator`?

`Comparable` defines natural ordering through `compareTo()`. `Comparator` defines an external ordering through `compare()`, allowing multiple ordering strategies without changing the class.

[⬆️ Back to top](#top)

---

<a id="s15"></a>

## 15. Queue interview revision cheat sheet

| Topic | Remember this |
|---|---|
| **Queue** | Head-based insertion and removal; often FIFO |
| **`offer()`** | Insert without throwing merely because a bounded queue is full |
| **`poll()`** | Remove head or return `null` |
| **`peek()`** | Inspect head or return `null` |
| **`ArrayDeque`** | Resizable array-backed deque |
| **`LinkedList`** | Doubly linked list implementing `List` and `Deque` |
| **`PriorityQueue`** | Binary heap; minimum at the head by default |
| **`Comparable`** | Natural ordering via `compareTo()` |
| **`Comparator`** | Custom ordering via `compare()` |
| **`Deque`** | Double-ended queue; supports stack and queue behavior |
| **`ArrayDeque` vs `LinkedList`** | `ArrayDeque` is generally more efficient for deque operations |
| **`BlockingQueue`** | Thread-safe queue supporting blocking operations |
| **`put()`** | Wait for space |
| **`take()`** | Wait for an element |
| **`ArrayBlockingQueue`** | Bounded array-backed blocking queue |
| **`LinkedBlockingQueue`** | Linked-node blocking queue, optionally bounded |
| **`BlockingDeque`** | Blocking operations at both ends |
| **Stack recommendation** | Prefer `Deque` with `ArrayDeque` for ordinary stack usage |

[⬆️ Back to top](#top)

---

<a id="s16"></a>

## 16. Recommended learning and practice order

For your Java interview revision, study these in **four groups**.

### 🟢 Group 1 — Queue fundamentals

- `Queue` interface and FIFO.
- `add()` vs `offer()`.
- `remove()` vs `poll()`.
- `element()` vs `peek()`.

### 🔵 Group 2 — Deque and implementations

- `ArrayDeque` internal structure.
- `LinkedList` as `Queue` and `Deque`.
- `ArrayDeque` vs `LinkedList`.
- Stack using `ArrayDeque`.

### 🟣 Group 3 — PriorityQueue

- Binary heap and complexity.
- Min-heap vs max-heap.
- `Comparable` vs `Comparator`.
- Custom objects and K-largest/K-smallest problems.

### 🟠 Group 4 — Concurrency

- `BlockingQueue`.
- `put()` vs `offer()`.
- `take()` vs `poll()`.
- `ArrayBlockingQueue` vs `LinkedBlockingQueue`.
- `BlockingDeque` and producer-consumer problems.

> [!TIP]
> **Most valuable practice exercises:** implement a queue using a deque, a stack using `ArrayDeque`, a max-heap using `PriorityQueue`, and a producer-consumer program using `BlockingQueue`. These exercises cover most of the important concepts in this topic.

[⬆️ Back to top](#top)