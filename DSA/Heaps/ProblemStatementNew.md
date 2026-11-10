# Heap Problems — Interview Preparation

---

# 🟢 Level 0 — Heap Basics & Core Operations

## 1. Min Heap — Basic Implementation

### Problem Statement

Implement a **Min Heap** that supports the following operations:

* Insert an element
* Get the minimum element
* Delete the minimum element

A Min Heap must maintain the property:

```text
Parent <= Children
```

The minimum element must always be at the root.

### Example 1

**Input:**

```text
Operations:
insert(10)
insert(5)
insert(20)
insert(3)
peek()
```

**Output:**

```text
3
```

### Example 2

**Input:**

```text
Operations:
insert(8)
insert(2)
insert(15)
insert(1)
deleteMin()
peek()
```

**Output:**

```text
2
```

---

## 2. Max Heap — Basic Implementation

### Problem Statement

Implement a **Max Heap** that supports:

* Insert an element
* Get the maximum element
* Delete the maximum element

A Max Heap must maintain:

```text
Parent >= Children
```

The maximum element must always be at the root.

### Example 1

**Input:**

```text
Operations:
insert(10)
insert(5)
insert(20)
insert(3)
peek()
```

**Output:**

```text
20
```

### Example 2

**Input:**

```text
Operations:
insert(8)
insert(25)
insert(15)
insert(30)
deleteMax()
peek()
```

**Output:**

```text
25
```

---

## 3. Insert into Heap — Heapify Up

### Problem Statement

Given a valid Min Heap and a new value, insert the value into the heap while maintaining the Min Heap property.

After inserting the element at the end, use **Heapify Up** to restore the heap.

### Example 1

**Input:**

```text
Heap = [10, 20, 15, 30, 40]
Insert = 5
```

**Output:**

```text
[5, 20, 10, 30, 40, 15]
```

### Example 2

**Input:**

```text
Heap = [2, 5, 8, 10, 12]
Insert = 1
```

**Output:**

```text
[1, 5, 2, 10, 12, 8]
```

---

## 4. Delete Root — Heapify Down

### Problem Statement

Given a valid Min Heap, remove the root element and maintain the Min Heap property.

Steps:

```text
Remove Root
    ↓
Move Last Element → Root
    ↓
Heapify Down
```

### Example 1

**Input:**

```text
Heap = [5, 10, 8, 20, 15, 12]
```

**Output:**

```text
[8, 10, 12, 20, 15]
```

### Example 2

**Input:**

```text
Heap = [1, 4, 3, 8, 7, 6]
```

**Output:**

```text
[3, 4, 6, 8, 7]
```

---

## 5. Build Heap from Array — Bottom-Up Heapify

### Problem Statement

Given an unsorted array, convert it into a **Min Heap** using the **Bottom-Up Heapify** approach.

### Example 1

**Input:**

```text
[10, 5, 20, 2, 4, 8]
```

**Output:**

```text
[2, 4, 8, 10, 5, 20]
```

### Example 2

**Input:**

```text
[9, 4, 7, 1, 3, 6, 8]
```

**Output:**

```text
[1, 3, 6, 4, 9, 7, 8]
```

---

## 6. Peek / Get Root

### Problem Statement

Given a Min Heap, return the minimum element without removing it.

### Example 1

**Input:**

```text
[2, 5, 8, 10, 15]
```

**Output:**

```text
2
```

### Example 2

**Input:**

```text
[1, 4, 3, 8, 7]
```

**Output:**

```text
1
```

---

# 🟡 Level 1 — Kth Element Problems

## 7. Kth Largest Element

### Problem Statement

Given an integer array and an integer `k`, return the **kth largest element** in the array.

### Example 1

**Input:**

```text
nums = [3, 2, 1, 5, 6, 4]
k = 2
```

**Output:**

```text
5
```

### Example 2

**Input:**

```text
nums = [7, 10, 4, 3, 20, 15]
k = 3
```

**Output:**

```text
10
```

---

## 8. Kth Smallest Element

### Problem Statement

Given an integer array and an integer `k`, return the **kth smallest element**.

### Example 1

**Input:**

```text
nums = [7, 10, 4, 3, 20, 15]
k = 3
```

**Output:**

```text
7
```

### Example 2

**Input:**

```text
nums = [9, 4, 7, 1, 3, 6]
k = 2
```

**Output:**

```text
3
```

---

## 9. K Largest Elements

### Problem Statement

Given an integer array and an integer `k`, return the **k largest elements**.

The order of the returned elements does not matter.

### Example 1

**Input:**

```text
nums = [3, 2, 1, 5, 6, 4]
k = 2
```

**Output:**

```text
[5, 6]
```

### Example 2

**Input:**

```text
nums = [10, 4, 7, 2, 8, 15, 1]
k = 3
```

**Output:**

```text
[10, 15, 8]
```

---

## 10. K Smallest Elements

### Problem Statement

Given an integer array and an integer `k`, return the **k smallest elements**.

### Example 1

**Input:**

```text
nums = [7, 10, 4, 3, 20, 15]
k = 3
```

**Output:**

```text
[3, 4, 7]
```

### Example 2

**Input:**

```text
nums = [9, 4, 7, 1, 3, 6]
k = 2
```

**Output:**

```text
[1, 3]
```

---

# 🟡 Level 2 — Top K Problems

## 11. Top K Frequent Elements

### Problem Statement

Given an integer array, return the `k` most frequent elements.

### Example 1

**Input:**

```text
nums = [1, 1, 1, 2, 2, 3]
k = 2
```

**Output:**

```text
[1, 2]
```

### Example 2

**Input:**

```text
nums = [4, 4, 4, 5, 5, 6, 6, 6]
k = 2
```

**Output:**

```text
[4, 6]
```

---

## 12. Top K Frequent Words

### Problem Statement

Given an array of words, return the `k` most frequent words.

If two words have the same frequency, return them in **lexicographical order**.

### Example 1

**Input:**

```text
words = ["i", "love", "leetcode", "i", "love", "coding"]
k = 2
```

**Output:**

```text
["i", "love"]
```

### Example 2

**Input:**

```text
words = ["the", "day", "is", "sunny", "the", "the",
         "the", "sunny", "is", "is"]
k = 4
```

**Output:**

```text
["the", "is", "sunny", "day"]
```

---

## 13. Sort Characters By Frequency

### Problem Statement

Given a string, sort its characters in decreasing order based on their frequency.

### Example 1

**Input:**

```text
s = "tree"
```

**Output:**

```text
"eert"
```

### Example 2

**Input:**

```text
s = "cccaaa"
```

**Output:**

```text
"aaaccc"
```

### Example 3

**Input:**

```text
s = "Aabb"
```

**Output:**

```text
"bbAa"
```

---

# 🟡 Level 3 — K Closest Problems

## 14. K Closest Points to Origin

### Problem Statement

Given an array of points `(x, y)`, return the `k` points closest to the origin `(0, 0)`.

Distance:

```text
distance² = x² + y²
```

### Example 1

**Input:**

```text
points = [[1,3], [-2,2]]
k = 1
```

**Output:**

```text
[[-2,2]]
```

### Example 2

**Input:**

```text
points = [[3,3], [5,-1], [-2,4]]
k = 2
```

**Output:**

```text
[[3,3], [-2,4]]
```

---

## 15. K Closest Numbers

### Problem Statement

Given an integer array, a target value `x`, and an integer `k`, return the `k` numbers closest to `x`.

### Example 1

**Input:**

```text
nums = [1, 2, 3, 4, 5]
x = 3
k = 2
```

**Output:**

```text
[2, 3]
```

### Example 2

**Input:**

```text
nums = [10, 20, 30, 40, 50]
x = 35
k = 2
```

**Output:**

```text
[30, 40]
```

---

## 16. K Closest Elements to a Given Number

### Problem Statement

Given a **sorted array**, an integer `x`, and an integer `k`, return the `k` elements closest to `x`.

If two elements have the same distance, choose the smaller element.

### Example 1

**Input:**

```text
arr = [1, 2, 3, 4, 5]
k = 4
x = 3
```

**Output:**

```text
[1, 2, 3, 4]
```

### Example 2

**Input:**

```text
arr = [1, 3, 5, 7, 9]
k = 2
x = 6
```

**Output:**

```text
[5, 7]
```

---

# 🟠 Level 4 — Merge Problems

## 17. Merge K Sorted Arrays

### Problem Statement

Given `k` sorted arrays, merge them into a single sorted array.

### Example 1

**Input:**

```text
arrays = [
    [1, 4, 7],
    [2, 5, 8],
    [3, 6, 9]
]
```

**Output:**

```text
[1, 2, 3, 4, 5, 6, 7, 8, 9]
```

### Example 2

**Input:**

```text
arrays = [
    [1, 10],
    [2, 5, 8],
    [3, 7, 9]
]
```

**Output:**

```text
[1, 2, 3, 5, 7, 8, 9, 10]
```

---

## 18. Merge K Sorted Linked Lists

### Problem Statement

Given `k` sorted linked lists, merge them into one sorted linked list.

### Example 1

**Input:**

```text
[
    [1,4,5],
    [1,3,4],
    [2,6]
]
```

**Output:**

```text
[1,1,2,3,4,4,5,6]
```

### Example 2

**Input:**

```text
[
    [1,2,7],
    [3,5],
    [4,6,8]
]
```

**Output:**

```text
[1,2,3,4,5,6,7,8]
```

---

## 19. Smallest Range Covering Elements from K Lists

### Problem Statement

You are given `k` sorted lists. Find the smallest range `[start, end]` such that the range contains at least one element from every list.

### Example 1

**Input:**

```text
lists = [
    [4,10,15,24,26],
    [0,9,12,20],
    [5,18,22,30]
]
```

**Output:**

```text
[20,24]
```

### Example 2

**Input:**

```text
lists = [
    [1,5,8],
    [4,12],
    [7,8,10]
]
```

**Output:**

```text
[4,7]
```

---

# 🟠 Level 5 — Scheduling Problems

## 20. Meeting Rooms II

### Problem Statement

Given an array of meeting intervals, determine the **minimum number of meeting rooms** required so that no meetings overlap.

### Example 1

**Input:**

```text
intervals = [[0,30],[5,10],[15,20]]
```

**Output:**

```text
2
```

### Example 2

**Input:**

```text
intervals = [[7,10],[2,4]]
```

**Output:**

```text
1
```

---

## 21. Minimum Number of Platforms

### Problem Statement

Given arrival and departure times of trains at a railway station, find the minimum number of platforms required so that no train has to wait.

### Example 1

**Input:**

```text
arrival   = [900, 940, 950, 1100, 1500, 1800]
departure = [910, 1200, 1120, 1130, 1900, 2000]
```

**Output:**

```text
3
```

### Example 2

**Input:**

```text
arrival   = [900, 940]
departure = [910, 1200]
```

**Output:**

```text
1
```

---

## 22. Task Scheduler

### Problem Statement

Given a list of tasks represented by characters and a non-negative integer `n`, execute all tasks such that the same task has at least `n` intervals between two executions.

Return the minimum number of intervals required.

### Example 1

**Input:**

```text
tasks = ["A","A","A","B","B","B"]
n = 2
```

**Output:**

```text
8
```

### Example 2

**Input:**

```text
tasks = ["A","A","A","A","B","B","B"]
n = 2
```

**Output:**

```text
10
```

---

## 23. CPU Task Scheduling

### Problem Statement

A CPU must execute tasks. Each task has a processing time and a priority. At every step, the CPU should select the available task with the highest priority.

If multiple tasks have the same priority, select the task with the smallest task ID.

Return the order in which the tasks are executed.

### Example 1

**Input:**

```text
tasks = [
    [1, 0, 3],
    [2, 1, 2],
    [3, 2, 1]
]
```

Format:

```text
[taskId, arrivalTime, priority]
```

**Output:**

```text
[1, 2, 3]
```

### Example 2

**Input:**

```text
tasks = [
    [1, 0, 5],
    [2, 0, 10],
    [3, 1, 7]
]
```

**Output:**

```text
[2, 3, 1]
```

---

# 🟠 Level 6 — Two Heap Problems


## 18. Find Median from Data Stream

### Problem Statement

Design a data structure that receives a stream of integers and can return the median of all numbers seen so far at any time.

The median is the middle value of the sorted data. If the count is even, the median is the average of the two middle values.

Support two operations:

- `addNum(num)` — add an integer to the stream.
- `findMedian()` — return the median of all elements added so far.

### Example 1

**Input:**

```text
operations = ["addNum 1", "addNum 2", "findMedian", "addNum 3", "findMedian"]
```

Format:

```text
["addNum x" | "findMedian"]
```

**Output:**

```text
[1.5, 2.0]
```

### Example 2

**Input:**

```text
operations = ["addNum 5", "findMedian", "addNum 15", "addNum 1", "findMedian"]
```

**Output:**

```text
[5.0, 5.0]
```

---

## 19. Sliding Window Median

### Problem Statement

Given an integer array `nums` and a window size `k`, the window slides from the left end of the array to the right end, one position at a time.

For every window position, compute the median of the `k` numbers inside the window.

If `k` is even, the median is the average of the two middle values.

Return the list of medians, one for each window position.

### Example 1

**Input:**

```text
nums = [1, 3, -1, -3, 5, 3, 6, 7]
k = 3
```

Format:

```text
nums = [integers]
k    = window size
```

**Output:**

```text
[1, -1, -1, 3, 5, 6]
```

### Example 2

**Input:**

```text
nums = [1, 2, 3, 4]
k = 2
```

**Output:**

```text
[1.5, 2.5, 3.5]
```

---
# 🔴 Level 7 — Advanced Heap Problems

## 20. Find K Pairs with Smallest Sums

### Problem Statement

You are given two integer arrays `nums1` and `nums2`, both sorted in ascending order, and an integer `k`.

A pair `(u, v)` consists of one element `u` from `nums1` and one element `v` from `nums2`.

Return the `k` pairs with the smallest sums `u + v`. If there are fewer than `k` pairs in total, return all of them.

### Example 1

**Input:**

```text
nums1 = [1, 7, 11]
nums2 = [2, 4, 6]
k = 3
```

Format:

```text
nums1 = sorted array
nums2 = sorted array
k     = number of pairs
```

**Output:**

```text
[[1, 2], [1, 4], [1, 6]]
```

### Example 2

**Input:**

```text
nums1 = [1, 1, 2]
nums2 = [1, 2, 3]
k = 2
```

**Output:**

```text
[[1, 1], [1, 1]]
```

---

## 21. Kth Smallest Element in a Sorted Matrix

### Problem Statement

Given an `n x n` matrix where every row and every column is sorted in ascending order, return the `k`-th smallest element in the matrix.

The `k`-th smallest is counted in sorted order of all elements, including duplicates (not the k-th distinct value).

### Example 1

**Input:**

```text
matrix = [
    [1,  5,  9],
    [10, 11, 13],
    [12, 13, 15]
]
k = 8
```

Format:

```text
matrix = n x n grid, rows and columns sorted
k      = rank to find (1-indexed)
```

**Output:**

```text
13
```

### Example 2

**Input:**

```text
matrix = [
    [-5]
]
k = 1
```

**Output:**

```text
-5
```

---

## 22. Kth Smallest Number in Multiple Sorted Lists

### Problem Statement

You are given `m` lists of integers, each sorted in ascending order, and an integer `k`.

Find the `k`-th smallest number among all the numbers in all the lists combined. Duplicates are counted separately.

### Example 1

**Input:**

```text
lists = [
    [2, 6, 8],
    [3, 6, 7],
    [1, 3, 4]
]
k = 5
```

Format:

```text
lists = m sorted lists
k     = rank to find (1-indexed)
```

**Output:**

```text
4
```

### Example 2

**Input:**

```text
lists = [
    [5, 8, 9],
    [1, 7]
]
k = 4
```

**Output:**

```text
8
```

---

## 23. Reorganize String

### Problem Statement

Given a string `s`, rearrange its characters so that no two adjacent characters are the same.

Return any valid rearrangement. If it is not possible, return an empty string `""`.

### Example 1

**Input:**

```text
s = "aab"
```

**Output:**

```text
"aba"
```

### Example 2

**Input:**

```text
s = "aaab"
```

**Output:**

```text
""
```

---

## 24. Rearrange String K Distance Apart

### Problem Statement

Given a string `s` and an integer `k`, rearrange the characters of `s` so that the same characters are at least distance `k` apart from each other.

That is, if a character appears at index `i` and again at index `j`, then `j - i >= k`.

Return any valid rearrangement. If it is not possible, return an empty string `""`. If `k = 0`, return `s` unchanged.

### Example 1

**Input:**

```text
s = "aabbcc"
k = 3
```

Format:

```text
s = input string
k = minimum distance between equal characters
```

**Output:**

```text
"abcabc"
```

### Example 2

**Input:**

```text
s = "aaabc"
k = 3
```

**Output:**

```text
""
```

### Example 3

**Input:**

```text
s = "aaadbbcc"
k = 2
```

**Output:**

```text
"abacabcd"
```

---

## 25. IPO / Maximize Capital

### Problem Statement

A company wants to maximize its capital before its IPO. It can complete at most `k` distinct projects.

Each project `i` has:

- a pure profit `profits[i]`
- a minimum capital `capital[i]` required to start it

You start with initial capital `w`. When a project is finished, its profit is added to your total capital.

Choose at most `k` distinct projects to maximize your final capital, and return that maximum value.

### Example 1

**Input:**

```text
k = 2
w = 0
profits = [1, 2, 3]
capital = [0, 1, 1]
```

Format:

```text
k       = maximum number of projects
w       = initial capital
profits = profit of each project
capital = minimum capital needed for each project
```

**Output:**

```text
4
```

### Example 2

**Input:**

```text
k = 3
w = 0
profits = [1, 2, 3]
capital = [0, 1, 2]
```

**Output:**

```text
6
```
