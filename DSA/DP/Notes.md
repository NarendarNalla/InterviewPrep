# Dynamic Programming (DP) — Learn from Zero to Interview Level

Your goal is not just to solve DP problems. Your goal is to look at a new problem, identify its DP pattern, derive the logic, and write the solution yourself.

The biggest mistake beginners make is memorizing DP code. Instead, learn to recognize the **state**, **choices**, and **repeated subproblems**.

Let's build that skill step by step using Java and interview-style problems.

---

## 1. What is Dynamic Programming?

Dynamic Programming is a technique where we solve smaller subproblems, store their answers, and reuse those answers instead of solving them repeatedly.

Consider Fibonacci:

$$
F(n)=F(n-1)+F(n-2)
$$

To calculate F(5), we need F(4) and F(3). But calculating F(4) also requires F(3).

So we calculate F(3) more than once.

DP avoids that repeated work by storing the answer.

> **Remember:** DP is not a completely different way to solve a problem. Usually, we first understand the recursive solution and then improve it by storing answers.

### Recursion vs DP

| Approach | What it does |
|---|---|
| Recursion | Solves a problem by calling smaller problems |
| Memoization | Recursion + storing calculated answers |
| Tabulation | Solves smaller problems first using a table |
| Space optimization | Stores only the previous answers that are necessary |

Learn these in this order:

**Recursion → Memoization → Tabulation → Space Optimization**

Do not skip the recursion step. It helps you discover the DP pattern.

---

## 2. How to identify a DP problem

When you read a problem statement, don't immediately think about arrays or `dp[]`.

Ask these five questions.

### Question 1: What is the final goal?

Look for what the problem asks you to calculate.

| Goal in the problem | Common DP type |
|---|---|
| Find the number of ways | Counting DP |
| Find the minimum cost | Minimum DP |
| Find the maximum profit or score | Maximum DP |
| Check whether something is possible | Boolean DP |
| Find the longest subsequence or path | Length/optimization DP |

These are clues, not proof that a problem requires DP.

### Question 2: What choices can I make?

For example, in **Climbing Stairs**, you can climb either one or two steps.

At stair `n`, your previous position must have been:

- Stair `n - 1`
- Stair `n - 2`

In **House Robber**, you can:

- Rob the current house.
- Skip the current house.

In **Coin Change**, you can choose a coin and then solve the remaining amount.

The choices usually reveal the recurrence.

### Question 3: Can I break the problem into smaller versions of itself?

Suppose you need to find the minimum cost to reach the last stone.

You can reach stone `i` from stone `i - 1` or stone `i - 2`.

Instead of solving the entire problem at once, solve the smaller problem:

- Minimum cost to reach stone 0
- Minimum cost to reach stone 1
- Minimum cost to reach stone 2
- Continue until the last stone

This is the beginning of DP.

### Question 4: Do the same subproblems appear repeatedly?

If multiple recursive paths ask for the same answer, storing that answer may help.

This is called **overlapping subproblems**.

### Question 5: Does the answer depend on smaller answers?

If the answer to a larger problem can be constructed from answers to smaller problems, you may have a DP solution.

This is called **optimal substructure** when optimal solutions can be built from optimal subproblem solutions.

---

## 3. The most important DP solving template

Use this template for almost every beginner and intermediate DP problem.

### S → B → T → O

| Step | Meaning | Question to ask |
|---|---|---|
| **S — State** | Define what DP stores | What exactly does `dp[i]` mean? |
| **B — Base case** | Smallest known answers | What are the simplest cases? |
| **T — Transition** | Relationship between states | How do smaller answers produce this answer? |
| **O — Order/Optimization** | Decide computation order and memory | Can I use recursion, a table, or fewer variables? |

Let's apply it to real problems.

---

## 4. Pattern 1 — One-dimensional DP

### Problem A: Climbing Stairs

**Problem:** You are at stair 0 and want to reach stair `n`. You can climb either one or two stairs at a time. How many distinct ways can you reach stair `n`?

#### Step 1: Identify the choices

To reach stair `n`, the previous stair must be either `n - 1` or `n - 2`.

Therefore:

$$
ways(n)=ways(n-1)+ways(n-2)
$$

Why addition? Because we count all ways from both possible previous positions.

#### Step 2: Define the state

```java
// dp[i] = number of distinct ways to reach stair i
```

#### Step 3: Base cases

Assume `dp[0] = 1`, representing one way to remain at the starting position.

- `dp[0] = 1`
- `dp[1] = 1`

Then `dp[2] = 2`, because you can take `1 + 1` or `2`.

#### Step 4: Build the table

| Stair `i` | Calculation | `dp[i]` |
|---|---|---|
| 0 | Starting position | 1 |
| 1 | `dp[0]` | 1 |
| 2 | `dp[1] + dp[0]` | 2 |
| 3 | `dp[2] + dp[1]` | 3 |
| 4 | `dp[3] + dp[2]` | 5 |
| 5 | `dp[4] + dp[3]` | 8 |

For `n = 5`, the answer is **8**.

#### What pattern did you learn?

Whenever the current state can be reached from a fixed number of previous states, think about 1D DP.

Practice these questions in order:

1. Fibonacci Number
2. Climbing Stairs
3. Min Cost Climbing Stairs
4. Frog Jump
5. Frog Jump with K Distances
6. Maximum Sum of Non-Adjacent Elements
7. House Robber
8. Ninja's Training

The recurrence changes between problems, but the state-and-transition thinking stays similar.

---

## 5. Pattern 2 — Minimum or maximum cost DP

### Problem B: Frog Jump

**Problem:** You are given the heights of stones. A frog starts at stone 0 and wants to reach the last stone. It can jump one or two positions. The cost of a jump is the absolute difference between the two stone heights. Find the minimum total cost.

Example:

```text
heights = [10, 20, 30, 10]
```

#### Step 1: Define the state

```java
// dp[i] = minimum energy required to reach stone i
```

This sentence is important. If you can define the state clearly, the rest becomes easier.

#### Step 2: Identify the choices

To reach stone `i`, the frog can jump from:

- `i - 1`
- `i - 2`, if that position exists

#### Step 3: Calculate the cost of each choice

From `i - 1`:

$$
dp[i-1]+|h[i]-h[i-1]|
$$

From `i - 2`:

$$
dp[i-2]+|h[i]-h[i-2]|
$$

Because the problem asks for the minimum cost, choose the smaller result.

$$
dp[i]=\min(\text{one-step cost},\text{two-step cost})
$$

Base case:

$$
dp[0]=0
$$

The frog is already at stone 0, so no energy is needed.

#### Step 4: Dry run

| Stone | Height | Minimum cost |
|---|---|---|
| 0 | 10 | 0 |
| 1 | 20 | 10 |
| 2 | 30 | 20 |
| 3 | 10 | 20 |

For stone 3, jumping from stone 1 costs `10 + 10 = 20`, while jumping from stone 2 costs `20 + 20 = 40`. The minimum is 20.

**Answer: 20**

#### What pattern did you learn?

If a problem asks for the minimum or maximum cost and offers multiple choices, calculate the result of each choice and combine them using `Math.min()` or `Math.max()` as appropriate.

But don't assume every minimum/maximum problem is DP. You still need to establish the subproblem relationship.

---

## 6. Pattern 3 — Take or skip

### Problem C: Maximum Sum of Non-Adjacent Elements

**Problem:** Given an array of positive integers, find the maximum sum such that no two selected elements are adjacent.

Example:

```text
arr = [2, 1, 4, 9]
```

You cannot select both `4` and `9`, because they are adjacent.

#### Step 1: Identify the choices

At each index `i`:

1. Take the current element.
2. Skip the current element.

This is the key to recognizing the take/skip DP pattern.

#### Step 2: Define the state

```java
// dp[i] = maximum sum obtainable from elements 0 through i,
// without selecting adjacent elements
```

#### Step 3: Derive the transition

**Choice A — Take `arr[i]`**

If you take the current element, you cannot take the previous one.

$$
take=arr[i]+dp[i-2]
$$

**Choice B — Skip `arr[i]`**

If you skip it, the best answer remains the answer through the previous index.

$$
skip=dp[i-1]
$$

Therefore:

$$
dp[i]=\max(take,skip)
$$

For the example, one optimal selection is `2 + 9 = 11`.

#### Why this pattern is important

The same idea appears in:

- House Robber
- Maximum sum of non-adjacent elements
- Some scheduling problems
- Certain subsequence optimization problems

The exact state and transition may differ, but identifying the take/skip decision is a major step.

---

## 7. Pattern 4 — Grid DP

### Problem D: Unique Paths

**Problem:** A robot starts at the top-left cell of a grid and must reach the bottom-right cell. It can move only right or down. Find the number of possible paths.

#### Identify the choices

To reach cell `(r, c)`, the robot must come from:

- Above: `(r - 1, c)`
- Left: `(r, c - 1)`

#### State

```java
// dp[r][c] = number of ways to reach cell (r, c)
```

#### Transition

$$
dp[r][c]=dp[r-1][c]+dp[r][c-1]
$$

The first row and first column each contain only one path, assuming the grid has no obstacles.

#### Recognize this pattern

When the problem involves moving through a matrix, especially with restrictions on movement, consider grid DP.

Practice:

1. Unique Paths
2. Unique Paths II — with obstacles
3. Minimum Path Sum
4. Triangle
5. Minimum Falling Path Sum

Notice how the transition stays similar while the objective changes from counting paths to minimizing cost.

---

## 8. Pattern 5 — Subsequence and knapsack DP

This is one of the most important interview patterns.

Typical problems:

- Subset Sum
- Partition Equal Subset Sum
- Count Subsets with Sum K
- 0/1 Knapsack
- Coin Change
- Coin Change II

### The mental model

You have items and a target. For each item, ask:

- Should I take it?
- Should I skip it?

For a subset-sum problem, your state may be:

```java
// dp[i][target] = whether a subset of the first i items
// can make the required target
```

For counting subsets, the state may instead store the number of ways.

The transition changes according to the question:

| Goal | What the DP stores | How alternatives combine |
|---|---|---|
| Is the target possible? | Boolean | Logical OR |
| How many subsets? | Count | Addition |
| Minimum number of coins | Minimum count | Minimum |
| Maximum profit | Maximum value | Maximum |

### Important trick: 0/1 vs unbounded

- **0/1 Knapsack:** each item can be selected at most once.
- **Unbounded Knapsack:** an item can be selected multiple times.

This distinction affects the recurrence and often the loop order in tabulation.

Don't memorize loop directions without understanding whether taking an item again is allowed.

---

## 9. The complete DP pattern roadmap

Follow this order. Don't jump straight into hard DP.

| Level | Pattern | Problems to solve |
|---|---|---|
| 1 | Basic 1D DP | Fibonacci, Climbing Stairs, Frog Jump |
| 2 | 1D choices | Frog Jump K, House Robber, Ninja's Training |
| 3 | Grid DP | Unique Paths, Unique Paths II, Minimum Path Sum |
| 4 | Take/skip and knapsack | Subset Sum, Partition Equal Subset Sum, 0/1 Knapsack |
| 5 | Coin and target DP | Coin Change, Coin Change II, Rod Cutting |
| 6 | String DP | LCS, Longest Common Substring, Edit Distance |
| 7 | LIS pattern | Longest Increasing Subsequence, Print LIS, Number of LIS |
| 8 | Stock DP | Best Time to Buy/Sell Stock I, II, III, Cooldown |
| 9 | Interval DP | Matrix Chain Multiplication, Burst Balloons |
| 10 | Tree and DAG DP | House Robber III, Longest Path in a DAG |
| 11 | Bitmask DP | Traveling Salesperson Problem |
| 12 | Digit DP | Count numbers satisfying digit constraints |

You don't need to learn all 12 levels at once. Master **Levels 1–5** first; they teach most of the essential DP reasoning.

---

## 10. Most important DP tricks

### Trick 1: Start with recursion, not the DP array

Write down the decisions first.

For example:

**Problem:** Maximum sum without adjacent elements

```text
At index i:
    Take current element
    OR
    Skip current element
```

Only then write the recurrence.

### Trick 2: Always write the state definition as a sentence

Weak definition:

```java
dp[i] = answer
```

Strong definition:

```java
// dp[i] = maximum sum obtainable from elements 0 through i
```

A clear state definition prevents many incorrect transitions.

### Trick 3: Separate the transition from the final answer

Sometimes the answer is `dp[n - 1]`. Sometimes it is the minimum or maximum across several states.

Don't assume the final answer is always the last array element.

### Trick 4: Match the operation to the objective

- **Counting:** usually add ways from valid alternatives.
- **Feasibility:** combine alternatives with logical OR.
- **Minimum:** choose the smallest valid candidate.
- **Maximum:** choose the largest valid candidate.

These are common patterns, not universal rules; the problem's exact definition controls the transition.

### Trick 5: Watch your indices

For `dp[i - 2]`, check that `i >= 2`.

For arrays, decide whether your state represents:

- An index starting at 0, or
- The first `i` elements, where `i` can range from 0 to `n`.

The second approach often makes knapsack and subsequence base cases easier to express.

### Trick 6: Optimize space only after the DP works

If `dp[i]` depends only on `dp[i-1]` and `dp[i-2]`, you may need only two previous values instead of an entire array.

But don't optimize prematurely. First produce a correct recurrence and table.

### Trick 7: Test small cases manually

Always test:

- Empty or minimum-size input, if allowed
- One element
- Two elements
- Repeated values
- Impossible targets
- Boundary indices

A DP solution can have the right idea but still fail because of a wrong base case or initialization.

---

## 11. How to practice so you actually recognize patterns

For each problem, use this checklist before coding:

1. What exactly is the question asking me to optimize, count, or decide?
2. What choices can I make at the current position?
3. What smaller problem remains after each choice?
4. What does `dp[state]` mean in one sentence?
5. What are the base cases?
6. What is the recurrence?
7. In what order should I compute the states?
8. Can I reduce the space after the solution works?
9. What are the time and space complexities?

> **The most important practice rule:** after studying a solution, close it and derive the recurrence again yourself. If you can explain why every term exists, you're learning DP rather than memorizing it.

---

## 12. Let's begin your actual DP training

My recommendation is to start with **Frog Jump**, then generalize it to **Frog Jump with K distances**. That will help you understand state definitions, choices, transitions, minimum-cost DP, and eventually space optimization.

We'll use this learning sequence for each problem:

1. Understand the problem in plain English.
2. Identify the pattern.
3. Derive the recurrence together.
4. Dry-run a small example.
5. Write the Java solution.
6. Compare memoization, tabulation, and optimization.
7. Try a similar problem without help.

You already have the broad roadmap. Now the priority is learning how to derive solutions yourself, one pattern at a time.