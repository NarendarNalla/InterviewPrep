# Heap Problems — Interview Preparation Roadmap

## Level 1 — Kth Element Problems

1. **Kth Largest Element**
2. **Kth Smallest Element**
3. **K Largest Elements**
4. **K Smallest Elements**

⬇️

## Level 2 — Top K Problems

5. **Top K Frequent Elements**
6. **Top K Frequent Words**
7. **Sort Characters By Frequency**

⬇️

## Level 3 — K Closest Problems

8. **K Closest Points to Origin**
9. **K Closest Numbers**
10. **K Closest Elements to a Given Number**

⬇️

## Level 4 — Merge Problems

11. **Merge K Sorted Arrays**
12. **Merge K Sorted Linked Lists**
13. **Smallest Range Covering Elements from K Lists**

⬇️

## Level 5 — Scheduling Problems

14. **Meeting Rooms II**
15. **Minimum Number of Platforms**
16. **Task Scheduler**
17. **CPU Task Scheduling**

⬇️

## Level 6 — Two Heap Problems

18. **Find Median from Data Stream**
19. **Sliding Window Median**

⬇️

## Level 7 — Advanced Heap Problems

20. **Find K Pairs with Smallest Sums**
21. **Kth Smallest Element in a Sorted Matrix**
22. **Kth Smallest Number in Multiple Sorted Lists**
23. **Reorganize String**
24. **Rearrange String K Distance Apart**
25. **IPO / Maximize Capital**

---

# 🔥 Heap Problems — Level 4

---

# 🟢 Level 1 — Kth Element Problems

## 1. Kth Largest Element

Given an integer array `nums` and an integer `k`, return the **kth largest element** in the array.

### Example

**Input:**

```text
nums = [3, 2, 1, 5, 6, 4]
k = 2
```

**Output:**

```text
5
```

---

## 2. Kth Smallest Element

Given an integer array `nums` and an integer `k`, return the **kth smallest element**.

### Example

**Input:**

```text
nums = [7, 10, 4, 3, 20, 15]
k = 3
```

**Output:**

```text
7
```

---

## 3. K Largest Elements

Given an integer array and an integer `k`, return the **k largest elements**.

### Example

**Input:**

```text
nums = [3, 1, 5, 12, 2, 11]
k = 3
```

**Output:**

```text
[5, 12, 11]
```

---

## 4. K Smallest Elements

Given an integer array and an integer `k`, return the **k smallest elements**.

### Example

**Input:**

```text
nums = [7, 10, 4, 3, 20, 15]
k = 3
```

**Output:**

```text
[3, 4, 7]
```

---

# 🟢 Level 2 — Top K Problems

## 5. Top K Frequent Elements ⭐

Given an integer array, return the `k` most frequently occurring elements.

### Example

**Input:**

```text
nums = [1, 1, 1, 2, 2, 3]
k = 2
```

**Output:**

```text
[1, 2]
```

---

## 6. Top K Frequent Words

Given an array of strings, return the `k` most frequent words.

If two words have the same frequency, return them in **lexicographical order**.

### Example

**Input:**

```text
["i", "love", "leetcode", "i", "love", "coding"]
k = 2
```

**Output:**

```text
["i", "love"]
```

---

## 7. Sort Characters By Frequency

Given a string, sort its characters in decreasing order based on their frequency.

### Example

**Input:**

```text
"tree"
```

**Output:**

```text
"eert"
```

Another valid output:

```text
"eetr"
```

---

# 🟡 Level 3 — K Closest Problems

## 8. K Closest Points to Origin ⭐

Given an array of points `(x, y)`, return the `k` points closest to the origin `(0, 0)`.

### Distance

```text
√(x² + y²)
```

### Example

**Input:**

```text
points = [[1, 3], [-2, 2]]
k = 1
```

**Output:**

```text
[[-2, 2]]
```

---

## 9. K Closest Numbers

Given a sorted array, an integer `x`, and `k`, find the `k` numbers closest to `x`.

### Example

**Input:**

```text
arr = [1, 2, 3, 4, 5]
x = 3
k = 2
```

**Output:**

```text
[2, 3]
```

---

## 10. K Closest Elements to a Given Number

Given an unsorted array, find the `k` elements whose absolute difference from `x` is minimum.

### Example

**Input:**

```text
nums = [10, 5, 3, 9, 2]
x = 7
k = 2
```

**Output:**

```text
[5, 9]
```

---

# 🟡 Level 4 — Merge Problems

## 11. Merge K Sorted Arrays ⭐

You are given `K` sorted arrays. Merge them into a single sorted array.

### Input

```text
[1, 4, 7]
[2, 5, 8]
[3, 6, 9]
```

### Output

```text
[1, 2, 3, 4, 5, 6, 7, 8, 9]
```

---

## 12. Merge K Sorted Linked Lists ⭐⭐⭐

You are given `K` sorted linked lists. Merge all of them into one sorted linked list.

### Input

```text
1 → 4 → 5
1 → 3 → 4
2 → 6
```

### Output

```text
1 → 1 → 2 → 3 → 4 → 4 → 5 → 6
```

---

## 13. Smallest Range Covering Elements from K Lists ⭐⭐⭐

You are given `K` sorted lists. Find the smallest range `[a, b]` that contains at least one element from every list.

### Input

```text
[4, 10, 15, 24, 26]
[0, 9, 12, 20]
[5, 18, 22, 30]
```

### Output

```text
[20, 24]
```

---

# 🟡 Level 5 — Scheduling Problems

## 14. Meeting Rooms II ⭐⭐⭐

Given an array of meeting intervals, determine the minimum number of meeting rooms required.

### Input

```text
[[0, 30], [5, 10], [15, 20]]
```

### Output

```text
2
```

---

## 15. Minimum Number of Platforms

Given arrival and departure times of trains, find the minimum number of platforms required so that no train has to wait.

### Input

```text
Arrival:
[900, 940, 950, 1100, 1500, 1800]

Departure:
[910, 1200, 1120, 1130, 1900, 2000]
```

### Output

```text
3
```

---

## 16. Task Scheduler ⭐

Given tasks represented by characters and a cooldown period `n`, schedule the tasks so that identical tasks have at least `n` intervals between them.

### Input

```text
tasks = [A, A, A, B, B, B]
n = 2
```

### Output

```text
8
```

One possible schedule:

```text
A → B → idle → A → B → idle → A → B
```

---

## 17. CPU Task Scheduling

Given tasks with different execution times and priorities, determine the order in which tasks should be executed based on priority.

### Example

| Task | Priority |
| ---- | -------: |
| A    |        3 |
| B    |        1 |
| C    |        2 |

Return the execution order according to the scheduling rules.

---

# 🔴 Level 6 — Two Heap Problems

## 18. Find Median from Data Stream ⭐⭐⭐

Design a data structure that continuously receives integers and can return the median at any time.

### Operations

```java
addNum(int num)

findMedian()
```

### Example

```text
addNum(1)
median → 1

addNum(2)
median → 1.5

addNum(3)
median → 2
```

---

## 19. Sliding Window Median ⭐⭐⭐

Given an integer array and window size `k`, return the median of every sliding window.

### Input

```text
nums = [1, 3, -1, -3, 5, 3, 6, 7]
k = 3
```

### Output

```text
[1, -1, -1, 3, 5, 6]
```

---

# 🔴 Level 7 — Advanced Heap Problems

## 20. Find K Pairs with Smallest Sums

Given two sorted arrays, find the `k` pairs `(u, v)` with the smallest sums.

### Input

```text
nums1 = [1, 7, 11]
nums2 = [2, 4, 6]
k = 3
```

### Output

```text
[[1, 2], [1, 4], [1, 6]]
```

---

## 21. Kth Smallest Element in a Sorted Matrix ⭐⭐

Given an `n × n` matrix where every row and column is sorted, find the kth smallest element.

### Input

```text
1   5   9
10  11  13
12  13  15

k = 8
```

### Output

```text
13
```

---

## 22. Kth Smallest Number in Multiple Sorted Lists

Given multiple sorted lists, find the kth smallest number among all elements.

### Input

```text
[1, 4, 7]
[2, 5, 8]
[3, 6, 9]

k = 5
```

### Output

```text
5
```

---

## 23. Reorganize String

Given a string, rearrange its characters so that no two adjacent characters are the same.

### Input

```text
"aab"
```

### Output

```text
"aba"
```

If it is impossible:

```text
return ""
```

---

## 24. Rearrange String K Distance Apart ⭐⭐

Given a string and integer `k`, rearrange the string so that identical characters are at least `k` positions apart.

### Input

```text
s = "aabbcc"
k = 3
```

### Output

```text
"abcabc"
```

---

## 25. IPO / Maximize Capital ⭐⭐⭐

You have a limited amount of initial capital. Each project:

* Requires some capital
* Provides some profit

You can complete at most `k` projects.

Choose projects to maximize your final capital.

### Input

```text
k = 2
initialCapital = 0

profits = [1, 2, 3]
capital = [0, 1, 1]
```

### Output

```text
4
```
