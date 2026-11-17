# Kadane's Algorithm — When to Apply?

**Kadane's Algorithm is used to find the maximum sum of a contiguous subarray.**

It is an optimization technique that solves the Maximum Subarray Sum problem in **O(N) time and O(1) space**.

## 1. When should you identify Kadane's Algorithm?

Look for these keywords in a problem:

- Maximum subarray sum.
- Largest sum of a contiguous subarray.
- Maximum sum of consecutive elements.
- Find the subarray with the maximum total.
- Maximum profit from a contiguous segment (when modeled as an array of gains/losses).

**Example:**

```java
int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

// Maximum sum = 6
// Subarray = {4, -1, 2, 1}
```

## 2. Kadane's Algorithm — Core Logic

At every index, we have two choices:

1. Extend the previous subarray.
2. Start a new subarray from the current element.

**Formula:**

\[
currentSum = \max(arr[i], currentSum + arr[i])
\]

\[
maxSum = \max(maxSum, currentSum)
\]

**Java Code:**

```java
public static int maxSubArray(int[] arr) {

    int currentSum = arr[0];
    int maxSum = arr[0];

    for (int i = 1; i < arr.length; i++) {

        currentSum = Math.max(arr[i], currentSum + arr[i]);

        maxSum = Math.max(maxSum, currentSum);
    }

    return maxSum;
}
```

**Complexity:**

- Time: O(N)
- Space: O(1)

## 3. Kadane vs Prefix Sum vs Sliding Window

| Problem requirement | Pattern |
|---|---|
| Maximum contiguous subarray sum | Kadane |
| Minimum contiguous subarray sum | Modified Kadane |
| Count subarrays with sum K | Prefix + HashMap |
| Longest subarray with sum K | Prefix + HashMap |
| Range sum queries | Prefix Sum |
| Maximum sum of subarray of size K | Sliding Window |
| Maximum sum with variable window and non-negative elements | Sliding Window (depending on constraints) |
| Maximum product subarray | Modified Kadane |
| Maximum circular subarray sum | Kadane + Total Sum |

## 4. Important Kadane Variations

| Problem | Pattern |
|---|---|
| Maximum Subarray | Standard Kadane |
| Maximum Product Subarray | Modified Kadane (track min and max) |
| Maximum Circular Subarray Sum | Kadane + Minimum Subarray |
| Maximum Absolute Sum of Any Subarray | Maximum and minimum Kadane |
| Maximum Sum Rectangle in 2D Matrix | Kadane + Column/Row Compression |
| Best Time to Buy and Sell Stock | Kadane-like transformation (price differences) |

## 5. Kadane vs Prefix Sum + HashMap — Key Difference

**Example:**

```java
int[] arr = {2, -1, 3, -4, 5};
```

**Question A: Find the maximum subarray sum.**

Use Kadane.

We only need the maximum sum, not the number or length of subarrays.

**Question B: Count subarrays whose sum equals 5.**

Use Prefix Sum + HashMap.

We need to identify how many previous prefix sums satisfy:

\[
previousSum = currentSum - 5
\]

**Question C: Find the longest subarray whose sum equals 5.**

Use Prefix Sum + HashMap, storing the first occurrence of each prefix sum.

## 6. Quick Interview Identification Cheat Sheet

| If the question says... | Think of... |
|---|---|
| Maximum sum contiguous subarray | Kadane |
| Minimum sum contiguous subarray | Minimum Kadane |
| Maximum product contiguous subarray | Modified Kadane |
| Maximum sum of exactly K elements | Sliding Window |
| Count subarrays with target sum K | Prefix + HashMap |
| Longest subarray with target sum K | Prefix + HashMap |
| Maximum sum in circular array | Circular Kadane |
| Maximum sum in a 2D matrix rectangle | 2D Kadane |

## Golden Rules to Remember

- **Kadane** → Find the maximum/minimum subarray sum.
- **Prefix + HashMap** → Count or find the length of subarrays satisfying a sum condition.
- **Sliding Window** → Maintain a moving window, often with a fixed size or suitable non-negative constraints.
