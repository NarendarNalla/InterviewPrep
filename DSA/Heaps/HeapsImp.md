# Heap Data Structure

## 1. Heap Property

A **Heap** is a **Complete Binary Tree** that follows a specific heap property.

### Rule 1: Complete Binary Tree

A Complete Binary Tree follows these rules:

* All levels must be completely filled except possibly the last level.
* The last level must be filled **from left to right**.
* There should be no gaps before a node on the last level.

### Rule 2: Min Heap and Max Heap

#### Min Heap

```text
Parent <= Left Child
Parent <= Right Child
```

The **smallest element** will always be at the root.

#### Max Heap

```text
Parent >= Left Child
Parent >= Right Child
```

The **largest element** will always be at the root.

---

## 2. Heap vs Binary Search Tree

### Binary Search Tree (BST)

A BST follows:

```text
Left < Parent < Right
```

Example:

```text
        10
       /  \
      5    15
```

### Heap

A Heap only maintains a relationship between a **parent and its children**.

```text
Min Heap → Parent <= Children

Max Heap → Parent >= Children
```

Unlike a BST, a Heap does **not** maintain complete ordering between the left and right subtrees.

---

## 3. Heap Representation

Heap values are commonly stored in an **array**.

For a node at index `i` using **0-based indexing**:

```text
Parent = (i - 1) / 2

Left Child = 2 * i + 1

Right Child = 2 * i + 2
```

Example:

```text
             10
           /    \
         20      30
        /  \    /  \
       40   50 60  70
```

Array representation:

```text
[10, 20, 30, 40, 50, 60, 70]
```

---

## 4. Heap Operations

Common operations performed on a Heap:

* `peek`
* `insert`
* `delete`
* `build heap`
* `heapify`

These operations help us efficiently access and maintain the **minimum or maximum element**.

### Time Complexity

| Operation   | Complexity |
| ----------- | ---------: |
| Peek        |     `O(1)` |
| Insert      | `O(log n)` |
| Delete Root | `O(log n)` |
| Build Heap  |     `O(n)` |

---

# 5. Insert in Heap

When inserting an element:

```text
Insert
   ↓
Add at END
   ↓
Heap Property may be violated
   ↓
Heapify Up / Bubble Up
   ↓
Compare with Parent
   ↓
Swap if necessary
```

### Example

Suppose we have a Min Heap:

```text
        10
       /  \
     20    30
```

Insert `5`.

First, insert at the end:

```text
        10
       /  \
     20    30
    /
   5
```

Heap property is violated because:

```text
5 < 20
```

Perform **Heapify Up**:

```text
        10
       /  \
      5    30
     /
    20
```

Again:

```text
5 < 10
```

Swap again:

```text
         5
       /   \
     10     30
    /
   20
```

Now the Min Heap property is satisfied.

### Complexity

```text
Insert = O(log n)
```

---

# 6. Delete Root from Heap

Usually, when we talk about deleting from a Heap, we mean **deleting the root**.

Process:

```text
Delete Root
     ↓
Move LAST element → ROOT
     ↓
Heap Property may be violated
     ↓
Heapify Down / Bubble Down
     ↓
Compare Children
     ↓
Choose Appropriate Child
     ↓
Swap if necessary
```

### Example

Min Heap:

```text
        5
       / \
     10   20
    /  \
   30  40
```

Delete root `5`.

Move the last element `40` to the root:

```text
        40
       /  \
     10    20
    /
   30
```

Heap property is violated.

Compare the children:

```text
10 < 20
```

Choose the smaller child `10` and swap:

```text
        10
       /  \
     40    20
    /
   30
```

Again compare `40` with its child `30`.

```text
30 < 40
```

Swap:

```text
        10
       /  \
     30    20
    /
   40
```

Now the Min Heap property is satisfied.

### Complexity

```text
Delete Root = O(log n)
```

---

# 7. Peek

`peek` means accessing the root element **without removing it**.

### Min Heap

```text
Root = Minimum Element
```

### Max Heap

```text
Root = Maximum Element
```

Therefore:

```text
Peek = O(1)
```

---

# 8. Priority Queue

Heaps are commonly used to implement a **Priority Queue**.

A Priority Queue works based on **priority order**.

For example:

```text
Higher Priority
      ↓
Processed First
```

### Important Distinction

```text
Heap
  ↓
Underlying Data Structure

Priority Queue
  ↓
Abstract Data Type (ADT)
```

A Priority Queue can be implemented using a Heap.

---

# 9. Java PriorityQueue

By default, Java's `PriorityQueue` behaves as a **Min Heap**.

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
```

Example:

```java
pq.add(30);
pq.add(10);
pq.add(20);

System.out.println(pq.peek());
```

Output:

```text
10
```

Because the smallest element has the highest priority.

---

## Max Heap using PriorityQueue

### Method 1: `Collections.reverseOrder()`

```java
PriorityQueue<Integer> pq =
    new PriorityQueue<>(Collections.reverseOrder());
```

This creates a **Max Heap**.

---

### Method 2: Custom Comparator

```java
PriorityQueue<Integer> pq =
    new PriorityQueue<>((a, b) -> Integer.compare(b, a));
```

This also creates a **Max Heap**.

> `Integer.compare(b, a)` is safer than `b - a` because subtraction can overflow for extreme integer values.

---

# 10. Where Heaps Are Used in DSA

Heaps are useful when we repeatedly need access to the **minimum or maximum** element.

## 10.1 Top K Problems

Common examples:

* Kth Largest Element
* Kth Smallest Element
* Top K Elements
* K Largest Elements
* K Smallest Elements
* Top K Frequent Elements

Important pattern:

```text
K Largest  → Min Heap

K Smallest → Max Heap
```

---

## 10.2 Scheduling

Heaps are used in scheduling problems where tasks need to be processed according to priority.

Examples:

* CPU Scheduling
* Task Scheduling
* Meeting Scheduling
* Event Scheduling

---

## 10.3 Merging

Heaps are useful for:

* Merge K Sorted Arrays
* Merge K Sorted Linked Lists

A Min Heap can efficiently determine the smallest current element among multiple sorted sources.

---

## 10.4 Graph Algorithms

Heaps / Priority Queues are used in several graph algorithms.

Examples:

* Dijkstra's Algorithm
* Prim's Algorithm
* A* Search

For example, Dijkstra's Algorithm uses a Priority Queue to efficiently select the next node with the smallest known distance.

---

# 11. Heap Sort

A Heap can also be used for sorting.

For **ascending order**, a common approach is:

```text
Build Max Heap
      ↓
Move Maximum → End
      ↓
Heapify Remaining Elements
      ↓
Repeat
```

Process:

```text
Array
  ↓
Build Max Heap
  ↓
Maximum element comes to Root
  ↓
Move Root → End
  ↓
Heapify Remaining Heap
  ↓
Repeat
```

### Complexity

```text
Build Heap       = O(n)

Repeated Heapify = O(n log n)

Overall           = O(n log n)
```

---

# 12. Insert + Delete Together

This is the core picture of Heap operations.

## Insert

```text
INSERT
   ↓
Add at END
   ↓
Heapify UP
   ↓
Compare with Parent
   ↓
Swap if necessary
```

### Complexity

```text
O(log n)
```

---

## Delete Root

```text
DELETE ROOT
   ↓
Move LAST → ROOT
   ↓
Heapify DOWN
   ↓
Compare Children
   ↓
Choose Appropriate Child
   ↓
Swap if necessary
```

### Complexity

```text
O(log n)
```

---

# 13. Bottom-Up Heapify

Another important concept is **Build Heap using Bottom-Up Heapify**.

Instead of inserting every element individually, we can build a Heap efficiently from an existing array.

Process:

```text
Array
  ↓
Find Last Non-Leaf Node
  ↓
Heapify Down
  ↓
Move Towards Root
  ↓
Heapify Down
  ↓
Continue Until Index 0
```

For a 0-based array:

```text
Last Non-Leaf Node = (n / 2) - 1
```

Then:

```text
for (i = n/2 - 1; i >= 0; i--) {
    heapifyDown(i);
}
```

### Complexity

```text
Build Heap = O(n)
```

---

# 14. Why is Bottom-Up Heapify O(n)?

A common doubt is:

> If Heapify Down takes `O(log n)`, why is Build Heap only `O(n)`?

The reason is that **not every node moves `log n` levels**.

Most nodes are leaf nodes or are very close to the bottom of the tree.

Therefore, the total work performed by Bottom-Up Heapify is:

```text
O(n)
```

So:

```text
Build Heap using Bottom-Up Heapify = O(n)
```

---

# 15. Top K Pattern

One of the most important Heap patterns in DSA is:

## K Largest

Use a **Min Heap**.

```text
K Largest Elements
        ↓
     Min Heap
        ↓
Keep only K elements
        ↓
Smallest of those K → Root
```

## K Smallest

Use a **Max Heap**.

```text
K Smallest Elements
        ↓
     Max Heap
        ↓
Keep only K elements
        ↓
Largest of those K → Root
```

### Easy Rule

```text
K Largest  → Min Heap

K Smallest → Max Heap
```

---

# 16. Heap Mental Model

Think of Heap as:

```text
                    HEAP
                      |
             ┌────────┴────────┐
             |                 |
         MIN HEAP          MAX HEAP
             |                 |
         Smallest           Largest
           Root               Root
```

---

# 17. Core Heap Operations — Mental Model

## INSERT

```text
INSERT
   ↓
Add at End
   ↓
Heapify Up
   ↓
Compare with Parent
   ↓
Swap if Necessary
   ↓
O(log n)
```

---

## DELETE ROOT

```text
DELETE ROOT
   ↓
Move Last → Root
   ↓
Heapify Down
   ↓
Compare Children
   ↓
Choose Appropriate Child
   ↓
Swap if Necessary
   ↓
O(log n)
```

---

## PEEK

```text
PEEK
  ↓
Root
  ↓
O(1)
```

---

# 18. Final Heap Cheat Sheet

| Concept               | Key Point                            |
| --------------------- | ------------------------------------ |
| Heap                  | Complete Binary Tree + Heap Property |
| Min Heap              | Parent `<=` Children                 |
| Max Heap              | Parent `>=` Children                 |
| Min Heap Root         | Minimum element                      |
| Max Heap Root         | Maximum element                      |
| Heap Representation   | Usually Array                        |
| Parent                | `(i - 1) / 2`                        |
| Left Child            | `2 * i + 1`                          |
| Right Child           | `2 * i + 2`                          |
| Insert                | Add at End + Heapify Up              |
| Insert Complexity     | `O(log n)`                           |
| Delete Root           | Last → Root + Heapify Down           |
| Delete Complexity     | `O(log n)`                           |
| Peek                  | Root                                 |
| Peek Complexity       | `O(1)`                               |
| Build Heap            | Bottom-Up Heapify                    |
| Build Heap Complexity | `O(n)`                               |
| Priority Queue        | ADT commonly implemented using Heap  |
| Java PriorityQueue    | Min Heap by default                  |
| Max PriorityQueue     | `Collections.reverseOrder()`         |
| K Largest             | Min Heap                             |
| K Smallest            | Max Heap                             |
| Heap Sort             | `O(n log n)`                         |
| Dijkstra              | Priority Queue / Heap                |
| Merge K Sorted        | Min Heap                             |

---

# 19. One-Line Memory Trick

```text
MIN HEAP → Smallest stays on TOP

MAX HEAP → Largest stays on TOP

INSERT → Add at END → Heapify UP

DELETE → Last → Root → Heapify DOWN

PEEK → Root → O(1)

BUILD HEAP → Bottom-Up Heapify → O(n)

K Largest → Min Heap

K Smallest → Max Heap
```

---

# 20. Quick Revision

```text
                    HEAP
                      |
            ┌─────────┴─────────┐
            ↓                   ↓
        MIN HEAP            MAX HEAP
            ↓                   ↓
       Smallest Root       Largest Root


INSERT
  ↓
Add at End
  ↓
Heapify Up
  ↓
O(log n)


DELETE ROOT
  ↓
Last → Root
  ↓
Heapify Down
  ↓
O(log n)


PEEK
  ↓
Root
  ↓
O(1)


BUILD HEAP
  ↓
Bottom-Up Heapify
  ↓
O(n)


TOP K

K Largest  → Min Heap

K Smallest → Max Heap
```
