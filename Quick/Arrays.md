# 🧩 DSA Arrays: Pattern-Based Cheat Sheet (Java)

> Legend: ⏱ = time complexity, 💾 = extra space, ⚠️ = pitfall, 💡 = tip, 🧠 = how to recognize the pattern


---

## 📚 Table of Contents

1. [Array Fundamentals](#1--array-fundamentals)
2. [Two Pointers](#2--pattern-1-two-pointers)
3. [Sliding Window](#3--pattern-2-sliding-window)
4. [Prefix Sum](#4--pattern-3-prefix-sum)
5. [Hashing](#5--pattern-4-hashing)
6. [Binary Search](#6--pattern-5-binary-search)
7. [Kadane's Algorithm](#7--pattern-6-kadanes-algorithm)
8. [Sorting + Two Pointers](#8--pattern-7-sorting--two-pointers)
9. [Dutch National Flag](#9--pattern-8-dutch-national-flag)
10. [Greedy](#10--pattern-9-greedy)
11. [Monotonic Stack](#11--pattern-10-monotonic-stack)
12. [Matrix / 2D Arrays](#12--pattern-11-matrix--2d-arrays)
13. [Fundamentals Problems, Intervals, Permutations, Inversions, Heap](#13--more-must-know-problems)
14. [Subarray vs Subsequence vs Subset](#14--subarray-vs-subsequence-vs-subset)
15. [Java Array Utilities](#15--java-array-utilities)
16. [Java Pitfalls](#16--java-pitfalls)
17. [Pattern Identification Table](#17--pattern-identification-table)
18. [Common Mistakes](#18--common-mistakes)
19. [Roadmap and Revision Checklist](#19--interview-roadmap-and-revision-checklist)
20. [Golden Rule](#20--golden-rule)

---

## 1. 🔹 Array Fundamentals

### Properties

- Fixed size, contiguous memory
- Homogeneous elements (one type)
- Zero-based indexing
- In Java, arrays are objects on the heap (`new int[n]`) and are filled with defaults (`0`, `false`, `null`)
- `length` is a **field** for arrays (`arr.length`), a **method** for Strings (`s.length()`) and Lists (`list.size()`)

### Complexity

| Operation | Time |
|---|---|
| Access / Update by index | O(1) |
| Traversal | O(n) |
| Linear search | O(n) |
| Binary search (sorted) | O(log n) |
| Insert / Delete at beginning or middle | O(n) |
| Insert / Delete at end (if space exists) | O(1) |
| Sorting | O(n log n) |

```java
int[] a = new int[5];                 // {0, 0, 0, 0, 0}
int[] nums = {3, 1, 4, 1, 5};         // literal initialization
int[][] grid = new int[3][4];         // 3 rows, 4 columns
int[][] jagged = new int[3][];        // rows allocated later
int len = nums.length;
```

---

## 2. 🔀 Pattern 1: Two Pointers

### When to use
- Sorted array, pair or triplet search
- Reversing, partitioning, in-place modifications
- Removing duplicates or specific values
- Comparing elements from both ends

### A. Opposite Direction

```java
int left = 0, right = arr.length - 1;
while (left < right) {
    // process arr[left], arr[right]
    if (condition) left++;
    else right--;
}
```

#### Reverse an array

```java
// Input: [1,2,3,4,5]  ->  Output: [5,4,3,2,1]
int l = 0, r = arr.length - 1;
while (l < r) {
    int tmp = arr[l];
    arr[l] = arr[r];
    arr[r] = tmp;
    l++;
    r--;
}
```
⏱ O(n) | 💾 O(1)

#### Two Sum in a sorted array

```java
// Input: [1,2,4,6,8], target = 10  ->  Output: [2, 8]
int l = 0, r = arr.length - 1;
while (l < r) {
    int sum = arr[l] + arr[r];
    if (sum == target) return new int[]{arr[l], arr[r]};
    else if (sum < target) l++;     // need a bigger sum
    else r--;                       // need a smaller sum
}
return new int[0];
```
⏱ O(n) | 💾 O(1)

**Why it works:** moving `left` right can only increase the sum, moving `right` left can only decrease it.

### B. Same Direction (Slow / Fast)

```java
int slow = 0;
for (int fast = 0; fast < arr.length; fast++) {
    if (keep(arr[fast])) {
        arr[slow++] = arr[fast];
    }
}
// slow = new logical length
```

#### Remove duplicates from sorted array

```java
// Input: [1,1,2,2,3]  ->  length 3, array starts with [1,2,3]
if (arr.length == 0) return 0;
int i = 0;
for (int j = 1; j < arr.length; j++) {
    if (arr[j] != arr[i]) {
        arr[++i] = arr[j];
    }
}
return i + 1;
```
⏱ O(n) | 💾 O(1)

#### Move zeroes to end

```java
// Input: [0,1,0,3,12]  ->  Output: [1,3,12,0,0]
int slow = 0;
for (int fast = 0; fast < arr.length; fast++) {
    if (arr[fast] != 0) arr[slow++] = arr[fast];
}
while (slow < arr.length) arr[slow++] = 0;
```
⏱ O(n) | 💾 O(1)

> 💡 The slow pointer preserves the relative order of non-zero elements.

---

## 3. 🪟 Pattern 2: Sliding Window

### When to use
- Contiguous subarray or substring
- Max / min of a fixed-size window
- Longest or shortest subarray meeting a condition

### A. Fixed Size Window

```java
// Maximum sum of K consecutive elements
long sum = 0;
for (int i = 0; i < K; i++) sum += arr[i];   // first window
long maxSum = sum;
for (int i = K; i < arr.length; i++) {
    sum += arr[i];          // element entering
    sum -= arr[i - K];      // element leaving
    maxSum = Math.max(maxSum, sum);
}
```

**Dry run:** `[2,1,5,1,3,2]`, K = 3

| Window | Sum |
|---|---:|
| [2,1,5] | 8 |
| [1,5,1] | 7 |
| [5,1,3] | **9** ✅ |
| [1,3,2] | 6 |

⏱ O(n) | 💾 O(1)

### B. Variable Size Window

```java
int left = 0;
for (int right = 0; right < arr.length; right++) {
    // 1. expand: include arr[right]
    while (/* window invalid */) {
        // 2. shrink: remove arr[left]
        left++;
    }
    // 3. update answer with window [left, right], size = right - left + 1
}
```

#### Longest subarray with sum ≤ K (non-negative elements)

```java
// Input: [2,1,3,2,1], K = 5  ->  Output: 2
int left = 0, best = 0;
long sum = 0;
for (int right = 0; right < arr.length; right++) {
    sum += arr[right];
    while (sum > K) sum -= arr[left++];
    best = Math.max(best, right - left + 1);
}
return best;
```
⏱ O(n) | 💾 O(1)

> ⚠️ Only valid for **non-negative** numbers. With negatives, the window is no longer monotonic. Use prefix sum + HashMap.

#### Minimum size subarray with sum ≥ target

```java
// Input: [2,3,1,2,4,3], target = 7  ->  Output: 2  ([4,3])
int left = 0, best = Integer.MAX_VALUE;
long sum = 0;
for (int right = 0; right < arr.length; right++) {
    sum += arr[right];
    while (sum >= target) {
        best = Math.min(best, right - left + 1);
        sum -= arr[left++];
    }
}
return best == Integer.MAX_VALUE ? 0 : best;
```
⏱ O(n) | 💾 O(1)

#### Longest substring without repeating characters

```java
int[] lastSeen = new int[128];          // stores (index + 1)
int left = 0, best = 0;
for (int right = 0; right < s.length(); right++) {
    char c = s.charAt(right);
    left = Math.max(left, lastSeen[c]); // jump past the previous occurrence
    best = Math.max(best, right - left + 1);
    lastSeen[c] = right + 1;
}
return best;
```
⏱ O(n) | 💾 O(1) (fixed alphabet)

#### Longest substring with at most K distinct characters

```java
Map<Character, Integer> cnt = new HashMap<>();
int left = 0, best = 0;
for (int right = 0; right < s.length(); right++) {
    cnt.merge(s.charAt(right), 1, Integer::sum);
    while (cnt.size() > K) {
        char c = s.charAt(left++);
        if (cnt.merge(c, -1, Integer::sum) == 0) cnt.remove(c);
    }
    best = Math.max(best, right - left + 1);
}
return best;
```
> 💡 Same template solves **Fruit Into Baskets** (K = 2).

---

## 4. ➕ Pattern 3: Prefix Sum

### Concept

```
prefix[i] = arr[0] + ... + arr[i]
sum(L, R) = prefix[R] - prefix[L - 1]      (prefix[R] if L = 0)
```

### Safe build (size n + 1, no edge case)

```java
// prefix[i] = sum of first i elements
long[] prefix = new long[n + 1];
for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + arr[i];

// sum of arr[L..R] inclusive
long rangeSum = prefix[R + 1] - prefix[L];
```

**Example:** `[2,4,1,5,3]` gives `prefix = [0,2,6,7,12,15]`, and sum(1,3) = `12 - 2 = 10`.

⏱ build O(n), query O(1) | 💾 O(n)

### Subarray sum equals K (works with negatives and zeros)

```java
// Input: [1,2,3], K = 3  ->  Output: 2  ([1,2] and [3])
Map<Long, Integer> freq = new HashMap<>();
freq.put(0L, 1);                  // empty prefix handles subarrays starting at index 0
long prefix = 0;
int count = 0;
for (int num : arr) {
    prefix += num;
    count += freq.getOrDefault(prefix - K, 0);
    freq.merge(prefix, 1, Integer::sum);
}
return count;
```
⏱ O(n) average | 💾 O(n)

**Key observation:** if `currentPrefix - earlierPrefix = K`, then `earlierPrefix = currentPrefix - K`.

### Longest subarray with sum K (any integers)

```java
Map<Long, Integer> firstIndex = new HashMap<>();
firstIndex.put(0L, -1);
long prefix = 0;
int best = 0;
for (int i = 0; i < arr.length; i++) {
    prefix += arr[i];
    Integer j = firstIndex.get(prefix - K);
    if (j != null) best = Math.max(best, i - j);
    firstIndex.putIfAbsent(prefix, i);      // keep the earliest index
}
return best;
```

### Count subarrays with XOR K

```java
Map<Integer, Integer> freq = new HashMap<>();
freq.put(0, 1);
int x = 0, count = 0;
for (int num : arr) {
    x ^= num;
    count += freq.getOrDefault(x ^ K, 0);   // earlier ^ K == current prefix
    freq.merge(x, 1, Integer::sum);
}
return count;
```

### Equilibrium index

```java
long total = 0;
for (int v : arr) total += v;
long leftSum = 0;
for (int i = 0; i < arr.length; i++) {
    long rightSum = total - leftSum - arr[i];
    if (leftSum == rightSum) return i;
    leftSum += arr[i];
}
return -1;
```
⏱ O(n) | 💾 O(1)

---

## 5. 🗂 Pattern 4: Hashing

### HashSet: existence and duplicates

```java
Set<Integer> seen = new HashSet<>();
for (int num : arr) {
    if (!seen.add(num)) return true;   // add() returns false if already present
}
return false;
```

### HashMap: frequency

```java
Map<Integer, Integer> freq = new HashMap<>();
for (int num : arr) freq.merge(num, 1, Integer::sum);
```

### Two Sum (unsorted)

```java
// Input: [2,7,11,15], target = 9  ->  Output: [0, 1]
Map<Integer, Integer> index = new HashMap<>();
for (int i = 0; i < arr.length; i++) {
    int need = target - arr[i];
    if (index.containsKey(need)) return new int[]{index.get(need), i};
    index.put(arr[i], i);
}
return new int[0];
```
⏱ O(n) average | 💾 O(n)

### Longest consecutive sequence

```java
// Input: [100,4,200,1,3,2]  ->  Output: 4  (1,2,3,4)
Set<Integer> set = new HashSet<>();
for (int x : arr) set.add(x);
int best = 0;
for (int x : set) {
    if (!set.contains(x - 1)) {            // only start at the beginning of a run
        int len = 1;
        while (set.contains(x + len)) len++;
        best = Math.max(best, len);
    }
}
return best;
```
⏱ O(n) average | 💾 O(n)

### Majority element (Boyer-Moore voting)

```java
// Element appearing more than n/2 times
int candidate = 0, count = 0;
for (int x : arr) {
    if (count == 0) candidate = x;
    count += (x == candidate) ? 1 : -1;
}
return candidate;   // correct only if a majority is guaranteed; otherwise verify with a second pass
```
⏱ O(n) | 💾 O(1)

---

## 6. 🔍 Pattern 5: Binary Search

### When to use
- Sorted or monotonic search space
- First / last occurrence
- Minimum feasible / maximum possible answer
- Rotated sorted arrays, peak finding

### A. Standard

```java
int low = 0, high = arr.length - 1;
while (low <= high) {
    int mid = low + (high - low) / 2;     // avoids overflow of (low + high)
    if (arr[mid] == target) return mid;
    else if (arr[mid] < target) low = mid + 1;
    else high = mid - 1;
}
return -1;
```

### B. Lower bound (first index with `arr[i] >= target`)

```java
int low = 0, high = arr.length;
while (low < high) {
    int mid = low + (high - low) / 2;
    if (arr[mid] >= target) high = mid;
    else low = mid + 1;
}
return low;      // arr.length if every element < target
```

### C. Upper bound (first index with `arr[i] > target`)

```java
int low = 0, high = arr.length;
while (low < high) {
    int mid = low + (high - low) / 2;
    if (arr[mid] > target) high = mid;
    else low = mid + 1;
}
return low;
```

### D. First and last occurrence

```java
int first = lowerBound(arr, target);
int last  = upperBound(arr, target) - 1;
boolean found = first < arr.length && arr[first] == target;
// count of target = upperBound - lowerBound
```

### E. Search in rotated sorted array (distinct values)

```java
int lo = 0, hi = arr.length - 1;
while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;
    if (arr[mid] == target) return mid;
    if (arr[lo] <= arr[mid]) {                       // left half sorted
        if (arr[lo] <= target && target < arr[mid]) hi = mid - 1;
        else lo = mid + 1;
    } else {                                         // right half sorted
        if (arr[mid] < target && target <= arr[hi]) lo = mid + 1;
        else hi = mid - 1;
    }
}
return -1;
```
⏱ O(log n)

> ⚠️ With duplicates, when `arr[lo] == arr[mid] == arr[hi]` you must do `lo++; hi--;`, which degrades to O(n) in the worst case.

### F. Minimum in rotated sorted array

```java
int lo = 0, hi = arr.length - 1;
while (lo < hi) {
    int mid = lo + (hi - lo) / 2;
    if (arr[mid] > arr[hi]) lo = mid + 1;   // minimum is on the right
    else hi = mid;
}
return arr[lo];
```

### G. Find peak element

```java
int lo = 0, hi = arr.length - 1;
while (lo < hi) {
    int mid = lo + (hi - lo) / 2;
    if (arr[mid] > arr[mid + 1]) hi = mid;   // peak at mid or to the left
    else lo = mid + 1;                       // climb right
}
return lo;
```

### H. Binary search on answer

#### Ship packages within D days

```java
// Input: weights = [1..10], days = 5  ->  Output: 15
int lo = 0, hi = 0;
for (int w : weights) { lo = Math.max(lo, w); hi += w; }   // [max element, total]

while (lo < hi) {
    int mid = lo + (hi - lo) / 2;
    if (canShip(weights, days, mid)) hi = mid;   // feasible: try smaller
    else lo = mid + 1;                           // infeasible: need more
}
return lo;

static boolean canShip(int[] w, int days, int cap) {
    int needed = 1, load = 0;
    for (int x : w) {
        if (load + x > cap) { needed++; load = 0; }
        load += x;
    }
    return needed <= days;
}
```
⏱ O(n log(sum)) | 💾 O(1)

> 🧠 **Recognize it:** "minimize the maximum" or "maximize the minimum" → binary search on the answer.
> Same template: **Allocate Books**, **Split Array Largest Sum**, **Koko Eating Bananas**, **Aggressive Cows** (maximize minimum distance, flip the `if` direction).

---

## 7. 📈 Pattern 6: Kadane's Algorithm

### Core idea

```
currentSum = max(arr[i], currentSum + arr[i])     // start fresh or extend
```

#### Maximum subarray sum

```java
// Input: [-2,1,-3,4,-1,2,1,-5,4]  ->  Output: 6  ([4,-1,2,1])
int cur = arr[0], best = arr[0];
for (int i = 1; i < arr.length; i++) {
    cur = Math.max(arr[i], cur + arr[i]);
    best = Math.max(best, cur);
}
return best;
```
⏱ O(n) | 💾 O(1)

> ⚠️ Initialize with `arr[0]`, not `0`, so all-negative arrays return the correct (negative) answer.

#### Maximum circular subarray sum

```java
// Answer = max( normal max subarray, total - min subarray )
int total = arr[0], curMax = arr[0], maxSum = arr[0], curMin = arr[0], minSum = arr[0];
for (int i = 1; i < arr.length; i++) {
    total += arr[i];
    curMax = Math.max(arr[i], curMax + arr[i]);  maxSum = Math.max(maxSum, curMax);
    curMin = Math.min(arr[i], curMin + arr[i]);  minSum = Math.min(minSum, curMin);
}
if (maxSum < 0) return maxSum;          // all negative: wrap-around would give an empty subarray
return Math.max(maxSum, total - minSum);
```

#### Maximum product subarray (DP variant)

```java
int maxP = arr[0], minP = arr[0], best = arr[0];
for (int i = 1; i < arr.length; i++) {
    int x = arr[i];
    if (x < 0) { int t = maxP; maxP = minP; minP = t; }   // negative flips max and min
    maxP = Math.max(x, maxP * x);
    minP = Math.min(x, minP * x);
    best = Math.max(best, maxP);
}
return best;
```

---

## 8. 🔢 Pattern 7: Sorting + Two Pointers

### 3Sum

```java
// Input: [-1,0,1,2,-1,-4]  ->  Output: [[-1,-1,2],[-1,0,1]]
Arrays.sort(arr);
List<List<Integer>> res = new ArrayList<>();
for (int i = 0; i < arr.length - 2; i++) {
    if (i > 0 && arr[i] == arr[i - 1]) continue;       // skip duplicate anchors
    int l = i + 1, r = arr.length - 1;
    while (l < r) {
        int sum = arr[i] + arr[l] + arr[r];
        if (sum == 0) {
            res.add(Arrays.asList(arr[i], arr[l], arr[r]));
            while (l < r && arr[l] == arr[l + 1]) l++;  // skip duplicates
            while (l < r && arr[r] == arr[r - 1]) r--;
            l++; r--;
        } else if (sum < 0) l++;
        else r--;
    }
}
return res;
```
⏱ O(n²) | 💾 O(1) extra, excluding output and sort

> ⚠️ For 4Sum use `long sum` to avoid overflow.

### 4Sum

Sort, fix `i`, fix `j > i`, then two pointers on the rest. Skip duplicates at all four positions. ⏱ O(n³)

### Container with most water

```java
int l = 0, r = height.length - 1, best = 0;
while (l < r) {
    best = Math.max(best, Math.min(height[l], height[r]) * (r - l));
    if (height[l] < height[r]) l++;     // the shorter wall limits the area
    else r--;
}
return best;
```
⏱ O(n) | 💾 O(1)

### Trapping rain water (two pointers)

```java
// Input: [0,1,0,2,1,0,1,3,2,1,2,1]  ->  Output: 6
int l = 0, r = height.length - 1, leftMax = 0, rightMax = 0, water = 0;
while (l < r) {
    if (height[l] < height[r]) {
        if (height[l] >= leftMax) leftMax = height[l];
        else water += leftMax - height[l];
        l++;
    } else {
        if (height[r] >= rightMax) rightMax = height[r];
        else water += rightMax - height[r];
        r--;
    }
}
return water;
```
⏱ O(n) | 💾 O(1)

---

## 9. 🇳🇱 Pattern 8: Dutch National Flag

Sorts 0s, 1s and 2s in a single pass (also: three-way partition around a pivot).

```java
int low = 0, mid = 0, high = arr.length - 1;
while (mid <= high) {
    if (arr[mid] == 0) {
        swap(arr, low++, mid++);
    } else if (arr[mid] == 1) {
        mid++;
    } else {                            // arr[mid] == 2
        swap(arr, mid, high--);         // do NOT advance mid: swapped value is unchecked
    }
}

static void swap(int[] a, int i, int j) { int t = a[i]; a[i] = a[j]; a[j] = t; }
```
⏱ O(n) | 💾 O(1)

---

## 10. 💰 Pattern 9: Greedy

#### Best time to buy and sell stock (one transaction)

```java
// Input: [7,1,5,3,6,4]  ->  Output: 5
int minPrice = Integer.MAX_VALUE, best = 0;
for (int p : prices) {
    minPrice = Math.min(minPrice, p);
    best = Math.max(best, p - minPrice);
}
return best;
```

#### Best time to buy and sell stock II (unlimited transactions)

```java
int profit = 0;
for (int i = 1; i < prices.length; i++)
    if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
return profit;
```

#### Jump Game (can reach last index)

```java
// Input: [2,3,1,1,4]  ->  true
int farthest = 0;
for (int i = 0; i < arr.length; i++) {
    if (i > farthest) return false;          // unreachable position
    farthest = Math.max(farthest, i + arr[i]);
}
return true;
```

#### Jump Game II (minimum jumps)

```java
int jumps = 0, end = 0, farthest = 0;
for (int i = 0; i < arr.length - 1; i++) {
    farthest = Math.max(farthest, i + arr[i]);
    if (i == end) { jumps++; end = farthest; }   // forced to jump here
}
return jumps;
```
⏱ O(n) | 💾 O(1)

---

## 11. 📚 Pattern 10: Monotonic Stack

### When to use
- Next / previous greater or smaller element
- Daily Temperatures, Stock Span
- Largest Rectangle in Histogram

#### Next greater element

```java
// Input: [2,1,3,4]  ->  Output: [3,3,4,-1]
int n = arr.length;
int[] res = new int[n];
Deque<Integer> stack = new ArrayDeque<>();      // holds values (decreasing from bottom to top)
for (int i = n - 1; i >= 0; i--) {
    while (!stack.isEmpty() && stack.peek() <= arr[i]) stack.pop();
    res[i] = stack.isEmpty() ? -1 : stack.peek();
    stack.push(arr[i]);
}
return res;
```
⏱ O(n) | 💾 O(n)

#### Daily temperatures (store indices)

```java
int[] res = new int[n];
Deque<Integer> st = new ArrayDeque<>();         // indices
for (int i = 0; i < n; i++) {
    while (!st.isEmpty() && temps[st.peek()] < temps[i]) {
        int j = st.pop();
        res[j] = i - j;
    }
    st.push(i);
}
return res;
```

> 💡 Use `ArrayDeque`, not `Stack` (legacy and synchronized).

---

## 12. 🧱 Pattern 11: Matrix / 2D Arrays

### Traversal

```java
int m = matrix.length, n = matrix[0].length;
for (int i = 0; i < m; i++)
    for (int j = 0; j < n; j++)
        System.out.println(matrix[i][j]);
```

### Transpose (square matrix, in place)

```java
for (int i = 0; i < n; i++)
    for (int j = i + 1; j < n; j++) {        // j > i avoids swapping twice
        int t = m[i][j]; m[i][j] = m[j][i]; m[j][i] = t;
    }
```

### Rotate 90° clockwise (in place)

```java
// Step 1: transpose.  Step 2: reverse every row.
for (int i = 0; i < n; i++)
    for (int j = i + 1; j < n; j++) {
        int t = m[i][j]; m[i][j] = m[j][i]; m[j][i] = t;
    }
for (int[] row : m) {
    for (int l = 0, r = n - 1; l < r; l++, r--) {
        int t = row[l]; row[l] = row[r]; row[r] = t;
    }
}
```
⏱ O(n²) | 💾 O(1)

> 💡 Counter-clockwise: transpose, then reverse every **column** (or reverse rows first, then transpose).

### Spiral traversal

```java
List<Integer> res = new ArrayList<>();
int top = 0, bottom = m - 1, left = 0, right = n - 1;
while (top <= bottom && left <= right) {
    for (int j = left; j <= right; j++) res.add(mat[top][j]);
    top++;
    for (int i = top; i <= bottom; i++) res.add(mat[i][right]);
    right--;
    if (top <= bottom) {
        for (int j = right; j >= left; j--) res.add(mat[bottom][j]);
        bottom--;
    }
    if (left <= right) {
        for (int i = bottom; i >= top; i--) res.add(mat[i][left]);
        left++;
    }
}
return res;
```
⏱ O(m·n) | 💾 O(1) extra

> ⚠️ The two `if` guards prevent duplicate visits on single-row or single-column leftovers.

### Set matrix zeroes (O(1) space)

```java
boolean firstRowZero = false, firstColZero = false;
for (int j = 0; j < n; j++) if (mat[0][j] == 0) firstRowZero = true;
for (int i = 0; i < m; i++) if (mat[i][0] == 0) firstColZero = true;

for (int i = 1; i < m; i++)                 // mark using first row / column
    for (int j = 1; j < n; j++)
        if (mat[i][j] == 0) { mat[i][0] = 0; mat[0][j] = 0; }

for (int i = 1; i < m; i++)                 // apply marks
    for (int j = 1; j < n; j++)
        if (mat[i][0] == 0 || mat[0][j] == 0) mat[i][j] = 0;

if (firstRowZero) for (int j = 0; j < n; j++) mat[0][j] = 0;
if (firstColZero) for (int i = 0; i < m; i++) mat[i][0] = 0;
```
⏱ O(m·n) | 💾 O(1)

### Search in a row-wise and column-wise sorted matrix

```java
int i = 0, j = n - 1;                        // start at top-right
while (i < m && j >= 0) {
    if (mat[i][j] == target) return true;
    else if (mat[i][j] > target) j--;
    else i++;
}
return false;
```
⏱ O(m + n)

---

## 13. 🧮 More Must-Know Problems

### Largest and second largest (single pass)

```java
int max = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
for (int x : arr) {
    if (x > max) { second = max; max = x; }
    else if (x > second && x < max) second = x;   // x != max keeps it strictly second
}
```

### Check if array is sorted

```java
for (int i = 1; i < arr.length; i++)
    if (arr[i] < arr[i - 1]) return false;
return true;
```

### Left rotate by K (reversal algorithm)

```java
// [1,2,3,4,5,6,7], k = 3  ->  [4,5,6,7,1,2,3]
k %= n;
reverse(arr, 0, k - 1);
reverse(arr, k, n - 1);
reverse(arr, 0, n - 1);

static void reverse(int[] a, int l, int r) {
    while (l < r) { int t = a[l]; a[l++] = a[r]; a[r--] = t; }
}
```
⏱ O(n) | 💾 O(1)

> 💡 For right rotation by K, use `k = n - (k % n)` and apply the same steps.

### Missing number (0..n)

```java
int xor = arr.length;
for (int i = 0; i < arr.length; i++) xor ^= i ^ arr[i];
return xor;                      // or: n*(n+1)/2 - sum(arr), using long
```

### Maximum consecutive ones

```java
int cur = 0, best = 0;
for (int x : arr) {
    cur = (x == 1) ? cur + 1 : 0;
    best = Math.max(best, cur);
}
```

### Merge intervals

```java
// [[1,3],[2,6],[8,10],[15,18]]  ->  [[1,6],[8,10],[15,18]]
Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));   // never use a[0] - b[0]
List<int[]> res = new ArrayList<>();
for (int[] cur : intervals) {
    if (res.isEmpty() || res.get(res.size() - 1)[1] < cur[0]) {
        res.add(cur.clone());                                   // no overlap
    } else {
        int[] last = res.get(res.size() - 1);
        last[1] = Math.max(last[1], cur[1]);                    // merge
    }
}
return res.toArray(new int[0][]);
```
⏱ O(n log n) | 💾 O(n)

### Next permutation

```java
// [1,2,3] -> [1,3,2],  [3,2,1] -> [1,2,3]
int i = n - 2;
while (i >= 0 && a[i] >= a[i + 1]) i--;        // 1. find first decrease from the right
if (i >= 0) {
    int j = n - 1;
    while (a[j] <= a[i]) j--;                  // 2. smallest element greater than a[i]
    swap(a, i, j);                             // 3. swap
}
reverse(a, i + 1, n - 1);                      // 4. reverse the suffix
```
⏱ O(n) | 💾 O(1)

### Count inversions (merge sort)

```java
static long mergeSort(int[] a, int[] tmp, int lo, int hi) {
    if (lo >= hi) return 0;
    int mid = lo + (hi - lo) / 2;
    long count = mergeSort(a, tmp, lo, mid) + mergeSort(a, tmp, mid + 1, hi);
    int i = lo, j = mid + 1, k = lo;
    while (i <= mid && j <= hi) {
        if (a[i] <= a[j]) tmp[k++] = a[i++];
        else { tmp[k++] = a[j++]; count += mid - i + 1; }   // all of a[i..mid] > a[j]
    }
    while (i <= mid) tmp[k++] = a[i++];
    while (j <= hi) tmp[k++] = a[j++];
    System.arraycopy(tmp, lo, a, lo, hi - lo + 1);
    return count;
}
```
⏱ O(n log n) | 💾 O(n)

> 💡 **Reverse Pairs** uses the same structure with an extra counting loop (`a[i] > 2L * a[j]`) before merging.

### Top K frequent elements (heap)

```java
Map<Integer, Integer> freq = new HashMap<>();
for (int x : arr) freq.merge(x, 1, Integer::sum);

PriorityQueue<Map.Entry<Integer, Integer>> pq =
    new PriorityQueue<>((a, b) -> Integer.compare(a.getValue(), b.getValue()));  // min-heap of size K
for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
    pq.offer(e);
    if (pq.size() > K) pq.poll();
}
int[] res = new int[K];
for (int i = K - 1; i >= 0; i--) res[i] = pq.poll().getKey();
```
⏱ O(n log K) | 💾 O(n)

> 💡 Bucket sort by frequency gives O(n).

---

## 14. 🧬 Subarray vs Subsequence vs Subset

| Type | Meaning | Contiguous? | Keeps order? |
|---|---|---|---|
| Subarray | Continuous slice | ✅ | ✅ |
| Subsequence | Pick elements in original order | ❌ | ✅ |
| Subset | Any selection | ❌ | ❌ (order irrelevant) |

For an array of size `n`:

| Count | Formula |
|---|---|
| Non-empty subarrays | n(n + 1) / 2 |
| Subsequences (including empty) | 2ⁿ |
| Non-empty subsequences | 2ⁿ − 1 |

#### Print all subarrays (brute force)

```java
for (int i = 0; i < n; i++)
    for (int j = i; j < n; j++) {
        for (int k = i; k <= j; k++) System.out.print(arr[k] + " ");
        System.out.println();
    }
```
⏱ O(n³) (including printing)

#### Subarray sums (brute force, better)

```java
for (int i = 0; i < n; i++) {
    long sum = 0;
    for (int j = i; j < n; j++) {
        sum += arr[j];          // sum of arr[i..j]
    }
}
```
⏱ O(n²)

---

## 15. 🛠 Java Array Utilities

```java
Arrays.sort(arr);                          // primitives: dual-pivot quicksort, O(n log n) average
Arrays.sort(arr, from, to);                // sort a range [from, to)
Arrays.sort(objArr, Comparator.reverseOrder());   // needs Integer[], not int[]
Arrays.fill(arr, -1);                      // fill entire array
int[] copy  = Arrays.copyOf(arr, newLen);  // truncates or pads with zeros
int[] slice = Arrays.copyOfRange(arr, 2, 5);   // [2, 5)
int idx = Arrays.binarySearch(arr, key);   // sorted arrays only, negative if absent: -(insertionPoint) - 1
boolean same = Arrays.equals(a, b);        // content compare (1D)
boolean deep = Arrays.deepEquals(m1, m2);  // content compare (2D)
String s = Arrays.toString(arr);           // [1, 2, 3]
String s2 = Arrays.deepToString(matrix);   // [[1, 2], [3, 4]]
int[] clone = arr.clone();                 // shallow copy
System.arraycopy(src, srcPos, dest, destPos, length);   // fastest bulk copy
int[] fromList = list.stream().mapToInt(Integer::intValue).toArray();
List<Integer> asList = Arrays.stream(arr).boxed().collect(Collectors.toList());
int sum = Arrays.stream(arr).sum();
int max = Arrays.stream(arr).max().getAsInt();
```

#### Sort a 2D array

```java
Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));   // by first column
Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));      // equivalent
```

---

## 16. ⚠️ Java Pitfalls

| Pitfall | Explanation / Fix |
|---|---|
| `arr1 == arr2` | Compares references. Use `Arrays.equals`. |
| `arr1.equals(arr2)` | Also a reference compare for arrays. Use `Arrays.equals`. |
| `System.out.println(arr)` | Prints `[I@1b6d3586`. Use `Arrays.toString`. |
| `Arrays.asList(int[])` | Returns `List<int[]>` (size 1), not `List<Integer>`. Use `Integer[]` or streams. |
| `int[] b = a;` | Both reference the same array. Use `a.clone()` or `Arrays.copyOf`. |
| 2D `clone()` | Shallow: inner rows are shared. Copy row by row. |
| `Integer` comparison with `==` | Works only for -128..127 (cache). Use `.equals()` or compare after unboxing. |
| Comparator `(a, b) -> a - b` | Can overflow. Use `Integer.compare(a, b)`. |
| `mid = (low + high) / 2` | Can overflow. Use `low + (high - low) / 2`. |
| `Integer.MIN_VALUE` negation / `Math.abs` | `-Integer.MIN_VALUE == Integer.MIN_VALUE`. |
| `int * int` assigned to `long` | Multiplication happens in `int` first. Write `1L * a * b`. |
| Sorting `int[]` adversarially | Dual-pivot quicksort worst case is O(n²). Rare in practice; shuffle or use `Integer[]` if worried. |
| Modifying a list while iterating | `ConcurrentModificationException`. Use an iterator or index loop. |
| `HashMap<Integer, Integer>` default values | `map.get(k)` returns `null`; unboxing it causes `NullPointerException`. Use `getOrDefault`. |
| Using `Stack` | Legacy class. Prefer `ArrayDeque`. |

---

## 17. 🧭 Pattern Identification Table

| Problem clue | Pattern |
|---|---|
| Sorted array + pair / triplet | Two Pointers (+ sort) |
| Reverse, partition in place | Two Pointers |
| Remove duplicates, move zeroes | Slow / Fast pointers |
| Consecutive K elements | Fixed sliding window |
| Longest / shortest valid subarray (non-negative) | Variable sliding window |
| Longest substring with a constraint | Sliding window + map |
| Range sum queries | Prefix sum |
| Subarray sum = K (with negatives) | Prefix sum + HashMap |
| Subarray XOR = K | Prefix XOR + HashMap |
| Frequency counting, duplicates | HashMap / HashSet |
| Two Sum (unsorted) | HashMap |
| Search in sorted array | Binary search |
| First / last occurrence, insert position | Lower / upper bound |
| Rotated sorted array | Modified binary search |
| Peak element | Binary search on slope |
| Minimum feasible / minimize the maximum | Binary search on answer |
| Maximum subarray sum | Kadane's |
| Maximum product subarray | Kadane variant (track min and max) |
| Triplet / quadruplet sum | Sort + two pointers |
| Sort 0, 1, 2 | Dutch National Flag |
| Buy and sell stock, Jump Game | Greedy |
| Next greater / smaller, histogram | Monotonic stack |
| Overlapping intervals | Sort by start + sweep |
| Next permutation | Find pivot, swap, reverse suffix |
| Matrix rotation | Transpose + reverse |
| Spiral traversal | Boundary simulation |
| Count inversions / reverse pairs | Merge sort |
| Top K elements | Heap or bucket sort |
| Missing / duplicate in 1..n | XOR, sum formula, or index marking |

---

## 18. 🚫 Common Mistakes

1. Array index out of bounds (check `i + 1`, `i - 1`, `mid + 1` usage)
2. Forgetting empty-array and single-element cases
3. Wrong pointer movement (`left++` vs `right--`)
4. Infinite loop in sliding window (forgetting `left++`)
5. Window length: it is `right - left + 1`
6. Using sliding-window sum logic with negative numbers
7. Forgetting `map.put(0, 1)` in prefix-sum counting
8. Integer overflow in sum or product (use `long`)
9. Not skipping duplicates in 3Sum / 4Sum
10. Wrong binary search boundaries (`<` vs `<=`, `mid` vs `mid ± 1`)
11. Initializing Kadane's with `0` instead of `arr[0]`
12. Advancing `mid` after swapping with `high` in Dutch National Flag
13. Mutating the input when the problem requires preserving it
14. Confusing subarray with subsequence
15. Assuming the array is sorted without checking
16. Comparator subtraction overflow (`a - b`)
17. Forgetting that `Arrays.asList(int[])` does not box

---

## 19. 🗺 Interview Roadmap and Revision Checklist

### Level 1: Fundamentals
- [ ] Largest element
- [ ] Second largest element
- [ ] Check sorted array
- [ ] Remove duplicates
- [ ] Left rotate by K
- [ ] Move zeroes
- [ ] Missing number
- [ ] Maximum consecutive ones

### Level 2: Two Pointers
- [ ] Two Sum II
- [ ] Remove duplicates II
- [ ] Container With Most Water
- [ ] Sort Colors
- [ ] 3Sum
- [ ] 4Sum
- [ ] Trapping Rain Water

### Level 3: Sliding Window
- [ ] Maximum sum of K elements
- [ ] Maximum average subarray
- [ ] Longest subarray with sum ≤ K (non-negative)
- [ ] Minimum size subarray sum
- [ ] Longest substring without repeating characters
- [ ] Fruit Into Baskets
- [ ] Minimum Window Substring
- [ ] Sliding Window Maximum (monotonic deque)

### Level 4: Prefix Sum and Hashing
- [ ] Two Sum
- [ ] Subarray Sum Equals K
- [ ] Longest Subarray with Sum K
- [ ] Count Subarrays with XOR K
- [ ] Longest Consecutive Sequence
- [ ] Majority Element (I and II)
- [ ] Equilibrium Index
- [ ] Product of Array Except Self

### Level 5: Binary Search
- [ ] Binary Search
- [ ] First and Last Position
- [ ] Search Insert Position
- [ ] Search in Rotated Sorted Array
- [ ] Find Minimum in Rotated Sorted Array
- [ ] Find Peak Element
- [ ] Koko Eating Bananas / Ship Packages
- [ ] Allocate Books / Split Array Largest Sum
- [ ] Aggressive Cows
- [ ] Median of Two Sorted Arrays

### Level 6: Advanced Arrays
- [ ] Maximum Subarray Sum
- [ ] Maximum Product Subarray
- [ ] Next Permutation
- [ ] Merge Intervals
- [ ] Merge Sorted Arrays
- [ ] Count Inversions
- [ ] Reverse Pairs
- [ ] Maximum Circular Subarray Sum
- [ ] Spiral Matrix / Rotate Image / Set Matrix Zeroes

### Pattern revision checklist
- [ ] Two Pointers: opposite and same direction
- [ ] Fixed and variable sliding window
- [ ] Prefix sum and prefix sum + HashMap
- [ ] HashMap / HashSet
- [ ] Binary search, lower / upper bound, search on answer
- [ ] Kadane's (and variants)
- [ ] Sorting + two pointers
- [ ] Dutch National Flag
- [ ] Greedy array problems
- [ ] Monotonic stack
- [ ] Matrix traversal and rotation
- [ ] Subarray vs subsequence
- [ ] Time and space complexity for every solution

---

## 20. 🏆 Golden Rule

For every array problem, follow this order:

1. Understand the problem and constraints (size, value range, negatives, duplicates).
2. Decide whether it involves a pair, subarray, subsequence, or range.
3. Check whether the array is sorted (or can be sorted).
4. State the brute force first.
5. Identify the pattern.
6. Optimize time and space.
7. Dry run with normal and edge cases (empty, single element, all equal, all negative).
8. Implement in Java and validate, watching for overflow and off-by-one errors.

> 🎯 **Interview tip:** Always say the brute-force complexity first, then name the pattern you are switching to, and finish by stating the final time and space complexity.