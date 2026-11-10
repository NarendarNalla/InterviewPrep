# 🟢 Greedy Algorithm — Quick Revision Cheat Sheet

## 1. Core Idea

| Concept | Meaning |
|---|---|
| Greedy | Make the best local choice at every step |
| Local choice | Best decision available **right now** |
| Goal | Reach the global optimum |
| Key property | Once a choice is made, we normally don't undo it |
| Main challenge | Prove that the local choice is safe |

### Basic Pattern

Problem
→ Identify best local choice
→ Make the choice
→ Update state
→ Repeat


## 2. How to Identify a Greedy Problem

| Signal in Problem | Think |
|---|---|
| Maximum number of items/tasks | Greedy candidate |
| Minimum number of resources | Greedy candidate |
| Choose activities/jobs | Greedy candidate |
| Intervals `[start,end]` | Interval Greedy |
| Repeated smallest/largest choice | Heap + Greedy |
| Pair elements optimally | Two Pointer + Greedy |
| Value + Weight | Ratio Greedy |
| Deadlines + Profit | Scheduling Greedy |
| Maximum reachable distance | Reachability Greedy |
| Character/task frequencies | Frequency + Heap |
| Remove elements to improve result | Greedy + Stack/Heap |
| Circular journey | Circular Greedy |


## 3. Greedy Decision Process

| Step | Question |
|---|---|
| 1 | What am I optimizing? |
| 2 | What is the best local choice? |
| 3 | Can sorting help? |
| 4 | Do I repeatedly need minimum/maximum? |
| 5 | Do I need two pointers? |
| 6 | Are intervals involved? |
| 7 | Can I prove the choice is safe? |


## 4. Main Greedy Patterns

| Pattern | Core Idea | Typical Tool |
|---|---|---|
| Sort + Greedy | Sort and make best choice | Arrays.sort |
| Two Pointer | Pair smallest/largest intelligently | Two pointers |
| Interval Greedy | Select/merge intervals | Sorting |
| Heap + Greedy | Repeatedly choose best available | PriorityQueue |
| Scheduling | Optimize jobs/tasks around deadlines | Sort + Heap |
| Resource Allocation | Match limited resources | Sort + Two pointers |
| Ratio Greedy | Choose highest value/weight | Sorting |
| Reachability | Maintain maximum reachable position | Variables |
| Frequency Greedy | Use most/least frequent item | HashMap + Heap |
| Remove/Replace | Remove worst item when needed | Heap/Stack |
| Monotonic Greedy | Maintain increasing/decreasing order | Stack |
| Circular Greedy | Eliminate impossible starting points | Variables |


## 5. Sorting Rules

| Sort By | Common Problems |
|---|---|
| Earliest ending time | Activity Selection |
| Earliest ending time | Non-overlapping Intervals |
| Starting time | Merge Intervals |
| Starting time | Insert Interval |
| Highest value/weight | Fractional Knapsack |
| Highest units | Maximum Units on Truck |
| Deadline | Job Scheduling |
| Smallest first | Resource allocation |
| Largest first | Pairing/balancing problems |


## 6. Data Structure Selection

| Requirement | Data Structure |
|---|---|
| Repeated minimum | Min Heap |
| Repeated maximum | Max Heap |
| Pair elements | Two Pointers |
| Count frequency | HashMap / int[] |
| Remove previous element | Stack |
| Intervals | Array + Sorting |
| Track maximum reach | Variable |
| Scheduling + best available | Heap |


## 7. Important Greedy Problems

| Level | Problem | Pattern |
|---|---|---|
| 1 | Activity Selection | Sort by end |
| 1 | Assign Cookies | Two Pointer |
| 1 | Lemonade Change | Greedy State |
| 1 | Can Place Flowers | Local placement |
| 1 | Stock II | Local profit |
| 2 | Fractional Knapsack | Ratio |
| 2 | Maximum Units on Truck | Sort descending |
| 2 | Minimum Arrows | Interval |
| 2 | Non-overlapping Intervals | Interval |
| 3 | Boats to Save People | Two Pointer |
| 4 | Merge Intervals | Sort + Merge |
| 4 | Insert Interval | Interval |
| 5 | Jump Game I | Reachability |
| 5 | Jump Game II | Farthest reach |
| 5 | Gas Station | Circular Greedy |
| 6 | Meeting Rooms II | Min Heap |
| 6 | Connect Sticks | Min Heap |
| 6 | IPO | Max Heap |
| 6 | Farthest Building | Min Heap |
| 6 | Task Scheduler | Frequency + Heap |
| 7 | Partition Labels | Last occurrence |
| 7 | Job Sequencing | Deadline |
| 7 | Candy | Two-pass Greedy |
| 7 | Hand of Straights | Frequency |
| 8 | Reorganize String | Max Heap |
| 8 | Remove K Digits | Monotonic Stack |
| 8 | Huffman Coding | Min Heap |
| 8 | Queue Reconstruction | Sorting + Greedy |


## 8. Greedy vs Other Techniques

| Technique | Decision Style |
|---|---|
| Greedy | Make best choice now |
| DP | Consider multiple states and store results |
| Backtracking | Try → explore → undo |
| Binary Search | Search answer space |
| Heap | Continuously access min/max |
| Two Pointer | Move pointers based on greedy decision |


## 9. Greedy Warning Signs

| Situation | Be Careful |
|---|---|
| Choice may need to be undone | Greedy may fail |
| Future depends heavily on current choice | Consider DP |
| Need to explore combinations | Backtracking |
| 0/1 Knapsack | Ratio greedy does NOT work |
| "Maximum/Minimum" only | Does NOT automatically mean greedy |


## 10. Greedy Proof

| Concept | Meaning |
|---|---|
| Greedy choice | Best local decision |
| Safe choice | Choice that does not destroy optimality |
| Exchange argument | Show an optimal solution can use your greedy choice |
| Optimal substructure | After greedy choice, remaining problem is still optimizable |


## ⭐ Quick Recognition Formula

```text
Optimization
      ↓
Can I make a local best choice?
      ↓
Can I sort / use heap / pointers?
      ↓
Can I prove the choice is safe?
      ↓
GREEDY