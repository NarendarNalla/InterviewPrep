# Greedy Algorithms Roadmap

## 📍 Roadmap Overview

```
Level 1
│
├── Activity Selection
├── Assign Cookies
├── Lemonade Change
├── Best Time to Buy/Sell Stock II
└── Can Place Flowers
        ↓
Level 2
│
├── Fractional Knapsack
├── Maximum Units on a Truck
├── Minimum Arrows
└── Non-overlapping Intervals
        ↓
Level 3
│
├── Boats to Save People
└── Bag of Tokens
        ↓
Level 4
│
├── Merge Intervals
├── Insert Interval
└── Range Covering
        ↓
Level 5
│
├── Jump Game
├── Jump Game II
└── Gas Station
        ↓
Level 6
│
├── Meeting Rooms II
├── Connect Sticks
└── IPO
        ↓
Level 7–8
│
├── Partition Labels
├── Task Scheduler
├── Huffman Coding
├── Candy
├── Remove K Digits
├── Hand of Straights
├── Queue Reconstruction
├── Pair Chain
└── Job Sequencing
```

---

## 🟢 Level 1 — Greedy Fundamentals

### 1. Activity Selection

Given a set of activities, where each activity has a start time and finish time, select the maximum number of non-overlapping activities.

**Example**

Input:
```
activities = [[1, 3], [2, 5], [4, 6], [6, 7], [5, 9]]
```

Output:
```
3
```

### 2. Assign Cookies

You have `g` children, where `g[i]` represents the minimum cookie size required by child `i`.

You also have `s` cookies, where `s[j]` represents the size of cookie `j`.

Each child can receive at most one cookie. A child is satisfied if the cookie size is greater than or equal to their greed factor.

Return the maximum number of satisfied children.

**Example**

Input:
```
g = [1, 2, 3]
s = [1, 1]
```

Output:
```
1
```

### 3. Lemonade Change

At a lemonade stand, each lemonade costs $5.

Customers pay using $5, $10, or $20 bills.

You must provide the correct change to each customer using the bills you currently have.

Return `true` if you can provide correct change to every customer in order, otherwise return `false`.

**Example**

Input:
```
bills = [5, 5, 5, 10, 20]
```

Output:
```
true
```

### 4. Best Time to Buy and Sell Stock II

Given an array `prices` where `prices[i]` represents the price of a stock on day `i`, you may buy and sell the stock multiple times.

You may hold at most one share at a time.

Return the maximum profit you can achieve.

**Example**

Input:
```
prices = [7, 1, 5, 3, 6, 4]
```

Output:
```
7
```

### 5. Can Place Flowers

You have a flowerbed represented by an integer array where:

- `0` represents an empty position.
- `1` represents a position containing a flower.

Flowers cannot be planted in adjacent positions.

Given an integer `n`, return `true` if `n` new flowers can be planted without violating the rule.

**Example**

Input:
```
flowerbed = [1, 0, 0, 0, 1]
n = 1
```

Output:
```
true
```

---

## 🟡 Level 2 — Sorting + Greedy

### 6. Fractional Knapsack

You are given `n` items. Each item has a value and a weight.

You have a knapsack with a maximum capacity `W`.

Unlike the 0/1 Knapsack problem, you are allowed to take fractions of an item.

Return the maximum total value that can be obtained.

**Example**

Input:
```
values = [60, 100, 120]
weights = [10, 20, 30]
W = 50
```

Output:
```
240
```

### 7. Maximum Units on a Truck

You are given different types of boxes. Each box type contains:

- `numberOfBoxes`
- `unitsPerBox`

You have a truck that can carry at most `truckSize` boxes.

Return the maximum number of units that can be loaded onto the truck.

**Example**

Input:
```
boxTypes = [[1, 3], [2, 2], [3, 1]]
truckSize = 4
```

Output:
```
8
```

### 8. Minimum Number of Arrows to Burst Balloons

You are given a 2D array `points`, where `points[i] = [xStart, xEnd]` represents a balloon whose horizontal diameter spans from `xStart` to `xEnd`.

An arrow shot vertically at position `x` bursts every balloon whose range contains `x`.

Return the minimum number of arrows required to burst all balloons.

**Example**

Input:
```
points = [[10, 16], [2, 8], [1, 6], [7, 12]]
```

Output:
```
2
```

### 9. Non-overlapping Intervals

Given an array of intervals where `intervals[i] = [start, end]`, return the minimum number of intervals you need to remove to make the remaining intervals non-overlapping.

**Example**

Input:
```
intervals = [[1, 2], [2, 3], [3, 4], [1, 3]]
```

Output:
```
1
```

### 10. Minimum Platforms

Given the arrival and departure times of trains at a railway station, determine the minimum number of platforms required so that no train has to wait.

A platform cannot be used by another train until the previous train has departed.

**Example**

Input:
```
arrival   = [900, 940, 950, 1100, 1500, 1800]
departure = [910, 1200, 1120, 1130, 1900, 2000]
```

Output:
```
3
```

---

## 🟠 Level 3 — Greedy + Two Pointers

### 11. Boats to Save People

You are given an array `people`, where `people[i]` represents the weight of a person.

Each boat can carry at most two people and has a maximum weight capacity limit.

Return the minimum number of boats required to carry everyone.

**Example**

Input:
```
people = [3, 2, 2, 1]
limit = 3
```

Output:
```
3
```

### 12. Bag of Tokens

You are given an array `tokens`, where each token has a value.

You start with `power` and `0` score.

You can perform either operation:

- Spend power equal to a token's value to gain 1 score.
- Spend 1 score to gain power equal to a token's value.

Each token can be used at most once.

Return the maximum score you can achieve.

**Example**

Input:
```
tokens = [100, 200, 300, 400]
power = 200
```

Output:
```
2
```

---

## 🔵 Level 4 — Greedy + Intervals

### 13. Merge Intervals

Given an array of intervals where `intervals[i] = [start, end]`, merge all overlapping intervals and return the resulting set of non-overlapping intervals.

**Example**

Input:
```
intervals = [[1, 3], [2, 6], [8, 10], [15, 18]]
```

Output:
```
[[1, 6], [8, 10], [15, 18]]
```

### 14. Insert Interval

You are given a sorted list of non-overlapping intervals and a new interval.

Insert the new interval into the list and merge overlapping intervals if necessary.

Return the resulting list of non-overlapping intervals.

**Example**

Input:
```
intervals = [[1, 3], [6, 9]]
newInterval = [2, 5]
```

Output:
```
[[1, 5], [6, 9]]
```

### 15. Minimum Number of Intervals to Cover a Range

Given a collection of intervals and a target range `[start, end]`, select the minimum number of intervals needed to completely cover the target range.

Return the minimum number of intervals, or `-1` if the range cannot be completely covered.

**Example**

Input:
```
intervals = [[1, 4], [2, 6], [5, 8], [7, 10]]
start = 1
end = 10
```

Output:
```
3
```

---

## 🔴 Level 5 — Greedy + Reachability

### 16. Jump Game

You are given an integer array `nums`.

You start at the first index. `nums[i]` represents the maximum number of positions you can jump forward from index `i`.

Return `true` if you can reach the last index, otherwise return `false`.

**Example**

Input:
```
nums = [2, 3, 1, 1, 4]
```

Output:
```
true
```

### 17. Jump Game II

You are given an integer array `nums`.

You start at the first index. `nums[i]` represents the maximum number of positions you can jump forward from index `i`.

Return the minimum number of jumps required to reach the last index.

**Example**

Input:
```
nums = [2, 3, 1, 1, 4]
```

Output:
```
2
```

### 18. Gas Station

There are `n` gas stations arranged in a circle.

You are given two integer arrays:

- `gas[i]` — amount of gas available at station `i`.
- `cost[i]` — amount of gas required to travel from station `i` to the next station.

Return the starting gas station index from which you can travel around the entire circuit once.

If no solution exists, return `-1`.

**Example**

Input:
```
gas  = [1, 2, 3, 4, 5]
cost = [3, 4, 5, 1, 2]
```

Output:
```
3
```

---

## 🟣 Level 6 — Greedy + Priority Queue

### 19. Meeting Rooms II

Given an array of meeting time intervals, determine the minimum number of meeting rooms required so that all meetings can take place without conflicts.

**Example**

Input:
```
intervals = [[0, 30], [5, 10], [15, 20]]
```

Output:
```
2
```

### 20. Minimum Cost to Connect Sticks

You are given an array `sticks`, where `sticks[i]` represents the length of a stick.

To connect two sticks, the cost is equal to their combined length.

Continue connecting sticks until only one stick remains.

Return the minimum total cost required.

**Example**

Input:
```
sticks = [2, 4, 3]
```

Output:
```
14
```

### 21. IPO

You are given `n` projects. Each project has:

- `capital[i]` — the minimum capital required to start the project.
- `profits[i]` — the profit earned after completing the project.

You initially have `w` units of capital and can complete at most `k` projects.

After completing a project, its profit is added to your capital.

Return the maximum capital you can have after completing at most `k` projects.

**Example**

Input:
```
k = 2
w = 0
profits = [1, 2, 3]
capital = [0, 1, 1]
```

Output:
```
4
```

---

## 🟤 Level 7 — Advanced Greedy

### 22. Partition Labels

You are given a string `s`.

Partition the string into as many parts as possible so that each character appears in at most one part.

Return a list containing the size of each partition.

**Example**

Input:
```
s = "ababcbacadefegdehijhklij"
```

Output:
```
[9, 7, 8]
```

### 23. Task Scheduler

You are given a list of CPU tasks represented by characters.

The CPU must execute one task at a time.

The same task must have at least `n` intervals between two executions of the same task.

Return the minimum number of intervals required to execute all tasks.

**Example**

Input:
```
tasks = ['A', 'A', 'A', 'B', 'B', 'B']
n = 2
```

Output:
```
8
```

### 24. Huffman Coding

Given a set of characters and their frequencies, construct a Huffman Tree that produces prefix-free binary codes while minimizing the total encoded cost.

Return the minimum total cost of constructing the Huffman encoding.

**Example**

Input:
```
frequencies = [5, 9, 12, 13, 16, 45]
```

Output:
```
224
```

### 25. Candy

There are `n` children standing in a line.

Each child has a rating represented by `ratings[i]`.

You must give each child at least one candy.

If a child has a higher rating than an adjacent child, that child must receive more candies than the adjacent child.

Return the minimum total number of candies required.

**Example**

Input:
```
ratings = [1, 0, 2]
```

Output:
```
5
```

---

## ⚫ Level 8 — Interview-Level Greedy

### 26. Remove K Digits

Given a non-negative integer represented as a string `num` and an integer `k`, remove exactly `k` digits from the number so that the resulting number is the smallest possible number.

**Example**

Input:
```
num = "1432219"
k = 3
```

Output:
```
"1219"
```

### 27. Hand of Straights

You are given an integer array `hand` representing cards and an integer `groupSize`.

Return `true` if the cards can be rearranged into groups of size `groupSize`, where each group consists of consecutive cards.

**Example**

Input:
```
hand = [1, 2, 3, 6, 2, 3, 4, 7, 8]
groupSize = 3
```

Output:
```
true
```

### 28. Queue Reconstruction by Height

You are given an array of people where `people[i] = [h, k]`.

- `h` represents the person's height.
- `k` represents the number of people in front of them who have a height greater than or equal to `h`.

Reconstruct and return the queue satisfying all people's conditions.

**Example**

Input:
```
people = [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
```

Output:
```
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
```

### 29. Maximum Length of Pair Chain

You are given an array of pairs `pairs`, where `pairs[i] = [a, b]`.

A pair `[a, b]` can be followed by `[c, d]` if:

- `b < c`

Return the maximum length of a chain that can be formed.

**Example**

Input:
```
pairs = [[1, 2], [2, 3], [3, 4]]
```

Output:
```
2
```

### 30. Job Sequencing Problem

You are given a set of jobs. Each job has:

- a deadline
- a profit

Each job takes exactly one unit of time to complete.

A job earns its profit only if it is completed before or on its deadline.

Schedule the jobs to maximize total profit and return the maximum profit.

**Example**

Input:
```
jobs = [
    [2, 100],
    [1, 19],
    [2, 27],
    [1, 25],
    [3, 15]
]
```

Output:
```
142
```