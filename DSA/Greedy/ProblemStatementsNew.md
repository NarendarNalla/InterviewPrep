# Greedy Algorithms: Problem Statements & Examples
*(Corrected version — see change log at the end)*

---

## LEVEL 1 — Greedy Fundamentals

---

### 1. Activity Selection ⭐⭐⭐⭐⭐

**Problem Statement:**
Given `n` activities with their start and end times, select the **maximum number of activities** that can be performed by a single person, assuming that a person can only work on a single activity at a time. Two activities `[s1,e1]` and `[s2,e2]` overlap only if `s1 < e2` **and** `s2 < e1` (each one must start before the other ends). Activities that merely touch at a boundary (one starts exactly when the other ends) are **not** considered overlapping.

**Example 1:**
```
Input:  activities = [(1,3), (2,4), (3,5), (4,6), (5,7)]
Output: 3
Selected: [(1,3), (3,5), (5,7)]
```

**Example 2:**
```
Input:  activities = [(0,6), (1,2), (3,4), (5,7), (8,9)]
Output: 4
Selected: [(1,2), (3,4), (5,7), (8,9)]
```

**Example 3:**
```
Input:  activities = [(1,2), (2,3), (3,4), (4,5)]
Output: 4
Selected: [(1,2), (2,3), (3,4), (4,5)]
Explanation: Back-to-back activities are allowed (end == start is not overlap)
```

**Example 4:**
```
Input:  activities = [(1,10), (2,3), (4,5), (6,7)]
Output: 3
Selected: [(2,3), (4,5), (6,7)]
Explanation: Skipping the long activity (1,10) allows more selections
```

---

### 2. Assign Cookies ⭐⭐⭐⭐

**Problem Statement:**
You are a parent and want to give cookies to children. Each child `i` has a greed factor `g[i]` (minimum cookie size to satisfy them). Each cookie `j` has size `s[j]`. A child is satisfied if `s[j] >= g[i]`. Maximize the number of **satisfied children**.

**Example 1:**
```
Input:  g = [1, 2, 3],  s = [1, 1]
Output: 1
Explanation: Only one child with greed=1 can be satisfied
```

**Example 2:**
```
Input:  g = [1, 2],  s = [1, 2, 3]
Output: 2
Explanation: Both children satisfied — cookie 1→child 1, cookie 2→child 2
```

**Example 3:**
```
Input:  g = [3, 5, 2],  s = [1, 1]
Output: 0
Explanation: No cookie is large enough for any child
```

**Example 4:**
```
Input:  g = [1, 2, 3],  s = [3]
Output: 1
Explanation: Only one cookie exists, so at most one child can be satisfied (any child with g <= 3 qualifies)
```

**Example 5:**
```
Input:  g = [1, 2, 3],  s = [1, 2, 3, 4, 5]
Output: 3
Explanation: All 3 children satisfied
```

---

### 3. Lemonade Change ⭐⭐⭐⭐

**Problem Statement:**
At a lemonade stand, each lemonade costs `$5`. Customers pay with `$5`, `$10`, or `$20` bills. You start with **no change**. Return `true` if you can give every customer correct change, `false` otherwise.

**Example 1:**
```
Input:  bills = [5, 5, 5, 10, 20]
Output: true
Explanation:
  Customer 1 pays $5  → no change needed. [five=1, ten=0]
  Customer 2 pays $5  → no change needed. [five=2, ten=0]
  Customer 3 pays $5  → no change needed. [five=3, ten=0]
  Customer 4 pays $10 → give $5 change.   [five=2, ten=1]
  Customer 5 pays $20 → give $10+$5.      [five=1, ten=0]
```

**Example 2:**
```
Input:  bills = [5, 5, 10, 10, 20]
Output: false
Explanation:
  After 2x$5 and 2x$10, we have [five=0, ten=2]
  Customer pays $20 → need $15 change but no $5 left → FAIL
```

**Example 3:**
```
Input:  bills = [5, 5, 5, 5, 20, 20, 5, 5, 5, 20]
Output: false
```

**Example 4:**
```
Input:  bills = [5, 5, 10]
Output: true
```

---

### 4. Best Time to Buy and Sell Stock II ⭐⭐⭐⭐

**Problem Statement:**
Given an array `prices` where `prices[i]` is the price of a stock on day `i`, you may buy and sell on **multiple occasions** but can only hold **one stock at a time**. Find the **maximum profit** you can achieve.

**Example 1:**
```
Input:  prices = [7, 1, 5, 3, 6, 4]
Output: 7
Explanation:
  Buy on day 2 (price=1), sell on day 3 (price=5) → profit = 4
  Buy on day 4 (price=3), sell on day 5 (price=6) → profit = 3
  Total = 7
```

**Example 2:**
```
Input:  prices = [1, 2, 3, 4, 5]
Output: 4
Explanation:
  Buy on day 1 (price=1), sell on day 5 (price=5) → profit = 4
  OR capture every consecutive gain: (2-1)+(3-2)+(4-3)+(5-4) = 4
```

**Example 3:**
```
Input:  prices = [7, 6, 4, 3, 1]
Output: 0
Explanation: Prices only decrease — no profitable transaction possible
```

**Example 4:** *(corrected — see change log)*
```
Input:  prices = [1, 7, 2, 3, 6, 7, 6, 1]
Output: 11
Explanation:
  Buy at 1, sell at 7 → +6
  Buy at 2, sell at 7 → +5
  (skipping the dip from 7→6 at the end)
  Total = 11
```

---

### 5. Can Place Flowers ⭐⭐⭐

**Problem Statement:**
You have a flowerbed array of `0s` and `1s` where `0` means empty and `1` means planted. Flowers **cannot be planted in adjacent plots**. Given `n` new flowers to plant, return `true` if `n` flowers can be planted without violating the no-adjacent rule.

**Example 1:**
```
Input:  flowerbed = [1,0,0,0,1],  n = 1
Output: true
Explanation: Plant at index 2 → [1,0,1,0,1]
```

**Example 2:**
```
Input:  flowerbed = [1,0,0,0,1],  n = 2
Output: false
Explanation: Only 1 spot available between the two 1s
```

**Example 3:**
```
Input:  flowerbed = [0,0,0,0,0],  n = 3
Output: true
Explanation: Plant at index 0, 2, 4 → [1,0,1,0,1]
```

**Example 4:**
```
Input:  flowerbed = [0,0,1,0,0],  n = 2
Output: false
Explanation: Only indices 0 and 4 are valid → can plant only 1 each side
```

---
---

## LEVEL 2 — Sorting + Greedy

---

### 1. Fractional Knapsack ⭐⭐⭐⭐⭐

**Problem Statement:**
Given `n` items each with a `weight` and `value`, and a knapsack with capacity `W`, maximize the total value. **You can take fractions of an item.**

**Example 1:**
```
Input:
  items  = [(value=60, weight=10), (value=100, weight=20), (value=120, weight=30)]
  W = 50

Output: 240.0
Explanation:
  Take all of item1 (10kg) → 60
  Take all of item2 (20kg) → 100
  Take 2/3 of item3 (20kg) → 80
  Total = 240
```

**Example 2:**
```
Input:
  items = [(value=500, weight=30)]
  W = 10

Output: 166.67
Explanation: Take 1/3 of item → (10/30)*500 = 166.67
```

**Example 3:** *(corrected — see change log)*
```
Input:
  items = [(10,2), (5,3), (15,5), (7,7), (6,1), (18,4), (3,1)]
  W = 15

Output: 55.33
Explanation:
  Ratios (value/weight): (6,1)→6, (10,2)→5, (18,4)→4.5, (15,5)→3, (3,1)→3, (5,3)→1.67, (7,7)→1
  Take (6,1) fully → 6, take (10,2) fully → 10, take (18,4) fully → 18,
  take (15,5) fully → 15, take (3,1) fully → 3   [13kg used, value = 52]
  2kg capacity remains → take 2/3 of (5,3) → +3.33
  Total = 55.33
```

---

### 2. Maximum Units on a Truck ⭐⭐⭐⭐

**Problem Statement:**
You have a truck with room for `truckSize` boxes. You are given `boxTypes[i] = [numberOfBoxes, numberOfUnitsPerBox]`, telling you how many boxes of type `i` are available and how many units each one holds. You may load any combination of boxes across the given types — up to the number available of each — as long as the **total number of boxes loaded** does not exceed `truckSize`. Maximize the **total number of units** on the truck.

**Example 1:**
```
Input:
  boxTypes = [[1,3],[2,2],[3,1]],  truckSize = 4

Output: 8
Explanation:
  Take 1 box of type 1 → 1×3 = 3 units
  Take 2 boxes of type 2 → 2×2 = 4 units
  Take 1 box of type 3 → 1×1 = 1 unit
  Total = 8
```

**Example 2:**
```
Input:
  boxTypes = [[5,10],[2,5],[4,7],[3,9]],  truckSize = 10

Output: 91
Explanation:
  Take 5 boxes (10 units each) → 50
  Take 3 boxes (9 units each) → 27
  Take 2 boxes (7 units each) → 14
  Total = 91
```

**Example 3:**
```
Input:
  boxTypes = [[1,1]],  truckSize = 3

Output: 1
Explanation: Only 1 box available
```

---

### 3. Minimum Arrows to Burst Balloons ⭐⭐⭐⭐⭐

**Problem Statement:**
Balloons are represented as `[x_start, x_end]` on a horizontal axis. An arrow shot at position `x` bursts all balloons where `x_start <= x <= x_end`. Find the **minimum number of arrows** to burst all balloons.

**Example 1:**
```
Input:  points = [[10,16],[2,8],[1,6],[7,12]]
Output: 2
Explanation:
  Arrow 1 at x=6 bursts [1,6] and [2,8]
  Arrow 2 at x=11 bursts [7,12] and [10,16]
```

**Example 2:**
```
Input:  points = [[1,2],[3,4],[5,6],[7,8]]
Output: 4
Explanation: No balloons overlap — need 4 separate arrows
```

**Example 3:**
```
Input:  points = [[1,2],[2,3],[3,4],[4,5]]
Output: 2
Explanation:
  Arrow at x=2 bursts [1,2] and [2,3]
  Arrow at x=4 bursts [3,4] and [4,5]
```

**Example 4:** *(corrected — see change log)*
```
Input:  points = [[1,10],[2,3],[4,5],[6,7]]
Output: 3
Explanation:
  A single arrow can't burst all four: [2,3] and [4,5] don't share any point, so no
  x exists that lies inside both. [1,10] contains everything, but that alone doesn't
  let one arrow hit balloons that don't overlap each other.
  Arrow 1 at x=3 bursts [1,10] and [2,3]
  Arrow 2 at x=5 bursts [4,5]
  Arrow 3 at x=7 bursts [6,7]
```

---

### 4. Non-overlapping Intervals ⭐⭐⭐⭐⭐

**Problem Statement:**
Given an array of intervals, find the **minimum number of intervals to remove** so that the remaining intervals are non-overlapping. As with Activity Selection, two intervals are only considered overlapping if one strictly starts before the other ends — intervals that merely touch at an endpoint are not overlapping.

**Example 1:**
```
Input:  intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: Remove [1,3] → remaining are non-overlapping
```

**Example 2:**
```
Input:  intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: Remove 2 duplicates — keep one [1,2]
```

**Example 3:**
```
Input:  intervals = [[1,2],[2,3]]
Output: 0
Explanation: Already non-overlapping
```

**Example 4:** *(corrected — see change log)*
```
Input:  intervals = [[1,100],[11,22],[1,11],[2,12]]
Output: 2
Explanation: Remove [1,100] and [2,12] — keep [1,11],[11,22] (touching at 11 is allowed)
```

---
---

## LEVEL 3 — Greedy + Two Pointers

---

### 1. Boats to Save People ⭐⭐⭐⭐⭐

**Problem Statement:**
Each boat carries **at most 2 people**, and the combined weight of everyone in a boat cannot exceed `limit`. Given array `people[i]` as the weight of person `i`, return the **minimum number of boats** needed to carry everyone.

**Example 1:**
```
Input:  people = [1,2],  limit = 3
Output: 1
Explanation: Both fit in one boat (1+2=3)
```

**Example 2:**
```
Input:  people = [3,2,2,1],  limit = 3
Output: 3
Explanation:
  Boat 1: [1,2]
  Boat 2: [2]
  Boat 3: [3]
```

**Example 3:**
```
Input:  people = [3,5,3,4],  limit = 5
Output: 4
Explanation: Each person needs their own boat
```

**Example 4:**
```
Input:  people = [1,1,1,1],  limit = 3
Output: 2
Explanation: Pair them up → 2 boats
```

---

### 2. Bag of Tokens ⭐⭐⭐⭐

**Problem Statement:**
You have `power` and `tokens[i]`. You can:
- **Face up**: Spend `tokens[i]` power → gain 1 score (only if power >= tokens[i])
- **Face down**: Spend 1 score → gain `tokens[i]` power (only if score >= 1)

Return the **maximum score** you can achieve.

**Example 1:**
```
Input:  tokens = [100],  power = 50
Output: 0
Explanation: Can't afford the only token
```

**Example 2:**
```
Input:  tokens = [100,200],  power = 150
Output: 1
Explanation: Play 100 face-up → score=1, power=50. Can't play 200.
```

**Example 3:**
```
Input:  tokens = [100,200,300,400],  power = 200
Output: 2
Explanation:
  Play 100 face-up → score=1, power=100
  Play 400 face-down → score=0, power=500
  Play 200 face-up → score=1, power=300
  Play 300 face-up → score=2, power=0
```

**Example 4:**
```
Input:  tokens = [71,55,82],  power = 54
Output: 0
```

---
---

## LEVEL 4 — Interval Problems

---

### 1. Merge Intervals ⭐⭐⭐⭐⭐

**Problem Statement:**
Given an array of intervals, **merge all overlapping or touching intervals** and return the resulting array. Two intervals `[s1,e1]` and `[s2,e2]` (sorted by start) should be merged if `s2 <= e1` — this includes intervals that only touch at a shared endpoint (see Example 2, where `[1,4]` and `[4,5]` merge into `[1,5]`). This is a slightly looser rule than in Activity Selection / Non-overlapping Intervals, where touching intervals are allowed to coexist without merging.

**Example 1:**
```
Input:  intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: [1,3] and [2,6] overlap → merge to [1,6]
```

**Example 2:**
```
Input:  intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Touching intervals merge
```

**Example 3:**
```
Input:  intervals = [[1,4],[0,4]]
Output: [[0,4]]
```

**Example 4:**
```
Input:  intervals = [[1,4],[0,2],[3,5]]
Output: [[0,5]]
Explanation: All three merge into one
```

---

### 2. Insert Interval ⭐⭐⭐⭐⭐

**Problem Statement:**
Given a list of **non-overlapping intervals sorted by start time**, and a `newInterval`, insert `newInterval` at the correct position, merging with any intervals it overlaps, so the result stays sorted and non-overlapping.

**Example 1:**
```
Input:  intervals = [[1,3],[6,9]],  newInterval = [2,5]
Output: [[1,5],[6,9]]
```

**Example 2:**
```
Input:  intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]],  newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: New interval overlaps [3,5],[6,7],[8,10] → merge all
```

**Example 3:**
```
Input:  intervals = [],  newInterval = [5,7]
Output: [[5,7]]
```

**Example 4:**
```
Input:  intervals = [[1,5]],  newInterval = [2,3]
Output: [[1,5]]
Explanation: New interval is completely inside existing one
```

---

### 3. Meeting Rooms ⭐⭐⭐⭐

**Problem Statement:**
Given an array of meeting time intervals `[start, end]`, determine if a person can **attend all meetings** (i.e., no two meetings overlap).

**Example 1:**
```
Input:  intervals = [[0,30],[5,10],[15,20]]
Output: false
Explanation: [0,30] overlaps with both other meetings
```

**Example 2:**
```
Input:  intervals = [[7,10],[2,4]]
Output: true
Explanation: No overlap — person can attend both
```

**Example 3:**
```
Input:  intervals = [[1,5],[5,10],[10,15]]
Output: true
Explanation: Back-to-back is allowed
```

**Example 4:**
```
Input:  intervals = [[1,10],[2,3],[3,4]]
Output: false
```

---

### 4. Range Covering / Minimum Taps ⭐⭐⭐⭐⭐

**Problem Statement:**
You have a garden of length `n`. There are `n+1` taps at positions `0` to `n`. `ranges[i]` means tap `i` can water `[i-ranges[i], i+ranges[i]]`. Find the **minimum number of taps** to water the whole garden `[0,n]`. Return `-1` if impossible.

**Example 1:**
```
Input:  n = 5,  ranges = [3,4,1,1,0,0]
Output: 1
Explanation: Tap 1 covers [1-4, 1+4] = [-3,5] → covers [0,5]
```

**Example 2:**
```
Input:  n = 3,  ranges = [0,0,0,0]
Output: -1
Explanation: No tap has any range
```

**Example 3:**
```
Input:  n = 7,  ranges = [1,2,1,0,2,1,0,1]
Output: 3
```

**Example 4:** *(corrected — see change log)*
```
Input:  n = 4,  ranges = [1,0,2,0,1]
Output: 1
Explanation: Tap 2 alone covers [2-2, 2+2] = [0,4], which is the entire garden — no other tap needed
```

---
---

## LEVEL 5 — Reachability / Running Greedy

---

### 1. Jump Game ⭐⭐⭐⭐⭐

**Problem Statement:**
Given an array `nums` where `nums[i]` is the maximum jump length from index `i`, return `true` if you can reach the **last index**, starting from index `0`.

**Example 1:**
```
Input:  nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from 0→1, then 3 steps to last index
```

**Example 2:**
```
Input:  nums = [3,2,1,0,4]
Output: false
Explanation: Always land on index 3 (value 0) — can never move forward
```

**Example 3:**
```
Input:  nums = [1,0,1,0]
Output: false
```

**Example 4:**
```
Input:  nums = [0]
Output: true
Explanation: Already at the last index
```

**Example 5:**
```
Input:  nums = [2,0,0]
Output: true
Explanation: Jump 2 from index 0 → reach index 2 (last)
```

---

### 2. Jump Game II ⭐⭐⭐⭐⭐

**Problem Statement:**
Same setup as Jump Game. You are **guaranteed** to reach the last index. Return the **minimum number of jumps** to reach the last index.

**Example 1:**
```
Input:  nums = [2,3,1,1,4]
Output: 2
Explanation: Jump from 0→1 (jump 1), then 1→4 (jump 2)
```

**Example 2:**
```
Input:  nums = [2,3,0,1,4]
Output: 2
Explanation: 0→1→4
```

**Example 3:**
```
Input:  nums = [1,2,3]
Output: 2
Explanation: 0→1→2
```

**Example 4:**
```
Input:  nums = [1,1,1,1]
Output: 3
Explanation: Must jump every single step
```

---

### 3. Gas Station ⭐⭐⭐⭐⭐

**Problem Statement:**
There are `n` gas stations in a circle. `gas[i]` is the fuel available at station `i`. `cost[i]` is the fuel to travel from station `i` to `i+1`. Find the **starting station index** from which you can complete the full circle. Return `-1` if impossible. The solution is guaranteed to be unique.

**Example 1:**
```
Input:  gas  = [1,2,3,4,5]
        cost = [3,4,5,1,2]
Output: 3
Explanation:
  Start at station 3:
  3→4: gas=4, cost=1, tank=3
  4→0: gas=5, cost=2, tank=6
  0→1: gas=1, cost=3, tank=4
  1→2: gas=2, cost=4, tank=2
  2→3: gas=3, cost=5, tank=0 ✓
```

**Example 2:**
```
Input:  gas  = [2,3,4]
        cost = [3,4,3]
Output: -1
Explanation: Total gas=9 < total cost=10 → impossible
```

**Example 3:**
```
Input:  gas  = [5,1,2,3,4]
        cost = [4,4,1,5,1]
Output: 4
```

**Example 4:**
```
Input:  gas  = [1,2,3,4,5]
        cost = [1,2,3,4,5]
Output: 0
Explanation: Net gain at each station is 0 — any start works, answer = 0
```

---
---

## LEVEL 6 — Greedy + Heap

---

### 1. Meeting Rooms II ⭐⭐⭐⭐⭐

**Problem Statement:**
Given meeting time intervals, find the **minimum number of conference rooms** required to hold all meetings simultaneously.

**Example 1:**
```
Input:  intervals = [[0,30],[5,10],[15,20]]
Output: 2
Explanation:
  Room 1: [0,30]
  Room 2: [5,10], then [15,20]
```

**Example 2:**
```
Input:  intervals = [[7,10],[2,4]]
Output: 1
Explanation: Sequential — 1 room suffices
```

**Example 3:**
```
Input:  intervals = [[1,5],[2,6],[3,7]]
Output: 3
Explanation: All 3 overlap → 3 rooms needed
```

**Example 4:**
```
Input:  intervals = [[1,4],[2,5],[7,9],[3,6]]
Output: 3
```

---

### 2. Connect Sticks / Connect Ropes ⭐⭐⭐⭐⭐

**Problem Statement:**
You have `n` sticks. The cost to connect two sticks is equal to their **sum of lengths**. Connect all sticks into one. Return the **minimum total cost**.

**Example 1:**
```
Input:  sticks = [2,4,3]
Output: 14
Explanation:
  Connect 2+3=5 (cost=5)
  Connect 5+4=9 (cost=9)
  Total = 14
```

**Example 2:**
```
Input:  sticks = [1,8,3,5]
Output: 30
Explanation:
  Connect 1+3=4 (cost=4)
  Connect 4+5=9 (cost=9)
  Connect 9+8=17 (cost=17)
  Total = 30
```

**Example 3:**
```
Input:  sticks = [5]
Output: 0
Explanation: Only one stick — no connection needed
```

**Example 4:**
```
Input:  sticks = [1,2,3,4,5]
Output: 33
```

---

### 3. IPO ⭐⭐⭐⭐⭐

**Problem Statement:**
You can do at most `k` projects. Each project has `profits[i]` and `capital[i]` (minimum capital required). You start with `w` capital. After each project, your capital increases by its profit. Maximize **final capital**.

**Example 1:**
```
Input:  k=2,  w=0,  profits=[1,2,3],  capital=[0,1,1]
Output: 4
Explanation:
  Start: w=0 → can do project 0 (profit=1) → w=1
  Now: w=1 → do project 2 (profit=3) → w=4
```

**Example 2:**
```
Input:  k=3,  w=0,  profits=[1,2,3],  capital=[0,1,2]
Output: 6
Explanation: Do all three in order
```

**Example 3:**
```
Input:  k=1,  w=0,  profits=[1,2,3],  capital=[1,1,2]
Output: 0
Explanation: No project affordable with w=0
```

---

### 4. Furthest Building You Can Reach ⭐⭐⭐⭐⭐

**Problem Statement:**
Starting at building index `0`, you want to travel as far as possible along a row of buildings with the given `heights[]`, using a limited supply of `bricks` and `ladders` to climb upward height differences. Moving from building `i` to `i+1`:
- If `heights[i+1] <= heights[i]` → free
- Otherwise → use `(heights[i+1] - heights[i])` bricks **OR** 1 ladder

Return the **furthest building index** you can reach.

**Example 1:**
```
Input:  heights=[4,2,7,6,9,14,12],  bricks=5,  ladders=1
Output: 4
Explanation:
  4→2: free
  2→7: use ladder (diff=5)
  7→6: free
  6→9: use 3 bricks
  9→14: need 5 bricks (only 2 left) → STOP at index 4
```

**Example 2:**
```
Input:  heights=[4,12,2,7,3,18,20,3,19],  bricks=10,  ladders=2
Output: 7
```

**Example 3:**
```
Input:  heights=[14,3,19,3],  bricks=17,  ladders=0
Output: 3
Explanation: Enough bricks for all climbs
```

---
---

## LEVEL 7 — Scheduling + Frequency

---

### 1. Task Scheduler ⭐⭐⭐⭐⭐

**Problem Statement:**
Given a list of CPU tasks (each a letter A–Z) and a cooldown `n`, two occurrences of the **same** task must have at least `n` other time units (filled by other tasks or idle slots) between them. The CPU may sit idle in a unit of time if no eligible task is available. Return the **minimum number of time units** needed to finish all tasks.

**Example 1:**
```
Input:  tasks = ['A','A','A','B','B','B'],  n = 2
Output: 8
Explanation: A→B→idle→A→B→idle→A→B
```

**Example 2:**
```
Input:  tasks = ['A','A','A','B','B','B'],  n = 0
Output: 6
Explanation: No cooldown needed
```

**Example 3:**
```
Input:  tasks = ['A','A','A','A','A','A','B','C','D','E','F','G'],  n = 2
Output: 16
Explanation: A appears 6 times → dominates the schedule
```

**Example 4:**
```
Input:  tasks = ['A','B','C','D','E','A'],  n = 1
Output: 6
```

---

### 2. Job Sequencing with Deadlines ⭐⭐⭐⭐⭐

**Problem Statement:**
Given jobs with `id`, `deadline`, and `profit`, each job takes **1 unit of time**. Only 1 job can be scheduled at a time. Maximize **total profit** by scheduling jobs before their deadline.

**Example 1:**
```
Input:
  jobs = [(J1, deadline=2, profit=100),
          (J2, deadline=1, profit=19),
          (J3, deadline=2, profit=27),
          (J4, deadline=1, profit=25),
          (J5, deadline=3, profit=15)]

Output: profit=142, jobs=['J1','J3','J5']
```

**Example 2:** *(corrected — see change log)*
```
Input:
  jobs = [(A, d=2, p=60), (B, d=1, p=100), (C, d=3, p=20), (D, d=2, p=40)]

Output: profit=180, jobs=['B','A','C']
Explanation:
  Sort by profit desc: B(100,d1), A(60,d2), D(40,d2), C(20,d3)
  Slot1 → B, Slot2 → A. D needs slot ≤2 but both slot1 and slot2 are taken, so D is dropped.
  Slot3 → C.
  Total = 100+60+20 = 180
```

**Example 3:**
```
Input:
  jobs = [(A, d=1, p=5), (B, d=1, p=10)]

Output: profit=10, jobs=['B']
Explanation: Only 1 time slot available — pick highest profit
```

---

### 3. Hand of Straights ⭐⭐⭐⭐

**Problem Statement:**
Alice has a `hand` of cards, where each card is an integer. She wants to rearrange **all** of the cards into groups, each of size `groupSize`, where each group consists of `groupSize` consecutive integers (e.g., `[3,4,5]`). Every card must be used in exactly one group. Return `true` if this is possible.

**Example 1:**
```
Input:  hand = [1,2,3,6,2,3,4,7,8],  groupSize = 3
Output: true
Explanation: Groups: [1,2,3], [2,3,4], [6,7,8]
```

**Example 2:**
```
Input:  hand = [1,2,3,4,5],  groupSize = 4
Output: false
Explanation: 5 cards can't be evenly grouped into size-4 groups
```

**Example 3:**
```
Input:  hand = [1,2,3],  groupSize = 3
Output: true
```

**Example 4:**
```
Input:  hand = [1,1,2,2,3,3],  groupSize = 3
Output: true
Explanation: Groups: [1,2,3], [1,2,3]
```

---

### 4. Partition Labels ⭐⭐⭐⭐⭐

**Problem Statement:**
Given string `s`, partition it into as **many parts as possible** such that each letter appears in **at most one part**. Return a list of partition sizes.

**Example 1:**
```
Input:  s = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
  Part 1: "ababcbaca" (a,b,c all last appear within first 9)
  Part 2: "defegde"   (d,e,f,g all contained here)
  Part 3: "hijhklij"  (h,i,j,k,l contained here)
```

**Example 2:**
```
Input:  s = "eccbbbbdec"
Output: [10]
Explanation: All characters span the entire string
```

**Example 3:**
```
Input:  s = "abcd"
Output: [1,1,1,1]
Explanation: Each character appears only once — 4 single-char partitions
```

**Example 4:**
```
Input:  s = "aabbcc"
Output: [2,2,2]
Explanation: "aa","bb","cc" — each letter confined to its own partition
```

---
---

## LEVEL 8 — Advanced Greedy

---

### 1. Huffman Coding ⭐⭐⭐⭐⭐

**Problem Statement:**
Given characters and their frequencies, build a **Huffman tree** and assign binary codes to minimize total encoded length. Return the codes for each character.

**Example 1:**
```
Input:  chars = ['a','b','c','d','e','f']
        freq  = [ 5,  9, 12, 13, 16, 45]

Output:
  f → 0
  c → 100
  d → 101
  a → 1100
  b → 1101
  e → 111

Total bits = 5*4 + 9*4 + 12*3 + 13*3 + 16*3 + 45*1 = 224
```

**Example 2:**
```
Input:  chars = ['a','b']
        freq  = [1, 1]

Output:
  a → 0
  b → 1
```

**Example 3:**
```
Input:  chars = ['x','y','z']
        freq  = [10, 20, 30]

Output:
  z → 0
  y → 10
  x → 11
```

---

### 2. Candy ⭐⭐⭐⭐⭐

**Problem Statement:**
Given an array `ratings` where `ratings[i]` is the rating of the `i`-th child standing in a line, distribute candies to the children subject to these rules:
1. Every child must get **at least 1 candy**
2. Children with a **higher rating than neighbors** must get **more candies**

Return the **minimum total candies**.

**Example 1:**
```
Input:  ratings = [1,0,2]
Output: 5
Explanation: [2,1,2] candies
```

**Example 2:**
```
Input:  ratings = [1,2,2]
Output: 4
Explanation: [1,2,1] candies — equal ratings don't need more
```

**Example 3:**
```
Input:  ratings = [1,3,2,2,1]
Output: 7
Explanation: [1,2,1,2,1] candies
```

**Example 4:**
```
Input:  ratings = [1,2,3,4,5]
Output: 15
Explanation: [1,2,3,4,5] strictly increasing
```

---

### 3. Remove K Digits ⭐⭐⭐⭐⭐

**Problem Statement:**
Given a non-negative integer `num` as a string and integer `k`, remove `k` digits to make the **smallest possible number**. No leading zeros (except "0" itself).

**Example 1:**
```
Input:  num = "1432219",  k = 3
Output: "1219"
Explanation: Remove 4, 3, 2 → smallest result
```

**Example 2:**
```
Input:  num = "10200",  k = 1
Output: "200"
Explanation: Remove 1 → "0200" → strip leading zero → "200"
```

**Example 3:**
```
Input:  num = "10",  k = 2
Output: "0"
Explanation: Remove both digits → result is "0"
```

**Example 4:**
```
Input:  num = "9",  k = 1
Output: "0"
```

---

### 4. Queue Reconstruction by Height ⭐⭐⭐⭐⭐

**Problem Statement:**
People stand in a queue. `people[i] = [h, k]` where `h` is height and `k` is the number of people **in front** with height >= h. Reconstruct and return the queue.

**Example 1:**
```
Input:  people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
Output:           [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
```

**Example 2:**
```
Input:  people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
Output:           [[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
```

**Example 3:** *(corrected — see change log)*
```
Input:  people = [[7,0],[4,1]]
Output:           [[7,0],[4,1]]
```

---

### 5. Maximum Length of Pair Chain ⭐⭐⭐⭐

**Problem Statement:**
Given an array of pairs where each `pairs[i] = [a,b]` satisfies `a < b`, a pair `[c,d]` can follow a pair `[a,b]` in a chain if `b < c`. You may select any subset of the given pairs (not necessarily all of them, in any order) to form a chain. Return the length of the **longest chain** possible.

**Example 1:**
```
Input:  pairs = [[1,2],[2,3],[3,4]]
Output: 2
Explanation: [1,2] → [3,4] (length 2)
```

**Example 2:**
```
Input:  pairs = [[1,2],[7,8],[4,5]]
Output: 3
Explanation: All 3 can be chained: [1,2]→[4,5]→[7,8]
```

**Example 3:**
```
Input:  pairs = [[-6,9],[1,6],[8,10],[-1,4],[-6,-2],[-9,8],[-5,3],[0,3]]
Output: 3
```

---

### 6. Reorganize String ⭐⭐⭐⭐⭐

**Problem Statement:**
Given string `s`, rearrange so that **no two adjacent characters are the same**. Return any valid rearrangement, or `""` if impossible.

**Example 1:**
```
Input:  s = "aab"
Output: "aba"
```

**Example 2:**
```
Input:  s = "aaab"
Output: ""
Explanation: 'a' appears 3 times — impossible to separate
```

**Example 3:**
```
Input:  s = "vvvlo"
Output: "vlvov"  (or any valid arrangement)
```

**Example 4:**
```
Input:  s = "aaabbc"
Output: "ababac"  (or any valid)
```

---
---

## LEVEL 9 — Greedy vs DP / Recognition

---

### 1. Coin Change — Why Greedy Fails

**Problem Statement:**
Given coins of certain denominations (unlimited supply of each) and a target amount, find the **minimum number of coins** needed to make that amount.

**Example where greedy fails:**
```
Input:  coins = [1, 3, 4],  amount = 6

Greedy picks largest first:
  4 → remaining 2 → 1 → 1 = 3 coins

DP optimal:
  3 + 3 = 2 coins ✓

Greedy is WRONG here
```

**Example where greedy also fails:**
```
Input:  coins = [1, 5, 6, 9],  amount = 11

Greedy: 9 → remaining 2 → 1+1 = 3 coins
DP:     5+6 = 2 coins ✓
```

---

### 2. 0/1 Knapsack — Why Greedy Fails

**Problem Statement:**
Given items each with a weight and value, and a knapsack of capacity `W`, choose a subset of items — each item taken **whole or not at all, no fractions** — to maximize total value without exceeding capacity `W`.

**Example where greedy fails:**
```
Input:
  items = [(value=60, weight=10), (value=100, weight=20), (value=120, weight=30)]
  W = 50

Greedy (by value/weight ratio):
  ratio: 6, 5, 4
  Take item1 (10kg,60val) + item2 (20kg,100val) → 30kg used, 20kg left
  item3 (30kg,120val) doesn't fit in remaining 20kg (no fractions allowed) → skip
  Greedy total = 160

DP optimal:
  item2 + item3 = 100+120 = 220 ✓ (exactly fills 50kg)

Greedy gives 160, DP gives 220
```

---

### 3. Fractional Knapsack — Why Greedy Works

```
Same items, same W=50, but fractions allowed:

Greedy by ratio (6,5,4):
  Take all item1 (10kg) → 60
  Take all item2 (20kg) → 100
  Take 2/3 of item3 (20kg) → 80
  Total = 240 ✓ — This IS optimal
```

---

### 4. Jump Game — Why Greedy Works

```
Input: nums = [2,3,1,1,4]

Greedy tracks max reachable index:
  i=0: max_reach = max(0, 0+2) = 2
  i=1: max_reach = max(2, 1+3) = 4 ✓ reached end

Greedy works because: reaching farther is always at least as good.
No future penalty for going as far as possible.
```

---

### 5. House Robber — Why Greedy Is Not Enough

**Problem Statement:**
Given an array of money amounts held in houses arranged in a line, maximize the total amount you can steal without robbing two adjacent houses.

**Example where greedy fails:**
```
Input:  nums = [2, 1, 1, 2]

Greedy picks largest first:
  Pick index 0 (value=2) → skip 1 → pick index 2 (value=1) = 3

DP optimal:
  Pick index 0 + index 3 = 2+2 = 4 ✓

Greedy gives 3, DP gives 4
```

**Why greedy fails:**
Local best choice (pick the largest available) does not guarantee global optimum because the **constraint is positional**, not value-based.

---
---

## LEVEL 10 — Interview Revision Cheat Sheet

---

### Sorting + Greedy
```
Pattern : Sort by some criterion, then scan once
Examples: Activity Selection, Non-overlapping Intervals,
          Minimum Arrows, Assign Cookies
Key Idea: Sorting creates the optimal order to make greedy choices
```

### Interval Greedy
```
Pattern : Sort by end time (or start time), merge/count overlaps
Examples: Merge Intervals, Insert Interval,
          Meeting Rooms, Minimum Taps
Key Idea: End-time sorting minimizes blocking of future intervals
```

### Two Pointer Greedy
```
Pattern : Sort array, use left+right pointers moving inward
Examples: Boats to Save People, Bag of Tokens
Key Idea: Pair extremes optimally — try to match biggest with smallest
```

### Heap + Greedy
```
Pattern : Use min/max heap to always pick the best available option
Examples: Meeting Rooms II, Connect Sticks, IPO,
          Furthest Building
Key Idea: Heap gives O(log n) access to current best choice
```

### Scheduling Greedy
```
Pattern : Sort by deadline/frequency, fill time slots greedily
Examples: Task Scheduler, Job Sequencing with Deadlines
Key Idea: Always schedule the most constrained/frequent task first
```

### Range Greedy
```
Pattern : Track coverage; always extend as far right as possible
Examples: Jump Game, Jump Game II, Minimum Taps,
          Gas Station
Key Idea: Maximize reach at each step — never look back
```

### Stack + Greedy
```
Pattern : Use monotonic stack to maintain increasing/decreasing order
Examples: Remove K Digits, Largest Rectangle in Histogram
Key Idea: Pop stack when a better (smaller/larger) element found
```

### Greedy vs DP
```
Use Greedy when:
  ✅ Greedy choice property holds
  ✅ Optimal substructure + local = global optimum
  ✅ No future penalty for current greedy pick

Use DP when:
  ❌ Local best ≠ global best
  ❌ Choices depend on future decisions
  ❌ Overlapping subproblems exist

Quick Tests:
  Coin Change      → DP  (arbitrary denominations)
  Fractional KS    → Greedy (ratios, fractions allowed)
  0/1 Knapsack     → DP  (binary choice, no fractions)
  Activity Select  → Greedy (end-time sort is provably optimal)
  House Robber     → DP  (skip pattern, positional constraint)
```

---

## Change Log

1. **Level 1 #4, Example 4** — Output was `12`, contradicting the explanation's own math. Correct answer: **11**.
2. **Level 2 #1, Example 3** — Output was `51.43`. Working the greedy ratio-sort through gives **55.33**.
3. **Level 2 #3, Example 4** — Output was `1` with the claim "all inside [1,10] so one arrow works." False: `[2,3]` and `[4,5]` share no point, so no single arrow position hits both. Correct answer: **3**.
4. **Level 2 #4, Example 4** — Output was `3`. The optimal solution only needs to remove 2 intervals (`[1,100]` and `[2,12]`), keeping `[1,11]` and `[11,22]`. Correct answer: **2**.
5. **Level 4 #4, Example 4** — Output was `2`, but the explanation itself admitted tap 2 alone covers `[0,4]`. Correct answer: **1**.
6. **Level 7 #2, Example 2** — Output profit was `200`; the jobs actually scheduled (`B`,`A`,`C`) sum to `100+60+20=180`, not 200. Corrected profit: **180**.
7. **Level 8 #4, Example 3** — Input `[[7,0],[4,4]]` was invalid: with only 2 people total, `k=4` (four people in front) is impossible. Changed to `[[7,0],[4,1]]`, a valid input with the same output shape.

All other problems and examples were checked by hand (re-deriving each output from the stated greedy algorithm) and were already correct.

---

## Change Log — Problem Statement Wording

8. **Activity Selection** — "Two activities overlap if one starts before the other ends" only described a one-directional check, which technically fails to capture the touching-boundary case correctly. Rewrote as the precise two-sided condition (`s1 < e2 and s2 < e1`) and stated explicitly that touching intervals don't count as overlapping.
9. **Merge Intervals** — reused the exact same overlap sentence as Activity Selection, but this problem actually *merges* touching intervals (Example 2: `[1,4]` + `[4,5]` → `[1,5]`), which is the opposite convention. Rewrote to give Merge Intervals its own correct rule (`s2 <= e1`) and flagged the difference from Activity Selection / Non-overlapping Intervals explicitly, since using identical wording across problems with different touching-boundary conventions was misleading.
10. **Non-overlapping Intervals** — never actually defined what "overlapping" means (needed since the examples rely on touching being allowed). Added the same precise definition used in Activity Selection.
11. **Maximum Units on a Truck** — "You can choose any box types" didn't say you're choosing *quantities* of boxes up to what's available, bounded by `truckSize`. Rewrote to state this explicitly.
12. **Insert Interval** — "non-overlapping sorted intervals" didn't say sorted by *what*. Specified "sorted by start time."
13. **Boats to Save People** — "has a weight limit" didn't connect back to the `limit` variable introduced in the next sentence. Rewrote as one connected statement.
14. **Task Scheduler** — "at least `n` intervals apart" is ambiguous between "index difference ≥ n" and "n slots between them," which differ by one. Rewrote to say explicitly that at least `n` *other* time units must separate two occurrences of the same task.
15. **Hand of Straights** — didn't say groups must consist of consecutive *integers*, or that every card must be used. Rewrote to make both explicit.
16. **Maximum Length of Pair Chain** — never stated that pairs are given as an array, that `a < b` within a pair, or that you may pick any subset (not all pairs, any order). Rewrote to specify all three.
17. **Furthest Building** — never stated you start at index 0. Added an opening sentence.
18. **Candy** — "`n` children stand in a line. Each child has a `ratings[i]`" is a slightly awkward paraphrase of the standard "given an array ratings" framing. Rewrote in that standard form.
19. **Coin Change** (Level 9) — didn't state coin supply is unlimited, which matters for the algorithm. Added it.
20. **0/1 Knapsack** (Level 9) — terse phrasing rewritten into a complete sentence stating the subset-selection framing explicitly.
21. **House Robber** (Level 9) — "Rob houses in a line" rewritten into the standard array-based problem framing.